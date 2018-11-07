package com.example.nico.flash;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

import com.firebase.ui.auth.AuthUI;

import java.util.Arrays;


public class LoginActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (new CurrentUser().getCurrentUser() != null) {
            logged();
        } else {
            signUp();
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (RC_SIGN_IN == requestCode) {
            if (RESULT_OK == resultCode) {
                logged();
            }
        }
    }

    public void signUp() {


        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(Arrays.asList(
                                new AuthUI.IdpConfig.EmailBuilder().build(),
                                new AuthUI.IdpConfig.GoogleBuilder().build()))
                        .setIsSmartLockEnabled(false)
                        .build(),
                RC_SIGN_IN);


    }


    public void logged() {


        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();


    }
}
