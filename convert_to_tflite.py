import torch
import torch.nn as nn
from torchvision import models
import os
import subprocess
import shutil

# Configuration
WEIGHTS_PATH = "muzzle_classifier_best.pth"
CLASS_NAMES_PATH = "class_names.txt"
ONNX_PATH = "muzzle_model.onnx"
OUTPUT_DIR = "tflite_output"
TFLITE_FINAL_PATH = "muzzle_classifier.tflite"

# Ensure output dir
if os.path.exists(OUTPUT_DIR):
    shutil.rmtree(OUTPUT_DIR)
os.makedirs(OUTPUT_DIR, exist_ok=True)

# Load labels to get class count
with open(CLASS_NAMES_PATH, "r") as f:
    classes = [line.strip() for line in f if line.strip()]
num_classes = len(classes)
print(f"Detected {num_classes} classes.")

def convert():
    # 1. Load PyTorch Model
    print("Loading PyTorch model...")
    model = models.mobilenet_v2(weights=None)
    model.classifier[1] = nn.Sequential(
        nn.Dropout(0.2),
        nn.Linear(model.last_channel, num_classes)
    )
    
    # Load state dict (map to CPU for conversion)
    state_dict = torch.load(WEIGHTS_PATH, map_location='cpu')
    model.load_state_dict(state_dict)
    model.eval()

    # 2. Export to ONNX
    print("Exporting to ONNX...")
    dummy_input = torch.randn(1, 3, 224, 224)
    torch.onnx.export(
        model, 
        dummy_input, 
        ONNX_PATH, 
        input_names=['input'], 
        output_names=['output'],
        dynamic_axes={'input': {0: 'batch_size'}, 'output': {0: 'batch_size'}},
        opset_version=12
    )
    print(f"ONNX model saved to {ONNX_PATH}")

    # 3. Simplify ONNX
    print("Simplifying ONNX...")
    venv_scripts = os.path.dirname(os.path.abspath(__file__)) + "\\muzzle_env\\Scripts\\"
    onnxsim_path = venv_scripts + "onnxsim.exe"
    onnx2tf_path = venv_scripts + "onnx2tf.exe"
    
    try:
        subprocess.run([onnxsim_path, ONNX_PATH, ONNX_PATH], check=True)
        print("ONNX simplified successfuly.")
    except Exception as e:
        print(f"ONNX simplification failed (skipping): {e}")

    # 4. Convert using onnx2tf
    print("Converting ONNX to TFLite using onnx2tf...")
    try:
        # We don't use -oiqt here to keep it simple float32
        subprocess.run([
            onnx2tf_path, 
            "-i", ONNX_PATH, 
            "-o", OUTPUT_DIR
        ], check=True)
    except Exception as e:
        print(f"onnx2tf conversion failed: {e}")
        return

    # Look for the generated tflite file
    for root, dirs, files in os.walk(OUTPUT_DIR):
        for file in files:
            if file.endswith("float32.tflite"):
                source = os.path.join(root, file)
                shutil.copy(source, TFLITE_FINAL_PATH)
                print(f"Successfully converted model to {TFLITE_FINAL_PATH}")
                return
    
    print("Could not find the final .tflite file in output directory.")

if __name__ == "__main__":
    convert()
