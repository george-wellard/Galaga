package com.example.p16216571.galaga2.Model;

import android.graphics.Color;

import java.util.ArrayList;

/**
 * Created by P16216571 on 18/04/2018.
 */

public class AlienConstructor {

    ArrayList<Alien> bbArray = new ArrayList<Alien>();

    public AlienConstructor()
    {
        for (int i = 0; i <= 1; i++) {
            Alien a1 = new Alien(100, 100, 200 * i, 200 * i, Color.BLACK); // Setting parameters for the alein object

            bbArray.add(a1); // Add to array
        }
    }

    public ArrayList<Alien> getBbArray()
    {
        return bbArray; // returning the Array
    }
}


