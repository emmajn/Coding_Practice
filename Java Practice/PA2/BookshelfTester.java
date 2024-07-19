// Name: Emma Nelson
// USC NetID: emmanels
// CSCI455 PA2
// Fall 2021
import java.util.ArrayList;

public class BookshelfTester {
    /**
     * hard-coded data to test the following subset of your Bookshelf class:
     * the two constructors plus the toString method.
     */
    public static void main(String[] args) {
        testBookshelfConstructor();
        testBookshelfToString();
        testAddFront();
        testAddLast();
        testRemoveFront();
        testRemoveLast();
        testGetHeight();
        testGetSize();
    }
    private static void testBookshelfConstructor(){
        System.out.println("Testing constructor...");
        Bookshelf testEmptyBookshelf = new Bookshelf();
        ArrayList<Integer> pileOfBooks = new ArrayList<Integer>();
        pileOfBooks.add(1);
        pileOfBooks.add(20);
        pileOfBooks.add(4);
        pileOfBooks.add(13);
        Bookshelf testBookShelf = new Bookshelf(pileOfBooks);
    }
    private static void testBookshelfToString(){
        /**
         * Test the empty constructor with the to string method
         * Test the non-empty constructor with the to string method
         */
        System.out.println("Testing toString method...");
        Bookshelf testEmptyBookshelf = new Bookshelf();
        System.out.println("Books as a string [exp: []]: " + testEmptyBookshelf.toString());

        ArrayList<Integer> pileOfBooks = new ArrayList<Integer>();
        pileOfBooks.add(1);
        pileOfBooks.add(20);
        pileOfBooks.add(4);
        pileOfBooks.add(13);
        Bookshelf testBookShelf = new Bookshelf(pileOfBooks);
        System.out.println("Books as a string [exp: [1, 20, 4, 13]]: "+ testBookShelf.toString());
    }
    private static void testAddFront(){
        System.out.println("Testing addFront method...");
        Bookshelf myBookshelf = new Bookshelf();
        System.out.println("Books as a string [exp: []]: " + myBookshelf.toString());
        myBookshelf.addFront(10);
        System.out.println("Books as a string after addFront [exp: [10]]: " + myBookshelf.toString());
        myBookshelf.addFront(20);
        System.out.println("Books as a string after addFront [exp: [20, 10]]: " + myBookshelf.toString());
        myBookshelf.addFront(3);
        System.out.println("Books as a string after addFront [exp: [3, 20, 10]]: " + myBookshelf.toString());
        myBookshelf.addFront(2);
        System.out.println("Books as a string after addFront [exp: [2, 3, 20, 10]]: " + myBookshelf.toString());
    }
    private static void testAddLast(){
        System.out.println("Testing addLst method...");
        Bookshelf myBookshelf = new Bookshelf();
        System.out.println("Books as a string [exp: []]: " + myBookshelf.toString());
        myBookshelf.addLast(10);
        System.out.println("Books as a string after addLast [exp: [10]]: " + myBookshelf.toString());
        myBookshelf.addLast(2);
        System.out.println("Books as a string after addLast [exp: [10, 2]]: " + myBookshelf.toString());
        myBookshelf.addLast(300);
        System.out.println("Books as a string after addLast [exp: [10, 2, 300]]: " + myBookshelf.toString());
        myBookshelf.addLast(1);
        System.out.println("Books as a string after addLast [exp: [10, 2, 300, 1]]: " + myBookshelf.toString());
    }
    private static void testRemoveFront(){
        System.out.println("Testing removeFront method...");
        ArrayList<Integer> pileOfBooks = new ArrayList<Integer>();
        pileOfBooks.add(12);
        pileOfBooks.add(30);
        pileOfBooks.add(41);
        pileOfBooks.add(15);
        Bookshelf testBookShelf = new Bookshelf(pileOfBooks);
        System.out.println("Books as a string [exp: [12, 30, 41, 15]]: "+ testBookShelf.toString());
        System.out.println("Book removed [exp: 12]: " + testBookShelf.removeFront());
        System.out.println("Books as a string after removeFront [exp: [30, 41, 15]]: "+ testBookShelf.toString());
        System.out.println("Book removed [exp: 30]: " + testBookShelf.removeFront());
        System.out.println("Books as a string after removeFront [exp: [41, 15]]: "+ testBookShelf.toString());
        System.out.println("Book removed [exp: 41]: " + testBookShelf.removeFront());
        System.out.println("Books as a string after removeFront [exp: [15]]: "+ testBookShelf.toString());
        System.out.println("Book removed [exp: 15]: " + testBookShelf.removeFront());
        System.out.println("Books as a string after removeFront [exp: []]: "+ testBookShelf.toString());
        System.out.println("Book removed [exp: 0]: " + testBookShelf.removeFront());
        System.out.println("Books as a string after removeFront [exp: []]: "+ testBookShelf.toString());
    }
    private static void testRemoveLast(){
        System.out.println("Testing removeLast method...");
        ArrayList<Integer> pileOfBooks = new ArrayList<Integer>();
        pileOfBooks.add(3);
        pileOfBooks.add(40);
        pileOfBooks.add(65);
        pileOfBooks.add(99);
        Bookshelf testBookShelf = new Bookshelf(pileOfBooks);
        System.out.println("Books as a string [exp: [3, 40, 65, 99]]: "+ testBookShelf.toString());
        System.out.println("Book removed [exp: 99]: " + testBookShelf.removeLast());
        System.out.println("Books as a string after removeLast [exp: [3, 40, 65]]: "+ testBookShelf.toString());
        System.out.println("Book removed [exp: 65]: " + testBookShelf.removeLast());
        System.out.println("Books as a string after removeLast [exp: [3, 40]]: "+ testBookShelf.toString());
        System.out.println("Book removed [exp: 40]: " + testBookShelf.removeLast());
        System.out.println("Books as a string after removeLast [exp: [3]]: "+ testBookShelf.toString());
        System.out.println("Book removed [exp: 3]: " + testBookShelf.removeLast());
        System.out.println("Books as a string after removeLast [exp: []]: "+ testBookShelf.toString());
        System.out.println("Book removed [exp: 0]: " + testBookShelf.removeLast());
        System.out.println("Books as a string after removeLast [exp: []]: "+ testBookShelf.toString());
    }
    private static void testGetHeight(){
        System.out.println("Testing getHeight method...");
        ArrayList<Integer> pileOfBooks = new ArrayList<Integer>();
        pileOfBooks.add(3);
        pileOfBooks.add(40);
        pileOfBooks.add(65);
        pileOfBooks.add(99);
        Bookshelf testBookShelf = new Bookshelf(pileOfBooks);
        System.out.println("Book height at position 0 [exp: 3]: " + testBookShelf.getHeight(0));
        System.out.println("Book height at position 1 [exp: 40]: " + testBookShelf.getHeight(1));
        System.out.println("Book height at position 2 [exp: 65]: " + testBookShelf.getHeight(2));
        System.out.println("Book height at position 3 [exp: 99]: " + testBookShelf.getHeight(3));
    }
    private static void testGetSize(){
        System.out.println("Testing get size...");
        ArrayList<Integer> pileOfBooks = new ArrayList<Integer>();
        pileOfBooks.add(3);
        pileOfBooks.add(40);
        pileOfBooks.add(65);
        pileOfBooks.add(99);
        Bookshelf testBookShelf = new Bookshelf(pileOfBooks);
        System.out.println("Bookshelf height [exp: 4]: " + testBookShelf.size());
        testBookShelf.addLast(10);
        System.out.println("Bookself height [exp: 5]: " + testBookShelf.size());
    }
}
