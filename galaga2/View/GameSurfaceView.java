package com.example.p16216571.galaga2.View;

/**
 * Created by P16216571 on 23/02/2018.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import com.example.p16216571.galaga2.Model.Alien;
import com.example.p16216571.galaga2.Model.AlienConstructor;
import com.example.p16216571.galaga2.Model.Background;
import com.example.p16216571.galaga2.Model.Bullet;
import com.example.p16216571.galaga2.Model.BulletConstructor;
import com.example.p16216571.galaga2.Model.EnemyBullets;
import com.example.p16216571.galaga2.Model.Player;
import com.example.p16216571.galaga2.Model.Ship;
import com.example.p16216571.galaga2.Model.ShipConstructer;
import com.example.p16216571.galaga2.Presenter.Collision;
import com.example.p16216571.galaga2.R;


import java.util.ArrayList;
import java.util.Iterator;
import java.lang.Class;

import static android.graphics.Color.BLUE;
import static android.support.v4.content.ContextCompat.startActivity;

public class GameSurfaceView extends SurfaceView implements Runnable{

    // Global Variables
    Point screenSize; //Holds the screen size
    SurfaceHolder holder; //Surface holder for the canvas
    private boolean ok = false; //Boolean to control pause and resume
    Thread t = null; //Thread for the game logic
    Paint paint = new Paint();
    ArrayList<Ship> ships; // ArrayList for enemy ships
    ShipConstructer sc = new ShipConstructer(); // Constructor class for enemy ships
    Player player; // Calling player class
    Background background; // Calling background class
    ArrayList<Bitmap> bitmap; // Creating ArrayList for background bitmaps
    ArrayList<Bullet> bullets; // ArrayList for the player bullets
    ArrayList<Bullet> enemybullets; // ArrayList for the enemy bullets
    Collision collision; // Calling Collision Class
    ArrayList<Alien> alien; // Calling Alien class
    AlienConstructor ac = new AlienConstructor();
    private Path sPath;// Creating Path
    private PathMeasure pmAlien; // Creating Path Measure
    private int alienOffsetX, alienOffsetY; //
    private float[] posAlien;
    private Matrix matrix; // Creating Matrix
    // Setting speed in which the alien moves along the path
    private float curStep, speed = 0.0f;
    final static  int STEPS = 250;
    private float[] tanAlien;
    int touchX; // Setting player touch for x-axis
    int touchY; // Setting player touch for y-axis
    boolean touch; // Setting general touch sensor
    int playerLives = 5;
    private Bitmap starsBitmap; // Setting all the bitmaps
    private Bitmap scaledStars;
    private Bitmap playerBitmap;
    private Bitmap scaledPlayer;
    private Bitmap alienBitmap;
    private Bitmap scaledAlien;
    private Bitmap meteorBitmap;
    private Bitmap scaledMeteor;
    private Bitmap alien2Bitmap;
    private Bitmap scaledAlien2;
    boolean dead; // Boolean to check if the alien is dead
    Context gameEnd; // Setting context for game end state
    Bundle bundle; // Creating bundle
    int currentLevel = 1; // Setting the current level
    int score = 0; // Setting the score to zero
    Paint scoreText; // Creating the score text

    public GameSurfaceView(Context context, Point screenS) {
        //Place items in here for the constructor
        super(context);
        holder = getHolder();//Used for the screenview
        screenSize = screenS;
        ships = new ArrayList<Ship>(sc.getArrayList()); // Caling the constructor for the ships
        player = new Player(100, 100, 400, 1100, Color.YELLOW);
        bullets = player.getBC().getArrayList();
        enemybullets = new ArrayList<Bullet>();
        collision = new Collision();
        alien = new ArrayList<Alien>(ac.getBbArray());
        // Setting the size, picture and what object to map the bitmaps on to
        starsBitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.space);
        scaledStars = Bitmap.createScaledBitmap(starsBitmap, screenSize.x, screenSize.y, false);
        playerBitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.player);
        scaledPlayer = Bitmap.createScaledBitmap(playerBitmap, player.getWidth(), player.getHeight(), false);
        alienBitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.enemy);
        meteorBitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.meteor);
        scaledMeteor = Bitmap.createScaledBitmap(meteorBitmap, screenSize.x, screenSize.y, false);
        alien2Bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.alien);
        for(int i = 0; i < alien.size(); i++)
        {
            scaledAlien2 = Bitmap.createScaledBitmap(alien2Bitmap, alien.get(i).getWidth(), alien.get(i).getHeight(), false);
        }
        for(int i = 0; i < ships.size(); i++)
        {
            scaledAlien = Bitmap.createScaledBitmap(alienBitmap, ships.get(i).getWidth(), ships.get(i).getHeight(), false);
        }
        bitmap = new ArrayList<Bitmap>(); // Creating Bitmap ArrayList to store in background
        bitmap.add(scaledStars);
        bitmap.add(scaledMeteor);
        background = new Background(bitmap, screenS); // Creating background
        sPath = new Path(); // Creating a path
        // Creating the positons of the path in which the alien moves towards
        sPath.moveTo(20.0f, 100.0f);
        sPath.lineTo(450.0f, 1200.0f);
        sPath.lineTo(740.0f, 200.0f);
        sPath.lineTo(450.0f, 20.0f);
        sPath.lineTo(20.0f, 100.0f);
        sPath.moveTo(20.0f, 100.0f);
        sPath.close(); // Closing off the path
        pmAlien = new PathMeasure(sPath, false);
        speed = pmAlien.getLength()/STEPS;
        posAlien = new float[2]; // Using float
        for(int i = 0; i < alien.size(); i++) {
            // Getting the center of the object
            alienOffsetX = alien.get(i).getWidth() / 2;
            alienOffsetY = alien.get(i).getHeight() / 2;
        }
        matrix = new Matrix(); // Creatingg the Matrix
        gameEnd = context; //
        bundle = new Bundle();
        scoreText = new Paint(); // Constructing the score text
        scoreText.setColor(Color.GREEN); // Setting the colour of the score text
        scoreText.setTextSize(50); // Setting the text size
    }

    private void updateCanvas (Canvas canvas) {
        //Update the items in the canvas
        background.update(); // calling the update function from the Background class to movve it along the screen

        for (int i = 0; i < ships.size(); i++) {
            ships.get(i).shipUpdate(); // Using a for loop to grab all the ships so then we can update them meaning they can move
        }
        player.playerUpdate(touchX, touchY, screenSize, touch); // calling the player update to get player movement

        for (Iterator<Ship> shipIterator = ships.iterator(); shipIterator.hasNext();) // Using an iterator to cycle through the ships
        {
            Ship ship = shipIterator.next();
            for (Iterator<Bullet> bulletIterator = bullets.iterator(); bulletIterator.hasNext();) // Cycling through all the bullets in the array using an iterator
            {
                Bullet bullet = bulletIterator.next();
                if (collision.bulletShipCollision(bullet, ship)) // Checking if bullet has collied with a ship
                {
                    shipIterator.remove(); // Removing collided ship
                    bulletIterator.remove(); // Removing collided bullet
                    score++; // Increment score
                }
            }
        }

        for (Ship enemy : ships) { // For lopp to make sure all enemy ship bullets work
            for (Iterator<Bullet> bulletIterator = enemy.getEB().getArrayList().iterator(); bulletIterator.hasNext(); ) { // Cycling through bullets with iterator
                Bullet bullet = bulletIterator.next();
                if (collision.bulletPlayerCollision(bullet, player)) { // Checking if bullet has collided with player
                    player.setxPos(100); // Changing player Position
                    player.setyPos(1100);
                    bulletIterator.remove(); // Removing collided bullet
                    playerLives--; // Remove life
                }
            }

            if (ships.size() == 0) {
                enemybullets.clear(); // Cleaing enemy bullets after the ship has been destoryed so they don't stick onto the screen
            }
        }

        for(int i = 0; i < alien.size(); i++)
        {
            alien.get(i).alienUpdate(); // Calling update function so the collison parmameters can move along the matrix with the object
        }

        for(Iterator<Alien> alienIterator = alien.iterator(); alienIterator.hasNext();) // Cycling through alien array with an iterator
        {
            Alien alien = alienIterator.next();
            if(collision.playerAlienCollision(player, alien)) // Checking if the alien has collided with the player
            {
                player.setxPos(100); // Changing player position
                player.setyPos(1100);
                playerLives--; // Remove life
            }
        }

        for(Iterator<Alien> alienIterator = alien.iterator(); alienIterator.hasNext();)
        {
            Alien alien = alienIterator.next();
            for (Iterator<Bullet> bulletIterator = bullets.iterator(); bulletIterator.hasNext(); ) { // Cycling through bullets with iterator
                Bullet bullet = bulletIterator.next();
                if (collision.bulletAlienCollision(bullet, alien))
                {
                    alienIterator.remove(); // Removing the alien from the screen
                    bulletIterator.remove(); // Removing the collided bullet
                    score++; // Increment score
                }
            }
        }

        for(int i = 0; i < bullets.size(); i++)
        {
            if(bullets.get(i).getyPos() < 0) // Checking if the bullet has moved past that particular poin
            {
                bullets.remove(bullets.get(i)); // Removing bullets after they move past they screen to prevent crashing from using too much memeory
            }
        }

        for(int i = 0; i < enemybullets.size(); i++)
        {
            if(enemybullets.get(i).getyPos() > 1400) // Checking if the bullet has moved past that particular point
            {
                enemybullets.remove(enemybullets.get(i)); // Removing the enemy bullets after they move past the screen to prevent crashes from using too much memory
            }
        }

        if(curStep < pmAlien.getLength())
        {
            pmAlien.getPosTan(curStep, posAlien, tanAlien);	//Checking the step against the length of the path and moving the image accordingly
            matrix.reset(); // Resetting after each iteration
            matrix.postTranslate(posAlien[0] - alienOffsetX, posAlien[1] - alienOffsetY); // Moving the matrix
            for(int i = 0; i < alien.size(); i++) {
                // Setting the position of the actual alien object onto the matrix
                alien.get(i).setxPos((int) posAlien[0] - alienOffsetX);
                alien.get(i).setyPos((int) posAlien[1] - alienOffsetY);
            }
            curStep += speed; // incrementing the speed
        }
        else
        {
            curStep = 0.0f; // Resetting the current step to the beginning if it is greater
        }


        if(ships.isEmpty()) // Checking if all the ships in the array have been removed
        {
            currentLevel++; // Incrementing the level
            if(currentLevel == 2)
            {
                sc.Fleet2(); // Calling in the second fleet of ships set in the ship constructor
                ships = sc.getArrayList(); // Grabbing the array list from the ship constructor in order to get the ships on tto the screen
                alien = ac.getBbArray(); // Grabbing array from the alien constructor so the alien can be drawn to the screen
            }

            // Same process for the next level
            if(currentLevel == 3)
            {
                sc.Fleet3();
                ships = sc.getArrayList();
                alien = ac.getBbArray();
            }
        }

        gameOver(gameEnd); // Having passed the context into gameEnd it is then passed into the game over function
    }

    protected void drawCanvas(Canvas canvas)
    {
        //Draw the items to the canvas
        canvas.drawARGB(255, 100, 100, 0);

        background.draw(canvas);// Drawing the background

        for (int i = 0; i < ships.size(); i++) {

            ships.get(i).drawRect(paint, canvas); // Drawing the shi[s
            canvas.drawBitmap(scaledAlien, ships.get(i).getxPos(), ships.get(i).getyPos(), null); // Drawing the enemy bitmaps onto the ships

        }
        player.drawRect(paint, canvas); // Drawing the player
        canvas.drawBitmap(scaledPlayer, player.getxPos(), player.getyPos(), null); // Drawing the bitmap for the player

        for(int i = 0; i < alien.size(); i++) {
            canvas.drawBitmap(scaledAlien2, matrix, null); // Drawing the bitmap of the alien and positioning it onto the path matrix
        }

        for(int i = 0; i < bullets.size(); i++)
        {
                bullets.get(i).drawBullet(paint, canvas); // Drawing all the bullets on the screen
        }
        canvas.drawText("score:" + score, 10, 40, scoreText); // Drawing the sore onto the screen
    }

    public void run()
    {
        //Remove conflict between the UI thread and the game thread.
        android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);

        while (ok) {
            //perform canvas drawing
            if (!holder.getSurface().isValid()) {//if surface is not valid
                continue;//skip anything below it
            }
            Canvas c = holder.lockCanvas(); //Lock canvas, paint canvas,

            this.updateCanvas(c);
            this.drawCanvas(c);
            holder.unlockCanvasAndPost(c);
        }
    }

    public void pause()
    {
        ok = false;
        while (true) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            break;
        }
        t = null;
    }

    public void resume()
    {
        ok = true; // If player is touching the scoree
        t = new Thread(this); // Create a new thread
        t.start(); // start new thread
    }

    public void pressUpdate(int xPos, int yPos, boolean touch)
    {
       touchX = xPos;
       touchY= yPos;
       this.touch = touch;

    }

    public void gameOver(Context context)
    {
        if(playerLives == 0)
        {
            Intent intent = new Intent(context, GameOverActivity.class); // Creating a new intent to switch over to the game over activity
            startActivity(context, intent, bundle); // Starting the new activity
        }

        // Same process as before only instead activated when the player wins
        if(currentLevel == 4)
        {
            Intent intent = new Intent(context, GameOverActivity.class);
            startActivity(context, intent, bundle);
        }
    }
}