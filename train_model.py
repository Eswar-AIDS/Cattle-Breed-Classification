import os
import cv2
import numpy as np
import tensorflow as tf
from tensorflow.keras.applications import MobileNetV2
from tensorflow.keras.layers import Dense, GlobalAveragePooling2D, Dropout, Input
from tensorflow.keras.models import Model
from tensorflow.keras.optimizers import Adam
from tensorflow.keras.preprocessing.image import ImageDataGenerator
from tensorflow.keras.callbacks import ModelCheckpoint, EarlyStopping
import matplotlib.pyplot as plt

# Configuration
DATA_DIR = r"E:\Mini PRoject\Muzzle\Cattle Muzzle - DB\Original"
IMG_SIZE = (224, 224)
BATCH_SIZE = 32
EPOCHS = 20
LEARNING_RATE = 0.0001
MODEL_SAVE_PATH = "muzzle_classifier.h5"
TFLITE_SAVE_PATH = "muzzle_classifier.tflite"

def apply_clahe(image):
    """
    Apply CLAHE (Contrast Limited Adaptive Histogram Equalization) to an image.
    Expects image in RGB format (0-255).
    """
    # Convert to LAB color space
    lab = cv2.cvtColor(image.astype('uint8'), cv2.COLOR_RGB2LAB)
    l, a, b = cv2.split(lab)
    
    # Apply CLAHE to L-channel
    clahe = cv2.createCLAHE(clipLimit=3.0, tileGridSize=(8, 8))
    cl = clahe.apply(l)
    
    # Merge channels and convert back to RGB
    limg = cv2.merge((cl, a, b))
    final = cv2.cvtColor(limg, cv2.COLOR_LAB2RGB)
    
    return final.astype('float32')

def preprocessing_function(image):
    """
    Custom preprocessing function for ImageDataGenerator.
    """
    # Apply CLAHE
    image = apply_clahe(image)
    # Normalize to [-1, 1] for MobileNetV2
    image = tf.keras.applications.mobilenet_v2.preprocess_input(image)
    return image

def create_model(num_classes):
    base_model = MobileNetV2(weights='imagenet', include_top=False, input_shape=(224, 224, 3))
    base_model.trainable = False # Freeze base model initially

    x = base_model.output
    x = GlobalAveragePooling2D()(x)
    x = Dropout(0.2)(x) # Regularization
    predictions = Dense(num_classes, activation='softmax')(x)

    model = Model(inputs=base_model.input, outputs=predictions)
    return model

def main():
    print(f"Loading data from {DATA_DIR}...")
    
    # Check if directory exists
    if not os.path.exists(DATA_DIR):
        print(f"Error: Directory {DATA_DIR} not found.")
        return

    # Data Augmentation
    train_datagen = ImageDataGenerator(
        preprocessing_function=preprocessing_function,
        validation_split=0.2,
        rotation_range=20,
        width_shift_range=0.2,
        height_shift_range=0.2,
        shear_range=0.2,
        zoom_range=0.2,
        horizontal_flip=True,
        fill_mode='nearest'
    )

    # Train Generator
    train_generator = train_datagen.flow_from_directory(
        DATA_DIR,
        target_size=IMG_SIZE,
        batch_size=BATCH_SIZE,
        class_mode='categorical',
        subset='training'
    )

    # Validation Generator
    validation_generator = train_datagen.flow_from_directory(
        DATA_DIR,
        target_size=IMG_SIZE,
        batch_size=BATCH_SIZE,
        class_mode='categorical',
        subset='validation'
    )

    num_classes = train_generator.num_classes
    class_names = list(train_generator.class_indices.keys())
    print(f"Found {num_classes} classes: {class_names}")

    # Create Model
    model = create_model(num_classes)
    model.compile(optimizer=Adam(learning_rate=LEARNING_RATE), 
                  loss='categorical_crossentropy', 
                  metrics=['accuracy'])

    # Callbacks
    checkpoint = ModelCheckpoint(MODEL_SAVE_PATH, monitor='val_accuracy', verbose=1, save_best_only=True, mode='max')
    early_stopping = EarlyStopping(monitor='val_loss', patience=5, restore_best_weights=True)

    # Train
    print("Starting training...")
    history = model.fit(
        train_generator,
        steps_per_epoch=train_generator.samples // BATCH_SIZE,
        validation_data=validation_generator,
        validation_steps=validation_generator.samples // BATCH_SIZE,
        epochs=EPOCHS,
        callbacks=[checkpoint, early_stopping]
    )

    # Fine-tuning (Optional but recommended)
    print("Fine-tuning base model...")
    base_model = model.layers[0]
    base_model.trainable = True
    # Freeze the first 100 layers
    for layer in base_model.layers[:100]:
        layer.trainable = False
        
    model.compile(optimizer=Adam(learning_rate=1e-5), # Lower learning rate
                  loss='categorical_crossentropy', 
                  metrics=['accuracy'])
    
    history_fine = model.fit(
        train_generator,
        epochs=10, # Fine-tune for a few more epochs
        validation_data=validation_generator,
        callbacks=[checkpoint, early_stopping]
    )

    # Save final model
    model.save(MODEL_SAVE_PATH)
    print(f"Model saved to {MODEL_SAVE_PATH}")

    # Convert to TFLite
    print("Converting to TFLite...")
    converter = tf.lite.TFLiteConverter.from_keras_model(model)
    tflite_model = converter.convert()

    with open(TFLITE_SAVE_PATH, 'wb') as f:
        f.write(tflite_model)
    print(f"TFLite model saved to {TFLITE_SAVE_PATH}")
    
    # Save Class Names
    with open("class_names.txt", "w") as f:
        for name in class_names:
            f.write(name + "\n")
    print("Class names saved to class_names.txt")

if __name__ == "__main__":
    main()
