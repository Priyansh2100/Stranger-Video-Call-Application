package com.example.strangers.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;   //Used to navigate between activities.
import android.os.Bundle;        //Allows passing data between activities during creation.
import android.view.View;

import com.example.strangers.R;
import com.google.firebase.auth.FirebaseAuth;

public class WelcomeActivity extends AppCompatActivity {       //Extends AppCompatActivity to utilize its lifecycle methods
    FirebaseAuth auth;                                        //Firebase Authentication instance

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);           //Links the activity to its XML layout

        auth = FirebaseAuth.getInstance();                   //Retrieves the current Firebase Authentication instance

        if(auth.getCurrentUser() != null) {
            goToNextActivity();
        }

        findViewById(R.id.getStarted).setOnClickListener(new View.OnClickListener() {  // Refers to the "Get Started" button
            @Override
            public void onClick(View view) {                                          //R -->contains IDs for all resources
                goToNextActivity();
            }
        });


    }
    void goToNextActivity() {
        startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
        finish();
    }
}