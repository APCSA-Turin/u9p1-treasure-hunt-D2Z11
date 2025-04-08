package com.example.project;

//DO NOT DELETE ANY METHODS BELOW
public class Grid {
    private Sprite[][] grid;
    private int size;

    public Grid(int size) { // initialize and create a grid with all DOT objects
        grid = new Sprite[size][size];
        this.size = size;
        // Insert a dot in every position of the grid
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                grid[r][c] = new Dot(r, c);
            }
        }
    }

    // ## GETTERS AND SETTERS ##
    public Sprite[][] getGrid() {
        return grid;
    }

    // Place sprite in new spot
    public void placeSprite(Sprite s) {
        // Convert from Carterian plane to 2D array coordinates by subtracting y coordinate and set that position to the sprite provided
        grid[(size - 1) - s.getY()][s.getX()] = s;
    }

    // Place the sprite based on direction it will place an empty dot in the last position and place the sprite in the new position
    public void placeSprite(Sprite s, String direction) {
        placeSprite(s);
        switch (direction.toLowerCase()) {
            case "w":
                placeSprite(new Dot(s.getX(), s.getY() - 1));
                break;
            case "s":
                placeSprite(new Dot(s.getX(), s.getY() + 1));
                break;
            case "d":
                placeSprite(new Dot(s.getX() - 1, s.getY()));
                break;
            case "a":
                placeSprite(new Dot(s.getX() + 1, s.getY()));
                break;
        }
    }

    // print out the current grid to the screen
    public void display() {
        // Loop through entire grid and check each object type and put corresponding emoji
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                if (grid[r][c] instanceof Trophy) {
                    System.out.print("🏆");
                } else if (grid[r][c] instanceof Treasure) {
                    System.out.print("💰");
                } else if (grid[r][c] instanceof Player) {
                    System.out.print("😎");
                } else if (grid[r][c] instanceof Dot) {
                    System.out.print("⬜");
                } else if (grid[r][c] instanceof Enemy) {
                    System.out.print("🦂");
                } else {
                    // This is to check if something is not working here
                    System.out.print("⁉️");
                }
            }
            System.out.println();
        }
    }

    public void gameover() { // use this method to display a loss
    }

    public void win() { // use this method to display a win
    }

}