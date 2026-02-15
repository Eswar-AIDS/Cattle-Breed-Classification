import sys
import os

# Set paths
BASE_DIR = os.path.dirname(os.path.abspath(__file__))
WEBAPP_DIR = os.path.join(BASE_DIR, 'cattle-breed-classifier', 'cattle-breed-classifier-webapp-master')
sys.path.insert(0, WEBAPP_DIR)

from backend_utils.detection_engine import get_detection_engine

try:
    print("Initializing Detection Engine...")
    engine = get_detection_engine()
    print("Success! YOLOv12 Detection Engine is ready.")
except Exception as e:
    print(f"Failed to initialize engine: {e}")
    import traceback
    traceback.print_exc()
