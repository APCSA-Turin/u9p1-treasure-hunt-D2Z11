package com.example.project;

import java.util.Scanner;

public class Game {
    private Grid grid;
    private Player player;
    private Enemy[] enemies;
    private Treasure[] treasures;
    private Trophy trophy;
    private int size;

    public Game(int size) { // the constructor should call initialize() and play()
        this.size = size;
        initialize();
        play();
    }

    public static void clearScreen() { // do not modify
        try {
            final String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                // Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Unix-based (Linux, macOS)
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() { // write your game logic here
        Scanner scanner = new Scanner(System.in);

        while (true) {
            grid.display();
            System.out.println("Treasure count: " + player.getTreasureCount());
            System.out.println("Lives: " + player.getLives());
            String in = scanner.next();
            // System.out.println(player.isValid(size, in) ? "Valid" : "");
            if (player.isValid(size, in)) {
                // System.out.println("Old: " + player.getCoords());
                player.move(in);
                player.interact(size, in, treasures.length, grid.getGrid()[((size - 1) - player.getY())][player.getX()]);
                // System.out.println("New: " + player.getCoords());
                grid.placeSprite(player);
            }

            try {
                Thread.sleep(100); // Wait for 1/10 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clearScreen(); // Clear the screen at the beggining of the while loop
        }
    }

    // Generates from min to max not including max
    private int randInt(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public void initialize() {
        // to test, create a player, trophy, grid, treasure, and enemies. Then call
        // placeSprite() to put them on the grid

        // Initialize game elements
        grid = new Grid(size);
        trophy = new Trophy(randInt(0, size), randInt(0, size));
        player = new Player(randInt(0, size), randInt(0, size));
        treasures = new Treasure[1];
        enemies = new Enemy[4];

        // Add trophy, player, treasures and enemies
        grid.placeSprite(trophy);
        System.out.println("Coords + " + player.getCoords());
        grid.placeSprite(player);
        for (Treasure t : treasures) {
            t = new Treasure(randInt(0, size), randInt(0, size));
            grid.placeSprite(t);
        }
        for (Enemy e : enemies) {
            e = new Enemy(randInt(0, size), randInt(0, size));
            grid.placeSprite(e);
        }
    }
}