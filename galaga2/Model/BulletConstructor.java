package com.example.p16216571.galaga2.Model;

/**
 * Created by P16216571 on 25/02/2018.
 */

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.p16216571.galaga2.Model.Player;
import com.example.p16216571.galaga2.Model.Bullet;

import java.util.ArrayList;

public class BulletConstructor {

    private ArrayList<Bullet>bulletArray; // Creating an arrayList

    private Player player; // Calling player class

    public BulletConstructor(Player p) {
        bulletArray= new ArrayList<Bullet>(); // Constructing array for bullets
        player = p; // Passing in player

    }

    public void fire()
    {
        bulletArray.add(new Bullet(player.centerX(), player.centerY(), 10, Color.RED)); // Firing the bullet by creating a new one at the player's current positions
    }

    public void updateBullet()
    {
        for(Bullet bullet : bulletArray){
            bullet.bulletUpdate(); // Updating bullet so it can move
        }
    }

    public void redrawBullet(Paint paint, Canvas canvas)
    {
        for(Bullet bullet : bulletArray){

            bullet.drawBullet(paint, canvas); // Redrawing the bullet after the bullet has updated

        }
    }


    public ArrayList<Bullet> getArrayList()
    {
        return bulletArray; // Returning the enemy bullet Array

    }
}
