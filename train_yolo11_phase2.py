from ultralytics import YOLO
import os
import torch

def train_phase2():
    print("--- YOLO11 Cattle Breed Classifier [PHASE 2: Next 50 Epochs] ---")
    
    # Path to the weights from Phase 1
    # We use 'last.pt' to ensure we resume exactly where we left off
    # FOUND AT: runs/classify/runs/train_phases/...
    phase1_weights = r"e:\Mini PRoject\runs\classify\runs\train_phases\cattle_yolo11_phase1\weights\last.pt"
    
    if not os.path.exists(phase1_weights):
        print(f"Error: Phase 1 weights not found at {phase1_weights}")
        print("Please ensure Phase 1 has completed or started.")
        return

    # Load the model from Phase 1
    model = YOLO(phase1_weights)
    
    # Dataset path
    dataset_path = r"e:\Mini PRoject\Roboflow image dataset"
    
    # Auto-detect GPU
    device = 0 if torch.cuda.is_available() else 'cpu'
    print(f"Using device: {device}")

    # Start Training Phase 2 (Fresh run initialized with Phase 1 weights)
    model.train(
        data=dataset_path,
        epochs=50,       # Run 50 new epochs (Total will be 50+50 = 100 equivalent)
        resume=False,    # Do not resume state, just load weights
        imgsz=224,
        batch=32,
        workers=2,
        cache=False,
        name='cattle_yolo11_phase2', # New directory to avoid overwriting source weights
        project='runs/train_phases',
        exist_ok=True,
        device=device
    )

    print("\n--- Phase 2 (Total 100 Epochs) Complete ---")

if __name__ == "__main__":
    train_phase2()
