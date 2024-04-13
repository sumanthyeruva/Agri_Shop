package com.example.agri_shop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class HomeActivity extends AppCompatActivity {

    FirebaseAuth auth;
    ProgressBar progressBar;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        auth=FirebaseAuth.getInstance();
        progressBar=findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);
        if(auth.getCurrentUser()!=null)
        {
            progressBar.setVisibility(View.VISIBLE);
            startActivity(new Intent(HomeActivity.this, MainActivity.class));
            Toast.makeText(this,"Please Wait you are already Logged in",Toast.LENGTH_SHORT);
            finish();
        }


    }

    public void login(View view)
    {
        startActivity(new Intent(HomeActivity.this, UserLoginActivity.class));
    }
    public void registration(View view)
    {
        startActivity(new Intent(HomeActivity.this, UserRegistrationActivity.class));
    }
}