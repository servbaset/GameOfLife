package ir.raikadev.run;


import ir.raikadev.game.GameOfLife;

import java.util.Scanner;

class Run {
    public static void main( String[] args ) {
        try {
            int worldWith = Integer.parseInt( args[0] );
            int worldHeight = Integer.parseInt( args[1] );
            GameOfLife gameOfLife = new GameOfLife(worldWith,worldHeight);
            System.out.println("press Enter Key for start  Game Of Life");
            new Scanner( System.in ).nextLine();
            gameOfLife.start();
            new Scanner( System.in ).nextLine();
            gameOfLife.stop();
        } catch ( NumberFormatException |ArrayIndexOutOfBoundsException e ) {
            System.out.println("please enter With and Height for your world");
        }
    }
}
