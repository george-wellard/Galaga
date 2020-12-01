package com.example.p16216571.galaga2.Model;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by P16216571 on 16/04/2018.
 */

public class Alien extends Ship {

    public Alien(int widthIn, int heightIn, int xPosIn, int yPosIn, int blockcolour)
    {
        super(widthIn, heightIn, xPosIn, yPosIn, blockcolour);

        // Collision paramenters
        left = xPosIn;
        right = widthIn + xPosIn;
        top = yPosIn;
        bottom = heightIn + yPosIn;

    }

    public void drawRect(Paint p, Canvas c)
    {
        super.drawRect(p, c); // Drawing the alien to the screen
    }

    public void alienUpdate()
    {
        // Updating the collision paremeters so they can move with the object
        left = getxPos();
        right = getWidth() + getxPos();
        top = getyPos();
        bottom = getHeight() + getyPos();
    }
}
