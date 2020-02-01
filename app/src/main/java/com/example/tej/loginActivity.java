package com.example.tej;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class loginActivity extends AppCompatActivity {
    EditText emailId,password;
    Button login;
    TextView signup;
    FirebaseAuth firebaseAuth;
    ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailId = findViewById(R.id.editText3);
        password = findViewById(R.id.editText4);
        login = findViewById(R.id.button2);
        signup = findViewById(R.id.textView2);
        firebaseAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar2);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = emailId.getText().toString();
                String pwd = password.getText().toString();


                if (TextUtils.isEmpty(email)) {
                    emailId.setError("Please enter emailId");
                    return;
                }
                if (TextUtils.isEmpty(pwd)) {
                    password.setError("Please enter password");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                if (!(TextUtils.isEmpty(email) && TextUtils.isEmpty(pwd))) {
                    firebaseAuth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(loginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(loginActivity.this, "Logged in successfully .", Toast.LENGTH_SHORT).show();
                                Intent intoHome = new Intent(loginActivity.this, HomeActivity.class);
                                startActivity(intoHome);
                                finish();
                            }
                            else {
                                Toast.makeText(loginActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                    });
                } else {
                    Toast.makeText(loginActivity.this, "An error occurred", Toast.LENGTH_SHORT).show();

                }
            }
        });


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intosignup = new Intent(loginActivity.this,MainActivity.class);
                startActivity(intosignup);
                finish();

            }
        });


            }


}
