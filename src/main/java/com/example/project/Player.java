package com.example.project;

//DO NOT DELETE ANY METHODS BELOW
public class Player extends Sprite {
    private int treasureCount;
    private int numLives;
    private boolean win;

    public Player(int x, int y) { // set treasureCount = 0 and numLives = 2
        super(x, y);
        numLives = 2;
    }

    public int getTreasureCount() {
        return treasureCount;
    }

    public int getLives() {
        return numLives;
    }

    public boolean getWin() {
        return win;
    }

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
        switch (direction.toLowerCase()) {
            case "w":
                super.setY(getY() + 1);
                break;
            case "s":
                super.setY(getY() - 1);
                break;
            case "d":
                super.setX(getX() + 1);
                break;
            case "a":
                super.setX(getX() - 1);
                break;
        }
    }

    public void interact(int size, String direction, int numTreasures, Object obj) { // interact with an object in the
                                                                                     // position you are moving to
        // numTreasures is the total treasures at the beginning of the game
        if (obj instanceof Trophy) {
            if (treasureCount == numTreasures) {
                win = true;
            }
        } else if (obj instanceof Treasure) {
            treasureCount += 1;
        } else if (obj instanceof Enemy) {
            numLives--;
        }
    }

    public boolean isValid(int size, String direction) { // check grid boundaries
        int tempX = getX();
        int tempY = getY();
        switch (direction.toLowerCase()) {
            case "w":
                if (tempY + 1 < size && tempY + 1 >= 0) {
                    return true;
                }
                break;
            case "s":
                if (tempY - 1 < size && tempY - 1 >= 0) {
                    return true;
                }
                break;
            case "d":
                if (tempX + 1 < size && tempX + 1 >= 0) {
                    return true;
                }
                break;
            case "a":
                if (tempX - 1 < size && tempX - 1 >= 0) {
                    return true;
                }
                break;
        }
        return false;
    }

}
