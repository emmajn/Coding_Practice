import java.util.ArrayList;
import java.util.Scanner;

public class BookshelfKeeperProg {
    private static Bookshelf myBookshelf;
    private static BookshelfKeeper myBookSelfKeeper;
    public static void main(String[] args){

        Scanner lineScanner = new Scanner(System.in);

        System.out.println("Please enter initial arrangement of books followed by newline:");
        String line = lineScanner.nextLine();
        Boolean inputSuccess = inputShelf(line);
        System.out.println("Type pick <index> or put <height> followed by newline. Type end to exit.");
        while (inputSuccess){
            inputSuccess = modifyBookshelf(lineScanner);
        }
        return;
    }

    private static Boolean inputShelf(String line){
        Boolean successfulInput = true;
        ArrayList<Integer> pileOfBooks = new ArrayList<>();

        line = line.trim();
        line = line + " ";
        String numString = "";
        for (int i = 0; i < line.length(); i++){
            if (!Character.isWhitespace(line.charAt(i))) {
                numString = numString + line.substring(i, i+1);
            }
            else if(Character.isWhitespace(line.charAt(i)) && numString.length()>0) {
                int newBookHeight = Integer.parseInt(numString);
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
        if (!myBookshelf.isSorted()) {
            System.out.println("ERROR: Heights must be specified in non-decreasing order.\n" +
                    "Exiting Program.");
            return false;
        }
        myBookSelfKeeper = new BookshelfKeeper(myBookshelf);
        System.out.println(myBookSelfKeeper.toString());
        return true;
    }
    private static boolean modifyBookshelf(Scanner lineScanner){
        Boolean successfulInput = true;

        ArrayList<Integer> pileOfBooks = new ArrayList<>();
        String action = lineScanner.next();
        if (action.toLowerCase().equals("end")){
            System.out.println("Exiting Program.");
            return false;
        }
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
