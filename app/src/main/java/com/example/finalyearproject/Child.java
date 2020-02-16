package com.example.finalyearproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Child extends AppCompatActivity {
    private EditText Name;
    private EditText Age;
    private Button Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);

        Name = findViewById(R.id.c1);
        Age = findViewById(R.id.c2);
        Login = findViewById(R.id.cbtn);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Child.this,Test.class);
                startActivity(intent);
            }
        });
    }
}
