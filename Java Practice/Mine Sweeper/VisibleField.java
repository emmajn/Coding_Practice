// Name: Emma Nelson
// USC NetID: emmanels
// CSCI455 PA3
// Fall 2021


import java.util.Arrays;

/**
  VisibleField class
  This is the data that's being displayed at any one point in the game (i.e., visible field, because it's what the
  user can see about the minefield). Client can call getStatus(row, col) for any square.
  It actually has data about the whole current state of the game, including  
  the underlying mine field (getMineField()).  Other accessors related to game status: numMinesLeft(), isGameOver().
  It also has mutators related to actions the player could do (resetGameDisplay(), cycleGuess(), uncover()),
  and changes the game state accordingly.
  
  It, along with the MineField (accessible in mineField instance variable), forms
  the Model for the game application, whereas GameBoardPanel is the View and Controller, in the MVC design pattern.
  It contains the MineField that it's partially displaying.  That MineField can be accessed (or modified) from 
  outside this class via the getMineField accessor.  
 */
public class VisibleField {
   // ----------------------------------------------------------   
   // The following public constants (plus numbers mentioned in comments below) are the possible states of one
   // location (a "square") in the visible field (all are values that can be returned by public method 
   // getStatus(row, col)).
   
   // The following are the covered states (all negative values):
   public static final int COVERED = -1;   // initial value of all squares
   public static final int MINE_GUESS = -2;
   public static final int QUESTION = -3;

   // The following are the uncovered states (all non-negative values):
   
   // values in the range [0,8] corresponds to number of mines adjacent to this square
   
   public static final int MINE = 9;      // this loc is a mine that hasn't been guessed already (end of losing game)
   public static final int INCORRECT_GUESS = 10;  // is displayed a specific way at the end of losing game
   public static final int EXPLODED_MINE = 11;   // the one you uncovered by mistake (that caused you to lose)
   // ----------------------------------------------------------   
  
   // <put instance variables here>

   private MineField myMineField; // underlying minefield
   private int[][] myVisibleStatus; // 2D int array of mine display states

   /**
      Create a visible field that has the given underlying mineField.
      The initial state will have all the mines covered up, no mines guessed, and the game
      not over.
      @param mineField  the minefield to use for this VisibleField
    */
   public VisibleField(MineField mineField) {
      this.myMineField = mineField;
      myVisibleStatus = new int[myMineField.numRows()][myMineField.numCols()];
      for (int i = 0; i < mineField.numRows(); i++){
         Arrays.fill(myVisibleStatus[i], COVERED);
      }
   }
   
   
   /**
      Reset the object to its initial state (see constructor comments), using the same underlying
      MineField. 
   */     
   public void resetGameDisplay() {
      for (int i = 0; i < myMineField.numRows(); i++){
         Arrays.fill(myVisibleStatus[i], COVERED);
      }
   }
  
   
   /**
      Returns a reference to the mineField that this VisibleField "covers"
      @return the minefield
    */
   public MineField getMineField() {
      return myMineField;
   }
   
   
   /**
      Returns the visible status of the square indicated.
      @param row  row of the square
      @param col  col of the square
      @return the status of the square at location (row, col).  See the public constants at the beginning of the class
      for the possible values that may be returned, and their meanings.
      PRE: getMineField().inRange(row, col)
    */
   public int getStatus(int row, int col) {
      return myVisibleStatus[row][col];
   }

   
   /**
      Returns the number of mines left to guess.  This has nothing to do with whether the mines guessed are correct
      or not.  Just gives the user an indication of how many more mines the user might want to guess.  This value can
      be negative, if they have guessed more than the number of mines in the minefield.     
      @return the number of mines left to guess.
    */
   public int numMinesLeft() {
      int minesGuessedSoFar = 0;
      for (int i = 0; i < myVisibleStatus.length; i++){
         for(int j = 0; j < myVisibleStatus[0].length; j++){
            if (this.getStatus(i,j)== MINE_GUESS){
               minesGuessedSoFar ++;
            }
         }
      }
      return myMineField.numMines()-minesGuessedSoFar;       // DUMMY CODE so skeleton compiles
   }
 
   
   /**
      Cycles through covered states for a square, updating number of guesses as necessary.  Call on a COVERED square
      changes its status to MINE_GUESS; call on a MINE_GUESS square changes it to QUESTION;  call on a QUESTION square
      changes it to COVERED again; call on an uncovered square has no effect.  
      @param row  row of the square
      @param col  col of the square
      PRE: getMineField().inRange(row, col)
    */
   public void cycleGuess(int row, int col) {
      if (this.getStatus(row,col) == COVERED){
         myVisibleStatus[row][col] = MINE_GUESS;
      }
      else if (this.getStatus(row,col) == MINE_GUESS){
         myVisibleStatus[row][col] = QUESTION;
      }
      else if (this.getStatus(row,col) == QUESTION) {
         myVisibleStatus[row][col] = COVERED;
      }
      // Update number of mines left since guess status has changed
      this.numMinesLeft();
   }

   
   /**
      Uncovers this square and returns false iff you uncover a mine here.
      If the square wasn't a mine or adjacent to a mine it also uncovers all the squares in 
      the neighboring area that are also not next to any mines, possibly uncovering a large region.
      Any mine-adjacent squares you reach will also be uncovered, and form 
      (possibly along with parts of the edge of the whole field) the boundary of this region.
      Does not uncover, or keep searching through, squares that have the status MINE_GUESS. 
      Note: this action may cause the game to end: either in a win (opened all the non-mine squares)
      or a loss (opened a mine).
      @param row  of the square
      @param col  of the square
      @return false   iff you uncover a mine at (row, col)
      PRE: getMineField().inRange(row, col)
    */
   public boolean uncover(int row, int col) {
      if (!myMineField.inRange(row,col)) {
         // Do nothing if this mine is out of range
         return true;
      }
      else if (myMineField.hasMine(row,col)){
         // Case where user clicks on a Mine
         myVisibleStatus[row][col] = EXPLODED_MINE;
         for (int i = 0; i < myMineField.numRows(); i++){
            for (int j = 0; j < myMineField.numCols(); j++){
               if (myMineField.hasMine(i,j) && myVisibleStatus[i][j] == COVERED){
                  // finds all the mine locations
                  myVisibleStatus[i][j] = MINE;
               }
               if (myMineField.hasMine(i,j) && myVisibleStatus[i][j] == QUESTION){
                  // finds all the mine locations
                  myVisibleStatus[i][j] = MINE;
               }
               if (!myMineField.hasMine(i,j) && myVisibleStatus[i][j] == MINE_GUESS){
                  // finds all the wrong guesses of mine locations
                  myVisibleStatus[i][j] = INCORRECT_GUESS;
               }
            }
         }
         return false;
      }
      else {
         // Case where user does not hit a mine
         floodUncover(row,col);
         return true;
      }
   }
 
   
   /**
      Returns whether the game is over.
      (Note: This is not a mutator.)
      @return whether game over: Boolean: True = game over, False = game continues
    */
   public boolean isGameOver() {
      // Case where you've uncovered a mine
      // When a player successfully opens all the non-mine locations
      boolean placesWithoutMinesUncovered = true;
      for (int i = 0; i < myVisibleStatus.length; i++){
         for(int j = 0; j < myVisibleStatus[0].length; j++){
            if (this.getStatus(i,j) == EXPLODED_MINE){
               // Case you uncovered a mine
               return true;
            }
            if (!myMineField.hasMine(i,j) && this.getStatus(i,j) < 0){
               // There are some non-mine places that have yet to be uncovered
               placesWithoutMinesUncovered = false;
            }
         }
      }
      // Case where you have everything uncovered except the mines
      return placesWithoutMinesUncovered;
   }
 
   
   /**
      Returns whether this square has been uncovered.  (i.e., is in any one of the uncovered states, 
      vs. any one of the covered states).
      @param row of the square
      @param col of the square
      @return whether the square is uncovered
      PRE: getMineField().inRange(row, col)
    */
   public boolean isUncovered(int row, int col) {
      // All uncovered states are positive
      if (this.getStatus(row,col) >= 0) {
         return true;
      }
      return false;
   }

   /**
    * Outputs string representation of the minefield and the which squares are visible/covered/mine guesses
    * @return Null
    */
   public String toString() {
      for (int i = 0; i < myMineField.numRows(); i++){
         System.out.println(Arrays.toString(myVisibleStatus[i]));
      }
      myMineField.toString();
      return null;
   }

   /**
    * Mutator. Recursive method for automatic uncovering of empty squares.
    * @param row of the square
    * @param col of the square
    * PRE: getMineField().inRange(row, col)
    */
   private void floodUncover(int row, int col){
      /**
       *  If square not in minefield grid
       *    return
       *  If square is mine
       *    return
       *  If square is a mine guess
       *    return
       *  If square is adjacent to a mine
       *    mark how many mines are adjacent
       *    return
       *  If square is already uncovered
       *    return
       *  else uncover square
       *    uncover square one step to the south of square.
       *    uncover square one step to the north of square
       *    uncover square one step to the west of square
       *    uncover square one step to the east of square
       *    uncover square one step to the NW of square
       *    uncover square one step to the NE of square
       *    uncover square one step to the SW of square
       *    uncover square one step to the SE of square
       *    return
       */

      if (!myMineField.inRange(row,col)) {
         return;
      }
      else if (this.getMineField().hasMine(row,col)){
         return;
      }
      else if (this.getStatus(row, col) == MINE_GUESS || this.getStatus(row,col) == QUESTION) {
         return;
      }
      else if ( myMineField.numAdjacentMines(row,col) > 0){
         myVisibleStatus[row][col] = myMineField.numAdjacentMines(row,col);
         return;
      }
      else if (this.getStatus(row,col) >= 0) {
         return;
      }
      else {
         myVisibleStatus[row][col] = myMineField.numAdjacentMines(row,col);
         // North West
         floodUncover(row + 1, col - 1);
         // North
         floodUncover(row + 1, col);
         // North East
         floodUncover(row + 1, col + 1);
         // East
         floodUncover(row, col + 1);
         // South East
         floodUncover(row - 1, col + 1);
         // South
         floodUncover(row - 1, col);
         // South West
         floodUncover(row - 1, col - 1);
         // West
         floodUncover(row, col - 1);
         return;
      }
   }
}