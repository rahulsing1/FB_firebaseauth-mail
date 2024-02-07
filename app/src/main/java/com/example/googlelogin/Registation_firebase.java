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

public class Registation_firebase extends AppCompatActivity {
    ActivityRegistationFirebaseBinding binding;
    FirebaseAuth mAuth;
    String email, password;
    @Override
    public void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(Registation_firebase.this, MainActivity2.class);
            startActivity(intent);
            finish();

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistationFirebaseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuth=FirebaseAuth.getInstance();
        binding.textLogin.setOnClickListener(view -> {
            Intent intent =new Intent(Registation_firebase.this,Login_firebase.class);
            startActivity(intent);

        });
        binding.btnReg.setOnClickListener(view -> {


            email = binding.regEmail.getText().toString();
            password =binding.regPassword.getText().toString();


            if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password))
            {
                Toast.makeText(this, "Please enter email or Password", Toast.LENGTH_SHORT).show();
            }
           else
            {
               Firebaseregistration();
            }

        });

    }
    private void Firebaseregistration() {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(Registation_firebase.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }


}