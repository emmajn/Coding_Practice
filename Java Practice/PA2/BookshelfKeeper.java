// Name: Emma Nelson
// USC NetID: emmanels
// CSCI455 PA2
// Fall 2021


import javax.swing.*;

/**
 * Class BookshelfKeeper 
 *
 * Enables users to perform efficient putPos or pickHeight operation on a bookshelf of books kept in 
 * non-decreasing order by height, with the restriction that single books can only be added 
 * or removed from one of the two *ends* of the bookshelf to complete a higher level pick or put 
 * operation.  Pick or put operations are performed with minimum number of such adds or removes.
 */
public class BookshelfKeeper {

   /**
    Representation invariant:
    myBookShelfKeeper: a Bookshelf with all book heights > 0 and sorted from min height to max height
    temporaryBookShelf: total number of calls to addFront/Last removeFront/Last on myBookShelfKeeper since it's construction
    pickHeightorPutCalls: total number of calls to addFront/Last removeFront/Last on myBookShelfKeeper for either action of picking a book at a certain position or inserting a book of a certain height
    */

   // <add instance variables here>
   public Bookshelf myBookShelfKeeper;
   private int totalNumberOfCalls = 0;
   private int pickHeightorPutCalls = 0;
   private Bookshelf temporaryBookShelf = new Bookshelf();

   /**
    * Creates a BookShelfKeeper object with an empty bookshelf
    */
   public BookshelfKeeper() {
      Bookshelf myBookShelfKeeper = new Bookshelf();
   }

   /**
    * Creates a BookshelfKeeper object initialized with the given sorted bookshelf.
    * Note: method does not make a defensive copy of the bookshelf.
    *
    * PRE: sortedBookshelf.isSorted() is true.
    */
   public BookshelfKeeper(Bookshelf sortedBookshelf) {
      assert sortedBookshelf.isSorted();
      myBookShelfKeeper =  sortedBookshelf;
   }

   /**
    * Removes a book from the specified position in the bookshelf and keeps bookshelf sorted 
    * after picking up the book.
    *
    * Returns the number of calls to mutators on the contained bookshelf used to complete this
    * operation. This must be the minimum number to complete the operation.
    *
    * PRE: 0 <= position < getNumBooks()
    */
   public int pickPos(int position) {
      assert this.getNumBooks() >= 0;
      pickHeightorPutCalls = 0; // Initialized the tally of addFront/Last removeFront/Last calls
      int middleOfBookShelf = this.getNumBooks()/2;
      // Checks which would take a shorter amount of addFront/Last removeFront/Last calls by looking to see if the position is closer to the front or back of the bookshelf
      // Front of Bookshelf
      if (position < middleOfBookShelf){
         createTempBoolshelf("FRONT", position);
         temporaryBookShelf.removeLast();
         int numBookstoAdd = temporaryBookShelf.size(); // this is the number of books to add back, it must be defined here before they are removed from temporaryBookShelf. Cant have changing array size in for for loop
         for (int i = 0; i < numBookstoAdd; i++){
            myBookShelfKeeper.addFront(temporaryBookShelf.removeLast());
            pickHeightorPutCalls ++;
         }
      }
      // Back of Bookshelf
      else {
         createTempBoolshelf("BACK", position);
         temporaryBookShelf.removeFront();
         int numBookstoAdd = temporaryBookShelf.size(); // this is the number of books to add back, it must be defined here before they are removed from temporaryBookShelf. Cant have changing array size in for for loop
         for (int i = 0; i < numBookstoAdd; i ++){
            myBookShelfKeeper.addLast(temporaryBookShelf.removeFront());
            pickHeightorPutCalls ++;
         }
      }
      totalNumberOfCalls = totalNumberOfCalls + pickHeightorPutCalls;
      return pickHeightorPutCalls;   // dummy code to get stub to compile
   }

   /**
    * Inserts book with specified height into the shelf.  Keeps the contained bookshelf sorted 
    * after the insertion.
    *
    * Returns the number of calls to mutators on the contained bookshelf used to complete this
    * operation. This must be the minimum number to complete the operation.
    *
    * PRE: height > 0
    */
   public int putHeight(int height) {
      // First find the optimal position to put the book such that it is the least amount of calls to addFront/Back and removeFront/Back
      int position = getOptimizedPosition(height);
      pickHeightorPutCalls = 0;
      if (position < this.getNumBooks()/2){
         createTempBoolshelf("FRONT", position-1);
         temporaryBookShelf.addLast(height);
         int numBookstoAdd = temporaryBookShelf.size(); // this is the number of books to add back, it must be defined here before they are removed from temporaryBookShelf. Cant have changing array size in for for loop
         for (int i = 0; i < numBookstoAdd; i++){
            myBookShelfKeeper.addFront(temporaryBookShelf.removeLast());
            pickHeightorPutCalls ++;
         }
      }
      else {
         createTempBoolshelf("BACK", position);
         temporaryBookShelf.addFront(height);
         int numBookstoAdd = temporaryBookShelf.size(); // this is the number of books to add back, it must be defined here before they are removed from temporaryBookShelf. Cant have changing array size in for for loop
         for (int i = 0; i < numBookstoAdd; i ++){
            myBookShelfKeeper.addLast(temporaryBookShelf.removeFront());
            pickHeightorPutCalls ++;
         }
      }
      totalNumberOfCalls = totalNumberOfCalls + pickHeightorPutCalls;
      return pickHeightorPutCalls;
   }

   /**
    * Returns the total number of calls made to mutators on the contained bookshelf
    * so far, i.e., all the ones done to perform all of the pick and put operations
    * that have been requested up to now.
    */
   public int getTotalOperations() {
      return totalNumberOfCalls;   // dummy code to get stub to compile
   }

   /**
    * Returns the number of books on the contained bookshelf.
    */
   public int getNumBooks() {
      return myBookShelfKeeper.size();   // dummy code to get stub to compile
   }

   /**
    * Returns string representation of this BookshelfKeeper. Returns a String containing height
    * of all books present in the bookshelf in the order they are on the bookshelf, followed 
    * by the number of bookshelf mutator calls made to perform the last pick or put operation, 
    * followed by the total number of such calls made since we created this BookshelfKeeper.
    *
    * Example return string showing required format: “[1, 3, 5, 7, 33] 4 10”
    *
    */
   public String toString() {
      return myBookShelfKeeper.toString() + " " + pickHeightorPutCalls + " " + totalNumberOfCalls;   // dummy code to get stub to compile
   }

   /**
    * Returns true iff the BookshelfKeeper data is in a valid state.
    * (See representation invariant comment for details.)
    */
   private boolean isValidBookshelfKeeper() {
      return false;  // dummy code to get stub to compile
   }

   /**
    *
    * @param height: PRE: height > 0
    * @return int: the optimal position to put the book of a given height such that it is the least amount of calls to addFront/Back and removeFront/Back
    */
   private int getOptimizedPosition(int height){
      int counterFromFront = 0;
      int counterFromBack = 0;

      for (int i = 0; i < this.getNumBooks(); i++){
         if (myBookShelfKeeper.getHeight(counterFromBack) < height){
            counterFromFront ++;
         }
         if (myBookShelfKeeper.getHeight(this.getNumBooks()-1-counterFromBack) > height){
            counterFromBack ++;
         }
      }
      if (counterFromFront < counterFromBack){
         return counterFromFront;
      }
      else{
         return this.getNumBooks()-counterFromBack;
      }
   }

   /**
    *Creates a temporary bookshelf giving the position of the book needed to be removed and whether the book is from the front or back
    * Example: Bookshelf = [1, 2, 3, 4, 5, 6, 7]
    * createTempBoolshelf("FRONT", 2]
    * temporaryBookShelf: [1, 2, 3]
    *
    * createTempBoolshelf("BACK", 5]
    * temporaryBookShelf: [6, 7]
    *
    * @param side: String that is Either "FRONT" or "BACK". It indicates if the temporary bookshelf should be made from removing the front or the back of the bookshelf
    * @param position: int that is the position of the book we want to remove from the bookshelf
    */
   private void createTempBoolshelf(String side, int position){
      if (side.compareTo("FRONT")==0){
         int counter = 0;
         while (counter <= position){
            temporaryBookShelf.addLast(myBookShelfKeeper.removeFront());
            pickHeightorPutCalls++;
            counter++;
         }
      }
      else {
         int counter = myBookShelfKeeper.size()-1; // Starts from the Maximum index of the bookshelf and then counts down
         while (counter >= position){
            temporaryBookShelf.addFront(myBookShelfKeeper.removeLast());
            pickHeightorPutCalls++;
            counter--;
         }
      }
   }
}
