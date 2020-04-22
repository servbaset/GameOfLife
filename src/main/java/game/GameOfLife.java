package main.java.game;

import main.java.cell.Cell;
import main.java.rule.Rule;

import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.Executors;

public class GameOfLife {

    private final int worldWith;
    private final int worldHeight;

    private final Rule rule = new Rule();
    private Cell[][] world;

    private boolean isResuming;

    public GameOfLife( int worldWith, int worldHeight ) {
        this.worldWith = worldWith;
        this.worldHeight = worldHeight;
    }

    private void generation() {

        world = new Cell[worldHeight][worldWith];
        Random random = new SecureRandom();

        for ( int row = 0; row < world.length; row++ ) {
            for ( int column = 0; column < world[row].length; column++ ) {
                Cell.CellStatus cellStatus = random.nextInt( 100 ) < 20 ? Cell.CellStatus.ALIVE : Cell.CellStatus.DEAD;
                world[row][column] = new Cell( row, column, cellStatus );
            }
        }
    }

    private void nextGeneration() {

        Cell[][] nextWorld = new Cell[worldHeight][worldWith];

        for ( int row = 0; row < worldHeight; row++ ) {
            for ( int column = 0; column < worldWith; column++ ) {
                Cell cell = nextWorld[row][column] = new Cell( row, column, Cell.CellStatus.DEAD );
                rule.detectCellStatus( cell, world );
            }
        }
        world = nextWorld;
    }

    private void showWorld() {
        for ( Cell[] cells : world ) {
            for ( Cell cell : cells )
                System.out.print( cell );
            System.out.println();
        }
        System.out.println( "press Enter key for stop game" );
    }

    private void clearScreen() {
        System.out.print( "\033[H\033[2J" );
        System.out.flush();
    }

    public void start() {
        isResuming = true;

        Executors
                .newSingleThreadExecutor()
                .execute( () -> {
                    generation();
                    while ( isResuming ) {
                        clearScreen();
                        showWorld();
                        nextGeneration();
                        try {
                            Thread.sleep( 150 );
                        } catch ( InterruptedException e ) {
                            e.printStackTrace();
                        }
                    }

                } );

    }

    public void stop() {
        isResuming = false;
    }
}
