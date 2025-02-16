package com.example.myjnisample;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        initViews();
    }

    static {
        System.loadLibrary("native-lib");  // This must match the CMakeLists.txt
    }

    private native int addNumbers(int a, int b); // Declare native method

    public void initViews() {
        EditText num1 = findViewById(R.id.number_1);
        EditText num2 = findViewById(R.id.number_2);
        findViewById(R.id.sum).setOnClickListener(v -> {
            int mySum = addNumbers(Integer.parseInt(num1.getText().toString()), Integer.parseInt(num2.getText().toString()));
            Toast.makeText(MainActivity.this, "Sum rcvd from C file calculation: " + mySum, Toast.LENGTH_SHORT).show();
        });

    }
}