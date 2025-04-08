package com.example.project;

import java.util.function.Function;

//DO NOT DELETE ANY METHODS BELOW
public class Player extends Sprite {
    // numTreasures is the total treasures at the beginning of the game
    private int treasureCount;
    private int numLives;
    private boolean win;

    // Setup x and y by using superclass (Sprite)
    public Player(int x, int y) {
        super(x, y);

        // set treasureCount = 0 and numLives = 2
        treasureCount = 0;
        numLives = 2;
    }

    // ## GETTERS AND SETTERS ##

    public int getTreasureCount() {
        return treasureCount;
    }

    public int getLives() {
        return numLives;
    }

    public boolean getWin() {
        return win;
    }

    public void decreaseLives() {
        numLives--;
    }

    // ## Functions to get coords and the row and column. It works by using the super function provided by sprite. ##

    // Override sprite method
    public String getCoords(){ //returns the coordinates of the sprite ->"(x,y)"
        return "Player:" + super.getCoords();
    }

    // Override sprite method
    public String getRowCol(int size){ //returns the row and column of the sprite -> "[row][col]"
        return "Player:"+ super.getRowCol(size);
    }

    // move method should override parent class, sprite
    public void move(String direction) { // move the (x,y) coordinates of the player
        // Get lowercase value of direction
        switch (direction.toLowerCase()) {
            case "w":
                // Set y to current y + 1
                super.setY(getY() + 1);
                break;
            case "s":
            // Set y to current x  1
                super.setY(getY() - 1);
                break;
            case "d":
            // Set x to current x + 1
                super.setX(getX() + 1);
                break;
            case "a":
            // Set y to current x - 1
                super.setX(getX() - 1);
                break;
        }
    }

    // Function to interact with objects on the field
    public void interact(int size, String direction, int numTreasures, Object obj) { // interact with an object in the position you are moving to
        if (obj instanceof Trophy) {
            // If it the object at the position is a trophy and we have the required treasure amount, we can set win to true
            if (treasureCount == numTreasures) {
                win = true;
            }
        // If it's a treasure, increment treasureCount
        } else if (obj instanceof Treasure) {
            treasureCount += 1;
        // Decrease lives if enemy touched
        } else if (obj instanceof Enemy) {
            numLives--;
        }
    }

    // check grid boundaries, check if pos is valid
    public boolean isValid(int size, String direction) {
        int tempX = getX();
        int tempY = getY();
        switch (direction.toLowerCase()) {
            case "w":
                // If it's w, we have to check if we were to move the amount w moves, then would we reach outside of the boundaries
                if (tempY + 1 < size && tempY + 1 >= 0) {
                    return true;
                }
                break;
            case "s":
            // If it's s, we have to check if we were to move the amount s moves, then would we reach outside of the boundaries
                if (tempY - 1 < size && tempY - 1 >= 0) {
                    return true;
                }
                break;
            case "d":
            // If it's d, we have to check if we were to move the amount d moves, then would we reach outside of the boundaries
                if (tempX + 1 < size && tempX + 1 >= 0) {
                    return true;
                }
                break;
            case "a":
            // If it's a, we have to check if we were to move the amount a moves, then would we reach outside of the boundaries
                if (tempX - 1 < size && tempX - 1 >= 0) {
                    return true;
                }
                break;
        }
        return false;
    }
}
