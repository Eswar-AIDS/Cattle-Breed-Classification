from ultralytics import YOLO
import os
import torch

def train_phase1():
    print("--- YOLO11 Cattle Breed Classifier [PHASE 1: 50 Epochs] ---")
    
    # Load base model
    model = YOLO('yolo11n-cls.pt')
    
    # Dataset path
    dataset_path = r"e:\Mini PRoject\Roboflow image dataset"
    
    # Auto-detect GPU
    device = 0 if torch.cuda.is_available() else 'cpu'
    print(f"Using device: {device}")

    # Start Training Phase 1
    model.train(
        data=dataset_path,
        epochs=50,       # Phase 1 goal
        imgsz=224,
        batch=32,
        workers=2,
        cache=False,
        name='cattle_yolo11_phase1',
        project='runs/train_phases',
        exist_ok=True,
        device=device
    )

    print("\n--- Phase 1 Complete ---")
    print("Next: Run train_yolo11_phase2.py to continue to 100 epochs.")

if __name__ == "__main__":
    train_phase1()
