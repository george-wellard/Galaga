package com.example.p16216571.galaga2.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.p16216571.galaga2.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    public void StartGame (View view)
    {
        Intent intentStart = new Intent (this, MenuActivity.class); // Setting new intent back to the Menu Activity

        startActivity(intentStart); // Switch Activity
    }

}