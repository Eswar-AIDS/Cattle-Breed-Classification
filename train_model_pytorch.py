import os
import cv2
import torch
import torch.nn as nn
import torch.optim as optim
from torch.utils.data import DataLoader, Dataset
from torchvision import datasets, models, transforms
from PIL import Image
import numpy as np
import copy
from tqdm import tqdm

# Configuration
DATA_DIR = r"E:\Mini PRoject\Muzzle\Cattle Muzzle - DB\Original"
IMG_SIZE = (224, 224)
BATCH_SIZE = 16
MAX_EPOCHS = 100
PATIENCE = 7  # Early stopping patience
LEARNING_RATE = 0.0001
DEVICE = torch.device("cuda" if torch.cuda.is_available() else "cpu")
MODEL_SAVE_PATH = "muzzle_classifier_best.pth"
LOG_FILE = "training_debug.log"

def log(msg):
    print(msg)
    with open(LOG_FILE, "a") as f:
        f.write(msg + "\n")
        f.flush()

log(f"Using device: {DEVICE}")
if DEVICE.type == 'cuda':
    log(f"GPU: {torch.cuda.get_device_name(0)}")

class MuzzleDataset(Dataset):
    def __init__(self, root_dir, transform=None, exclude_folders=None):
        if exclude_folders is None:
            exclude_folders = []
            
        log(f"Scanning directory: {root_dir}")
        # Manually filter folders
        all_dirs = [d for d in os.listdir(root_dir) if os.path.isdir(os.path.join(root_dir, d))]
        self.classes = sorted([d for d in all_dirs if d not in exclude_folders])
        
        log(f"Filtered classes ({len(self.classes)}): {self.classes}")
        self.class_to_idx = {cls_name: i for i, cls_name in enumerate(self.classes)}
        
        self.samples = []
        for target_class in self.classes:
            class_dir = os.path.join(root_dir, target_class)
            for root, _, fnames in sorted(os.walk(class_dir, followlinks=True)):
                for fname in fnames:
                    if fname.lower().endswith(('.png', '.jpg', '.jpeg', '.webp')):
                        path = os.path.join(root, fname)
                        self.samples.append((path, self.class_to_idx[target_class]))
                        
        self.transform = transform
        log(f"Found {len(self.samples)} valid images.")

    def apply_clahe(self, image):
        """Enhance contrast using CLAHE"""
        img_np = np.array(image.convert('RGB'))
        # Convert to LAB
        lab = cv2.cvtColor(img_np, cv2.COLOR_RGB2LAB)
        l, a, b = cv2.split(lab)
        clahe = cv2.createCLAHE(clipLimit=3.0, tileGridSize=(8, 8))
        cl = clahe.apply(l)
        limg = cv2.merge((cl, a, b))
        final = cv2.cvtColor(limg, cv2.COLOR_LAB2RGB)
        return Image.fromarray(final)

    def __len__(self):
        return len(self.samples)

    def __getitem__(self, idx):
        path, label = self.samples[idx]
        try:
            image = Image.open(path).convert('RGB')
            image = self.apply_clahe(image)
        except Exception as e:
            log(f"Error loading {path}: {e}")
            # Return dummy image if loading fails
            image = Image.new('RGB', IMG_SIZE, (0, 0, 0))
            
        if self.transform:
            image = self.transform(image)
        return image, label

def get_transforms():
    train_transform = transforms.Compose([
        transforms.Resize(IMG_SIZE),
        transforms.RandomHorizontalFlip(),
        transforms.RandomRotation(15),
        transforms.RandomAffine(0, shear=10, scale=(0.8, 1.2)),
        transforms.ColorJitter(brightness=0.1, contrast=0.1),
        transforms.ToTensor(),
        transforms.Normalize([0.485, 0.456, 0.406], [0.229, 0.224, 0.225])
    ])
    
    val_transform = transforms.Compose([
        transforms.Resize(IMG_SIZE),
        transforms.ToTensor(),
        transforms.Normalize([0.485, 0.456, 0.406], [0.229, 0.224, 0.225])
    ])
    
    return train_transform, val_transform

def train_one_epoch(model, loader, criterion, optimizer, device):
    model.train()
    running_loss = 0.0
    correct = 0
    total = 0
    
    pbar = tqdm(loader, desc="Training")
    for images, labels in pbar:
        images, labels = images.to(device), labels.to(device)
        
        optimizer.zero_grad()
        outputs = model(images)
        loss = criterion(outputs, labels)
        loss.backward()
        optimizer.step()
        
        running_loss += loss.item() * images.size(0)
        _, predicted = outputs.max(1)
        total += labels.size(0)
        correct += predicted.eq(labels).sum().item()
        
        pbar.set_postfix({'loss': f'{loss.item():.4f}', 'acc': f'{100.0 * correct / total:.2f}%'})
        
    return running_loss / total, 100.0 * correct / total

def validate(model, loader, criterion, device):
    model.eval()
    running_loss = 0.0
    correct = 0
    total = 0
    
    with torch.no_grad():
        pbar = tqdm(loader, desc="Validating")
        for images, labels in pbar:
            images, labels = images.to(device), labels.to(device)
            outputs = model(images)
            loss = criterion(outputs, labels)
            
            running_loss += loss.item() * images.size(0)
            _, predicted = outputs.max(1)
            total += labels.size(0)
            correct += predicted.eq(labels).sum().item()
            
            pbar.set_postfix({'loss': f'{loss.item():.4f}', 'acc': f'{100.0 * correct / total:.2f}%'})
            
    return running_loss / total, 100.0 * correct / total

def train_model():
    # Load Data and exclude OriginalMaster
    log("Loading data...")
    dataset = MuzzleDataset(DATA_DIR, exclude_folders=["OriginalMaster"])
    num_classes = len(dataset.classes)
    
    if len(dataset) == 0:
        log("ERROR: No images found! Check DATA_DIR.")
        return

    log(f"Total samples: {len(dataset)}")
    
    train_size = int(0.8 * len(dataset))
    val_size = len(dataset) - train_size
    train_data, val_data = torch.utils.data.random_split(dataset, [train_size, val_size])
    
    train_transform, val_transform = get_transforms()
    # Apply transforms specifically to splits
    train_data.dataset = copy.copy(dataset)
    train_data.dataset.transform = train_transform
    val_data.dataset = copy.copy(dataset)
    val_data.dataset.transform = val_transform
    
    train_loader = DataLoader(train_data, batch_size=BATCH_SIZE, shuffle=True)
    val_loader = DataLoader(val_data, batch_size=BATCH_SIZE, shuffle=False)
    
    log("Initializing Model...")
    # Initialize Model
    model = models.mobilenet_v2(weights=models.MobileNet_V2_Weights.IMAGENET1K_V1)
    
    # 1. Start with frozen base
    for param in model.parameters():
        param.requires_grad = False
        
    model.classifier[1] = nn.Sequential(
        nn.Dropout(0.2),
        nn.Linear(model.last_channel, num_classes)
    )
    
    model = model.to(DEVICE)
    criterion = nn.CrossEntropyLoss()
    optimizer = optim.Adam(model.classifier.parameters(), lr=LEARNING_RATE)
    
    # Early Stopping params
    best_val_acc = 0.0
    epochs_no_improve = 0
    
    log("\nPhase 1: Training Classifier Head...")
    for epoch in range(20): # Fixed short phase for head initialization
        log(f"--- Phase 1: Starting Epoch {epoch+1}/20 ---")
        train_loss, train_acc = train_one_epoch(model, train_loader, criterion, optimizer, DEVICE)
        val_loss, val_acc = validate(model, val_loader, criterion, DEVICE)
        
        log(f"Epoch {epoch+1}/20 | Train Loss: {train_loss:.4f} Acc: {train_acc:.2f}% | Val Loss: {val_loss:.4f} Acc: {val_acc:.2f}%")
        
        if val_acc > best_val_acc:
            best_val_acc = val_acc
            torch.save(model.state_dict(), MODEL_SAVE_PATH)

    # 2. Fine-tuning Phase: Unfreeze last blocks
    log("\nPhase 2: Fine-tuning (Unfreezing base model blocks)...")
    model.load_state_dict(torch.load(MODEL_SAVE_PATH)) # Load best head
    
    # Unfreeze from layer 14 onwards (out of ~18 layers in MobileNetV2 features)
    for i, layer in enumerate(model.features):
        if i >= 14: # Roughly the last few inverted residual blocks
            for param in layer.parameters():
                param.requires_grad = True
                
    optimizer = optim.Adam(filter(lambda p: p.requires_grad, model.parameters()), lr=1e-5)
    
    log(f"Starting optimized training up to {MAX_EPOCHS} epochs with patience {PATIENCE}...")
    for epoch in range(MAX_EPOCHS):
        log(f"--- Phase 2: Starting Epoch {epoch+1}/{MAX_EPOCHS} ---")
        train_loss, train_acc = train_one_epoch(model, train_loader, criterion, optimizer, DEVICE)
        val_loss, val_acc = validate(model, val_loader, criterion, DEVICE)
        
        log(f"Epoch {epoch+1}/{MAX_EPOCHS} | Train Loss: {train_loss:.4f} Acc: {train_acc:.2f}% | Val Loss: {val_loss:.4f} Acc: {val_acc:.2f}%")
        
        if val_acc > best_val_acc:
            best_val_acc = val_acc
            torch.save(model.state_dict(), MODEL_SAVE_PATH)
            epochs_no_improve = 0
            log("  --> Best Model Saved!")
        else:
            epochs_no_improve += 1
            
        if epochs_no_improve >= PATIENCE:
            log(f"Early stopping at epoch {epoch+1}. Best Val Acc: {best_val_acc:.2f}%")
            break
            
    log(f"\nTraining Complete! Best accuracy reached: {best_val_acc:.2f}%")
    
    # Save Class Names
    with open("class_names.txt", "w") as f:
        for name in dataset.classes:
            f.write(name + "\n")
    print("Class names saved to class_names.txt")

if __name__ == "__main__":
    try:
        train_model()
    except KeyboardInterrupt:
        print("\nTraining interrupted by user.")
