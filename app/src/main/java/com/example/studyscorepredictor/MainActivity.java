package com.example.studyscorepredictor;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.tensorflow.lite.Interpreter;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import android.content.res.AssetFileDescriptor;

public class MainActivity extends AppCompatActivity {

    EditText enterHours;
    Button predictButton;
    TextView resultText;

    Interpreter interpreter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Connect UI components
        enterHours = findViewById(R.id.enterHours);
        predictButton = findViewById(R.id.predictButton);
        resultText = findViewById(R.id.resultText);

        // Load TensorFlow Lite model
        try {
            interpreter = new Interpreter(loadModelFile());
        } catch (IOException e) {
            Toast.makeText(this,
                    "Model loading failed!",
                    Toast.LENGTH_LONG).show();
        }

        // Set button click listener
        predictButton.setOnClickListener(v -> {

            String inputText = enterHours.getText().toString().trim();

            // Check empty input
            if (inputText.isEmpty()) {
                enterHours.setError("Please enter study hours");
                return;
            }

            try {
                float hours = Float.parseFloat(inputText);

                // Validate study hours
                if (hours < 1 || hours > 8) {
                    enterHours.setError("Enter hours between 1 and 8");
                    return;
                }

                // Prepare model input
                float[][] input = {{hours}};

                // Prepare model output
                float[][] output = new float[1][1];

                // Run AI prediction
                interpreter.run(input, output);

                // Get predicted score
                float score = output[0][0];

                // Display result
                resultText.setText(
                        String.format("Predicted Score: %.2f / 100", score)
                );

            } catch (NumberFormatException e) {
                enterHours.setError("Enter a valid number");
            }
        });
    }

    // Load TensorFlow Lite model from assets folder
    private MappedByteBuffer loadModelFile() throws IOException {

        AssetFileDescriptor fileDescriptor =
                getAssets().openFd("study_score_model.tflite");

        FileInputStream fileInputStream =
                new FileInputStream(fileDescriptor.getFileDescriptor());

        FileChannel fileChannel = fileInputStream.getChannel();

        MappedByteBuffer modelBuffer = fileChannel.map(
                FileChannel.MapMode.READ_ONLY,
                fileDescriptor.getStartOffset(),
                fileDescriptor.getDeclaredLength()
        );

        fileInputStream.close();
        fileDescriptor.close();

        return modelBuffer;
    }

}