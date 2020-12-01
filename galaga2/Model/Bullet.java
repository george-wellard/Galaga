package com.example.p16216571.galaga2.Model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

/**
 * Created by P16216571 on 23/02/2018.
 */

public class Bullet extends GameObject {

    // Setting up the varaibles
    private int speed; // Setting speed
    Point screenSize = new Point(); // Setting screensize through a Point

    public Bullet(int x, int y, int radius, int blockcolour)
    {
        super(x, y, radius, blockcolour); // calling in parameters from GameObject

        setSpeed(9); // Giving a speed a value
    }

    public void drawBullet(Paint p, Canvas c)
    {
        p.setColor(getColour()); // Setting colour of the bullets
        c.drawCircle(getxPos(), getyPos(), 10, p); // Drawing the bullets
    }

    public void bulletUpdate()
    {
        setyPos(getyPos() - getSpeed());
    } // Updating the position of the bullets so they can move

    public void enemyBulletUpdate() { setyPos(getyPos() + getSpeed()); } // Updating the position of the enemy bullets so they can move in the opposite direction to the player bullets

    public void setSpeed(int speed)
    {
        this.speed = speed;
    } // Creating the speed function

    public int getSpeed()
    {
        return speed;
    } // Returning the bullet speed
}

