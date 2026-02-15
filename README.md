# BovineScan: AI-Powered Cattle Breed Classification & Identification

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Platform: Android](https://img.shields.io/badge/Platform-Android-green.svg)](https://developer.android.com/)
[![Framework: Kotlin](https://img.shields.io/badge/Framework-Kotlin-blue.svg)](https://kotlinlang.org/)
[![Model: YOLOv8/v11](https://img.shields.io/badge/Model-YOLOv8%2Fv11-orange.svg)](https://ultralytics.com/)

## 🐄 Overview

**BovineScan** is a comprehensive, dual-platform solution designed to modernize cattle management through advanced computer vision and machine learning. By integrating a native Android application with a robust muzzle biometric identification system, it provides farmers, veterinarians, and researchers with accurate, real-time tools for cattle breed classification and individual identification.

### Key Objectives
*   **Non-Invasive Identification**: Replace manual tagging with biometric identification using unique muzzle patterns.
*   **Offline-First**: Deploy optimized **TFLite** models directly on mobile devices for functionality in remote areas.
*   **High Accuracy**: Utilizing state-of-the-art YOLO architectures to achieve >94% accuracy in breed classification.

---

## 🚀 Key Modules

### 1. Mobile Application (Android)
A native Kotlin application designed for field use.
*   **Real-time Inference**: Uses TensorFlow Lite for on-device cattle detection and classification.
*   **Dual Analysis**: Support for both live camera feed and gallery image processing.
*   **Multi-language**: Localized support for English, Hindi, and Kannada.
*   **UI/UX**: Material Design 3 interface with dark mode.

### 2. Muzzle Biometrics System
Individual identification through biometric "fingerprints" of cattle muzzles.
*   **Uniqueness**: Muzzle ridge patterns are unique to each animal, similar to human fingerprints.
*   **Biometric Vault**: Securely store and match individual cattle identities.

### 3. Web Dashboard & Analytics
A central hub for managing herd data and visualizing performance.
*   **Analytics**: View breed distributions, accuracy metrics, and training summaries.
*   **Data Sync**: Synchronization between mobile field data and the central database.

---

## 🛠️ Technical Stack

*   **Deep Learning**: YOLOv8, YOLOv11 (Ultralytics)
*   **Inference**: TensorFlow Lite (TFLite), ONNX
*   **Mobile**: Kotlin, Jetpack Compose, CameraX
*   **Backend**: Python, Flask
*   **Training**: PyTorch, Roboflow Datasets

---

## 📊 Supported Breeds (71 TOTAL)

BovineScan supports a wide range of indigenous and international cattle breeds.

<details>
<summary>Click to view full list of supported breeds</summary>

| ID | Breed Name | ID | Breed Name | ID | Breed Name |
|---|---|---|---|---|---|
| 0 | Alambadi | 24 | Himachali Pahari | 48 | Nagpuri |
| 1 | Amritmahal | 25 | Himachali_Pahari | 49 | Nari |
| 2 | Ayrshire | 26 | Holstein_Friesian | 50 | Nili Ravi |
| 3 | Bachaur | 27 | Jaffrabadi | 51 | Nimari |
| 4 | Badri | 28 | Jersey | 52 | Ongole |
| 5 | Banni | 29 | Kangayam | 53 | Poda_Thirupu |
| 6 | Bargur | 30 | Kankrej | 54 | Ponwar |
| 7 | Bargur_Buffalo | 31 | Kasargod | 55 | Pulikulam |
| 8 | Belahi | 32 | Kenkatha | 56 | Punganur |
| 9 | Bhadawari | 33 | Khariar | 57 | Purnea |
| 10 | Binjharpuri | 34 | Kherigarh | 58 | Rathi |
| 11 | Brahmaputra | 35 | Khillari | 59 | Red Dane |
| 12 | Brown_Swiss | 36 | Konkan_Kapila | 60 | Red Sindhi |
| 13 | Chilika | 37 | Kosali | 61 | Red_Kandhari |
| 14 | Dagri | 38 | Krishna_Valley | 62 | Sahiwal |
| 15 | Dangi | 39 | Ladakhi | 63 | Shweta_Kapila |
| 16 | Deoni | 40 | Lakhimi | 64 | Siri |
| 17 | Gangatari | 41 | Malnad Gidda | 65 | Surti |
| 18 | Gaolao | 42 | Malvi | 66 | Tharparkar |
| 19 | Ghumsari | 43 | Mehsana | 67 | Thutho |
| 20 | Gir | 44 | Mewati | 68 | Toda |
| 21 | Guernsey | 45 | Motu | 69 | Umblachery |
| 22 | Hallikar | 46 | Murrah | 70 | Vechur |
| 23 | Hariana | 47 | Nagori | | |

</details>

---

## 💻 Getting Started

### Android App Setup
1. Open the `./Android APP` folder in **Android Studio**.
2. Ensure you have the latest TFLite model at `app/src/main/assets/cattle_classifier.tflite`.
3. Sync Gradle and run on a physical device (API 24+).

### Training Environment
1. Navigate to the `muzzle_biometrics` or `cattle-breed-classifier` directory.
2. Install dependencies: `pip install -r requirements.txt`.
3. Use the provided Python scripts (e.g., `train_model.py`) for retraining.

---

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 🤝 Acknowledgments

*   **Ultralytics** for the YOLO framework.
*   **Google TensorFlow Lite** for on-device inference support.
*   Custom datasets sourced and managed via **Roboflow**.
