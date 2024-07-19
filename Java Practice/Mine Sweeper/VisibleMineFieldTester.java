// Name: Emma Nelson
// USC NetID: emmanels
// CSCI455 PA3
// Fall 2021

import javax.swing.*;

/**
 * Tests the visibleminefield class and its methods
 */
public class VisibleMineFieldTester {

    // You can  modify this program so it uses a different one of the hardcoded mines below,
    // or add a new one, for testing purposes:

    private static boolean[][] smallMineField =
            {{false, false, false, false},
                    {true, false, false, false},
                    {false, true, true, false},
                    {false, true, false, true}};

    private static boolean[][] emptyMineField =
            {{false, false, false, false},
                    {false, false, false, false},
                    {false, false, false, false},
                    {false, false, false, false}};

    private static boolean[][] almostEmptyMineField =
            {{false, false, false, false},
                    {false, false, false, false},
                    {false, false, false, false},
                    {false, true, false, false}};

    public static void main(String[] args) {
        testConstructVisible();
        testResetGameDisplay();
        testGetMineField();
        testgetStatus();
        testNumMinesLeft();
        testCycleGuess();
        testUncover();
        testIsGameOver();
        testUncovered();
    }
    private static void testConstructVisible() {
        System.out.println("Testing visible constructor...");
        MineField nySmallMineField = new MineField(smallMineField);
        VisibleField myVisibleMineField = new VisibleField(nySmallMineField);
        myVisibleMineField.toString();
    }
    private static void testResetGameDisplay() {
        MineField myRandomMinefield = new MineField(6,6, 12);
        myRandomMinefield.populateMineField(0,0);
        VisibleField myVisibleMineField = new VisibleField(myRandomMinefield);
        myVisibleMineField.toString();
        myVisibleMineField.resetGameDisplay();
        myVisibleMineField.toString();
    }
    private static void testGetMineField() {
        System.out.println("Testing get minefield...");
        MineField myRandomMinefield = new MineField(6,6, 12);
        myRandomMinefield.populateMineField(0,0);
        VisibleField myVisibleMineField = new VisibleField(myRandomMinefield);
        myVisibleMineField.getMineField().resetEmpty();
        System.out.println("This mine field should now be empty");
        myRandomMinefield.toString();
    }
    private static void testgetStatus() {
        System.out.println("Testing get status");
        MineField nySmallMineField = new MineField(smallMineField);
        VisibleField myVisibleMineField = new VisibleField(nySmallMineField);
        myVisibleMineField.uncover(0,0);
        myVisibleMineField.uncover(0,2);
        myVisibleMineField.cycleGuess(3,3);
        myVisibleMineField.cycleGuess(2,3);
        myVisibleMineField.cycleGuess(2,3);
        myVisibleMineField.toString();
        System.out.println("Status at (0,0) [exp: 1]: " + myVisibleMineField.getStatus(0,0));
        System.out.println("Status at (2,3) [exp: -3]: " + myVisibleMineField.getStatus(2,3));
        System.out.println("Status at (3,3) [exp: -2]: " + myVisibleMineField.getStatus(3,3));
        System.out.println("Status at (1,1) [exp: 3]: " + myVisibleMineField.getStatus(1,1));
        myVisibleMineField.uncover(1,0);
        System.out.println("Status at (3,3) [exp: -2]: " + myVisibleMineField.getStatus(3,3));
        System.out.println("Status at (1,0) [exp: 11]: " + myVisibleMineField.getStatus(1,0));
        System.out.println("Status at (3,1) [exp: 9]: " + myVisibleMineField.getStatus(3,1));
    }
    private static void testNumMinesLeft() {
        System.out.println("Testing minesLeft...");
        MineField nySmallMineField = new MineField(6,6, 4);
        VisibleField myVisibleMineField = new VisibleField(nySmallMineField);
        myVisibleMineField.cycleGuess(0,0);
        System.out.println("Number Of Mines Left [exp: 3]: " + myVisibleMineField.numMinesLeft());
        myVisibleMineField.cycleGuess(0,1);
        System.out.println("Number Of Mines Left [exp: 2]: " + myVisibleMineField.numMinesLeft());
        myVisibleMineField.cycleGuess(0,3);
        System.out.println("Number Of Mines Left [exp: 1]: " + myVisibleMineField.numMinesLeft());
        myVisibleMineField.cycleGuess(0,4);
        System.out.println("Number Of Mines Left [exp: 0]: " + myVisibleMineField.numMinesLeft());
        myVisibleMineField.cycleGuess(0,5);
        System.out.println("Number Of Mines Left [exp: -1]: " + myVisibleMineField.numMinesLeft());
        myVisibleMineField.cycleGuess(0,5);
        myVisibleMineField.cycleGuess(0,3);
        System.out.println("Number Of Mines Left [exp: 1]: " + myVisibleMineField.numMinesLeft());
    }
    private static void testCycleGuess() {
        System.out.println("Testing cycleguess...");
        MineField nySmallMineField = new MineField(smallMineField);
        VisibleField myVisibleMineField = new VisibleField(nySmallMineField);
        myVisibleMineField.toString();
        myVisibleMineField.cycleGuess(0,0);
        System.out.println("Status of guess for loc (0,0) [exp: -2]: " + myVisibleMineField.getStatus(0,0));
        myVisibleMineField.cycleGuess(0,0);
        System.out.println("Status of guess for loc (0,0) [exp: -3]: " + myVisibleMineField.getStatus(0,0));
        myVisibleMineField.cycleGuess(0,0);
        System.out.println("Status of guess for loc (0,0) [exp: -1]: " + myVisibleMineField.getStatus(0,0));
    }
    private static void testUncover() {
        System.out.println("Testing uncover...");
        MineField nySmallMineField = new MineField(smallMineField);
        VisibleField myVisibleMineField = new VisibleField(nySmallMineField);
        myVisibleMineField.toString();
        myVisibleMineField.uncover(0,0);
        myVisibleMineField.toString();
        myVisibleMineField.uncover(0,2);
        myVisibleMineField.toString();
        myVisibleMineField.cycleGuess(3,2);
        myVisibleMineField.uncover(1,0);
        myVisibleMineField.toString();
        MineField myEmptyMineField = new MineField(emptyMineField);
        VisibleField myVisibleEmptyField = new VisibleField(myEmptyMineField);
        myVisibleEmptyField.toString();
        myVisibleEmptyField.uncover(0,0);
        myVisibleEmptyField.toString();
    }
    private static void testIsGameOver() {
        System.out.println("Testing isGameOver...");
        MineField nySmallMineField = new MineField(smallMineField);
        VisibleField myVisibleMineField = new VisibleField(nySmallMineField);
        System.out.println("Is game over [exp: false]: " + myVisibleMineField.isGameOver());
        myVisibleMineField.uncover(0,0);
        System.out.println("Is game over [exp: false]: " + myVisibleMineField.isGameOver());
        myVisibleMineField.uncover(0,2);
        System.out.println("Is game over [exp: false]: " + myVisibleMineField.isGameOver());
        myVisibleMineField.uncover(1,0);
        System.out.println("Is game over [exp: true]: " + myVisibleMineField.isGameOver());
        MineField myEmptyMineField = new MineField(emptyMineField);
        VisibleField myVisibleEmptyField = new VisibleField(myEmptyMineField);
        System.out.println("Is game over [exp: false]: " + myVisibleEmptyField.isGameOver());
        myVisibleEmptyField.uncover(0,0);
        System.out.println("Is game over [exp: true]: " + myVisibleEmptyField.isGameOver());
    }
    private static void testUncovered() {
        System.out.println("Testing isUncovered...");
        MineField nySmallMineField = new MineField(smallMineField);
        VisibleField myVisibleMineField = new VisibleField(nySmallMineField);
        System.out.println("Loc (0,0) is uncovered [exp: false]: " + myVisibleMineField.isUncovered(0,0));
        myVisibleMineField.uncover(0,0);
        System.out.println("Loc (0,0) is uncovered [exp: true]: " + myVisibleMineField.isUncovered(0,0));
        myVisibleMineField.uncover(0,2);
        myVisibleMineField.uncover(1,0);
        MineField myEmptyMineField = new MineField(emptyMineField);

    }
}
