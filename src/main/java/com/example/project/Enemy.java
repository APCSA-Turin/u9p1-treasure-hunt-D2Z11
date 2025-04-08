package com.example.project;

//Enemy only need constructor and getCoords() getRowCol()
public class Enemy extends Sprite { //child  of Sprite
    // Setup x and y by using superclass (Sprite)
    public Enemy(int x, int y) {
        super(x, y);
    }

    // ## Functions to get coords and the row and column. It works by using the super function provided by sprite. ##
    
    public String getCoords(){ //returns "Enemy:"+coordinates
        return "Enemy:" + super.getCoords();
    }

    public String getRowCol(int size){ //return "Enemy:"+row col
        return "Enemy:" + super.getRowCol(size);
    }
}