package com.example.p16216571.galaga2.Model;

/**
 * Created by P16216571 on 23/02/2018.
 */

public class GameObject {

    private int width;
    private int height;
    private int radius = 20;
    private int xPos = 10;
    private int yPos = 10;
    private int colour;

    GameObject(int widthIn, int heightIn, int xPosIn, int yPosIn, int blockcolour){

        width = widthIn;
        height = heightIn;
        xPos = xPosIn;
        yPos = yPosIn;
    }

    GameObject(int x, int y, int radiusIn, int blockcolour)
    {
        xPos = x;
        yPos = y;
        radius = radiusIn;
        colour = blockcolour;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    public void setRadius (int radius) {this.radius = radius;}

    public void setxPos(int xPos)
    {
        this.xPos = xPos;
    }

    public void setyPos(int yPos)
    {
        this.yPos = yPos;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getRect() { return width + height + getxPos() + getyPos(); }

    public int centerX() { return xPos + width/2; }

    public int centerY() { return yPos + height/2; }

    public int getRadius() { return radius; }

    public int getxPos() { return xPos; }

    public int getyPos() { return yPos; }

    public int getColour() { return colour; }
}
