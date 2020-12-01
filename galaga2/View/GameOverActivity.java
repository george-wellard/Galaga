package com.example.p16216571.galaga2.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.p16216571.galaga2.R;

public class GameOverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void NewGame(View view)
    {
        Intent intentStart = new Intent(this, MainActivity.class); // Setting new intent back to thew Main Activity to restart the gameS

        startActivity(intentStart); // Switch Activities
    }

    public void Home(View view)
    {
        Intent intentStart = new Intent(this, MenuActivity.class); // Setting new intent back to the Menu Actiity

        startActivity(intentStart); // Switch Activities
    }

}
