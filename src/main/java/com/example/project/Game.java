package com.example.project;

import java.util.Scanner;

public class Game {
    // Declare private variables to keep track of game state
    private Grid grid;
    private Player player;
    private Enemy[] enemies;
    private Treasure[] treasures;
    private Trophy trophy;
    private int size;

    public Game(int size) { // the constructor should call initialize() and play()
        this.size = size;
        // Immediately intialize and play the game
        initialize();
        play();
    }

    // Clear screen function
    public static void clearScreen() { // do not modify
        try {
            final String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                // Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Unix-based (Linux, macOS)
                // System.out.print("\033[H\033[2J");
                // System.out.flush();
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Check if a position is within the boundaries
    public boolean isValid(int x, int y) {
        // Unlike the other isValid function in Sprite, it will check a position and not check what would happen if the player moved a certain direction
        if ((x < 0 || x > size + 1) || (y < 0 || y > size + 1)) {
            return false;
        }
        return true;
    }

    // Check if a position has a empty position, or dot
    public boolean isEmptyPos(int x, int y) {
        if (getGridPos(x, y) instanceof Dot) {
            return true;
        }
        return false;
    }

    // Get a position in the grid by converting from Cartesian plane to 2D array format
    public Sprite getGridPos(int x, int y) {
        return grid.getGrid()[((size - 1) - y)][x];
    }

    // Move enemies toward the player
    public boolean moveEnemies() {
        boolean touchedPlayer = false;
        for (int i = 0; i < enemies.length; i++) {
            Enemy e = enemies[i];
            if (e == null) {
                continue;
            }
            int[] magnitudes = e.getMagnitudeFrom(player);
            // Since magnitudes returns which direction the player is (in terms of between 1
            // and -1), you can just add the magnitude to the position
            // System.out.println(magnitudes[0] + " " + magnitudes[1]);
            int newX = e.getX() + magnitudes[0];
            int newY = e.getY() + magnitudes[1];

            // OLD LOGIC, NO LONGER NEEDED: If enemy is touching the player
            // if (getGridPos(e.getX(), e.getY()) instanceof Player) {
            //     // Reverse magnitudes to give the player a chance to run
            //     newX = e.getX() - magnitudes[0];
            //     newY = e.getY() - magnitudes[1];
            // }

            // If the position to move to has an enemy or object there
            if (isValid(newX, newY) && !isEmptyPos(newX, newY)) {
                // Then, just go back that magnitude to move away from the enemy regardless if it's toward the player
                newX = e.getX() - magnitudes[0];
                // If the new position is valid and not empty, then it seems the enemy can only go backwards on the Y (and if this fails then the enemy will just not move...)
                if (isValid(newX, newY) && !isEmptyPos(newX, newY)) {
                    // Move backwards to move from enemy or object
                    newY = e.getY() - magnitudes[1];
                }
            }

            // Just to ensure that any new position the enemy takes will be valid regardless
            if (!isValid(newX, newY) || !isEmptyPos(newX, newY)) {
                continue;
            }

            // If the move to position is not empty
            // if (isValid(newX, newY) && !isEmptyPos(newX, newY)) {
            //     // Then, check if there is no player there
            //     if (getGridPos(newX, newY) instanceof Player) {
            //         // If there is a player, we should remove the enemy and set touchedPlayer to true

            //         // Remove enemy from grid
            //         Dot empty = new Dot(e.getX(), e.getY());
            //         grid.placeSprite(empty);
            //         // Remove enemy from array
            //         enemies[i] = null;

            //         // Set touchedPlayer
            //         touchedPlayer = true;
            //         continue;
            //     } else {
            //         // We are at a non empty position so anyways we can skip to the next iteration
            //         continue;
            //     }
            // }

            // Move enemy to new position and replace old position with Dot
            Dot empty = new Dot(e.getX(), e.getY());
            grid.placeSprite(empty);
            e.setX(newX);
            e.setY(newY);
            grid.placeSprite(e);

            // Quickly update for aesthetics
            grid.display();
            try {
                Thread.sleep(100); // Wait for 1/10 seconds
            } catch (InterruptedException err) {
                err.printStackTrace();
            }
            clearScreen();
        }
        return touchedPlayer;
    }

    // Method to handle game logic
    public void play() {
        // Create scanner
        Scanner scanner = new Scanner(System.in);

        while (true) {
            while (true) {
                // Display the current game state
                grid.display();
                // Then, display data about the player
                System.out.println("Treasure count: " + player.getTreasureCount());
                System.out.println("Lives: " + player.getLives());
                // Get the input for movement
                String in = scanner.next();
                // Ensure that the player position will be valid if it moves to the specified
                // direction
                if (player.isValid(size, in)) {
                    // Since the direction is valid, run move to CHANGE the coordinates
                    player.move(in);
                    // Get the sprite at the position the player now is in
                    Sprite newPosition = getGridPos(player.getX(), player.getY());
                    // Now, interact with that sprite (check what type of sprite it is and what
                    // player can do) BEFORE placing the sprite and overwriting the position
                    player.interact(size, in, treasures.length, newPosition);
                    // Finally, place the sprite
                    grid.placeSprite(player, in);
                }
                // moveEnemies returns true if the enemy touches the player when moving. If it is true, then we should decrease the player's lives
                if (moveEnemies() == true) {
                    player.decreaseLives();
                }
                // If the lives is less than or zero then the loop can be ended and game over should be shown
                if (player.getLives() <= 0) {
                    break;
                }

                try {
                    Thread.sleep(100); // Wait for 1/10 seconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                clearScreen(); // Clear the screen at the beggining of the while loop
            }
            // END LOGIC
            System.out.println("Game over! Enter y to play again!");
            // Exit function if y not entered
            if (!scanner.next().equals("y")) {
                return;
            }
            // Initialize grid and enemies again
            initialize();
            // Clear screen again
            clearScreen();
        }
    }

    // Generates from min to max not including max
    private int randInt(int min, int max) {
        // We learned this before - you get max - min, multiply by random and then add min and convert to int
        return (int) ((Math.random() * (max - min)) + min);
    }

    // Initalize grid/game
    public void initialize() {
        // Initialize game elements
        grid = new Grid(size);
        trophy = new Trophy(randInt(0, size), randInt(0, size));
        player = new Player(randInt(0, size), randInt(0, size));
        treasures = new Treasure[1];
        enemies = new Enemy[4];

        // Add trophy, player, treasures and enemies
        grid.placeSprite(trophy);
        grid.placeSprite(player);
        for (int i = 0; i < treasures.length; i++) {
            // Add treasures in random positions
            treasures[i] = new Treasure(randInt(0, size), randInt(0, size));
            grid.placeSprite(treasures[i]);
        }
        for (int i = 0; i < enemies.length; i++) {
            // Add enemies in random positions
            enemies[i] = new Enemy(randInt(0, size), randInt(0, size));
            grid.placeSprite(enemies[i]);
        }
    }
}