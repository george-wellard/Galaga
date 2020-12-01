package com.example.p16216571.galaga2.View;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.p16216571.galaga2.R;

public class MainActivity extends AppCompatActivity {

    private GameSurfaceView gsv; // Setting up view for Main Activity
    // Setting the positions
    private int xPos = 100;
    private int yPos = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Setting up motion controls
        int mUIFlag = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
//Set the flags on the view
        getWindow().getDecorView().setSystemUiVisibility(mUIFlag);
        Point screenSize = new Point(); // Using a point to set the screen size
        this.getWindowManager().getDefaultDisplay().getSize(screenSize); //
        gsv = new GameSurfaceView(this, screenSize); // Setting the view to the screen size
        setContentView(gsv); // Setting the view of the Main Acticity to be the Surface View
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "onPause()", Toast.LENGTH_LONG).show();
        gsv.pause(); // Calling pause function from Surface View
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "onResume()", Toast.LENGTH_LONG).show();
        gsv.resume(); // calling resume function from Surface View
    }

    public boolean onTouchEvent(MotionEvent event) {
        int eventaction = event.getAction();
        switch (eventaction) {
            case MotionEvent.ACTION_DOWN:
// finger touches the screen
                xPos = (int)event.getX();
                yPos = (int)event.getY();
                gsv.pressUpdate(xPos, yPos, true);
                gsv.resume();
                break;
            case MotionEvent.ACTION_MOVE:
// finger moves on the screen
                xPos = (int)event.getX();
                yPos = (int)event.getY();
                gsv.pressUpdate(xPos, yPos, true);
                break;
            case MotionEvent.ACTION_UP: // finger leaves the screen
                gsv.pressUpdate(xPos, yPos, false);
                gsv.pause();
                break;

            // tell the system that we handled the event and no further processing is required
        }
        return true;
    }
}

