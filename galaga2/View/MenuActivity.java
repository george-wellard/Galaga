package com.example.p16216571.galaga2.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.p16216571.galaga2.R;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void beginGame (View view) {
        Intent intentStart = new Intent(this, MainActivity.class);

        startActivity(intentStart); // Switching Activity to main
    }

    public void exit (View view)
    {
        Toast.makeText(this, "exit clicked", Toast.LENGTH_SHORT) .show();

        finishAffinity(); // Closing the application
    }
}
