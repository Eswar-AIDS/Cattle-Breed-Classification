import tensorflow as tf
import numpy as np

model_path = r"E:\Mini PRoject\New App\app\src\main\assets\breed_classifier.tflite"

try:
    interpreter = tf.lite.Interpreter(model_path=model_path)
    interpreter.allocate_tensors()

    print("=== Input Tensors ===")
    for i in interpreter.get_input_details():
        print(f"Index: {i['index']}, Name: {i['name']}, Shape: {i['shape']}, Type: {i['dtype']}")
        if 'quantization' in i:
            print(f"  Quantization: {i['quantization']}")

    print("\n=== Output Tensors ===")
    for i in interpreter.get_output_details():
        print(f"Index: {i['index']}, Name: {i['name']}, Shape: {i['shape']}, Type: {i['dtype']}")
        if 'quantization' in i:
            print(f"  Quantization: {i['quantization']}")

except Exception as e:
    print(f"Error: {e}")
