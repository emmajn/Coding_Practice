import java.io.FileNotFoundException;
import java.util.ArrayList;

public class AnagramDictionaryTester {
    public static void main(String[] args) throws IllegalDictionaryException, FileNotFoundException {
        // First Check if file is legal
//        testFileExecptions("testFiles/duplicate.txt");
//        testFileExecptions("nonExistent");
        testingCreateAnagramDictionary("testFiles/tinyDictionary.txt");
        System.out.println('a'-'a');
    }
    private static void testFileExecptions (String filename) throws IllegalDictionaryException, FileNotFoundException {
        try{
            AnagramDictionary myAnagramDictionary = new AnagramDictionary(filename);
        }
        catch (FileNotFoundException exception)
        {
            System.out.println("File not found: " + filename);
        }
        catch (IllegalDictionaryException exception)
        {
            System.out.println( exception.getMessage());
        }
    }
    private static void testingCreateAnagramDictionary(String filename) throws IllegalDictionaryException, FileNotFoundException {
        ArrayList<String> myAnagrams = new ArrayList<String>();

        AnagramDictionary myAnagramDictionary = new AnagramDictionary(filename);
        System.out.println(myAnagramDictionary.getAnagramsOf("em")); // ["em',"me"]
        System.out.println(myAnagramDictionary.getAnagramsOf("EM")); // ["em',"me"]
        System.out.println(myAnagramDictionary.getAnagramsOf("TESTING")); // []
    }
}
