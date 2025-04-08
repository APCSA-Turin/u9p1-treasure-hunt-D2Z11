package com.example.project;

public class Sprite {
    private int x, y;

    public Sprite(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // ## GETTERS AND SETTERS ##

    public int getX(){return x;}//placeholder
    public int getY(){return y;}

    public void setX(int x){this.x=x;}
    public void setY(int y){this.y=y;}

    public String getCoords(){ //returns the coordinates of the sprite ->"(x,y)"
        return "(" + x + "," + y + ")";
    }

    public String getRowCol(int size){ //returns the row and column of the sprite -> "[row][col]"
        // ((size - 1) - y) converts the y value to row format, in other words, it converts from Cartesian plane to 2D array format
        return "["+ ((size - 1) - y) + "][" + x + "]";
    }
    

    public void move(String direction) { //you can leave this empty
        // Default behavior (can be overridden by subclasses)
    }

    public void interact() { //you can leave this empty
        // Default behavior (can be overridden by subclasses)
    }

    // Function to get the distance to the Sprite in a number between 1 and -1. It returns the x and y "magnitude"
    public int[] getMagnitudeFrom(Sprite s) {
        // Subtract the sprites x value and the other sprites x value to get differences
        int xDiff = getX() - s.getX();
        int yDiff = getY() - s.getY();
        // If this is positive, then the sprite is to the left of what was called on. So set it to -1 if the x difference is positive otherwise 1
        if (xDiff > 0) {
            xDiff = -1;
        } else if (xDiff < 0) {
            xDiff = 1;
        }
        // If this is positive, then the sprite is above what was called on. So set it to -1 if the y difference is positive otherwise 1
        if (yDiff > 0) {
            yDiff = -1;
        } else if (yDiff < 0) {
            yDiff = 1;
        }
        // Return a "pair" or 2 item array
        return (new int[]{xDiff, yDiff});
    }
}
