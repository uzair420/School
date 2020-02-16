package com.example.finalyearproject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class MainActivity extends Activity implements View.OnClickListener {

    private EditText email;
    private EditText password;
    private Button Login;
    private TextView ParentSignUp;

    ProgressDialog progressDialog;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        email = findViewById(R.id.login_email);
        password = findViewById(R.id.et_Password);
        Login = findViewById(R.id.btnLogin);
        ParentSignUp = findViewById(R.id.btnParent);

        progressDialog = new ProgressDialog(this);
        auth = FirebaseAuth.getInstance();
        ParentSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ParentSignUp.class));
                finish();
            }
        });
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckValidation();
            }

            private void CheckValidation() {

                String Email = email.getText().toString().trim();
                String Password = password.getText().toString().trim();

                if (TextUtils.isEmpty(Email)) {

                    email.setError("Please Enter email");
                } else if(!(Patterns.EMAIL_ADDRESS.matcher(Email).matches())) {
                    email.setError("Please Enter valid Email");
                } else if (TextUtils.isEmpty(Password)) {
                    email.setError("Please Enter Password");
                } else {

                    PerformAuthentication(Email, Password);
                }

            }

            private void PerformAuthentication(String email, String password) {
                progressDialog.setMessage("Please Wait....");
                progressDialog.setCancelable(false);
                progressDialog.show();

                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    progressDialog.dismiss();
                                    startActivity(new Intent(MainActivity.this, SCHOOLS.class));
                                    finish();
                                } else {

                                    progressDialog.dismiss();
                                    Toast.makeText(MainActivity.this, "Not Autheticated", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(MainActivity.this, ParentSignUp.class));
        finish();
    }
}
