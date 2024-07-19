// Name: Emma Nelson
// USC NetID: emmanels
// CSCI455 PA3
// Fall 2021

import java.util.Arrays;

/**
 * Tests the MineField Class and its methods
 */

public class MineFieldTester {
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

    public static void main(String[] arg){

        testConstructor();
        testReset();
        testPopulateMineField();
        testNumAdjacentMines();
        testInRange();
        testGetNumRowsandCol();
        testNumMines();
        testHasMine();
    }
    private static void testConstructor(){
        System.out.println("Testing Minefield Constructor...");
        MineField mySmallFixedField = new MineField(smallMineField);
        MineField myInitialField = new MineField(4, 4, 4);
        mySmallFixedField.toString();
        myInitialField.toString();
    }
    private static void testReset(){
        System.out.println("Test reset Mine....");
        MineField myInitialField = new MineField(4, 4, 4);
        myInitialField.populateMineField(0,0);
        myInitialField.toString();
        myInitialField.resetEmpty();
        myInitialField.toString();
    }
    private static void testPopulateMineField() {
        System.out.println("Test populated mine field....");
        MineField myInitialField = new MineField(4, 4, 4);
        myInitialField.toString();
        myInitialField.populateMineField(0,0);
        System.out.println("This mine field should have 4 mines, and non in the upper left corner");
        myInitialField.toString();
    }
    private static void testNumAdjacentMines() {
        System.out.println("Test number of adjacent mine field...");
        MineField mySmallFixedField = new MineField(smallMineField);
        mySmallFixedField.toString();
        MineField myEmptyMineField = new MineField(emptyMineField);
        System.out.println("Number of mines next to (0,0) in Small Mine Field [exp: 1]: " + mySmallFixedField.numAdjacentMines(0,0));
        System.out.println("Number of mines next to (1,1) in Small Mine Field[exp: 3]: " + mySmallFixedField.numAdjacentMines(1,1));
        System.out.println("Number of mines next to (1,1) in empty Mine Field[exp: 0]: " + myEmptyMineField.numAdjacentMines(0,0));
        System.out.println("Number of mines next to (3,3) in Small Mine Field[exp: 1]: " + mySmallFixedField.numAdjacentMines(3,3));
    }
    private static void testInRange() {
        System.out.println("Test in range...");
        MineField myMineField = new MineField(6,4,4);
        System.out.println("Checking if loc (4,4) is in 6 by 4 mine field [exp: false]: " + myMineField.inRange(4,4));
        System.out.println("Checking if loc (3,3) is in 6 by 4 mine field [exp: true]: " + myMineField.inRange(3,3));
        System.out.println("Checking if loc (5,4) is in 6 by 4 mine field [exp: true]: " + myMineField.inRange(5,3));
        System.out.println("Checking if loc (4,5) is in 6 by 4 mine field [exp: false]: " + myMineField.inRange(4,5));
        System.out.println("Checking if loc (5,-1) is in 6 by 4 mine field [exp: false]: " + myMineField.inRange(5,-1));
        System.out.println("Checking if loc (-1,4) is in 6 by 4 mine field [exp: false]: " + myMineField.inRange(-1,3));
    }
    private static void testGetNumRowsandCol() {
        System.out.println("Test get num rows and get num columns...");
        MineField myMineField = new MineField(6,4,4);
        System.out.println("Num of Rows in Mine Field [exp: 6]: " + myMineField.numRows());
        System.out.println("Num of Cols in Mine Field [exp: 4[: " + myMineField.numCols());
    }
    private static void testNumMines() {
        System.out.println("Test get number of mines...");
        MineField myMineField = new MineField(6,4,4);
        System.out.println("Number of Mines in Mine Field [exp: 4]: " + myMineField.numMines());
        myMineField.populateMineField(1,1);
        System.out.println("Number of Mines in Mine Field [exp: 4]: " + myMineField.numMines());

        MineField myEmptyMineField = new MineField(emptyMineField);
        System.out.println("Number of Mines in Empty Mine Field [exp: 0]: " + myEmptyMineField.numMines());

        MineField mySmallMinefield = new MineField(almostEmptyMineField);
        System.out.println("Number of Mines in small Mine Field [exp: 1]: " + mySmallMinefield.numMines());
        mySmallMinefield.resetEmpty();
        System.out.println("Number of Mines in small Mine Field [exp: 1]: " + mySmallMinefield.numMines());
    }
    private static void testHasMine(){
        System.out.println("Test has mine..");
        MineField mySmallFixedField = new MineField(smallMineField);
        mySmallFixedField.toString();
        System.out.println("Loc (0,0) has mine [exp: false]: " + mySmallFixedField.hasMine(0,0));
        System.out.println("Loc (1,0) has mine [exp: true]: " + mySmallFixedField.hasMine(1,0));
    }
}
