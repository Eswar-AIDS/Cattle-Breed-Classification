from ultralytics import YOLO
import os

def train_yolo11():
    print("--- YOLO11 Cattle Breed Classifier Training Script ---")
    
    # Load the YOLO11 nano classification model
    # Note: Ultralytics will automatically download 'yolo11n-cls.pt' if not present
    try:
        model = YOLO('yolo11n-cls.pt')
        print("Success: YOLO11 model initialized.")
    except Exception as e:
        print(f"Error initializing YOLO11: {e}")
        return

    # Configuration for training
    # Ideally, the user should point this to their local dataset folder
    dataset_path = r"e:\Mini PRoject\Roboflow image dataset" # Default based on your dir structure
    
    if not os.path.exists(dataset_path):
        print(f"Warning: Dataset path not found at {dataset_path}")
        print("Please edit the 'dataset_path' variable in this script to point to your data.")
        return

    print(f"Starting training on dataset: {dataset_path}")
    
    import torch
    device = 0 if torch.cuda.is_available() else 'cpu'
    print(f"Using device: {device}")
    
    if device == 'cpu':
        print("WARNING: Training 50k images on CPU will be EXTREMELY slow.")
        print("Consider using a machine with an NVIDIA GPU for faster results.")

    # Optimized for 50k images on RTX 4050 (Memory Safe)
    model.train(
        data=dataset_path,
        epochs=100,      # Consistent training
        imgsz=224,       # Standard size
        batch=32,        # FIXED: Safer for 6GB VRAM than auto-batch
        workers=2,       # REDUCED: Prevents CPU/RAM overhead
        cache=False,     # DISABLED: Caching 50k images exhausts RAM
        patience=10,     
        name='cattle_yolo11_heavy_50k',
        project='runs/classify',
        exist_ok=True,
        device=device
    )

    print("\n--- Training Complete ---")
    print("Results saved to: runs/classify/cattle_yolo11_phase3")

if __name__ == "__main__":
    train_yolo11()
