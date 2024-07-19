import java.util.ArrayList;

public class BookshelfKeeperTester {
    public static void main(String[] args){
        testConstructor();
        testPickPos();
        testputHeight();
        testGetTotalOperations();
        testingGetNumBooks();
        testingToString();
    }
    private static void testConstructor(){
        System.out.println("Testing Constructor....");
        ArrayList<Integer> pileOfBooks = new ArrayList<Integer>();
        pileOfBooks.add(1);
        pileOfBooks.add(20);
        pileOfBooks.add(4);
        pileOfBooks.add(13);
        Bookshelf testBookshelf = new Bookshelf(pileOfBooks);
        BookshelfKeeper testBookshelfKeeper = new BookshelfKeeper(testBookshelf);
    }
    private static void testPickPos(){
        System.out.println("Testing pickPos...");
        ArrayList<Integer> pileOfBooks = new ArrayList<Integer>();
        pileOfBooks.add(1);
        pileOfBooks.add(2);
        pileOfBooks.add(3);
        pileOfBooks.add(4);
        pileOfBooks.add(5);
        pileOfBooks.add(30);
        pileOfBooks.add(40);
        Bookshelf testBookshelf = new Bookshelf(pileOfBooks);
        BookshelfKeeper testBookshelfKeeper = new BookshelfKeeper(testBookshelf);
        System.out.println("Testing pickPos(2) [exp: 5]: " + testBookshelfKeeper.pickPos(2) );
    }
    public static void testputHeight(){
        System.out.println("Testing putHeight...");
        ArrayList<Integer> pileOfBooks = new ArrayList<Integer>();
        pileOfBooks.add(1);
        pileOfBooks.add(2);
        pileOfBooks.add(3);
        pileOfBooks.add(4);
        pileOfBooks.add(5);
        pileOfBooks.add(30);
        pileOfBooks.add(40);
        Bookshelf testBookshelf = new Bookshelf(pileOfBooks);
        BookshelfKeeper testBookshelfKeeper = new BookshelfKeeper(testBookshelf);
        System.out.println("Testing putHeight(35) [exp: 3]: " + testBookshelfKeeper.putHeight(35) );
    }
    private static void testGetTotalOperations(){
        System.out.println("Testing getTotalOperations...");
        ArrayList<Integer> pileOfBooks = new ArrayList<Integer>();
        pileOfBooks.add(1);
        pileOfBooks.add(2);
        pileOfBooks.add(3);
        pileOfBooks.add(4);
        pileOfBooks.add(5);
        pileOfBooks.add(30);
        pileOfBooks.add(40);
        Bookshelf testBookshelf = new Bookshelf(pileOfBooks);
        BookshelfKeeper testBookshelfKeeper = new BookshelfKeeper(testBookshelf);
        testBookshelfKeeper.pickPos(2);
        testBookshelfKeeper.putHeight(35);
        System.out.println("Testing getTotalOperations() [exp: 8]: " + testBookshelfKeeper.getTotalOperations());
    }
    private static void testingGetNumBooks(){
        System.out.println("Testing getNumBooks...");
        ArrayList<Integer> pileOfBooks = new ArrayList<Integer>();
        pileOfBooks.add(1);
        pileOfBooks.add(2);
        pileOfBooks.add(3);
        pileOfBooks.add(4);
        pileOfBooks.add(5);
        pileOfBooks.add(30);
        pileOfBooks.add(40);
        Bookshelf testBookshelf = new Bookshelf(pileOfBooks);
        BookshelfKeeper testBookshelfKeeper = new BookshelfKeeper(testBookshelf);
        System.out.println("Testing getNumBooks [exp: 7]: " + testBookshelfKeeper.getNumBooks());
        testBookshelfKeeper.putHeight(35);
        System.out.println("Testing getNumBooks [exp: 8]: " + testBookshelfKeeper.getNumBooks());
    }
    private static void testingToString(){
        System.out.println("Testing toString...");
        ArrayList<Integer> pileOfBooks = new ArrayList<Integer>();
        pileOfBooks.add(1);
        pileOfBooks.add(2);
        pileOfBooks.add(3);
        pileOfBooks.add(4);
        pileOfBooks.add(5);
        pileOfBooks.add(30);
        pileOfBooks.add(40);
        Bookshelf testBookshelf = new Bookshelf(pileOfBooks);
        BookshelfKeeper testBookshelfKeeper = new BookshelfKeeper(testBookshelf);
        System.out.println("Testing toString [ex: [1, 2, 3, 4, 5, 30, 40] 0 0]: " + testBookshelfKeeper.toString());
        testBookshelfKeeper.pickPos(2);
        System.out.println("Testing toString [ex: [1, 2, 4, 5, 30, 40] 5 5]: " + testBookshelfKeeper.toString());
        testBookshelfKeeper.putHeight(35);
        System.out.println("Testing toString [ex: [1, 2, 4, 5, 30, 35, 40] 3 8]: " + testBookshelfKeeper.toString());
        testBookshelfKeeper.putHeight(3);
        System.out.println("Testing toString [ex: [1, 2, 3, 4, 5, 30, 35, 40] 5 13]: " + testBookshelfKeeper.toString());
        testBookshelfKeeper.pickPos(6);
        System.out.println("Testing toString [ex: [1, 2, 3, 4, 5, 30, 40] 3 16]: " + testBookshelfKeeper.toString());
    }
}
