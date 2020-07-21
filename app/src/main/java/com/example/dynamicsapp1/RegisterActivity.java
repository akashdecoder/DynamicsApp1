package com.example.dynamicsapp1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    EditText userName, userEmail, userPassword, userConfirmPassword, phone;
    Button btnSignUp;
    FirebaseAuth firebaseAuth;
    DatabaseReference reff;
    Member member;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();
        userName = findViewById(R.id.userName);
        userEmail = findViewById(R.id.useremail);
        phone = findViewById(R.id.phone);
        userPassword = findViewById(R.id.password);
        userConfirmPassword = findViewById(R.id.confirmPassword);
        btnSignUp = findViewById(R.id.registerbutton);

        member = new Member();
        reff = FirebaseDatabase.getInstance().getReference().child("Member");
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fname = userName.getText().toString();
                final String email = userEmail.getText().toString();
                String contact = phone.getText().toString();
//                final String pwd = "Dynamics20";
                final String pwd = userPassword.getText().toString();
                final String cnfpass = userConfirmPassword.getText().toString();
                member.setName(fname);
                member.setPhone(contact);
                member.setEmail(email);
                member.setPassword(pwd);
                if(email.isEmpty()){
                    userEmail.setError("Please enter email id");
                    userEmail.requestFocus();
                }
                else  if(pwd.isEmpty()){
                    userPassword.setError("Please enter your password");
                    userPassword.requestFocus();
                }
                else  if(email.isEmpty() && pwd.isEmpty()){
                    Toast.makeText(RegisterActivity.this,"Fields Are Empty!",Toast.LENGTH_SHORT).show();
                }
                else  if(!(email.isEmpty()) && !pwd.isEmpty()){
                    firebaseAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
//                            if(!task.isSuccessful()){
//                                Toast.makeText(RegisterActivity.this,"SignUp Unsuccessful or Email Already Exists, Please Try Again",Toast.LENGTH_SHORT).show();
//                            }
//                            else {
//                                reff.push().setValue(member);
//                                startActivity(new Intent(RegisterActivity.this,MainActivity.class));
//                            }
                            if(cnfpass.equals(pwd)){
                                firebaseAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if(!task.isSuccessful()){
                                            Toast.makeText(RegisterActivity.this,"SignUp Unsuccessful or Email Already Exists, Please Try Again",Toast.LENGTH_SHORT).show();
                                        }
                                        else {
                                            reff.push().setValue(member);
                                            startActivity(new Intent(RegisterActivity.this,MainActivity.class));
                                        }
                                    }
                                });
                            }
                            else {
                                Toast.makeText(RegisterActivity.this,"Password doesn't match",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(RegisterActivity.this,"Error Occurred!",Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}