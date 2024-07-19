// Name: Emma Nelson
// USC NetID: emmanels
// CSCI455 PA3
// Fall 2021


import java.util.Arrays;
import java.util.Random;

/**
   MineField
      class with locations of mines for a game.
      This class is mutable, because we sometimes need to change it once it's created.
      mutators: populateMineField, resetEmpty
      includes convenience method to tell the number of mines adjacent to a location.
 */
public class MineField {
    /**
     Representation invariant:

     myMineField is a 2D Array of Boolean.
     numOfMines: int of number of mines as defined in constructor that requires row dimension, column dimension, and number of mines 
     inRange: true iff the given row and column is in the myMineField array
     */

   // instance variables here
   private boolean[][] myMineField;
   private int numOfMines;
   
   /**
      Create a minefield with same dimensions as the given array, and populate it with the mines in the array
      such that if mineData[row][col] is true, then hasMine(row,col) will be true and vice versa.  numMines() for
      this minefield will corresponds to the number of 'true' values in mineData.
      @param mineData  the data for the mines; must have at least one row and one col,
                       and must be rectangular (i.e., every row is the same length)
    */
   public MineField(boolean[][] mineData) {
       myMineField = mineData;
       numOfMines = 0; 
       for (int i = 0; i < this.numRows(); i++){
           for (int j = 0; j < this.numCols(); j++){
               if (myMineField[i][j]) {
                   numOfMines++;
               }
           }
       }
   }
   
   
   /**
      Create an empty minefield (i.e. no mines anywhere), that may later have numMines mines (once 
      populateMineField is called on this object).  Until populateMineField is called on such a MineField, 
      numMines() will not correspond to the number of mines currently in the MineField.
      @param numRows  number of rows this minefield will have, must be positive
      @param numCols  number of columns this minefield will have, must be positive
      @param numMines   number of mines this minefield will have,  once we populate it.
      PRE: numRows > 0 and numCols > 0 and 0 <= numMines < (1/3 of total number of field locations). 
    */
   public MineField(int numRows, int numCols, int numMines) {
       myMineField = new boolean[numRows][numCols];
       for (int i = 0; i < this.numRows(); i++){
           Arrays.fill(myMineField[i],false);
       }
       this.numOfMines = numMines;
   }
   

   /**
      Removes any current mines on the minefield, and puts numMines() mines in random locations on the minefield,
      ensuring that no mine is placed at (row, col).
      @param row the row of the location to avoid placing a mine
      @param col the column of the location to avoid placing a mine
      PRE: inRange(row, col) and numMines() < (1/3 * numRows() * numCols())
    */
   public void populateMineField(int row, int col) {
        assert this.inRange(row,col);
        this.resetEmpty();
        int minesPlacedThusFar = 0;
        int randomRow;
        int randomCol;
        while (minesPlacedThusFar < this.numMines()){
            Random genRowColNum = new Random();
            randomRow = genRowColNum.nextInt(this.numRows());
            randomCol = genRowColNum.nextInt(this.numCols());
            if (randomRow == row && randomCol == col){
                // Cannot put a mine where first click is
            }
            else if (this.hasMine(randomRow,randomCol)) {
                // Cannot put a mine where this is already one
            }
            else {
                myMineField[randomRow][randomCol] = true;
                minesPlacedThusFar ++;
            }
        }
   }
   
   
   /**
      Reset the minefield to all empty squares.  This does not affect numMines(), numRows() or numCols()
      Thus, after this call, the actual number of mines in the minefield does not match numMines().  
      Note: This is the state a minefield created with the three-arg constructor is in 
         at the beginning of a game.
    */
   public void resetEmpty() {
       for (int i = 0; i < this.numRows(); i++) {
           Arrays.fill(myMineField[i],false);
       }
   }

   
  /**
     Returns the number of mines adjacent to the specified mine location (not counting a possible 
     mine at (row, col) itself).
     Diagonals are also considered adjacent, so the return value will be in the range [0,8]
     @param row  row of the location to check
     @param col  column of the location to check
     @return  the number of mines adjacent to the square at (row, col)
     PRE: inRange(row, col)
   */
   public int numAdjacentMines(int row, int col) {
      assert this.inRange(row,col);
      int countOfMinesAdjacent = 0;

      // Limits the ranges one can look in, it has to be adjacent and on the board
      int lowerRowLimit = Math.max(0, row - 1);
      int upperRowLimit = Math.min(this.numRows()-1, row + 1);
      int lowerColLimit = Math.max(0, col - 1);
      int upperColLimit = Math.min(this.numCols()-1, col + 1);
      for (int i = lowerRowLimit; i <= upperRowLimit; i ++){
          for (int j = lowerColLimit; j <= upperColLimit; j ++){
              if (i == row && j == col) {
                  // Don't count current location
              }
              else if (myMineField[i][j]) {
                  countOfMinesAdjacent ++ ;
              }
          }
      }
      return countOfMinesAdjacent;
   }

   
   /**
      Returns true iff (row,col) is a valid field location.  Row numbers and column numbers
      start from 0.
      @param row  row of the location to consider
      @param col  column of the location to consider
      @return whether (row, col) is a valid field location
   */
   public boolean inRange(int row, int col) {
       if (row < this.numRows() && row >= 0 && col < this.numCols() && col >= 0) {
           return true;
       }
      return false;       // DUMMY CODE so skeleton compiles
   }
   
   
   /**
      Returns the number of rows in the field.
      @return number of rows in the field
   */  
   public int numRows() {
      return myMineField.length;       // DUMMY CODE so skeleton compiles
   }
   
   
   /**
      Returns the number of columns in the field.
      @return number of columns in the field
   */    
   public int numCols() {
      return myMineField[0].length;       // DUMMY CODE so skeleton compiles
   }
   
   
   /**
      Returns whether there is a mine in this square
      @param row  row of the location to check
      @param col  column of the location to check
      @return whether there is a mine in this square
      PRE: inRange(row, col)   
   */    
   public boolean hasMine(int row, int col) {
      return myMineField[row][col];
   }
   
   
   /**
      Returns the number of mines you can have in this minefield.  For mines created with the 3-arg constructor,
      some of the time this value does not match the actual number of mines currently on the field.  See doc for that
      constructor, resetEmpty, and populateMineField for more details.
    * @return
    */
   public int numMines() {
      return this.numOfMines;       // DUMMY CODE so skeleton compiles
   }
   public String toString() {
       for (int i = 0; i < this.numRows(); i++){
           System.out.println( Arrays.toString(myMineField[i]));
       }
       return null;
   }
}

