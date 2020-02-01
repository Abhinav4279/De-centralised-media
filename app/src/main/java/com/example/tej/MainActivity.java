package com.example.tej;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
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

public class MainActivity extends AppCompatActivity {
    EditText emailId,password;
    Button signUp;
    TextView signIn;
    FirebaseAuth firebaseAuth;
    ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth =FirebaseAuth.getInstance();
        emailId = findViewById(R.id.editText);
        password = findViewById(R.id.editText2);
        signUp = findViewById(R.id.button);
        signIn = findViewById(R.id.textView);
        progressBar = findViewById(R.id.progressBar);




        if (firebaseAuth.getCurrentUser()!=null){
            Intent intologin = new Intent(MainActivity.this,loginActivity.class);
            startActivity(intologin);
            finish();
        }

        signUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                String email =emailId.getText().toString().trim();
                String pwd =password.getText().toString().trim();
                if (TextUtils.isEmpty(email)){
                    emailId.setError("Please enter emailId");
                    return;
                }
                if(TextUtils.isEmpty(pwd)){
                    password.setError("Please enter password");
                    return;
                }
                if(pwd.length()<7){
                    password.setError("password must be >= 7 characters ");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                if(!(TextUtils.isEmpty(email)&&TextUtils.isEmpty(pwd))){

                    firebaseAuth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(MainActivity.this,"Account created .",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(MainActivity.this,HomeActivity.class));
                                finish();
                            }
                            else{
                                Toast.makeText(MainActivity.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(MainActivity.this,"An error occurred",Toast.LENGTH_SHORT).show();

                }
            }
        });


       signIn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i =new Intent(MainActivity.this,loginActivity.class);
               startActivity(i);
               finish();
                        }
       });
    }
}
