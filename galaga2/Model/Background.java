package com.example.p16216571.galaga2.Model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;

import com.example.p16216571.galaga2.R;

import java.util.ArrayList;

/**
 * Created by P16216571 on 04/04/2018.
 */

public class Background {

    // Setting the variables
    private ArrayList<Bitmap> stars;
    private ArrayList<Integer> yValues, deltaValues;
    private int starsX, deltaY;
    private Point screenSize;
    private int starsSize;

    public Background(ArrayList<Bitmap> starsIn, Point screenSizeIn)
    {
        // Constructing variables
        stars = starsIn;
        deltaY = -5;
        screenSize = screenSizeIn;
        starsSize = stars.size();

        starsX = 0;
        yValues = new ArrayList<>();
        deltaValues = new ArrayList<>();

        // Adding Elements to Value ArrayLists
        for(int i = 0; i < starsSize; i++)
        {
            yValues.add(i,0);
            deltaValues.add(i, deltaY - i);
        }
    }

    public void update()
    {
        // This sets the bitmaps for the background to move upwards on the y position
        for(int j = 0; j < starsSize; j++)
        {
            yValues.set(j, yValues.get(j) + deltaValues.get(j));

            if(yValues.get(j) < -screenSize.y)
            {
                yValues.set(j, 0);
            }
        }
    }

    public void draw(Canvas canvas)
    {
        for(int k = 0; k < starsSize; k++)
        {
            canvas.drawBitmap(stars.get(k), starsX, yValues.get(k), null); // Drawing the background and Meteor through the ArrayList

            if (yValues.get(k) < 0) {
                canvas.drawBitmap(stars.get(k), starsX, yValues.get(k) + screenSize.y, null); // If the bitmaps go past zero on the y position, this sets them to be redrawn
            }
        }
    }

    public void setVector(int deltaIn)
    {
        this.deltaY = deltaIn; // Setting the vector which sets up how much the bitmaps scroll by in the background
    }
}
