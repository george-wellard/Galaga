package com.example.p16216571.galaga2.Presenter;

import android.graphics.Point;
import android.util.Log;

import com.example.p16216571.galaga2.Model.Alien;
import com.example.p16216571.galaga2.Model.Bullet;
import com.example.p16216571.galaga2.Model.Player;
import com.example.p16216571.galaga2.Model.Ship;

/**
 * Created by P16216571 on 26/02/2018.
 */

public class Collision {


    public boolean bulletShipCollision(Bullet bullet, Ship ship)
    {
        Point bulletDistance = new Point (0, 0); // Setting the distance of the bullet
        // Working out how far the the bullet is from the center of the ship
        bulletDistance.x = Math.abs(bullet.getxPos() - ship.centerX());
        bulletDistance.y = Math.abs(bullet.getyPos() - ship.centerY());

        // Checking if the bullet distance is greater than the width of ship
        if(bulletDistance.x > (ship.getWidth()/2 + bullet.getRadius())) { return false; }
        if(bulletDistance.y > (ship.getHeight()/2 + bullet.getRadius())) { return false; }

        // Checking if the bullet distance is greater than or equel to the heigh and width of the ship
        if(bulletDistance.x <= (ship.getWidth()/2)) { return true; }
        if(bulletDistance.y <= (ship.getHeight()/2)) { return true; }

        //
        double cornerDistanceSquare = (bulletDistance.x - ship.getWidth()/2)^2 +
                ( bulletDistance.y - ship.getHeight()/2)^2;

        return (cornerDistanceSquare <= (bullet.getRadius()^2));
    }

    public boolean bulletPlayerCollision(Bullet bullet, Player player)
    {
        // Same process as with bullet/Ship collison
        Point bulletDistance = new Point (0, 0);
        bulletDistance.x = Math.abs(bullet.getxPos() - player.centerX());
        bulletDistance.y = Math.abs(bullet.getyPos() - player.centerY());

        if(bulletDistance.x > (player.getWidth()/2 + bullet.getRadius())) { return false; }
        if(bulletDistance.y > (player.getHeight()/2 + bullet.getRadius())) { return false; }

        if(bulletDistance.x <= (player.getWidth()/2)) { return true; }
        if(bulletDistance.y <= (player.getHeight()/2)) { return true; }

        double cornerDistanceSquare = (bulletDistance.x - player.getWidth()/2)^2 +
                    ( bulletDistance.y - player.getHeight()/2)^2;

        return (cornerDistanceSquare <= (bullet.getRadius()^2));
    }


    public boolean playerAlienCollision(Player player, Alien alien)
    {
        boolean collision = false;

        if(player.right < alien.left ||
                player.left > alien.right) // Checking the x axis sides of the player and alien have crossed
        {
            return false;
        }
        else if(player.bottom < alien.top ||
                player.top > alien.bottom) // Checking the y axis sides of the player and alien have crossed
        {
            return false;
        }
        else
        {
            collision = true; // If there has been a collision, it's set to true and we return it
            return true;
        }
    }

    public boolean bulletAlienCollision(Bullet bullet, Alien alien) {
        // Same process as with bullet/Ship collision and bullet player collision
        Point bulletDistance = new Point(0, 0);
        bulletDistance.x = Math.abs(bullet.getxPos() - alien.centerX());
        bulletDistance.y = Math.abs(bullet.getyPos() - alien.centerY());

        if (bulletDistance.x > (alien.getWidth() / 2 + bullet.getRadius())) {
            return false;
        }
        if (bulletDistance.y > (alien.getHeight() / 2 + bullet.getRadius())) {
            return false;
        }

        if (bulletDistance.x <= (alien.getWidth() / 2)) {
            return true;
        }
        if (bulletDistance.y <= (alien.getHeight() / 2)) {
            return true;
        }

        double cornerDistanceSquare = (bulletDistance.x - alien.getWidth() / 2) ^ 2 +
                (bulletDistance.y - alien.getHeight() / 2) ^ 2;

        return (cornerDistanceSquare <= (bullet.getRadius() ^ 2));
    }
}
