
# AI Study Score Predictor

## Project Overview
AI Study Score Predictor is an Android mini application that uses a TensorFlow Lite machine learning model to predict a practice score based on the number of study hours entered by the user.

## Objectives
- Integrate a machine learning model into an Android application.
- Use TensorFlow Lite for on-device inference.
- Predict practice scores from study hours.
- Learn how to train, convert, test, and deploy a machine learning model.

## Technologies Used
- Java
- XML
- Android Studio
- Python
- TensorFlow
- TensorFlow Lite
- Pandas
- NumPy
- Scikit-learn

## How It Works
1. The user enters study hours.
2. The application validates the input.
3. The TensorFlow Lite model receives the input.
4. The model predicts a practice score.
5. The predicted score is displayed on the screen.

## Input Validation
- Empty input is not accepted.
- Study hours must be between 1 and 8.
- Invalid inputs display an error message.

## Model Information
- Model type: Linear Regression
- Training data: Sample synthetic dataset
- Input: Study hours
- Output: Predicted practice score
- Model format: TensorFlow Lite (.tflite)

## Testing
The application was tested on an Android mobile device.

| Study Hours | Predicted Score |
|---|---:|
| 2 | 30.28 |
| 5 | 62.82 |
| 8 | 95.37 |

Input validation was also tested with empty input, 0, and 9.

## Project Structure
- `MainActivity.java` — Application logic and model inference
- `activity_main.xml` — User interface layout
- `study_score_model.tflite` — TensorFlow Lite model

## How to Run
1. Clone or download the project.
2. Open it in Android Studio.
3. Allow Gradle to sync.
4. Connect an Android device with USB debugging enabled.
5. Run the app.

## Disclaimer
This application is an educational mini project. Its predictions are based on a small synthetic dataset and are not real academic score predictions.
