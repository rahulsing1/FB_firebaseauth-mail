package com.example.googlelogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.example.googlelogin.databinding.ActivityLoginFirebaseBinding;
import com.example.googlelogin.databinding.ActivityRegistationFirebaseBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login_firebase extends AppCompatActivity {
    ActivityLoginFirebaseBinding binding;
    FirebaseAuth mAuth;
    String email, password;

    @Override
    public void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Intent intent = new Intent(Login_firebase.this, MainActivity2.class);
            startActivity(intent);
            finish();

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginFirebaseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuth = FirebaseAuth.getInstance();

        binding.fb.setOnClickListener(view -> {
            Intent intent = new Intent(Login_firebase.this, Facebooklogin.class);
            startActivity(intent);

        });
        binding.textLogin.setOnClickListener(view -> {
            Intent intent = new Intent(Login_firebase.this, Registation_firebase.class);
            startActivity(intent);
        });

        binding.btnLogin.setOnClickListener(view -> {
            email = String.valueOf(binding.logEmail.getText().toString());
            password = String.valueOf(binding.logPassword.getText().toString());
           validationetauth();
            loginfirebase();

        });

    }



    private void validationetauth() {
        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "Please enter email or Password", Toast.LENGTH_SHORT).show();

        }
        else{

            loginfirebase();
        }

    }

    private void loginfirebase() {

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "signInWithEmail:success");
                            Intent intent = new Intent(Login_firebase.this, MainActivity2.class);
                            startActivity(intent);
                            finish();

                        } else {

                            Log.w("TAG", "signInWithEmail:failure", task.getException());
                            Toast.makeText(Login_firebase.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }
}