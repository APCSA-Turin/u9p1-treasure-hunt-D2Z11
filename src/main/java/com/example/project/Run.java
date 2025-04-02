package com.example.project;

public class Run {
    public static void main(String[] args) {
        Player player = new Player(9, 0);
        Grid grid = new Grid(11);
        player.move("d"); //move right
        grid.placeSprite(player, "d");

        System.out.println(player.getCoords() + (grid.getGrid()[9][1] instanceof Player) + "Player should be at [9][1] after moving right");

        // Verify the previous position is now a Dot
        System.out.println((grid.getGrid()[9][0] instanceof Dot) + "Previous position [9][0] should be a Dot");
    }
}
