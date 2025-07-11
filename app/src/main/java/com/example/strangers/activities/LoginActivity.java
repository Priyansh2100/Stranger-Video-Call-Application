package com.example.strangers.activities;

import androidx.annotation.NonNull;              //Marks a parameter or return value as non-nullable
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.strangers.R;
import com.example.strangers.models.User;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

public class LoginActivity extends AppCompatActivity {

    GoogleSignInClient mGoogleSignInClient;
    int RC_SIGN_ID = 11;
    FirebaseAuth mAuth;
    FirebaseDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Window w = getWindow();               //for fullscreen
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();


        if(mAuth.getCurrentUser() != null) {
            goToNextActivity();
        }


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                        .requestEmail()
                                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this,gso);

        findViewById(R.id.loginBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = mGoogleSignInClient.getSignInIntent();
               startActivityForResult(intent, RC_SIGN_ID);
              //  startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {   //onActivityResult: Handles the result of Google Sign-In.
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RC_SIGN_ID) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            GoogleSignInAccount account = task.getResult();
            authWithGoogle(account.getIdToken());
        }
    }
    void authWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser user = mAuth.getCurrentUser();
                            User firebaseUser = new User(user.getUid(), user.getDisplayName(), user.getPhotoUrl().toString(), "Unknown", 500);
                            database.getReference()
                                    .child("profiles")
                                    .child(user.getUid())
                                    .setValue(firebaseUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull @NotNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                                finishAffinity();
                                            } else {
                                                Toast.makeText(LoginActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                            //Log.e("profile", user.getPhotoUrl().toString());
                        } else {
                            Log.e("err", task.getException().getLocalizedMessage());
                            Log.e("LoginActivity", "FirebaseDatabase instance is null");
                        }
                    }
                });
    }
    void goToNextActivity() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }
}