package com.example.p16216571.galaga2.Model;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import java.util.Date;
import java.util.Timer;

import java.util.ArrayList;

/**
 * Created by P16216571 on 23/02/2018.
 */

public class Player extends Ship {

    // Setting variables
    public int speed;
    private int touchX;
    private int touchY;
    private boolean touch;
    Point screenSize = new Point();
    private BulletConstructor bc;
    private int bulletTime = 0;
    private int bulletDelay = 80;

    public Player(int widthIn, int heightIn, int xPosIn, int yPosIn, int blockcolour)
    {
        super(widthIn, heightIn, xPosIn, yPosIn, blockcolour); // Calling paremnters from GameObject


        bc = new BulletConstructor(this); // Calling bullet constructor

        setSpeed(2); // Setting speed to a particular value
    }


    public void playerUpdate(int XtouchIn, int YtouchIn, Point p, boolean touchIn) {

        touchX = XtouchIn; // Player touch from the x-acis
        touchY = YtouchIn; // Player touch from the Y-Axis
        touch = touchIn; // Player touch
        bulletTime++; // Increment since bullet was fired

        {
            if ((XtouchIn > getxPos())) // If the finger has touched in a greater x positon than the current player x pos
            {
                setxPos(getxPos() + getSpeed()); // adding the speed value to the x position to move to the right
                if(getxPos() > p.x)
                {
                    setxPos(p.x - getWidth()); // Minusing the width from the from the x position so the player can stay on the screen
                }
            }

            if (XtouchIn < getxPos())
            {
                setxPos(getxPos() - getSpeed()); // adding the speed value to the x position to move to the left
                if(getxPos() < 0)
                {
                    setxPos(0);
                }
            }

            if (YtouchIn > getyPos()) // If the player touch is greater than the position of the
            {
                setyPos(getyPos() + getSpeed()); // adding the speed value to the x position to move it down
                if(getyPos() > p.y)
                {
                    setyPos(p.y - getHeight());
                }
            }

            if (YtouchIn < getyPos())
            {
                setyPos(getyPos() - getSpeed()); // adding the speed value to the x position to move it up
                if(getyPos() < 0)
                {
                    setyPos(0);
                }
            }

            if(touchIn == true)
            {
                if(bulletTime > bulletDelay) // If the time since firing the bullet is greater than the set delay
                {
                    bc.fire(); // Fire bullet
                    bulletTime = 0; // reset time since bullet was fired to 0
                }

            }

            bc.updateBullet(); // Calling update function from bullet constructor
        }
    }

    public void drawRect(Paint p, Canvas c)
    {
        super.drawRect(p, c); // draw player
        bc.redrawBullet(p, c); // redraw bullet

    }

    public void playerFire()
    {
        bc.fire();
    }

    public void setSpeed(int speed)
    {
        this.speed = speed;
    }

    public int getSpeed()
    {
        return speed;
    }

    public Point getScreenSize() { return screenSize; }

    public BulletConstructor getBC()
    {
        return bc;
    }
}
