package com.example.p16216571.galaga2.Model;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import java.util.Random;

/**
 * Created by P16216571 on 23/02/2018.
 */

public class Ship extends GameObject {

    // Global Varibles
    public int left;
    public int right;
    public int top;
    public int bottom;
    Point screenSize = new Point();
    private EnemyBullets eb;
    private int bulletTime = 0;
    private int bulletDelay = 6;
    public int speed;

    public Ship(int widthIn, int heightIn, int xPosIn, int yPosIn, int blockcolour)
    {
        super(widthIn, heightIn, xPosIn, yPosIn, blockcolour); // Calling parameters from GameObject

        // Setting Collison parameters
        left = xPosIn;
        right = widthIn + xPosIn;
        top = yPosIn;
        bottom = heightIn + yPosIn;

        eb = new EnemyBullets(this); // Calling the enemy bullet constructor

        setSpeed(1); // Setting the speed for the ships to move along at
    }

    public void  drawRect(Paint p, Canvas c)
    {
        // Drawing the object paremeters
        left = getxPos();
        right = getWidth() + getxPos();
        top = getyPos();
        bottom = getHeight() + getyPos();

        p.setColor(getColour()); // Setting the colour
        c.drawRect(left, top, right, bottom, p); // Drawing the ships on to the screen
        eb.redrawBullet(p, c); // redrawing the bullet
    }

    public void shipUpdate()
    {
        setxPos(getxPos() + getSpeed()); // Moving the ships by the set speed along the x-axis
        bulletTime++; // incrementing the time between firing the bullet as the ship updates

        // Setting the collision paremeters
        left = getxPos();
        right = getWidth() + getxPos();
        top = getyPos();
        bottom = getHeight() + getyPos();

        if(getxPos() > 740 - getWidth()) // Checking if the ships have moved past the right of the screen
        {
            setSpeed(-getSpeed()); // setting the speed to minus so the ships can move in the opposite direction when they hit the edge of the screen
        }

        if(getxPos() < 0) // Checking if the ship has moved past the left side of the screen
        {
            setSpeed(-getSpeed()); // Continuing to set the speed to minus as mulitpling a negetive by a negetive
        }

        if(bulletTime%150 == 0) // Setting the time in which each bullet will be fired at
        {
            eb.fire(); // Firing the bullet
            bulletTime = 0; // Resetting the bullet time to 0
        }

        eb.updateEnemyBullet(); // Update the enemy bullet
    }


    public void enemyBullet()
    {
        eb.fire();
    } // Firing the enemy bullet


    public void setWidth (int widthIn)
    {
        super.setWidth(widthIn);
    }

    public void setHeight (int heightIn)
    {
        super.setHeight(heightIn);
    }

    public void setSpeed(int speed)
    {
        this.speed = speed;
    }

    public int getSpeed()
    {
        return speed;
    }

    public EnemyBullets getEB()
    {
        return eb;
    }
}
