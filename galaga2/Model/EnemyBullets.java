package com.example.p16216571.galaga2.Model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;

/**
 * Created by P16216571 on 06/03/2018.
 */

public class EnemyBullets {

    private ArrayList<Bullet> enemyBulletArray; // Creating ArrayList for Enemy Bullets

    private Ship ship; // Calling ship class

    public EnemyBullets(Ship s) {
        enemyBulletArray= new ArrayList<Bullet>(); // Constructing Array List
        ship = s; // Setting up the ship

    }

    public void fire()
    {
        enemyBulletArray.add(new Bullet(ship.centerX(), ship.centerY(), 10, Color.YELLOW)); // Firing the bullet by creating a new one at the ship's current positions
    }

    public void updateEnemyBullet()
    {
        for(Bullet bullet : enemyBulletArray){
            bullet.enemyBulletUpdate(); // Updating bullet so it can move
        }
    }

    public void redrawBullet(Paint paint, Canvas canvas)
    {
        for(Bullet bullet : enemyBulletArray){

            bullet.drawBullet(paint, canvas); // Redrawing the bullet after the bullet has updated

        }
    }


    public ArrayList<Bullet> getArrayList()
    {
        return enemyBulletArray; // Returning the enemy bullet Array

    }
}

