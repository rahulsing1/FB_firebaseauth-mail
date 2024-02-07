package com.example.googlelogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.example.googlelogin.databinding.ActivityMain2Binding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity2 extends MainActivity {
    Intent intent;
    ActivityMain2Binding binding;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.logout.setOnClickListener(view -> {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(MainActivity2.this, Registation_firebase.class);
            startActivity(intent);
            finish();
        });




    }
}