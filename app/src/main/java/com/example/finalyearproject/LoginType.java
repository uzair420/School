package com.example.finalyearproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class LoginType extends AppCompatActivity {
    private RadioButton radio1;
    private RadioButton radio2;
    private RadioButton radio3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_type);
        radio1=findViewById(R.id.str_radio1);
        radio2=findViewById(R.id.str_radio2);
        radio3=findViewById(R.id.str_radio3);
        radio1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginType.this,MainActivity.class);
                startActivity(intent);
            }

        });
        radio2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginType.this,MainActivity.class);
                startActivity(intent);
            }

        });
        radio3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginType.this,Child.class);
                startActivity(intent);
            }

        });
    }
}
