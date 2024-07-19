// Name: Emma Nelson
// USC NetID: emmanels
// CSCI455 PA2
// Fall 2021

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This main BookshelfKeeperProg class creates a terminal-based interactive program that allows the user to 
 * instantiate a BookshelfKeeper and 
 * perform a series of pick and put operations on a BookshelfKeeper object; 
 * It can also be run in a batch mode by using input and output redirection.
 */
public class BookshelfKeeperProg {
    private static Bookshelf myBookshelf;
    private static BookshelfKeeper myBookSelfKeeper;
    public static void main(String[] args){
        Scanner lineScanner = new Scanner(System.in);
        System.out.println("Please enter initial arrangement of books followed by newline:");
        Boolean inputSuccess = inputShelf(lineScanner); // Checks if the user put in a valid input for the bookshelf
        if (!inputSuccess){ 
            return; // Exit the program if the input is not valid
        }
        System.out.println("Type pick <index> or put <height> followed by newline. Type end to exit.");
        // Allow users to continue adding or removing books from a bookshelf as long as the commands are valid
        while (inputSuccess){
            inputSuccess = modifyBookshelf(lineScanner);  
        }
        return;  // Exit the program if the input is not valid
    }

    /**
     * 
     * @param lineScanner: Scanner to read user input
     *                   PRE: User only puts in integer values for books. No floats or strings. 
     * @return returns True or False based on user's bookshelf validity and sorted status 
     *          True: iff All books in bookshelf are of height > 0 and sorted in ascending order
     */
    private static Boolean inputShelf(Scanner lineScanner){
        Boolean successfulInput = true;
        ArrayList<Integer> pileOfBooks = new ArrayList<>();
        String line = lineScanner.nextLine();
        line = line.trim();
        line = line + " ";
        String numString = "";
        
        // Assuming users only input integers
        for (int i = 0; i < line.length(); i++){
            // Characters that are not white spaces make up an integer. Keep adding characters to numString until the next white space
            if (!Character.isWhitespace(line.charAt(i))) {
                numString = numString + line.substring(i, i+1);
            }
            else if(Character.isWhitespace(line.charAt(i)) && numString.length()>0) {
                int newBookHeight = Integer.parseInt(numString);
                // Book heights must be greater than 0 
                if (newBookHeight <= 0) { 
                    System.out.println("ERROR: Height of a book must be positive.\n" +
                            "Exiting Program.");
                    return false;
                }
                pileOfBooks.add(newBookHeight);
                numString = "";
            }
        }
        myBookshelf = new Bookshelf(pileOfBooks);
        // Books must be sorted in ascending order
        if (!myBookshelf.isSorted()) { 
            System.out.println("ERROR: Heights must be specified in non-decreasing order.\n" +
                    "Exiting Program.");
            return false;
        }
        myBookSelfKeeper = new BookshelfKeeper(myBookshelf);
        System.out.println(myBookSelfKeeper.toString());
        return true;
    }

    /**
     * This method reads the scanner inputs and interprets the user's commands and enacts them if they are valid
     * 
     * @param lineScanner: Scanner to read user input
     *                   PRE: User has a String: Action, and int: Value
     * @return whether the user input value is valid for continuing to another command.
     * if the input is valid then enacts it and return true. Otherwise this returns false
     * PRE for return True inputs. 
     * Action Value, Action must be "put" or "pick" 
     * if action = pick, 0 <= value <= bookshelf number of books
     * if action = put, 0 < value 
     * 
     */ 
    private static boolean modifyBookshelf(Scanner lineScanner){
        Boolean successfulInput = true;

        ArrayList<Integer> pileOfBooks = new ArrayList<>();
        
        // look at user's action 
        String action = lineScanner.next();
        
        // User will type end if they want to exit this program 
        if (action.toLowerCase().equals("end")){
            System.out.println("Exiting Program.");
            return false;
        }
        
        // look at users value and decide if it's valid based on the action 
        int value = lineScanner.nextInt();
        if (action.toLowerCase().equals("pick")) {
            if (value >= myBookSelfKeeper.getNumBooks() | value < 0) {
                System.out.println("ERROR: Entered pick operation is invalid on this shelf.\n" +
                        "Exiting Program.");
                return false;
            }
            myBookSelfKeeper.pickPos(value);
        }
        else if  (action.toLowerCase().equals("put")) {
            if (value <= 0) {
                System.out.println("ERROR: Height of a book must be positive.\n" +
                        "Exiting Program.");
                return false;
            }
            myBookSelfKeeper.putHeight(value);
        }
        else {
            System.out.println("ERROR: Invalid command. Valid commands are pick, put, or end.\n" +
                    "Exiting Program.");
            return false;
        }
        System.out.println(myBookSelfKeeper.toString());
        return true;
    }
}
