package com.example.googlelogin;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.util.Log;

import com.example.googlelogin.databinding.ActivityfacebookloginBinding;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import java.util.Arrays;

public class Facebooklogin extends AppCompatActivity {
    CallbackManager callbackManager = CallbackManager.Factory.create();
    ActivityfacebookloginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityfacebookloginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(Facebooklogin.this.getApplication());

       fbauthentication();
        binding.loginButton.setOnClickListener(view -> {
            //this nothing just permission;
            LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
        });

    }

    private void fbauthentication() {
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {

                        AccessToken accessToken = loginResult.getAccessToken();
                        Profile profile = Profile.getCurrentProfile();

                        Log.d("facebook id", accessToken.getApplicationId());
                        Log.d("profile name", profile.getFirstName());

                    }

                    @Override
                    public void onCancel() {
                        Log.d("TAG", "onCancel: ");
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        Log.d("TAG", "onError: ");
                    }
                });
    }
}