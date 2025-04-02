package com.example.project;

//DO NOT DELETE ANY METHODS BELOW
public class Grid {
    private Sprite[][] grid;
    private int size;

    public Grid(int size) { // initialize and create a grid with all DOT objects
        grid = new Sprite[size][size];
        this.size = size;
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                grid[r][c] = new Dot(r, c);
            }
        }
    }

    public Sprite[][] getGrid() {
        return grid;
    }

    public void placeSprite(Sprite s) { // place sprite in new spot
        // Convert from 2D Array to Carterian plane by subtracting y coordinate
        // System.out.println(size + " - " + s.getY());
        grid[(size - 1) - s.getY()][s.getX()] = s;
    }

    public void placeSprite(Sprite s, String direction) { // place sprite in a new spot based on direction
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

    public void display() { // print out the current grid to the screen
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