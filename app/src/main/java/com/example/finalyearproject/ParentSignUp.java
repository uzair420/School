package com.example.finalyearproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalyearproject.UsersModel.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ParentSignUp extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private EditText ConfirmPassword;
    private Button register;
    private EditText P1_Name;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_sign_up);

        P1_Name=findViewById(R.id.P1_Name);
        Name = findViewById(R.id.Name);
        Password = findViewById(R.id.S1_Password);
        ConfirmPassword = findViewById(R.id.P1_ConfPassword);
        register = findViewById(R.id.P1_btn);

        auth = FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckValidation();
            }
        });
    }

    private void CheckValidation() {
        String name= Name.getText().toString();
        String UserName= P1_Name.getText().toString();
        String password = Password.getText().toString();
        String confirmPassword= ConfirmPassword.getText().toString();

        if(TextUtils.isEmpty(name)){
            Name.setError("Please Enter Name");
        }
        else if(TextUtils.isEmpty(UserName)){
            P1_Name.setError("Please Enter Email");
        }
        else if(TextUtils.isEmpty(password)){
            Password.setError("Please Enter Passwor");
        }
        else if(TextUtils.isEmpty(confirmPassword)){
            ConfirmPassword.setError("Please Again Enter Password");
        }
        else if(password.equals(confirmPassword)){
            Toast.makeText(this, "Working", Toast.LENGTH_SHORT).show();
            InsertInDataBase(name,UserName,password);
        }
        else
            Toast.makeText(this, "Password Not Matched", Toast.LENGTH_SHORT).show();
    }

    private void InsertInDataBase(final String name, final String email, final String password) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        //Database code here
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressDialog.dismiss();
                    SaveInDatabase(name,email,password);
                }
                else{
                    progressDialog.dismiss();
                    Toast.makeText(ParentSignUp.this, "Due to technical issues Session not created", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void SaveInDatabase(String name, String email, String password) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

        Users values = new Users(name,email,password);

        ref.child("Users").child(auth.getCurrentUser().getUid()).setValue(values).addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    progressDialog.dismiss();
                    Toast.makeText(ParentSignUp.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ParentSignUp.this, LoginType.class));
                    finish();
                }
                else{
                    progressDialog.dismiss();
                    Toast.makeText(ParentSignUp.this, "Not Inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
