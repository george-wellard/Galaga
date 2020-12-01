package com.example.p16216571.galaga2.Model;

/**
 * Created by P16216571 on 23/02/2018.
 */

import android.graphics.Color;

import java.util.ArrayList;

import com.example.p16216571.galaga2.Model.ShipConstructer;

public class ShipConstructer {

    ArrayList<Ship>bbArray = new ArrayList<Ship>(); // Creating an array list for the shipsw

    public ShipConstructer(){

        for (int i = 0; i <= 3; i++) {
            Ship s1 = new Ship(50, 50, 200 * i, 200 * i, Color.BLACK); // Setting parameters for ship arrays

            bbArray.add(s1); // Adding these ships to the array
        }
    }

    public void Fleet2() // Second array for second level
    {
        for(int i = 0; i <= 4; i++) {
            Ship s2 = new Ship(40, 40, 100 * i, 100 * i, Color.BLACK);

            bbArray.add(s2);
        }
    }

    public void Fleet3() // Third arrray for third level
    {
        for(int i = 0; i <= 3; i++)
        {
            Ship s3 = new Ship( 40, 40, 100 * i, 200 * i, Color.BLACK);

            bbArray.add(s3);
        }
    }

    public ArrayList<Ship> getArrayList()
    {
        return bbArray;
    } // Returning array list
}