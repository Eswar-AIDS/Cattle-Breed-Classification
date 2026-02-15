import sys
import os

# Try to use the ultralytics from the yolov12 repo first
sys.path.insert(0, os.path.join(os.getcwd(), 'yolov12'))

try:
    from ultralytics import YOLO
    print("Successfully imported YOLO from yolov12 folder")
    
    model = YOLO('yolov12n.pt')
    print("Model loaded successfully!")
    
    # Test on a dummy image or common image if exists
    # results = model('https://ultralytics.com/images/bus.jpg')
    # print(results)
except Exception as e:
    print(f"Error: {e}")
