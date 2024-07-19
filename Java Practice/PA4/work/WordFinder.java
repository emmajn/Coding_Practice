// Name: Emma Nelson
// USC NetID: emmanels
// CS 455 PA4
// Fall 2021

import java.io.FileNotFoundException;
import java.util.*;

/**
 * This is the class for taking user inputs for a rack, checking if it can create legal words from the rack, and lists these words by order of most points.
 * This class checks word legality by comparing to either the specified dictionary or the default dictionary.
 * They class checks if the file exists for a specified dictionary or if the dictionary is legal and has no repeating words.
 * To Run this program, Type: java WordFinder [dictionaryFile]
 *  It will let the user know they can quit by typing in "."
 *  otherwise it keeps prompting for new rack.
 */
public class WordFinder {
    /**
     Representation invariant:
     myAnagramDictionary an instance of AnagramDictionary. it maps a string of characters to all the possible anagrams that are legal words in the input or default sowpods dictionar
     wordsFound is a TreeMap<String,Integer> it has >= 0 entries. The keys are the words found from a given rack. the values are the points for that word
     */

    private static AnagramDictionary myAnagramDictionary;
    private static TreeMap<String,Integer> wordsFound = new TreeMap<String,Integer>();

    public static void main(String[] args) throws IllegalDictionaryException, FileNotFoundException {
        Rack myRack;
        String userInput;

        // First open and process ditionary file
        if (!openAndProcessDictionary(args)){
            System.out.println("Exiting program.");
            return;
        }

        // Then prompt user to define a rack or quit the game
        Scanner in = new Scanner(System.in);
        System.out.println("Type . to quit.");
        System.out.print("Rack? ");
        userInput = in.nextLine();
        while (userInput.compareTo(".") != 0){
            myRack = new Rack(processInput(userInput));
            // Find and print all the words from the rack
            findPossibleWords(myRack);
            printRackWords(myRack);

            // Prompts user for the next rack
            System.out.print("Rack? ");
            userInput = in.nextLine();
        }
    }

    /**
     * This method opens the dictionary file and creates and anagram dictionary from the file
     * If no dictionary is specified, it goes to default sowpods.txt dictionary
     * This throw exceptions for two senaries
     *  1.) The dictionary file cannot be found
     *  2.) The dictionary file is not valid do to duplicate words
     * @param dictFile: dictionary file. it must
     */
    private static boolean openAndProcessDictionary(String[] dictFile){
        // First Check if file is legal
        Boolean validDictionary = true;
        String filename;

        if (dictFile.length == 0) { // Default dictionary
            filename = "sowpods.txt";
        } else {
            filename = dictFile[0];
        }
        try{
            myAnagramDictionary = new AnagramDictionary(filename);
        }
        catch (FileNotFoundException exception)
        {
            System.out.println("File not found: " + filename);
            validDictionary = false;
        }
        catch (IllegalDictionaryException exception)
        {
            System.out.println( exception.getMessage());
            validDictionary = false;
        }
        return validDictionary;
    }

    /**
     * This method processes the user input before creating a rack with it.
     * This method filters out non alphabet characters.
     * if the dictionary is lower case words: This filters out upper case wards
     * If the dictionary is upper case words: this filters out lower case words
     * @param s: user input string
     * @return alphabetOnlyString: filtered String
     */
    private static String processInput(String s){
        String alphabetOnlyString;
        if (myAnagramDictionary.getCaseType().compareTo("LOWER")==0){
            alphabetOnlyString = s.replaceAll("[^a-z]+", "").toLowerCase();
        } else {
            alphabetOnlyString = s.replaceAll("[^A-Z]+", "");
        }
        return alphabetOnlyString;
    }

    /**
     * This function finds all the possible legal words in a rack and puts them in wordsFound, a TreeMap<String,Integer>: the key is the word and the value is the point value of that word
     * it clears wordsFound before adding to the dictionary as to not keep words from a previous round.
     * @param rack
     */
    private static void findPossibleWords(Rack rack){
        ArrayList<String> allSubsets = rack.getAllSubsetsOfWords(); // all unique subsets of a rack
        ArrayList<String> anagrams; // all the anagrams of a given string
        ScoreTable myScoreTable = new ScoreTable(); // a scoreTable
        wordsFound.clear();

        for (int i = 0; i < allSubsets.size(); i++){
            anagrams = myAnagramDictionary.getAnagramsOf(allSubsets.get(i));
            for (int j = 0; j < anagrams.size(); j++){
                wordsFound.put(anagrams.get(j), myScoreTable.getScore(anagrams.get(j)));
            }
        }
    }

    /**
     * Prints the wordsFound from a rack by order of most points to least
     * @param rack
     */
    private static void printRackWords(Rack rack){
        Map.Entry[] arrayOfMapEntries = wordsFound.entrySet().toArray(new Map.Entry[0]);
        Arrays.sort(arrayOfMapEntries ,new ScoreCompare());
        System.out.println("We can make "+ wordsFound.size() +" words from " + "\"" + rack.toString() + "\"");
        if (wordsFound.size()>0){
            System.out.println("All of the words with their scores (sorted by score):");
        }
        for (int i = 0; i < arrayOfMapEntries.length; i++){
            System.out.println(arrayOfMapEntries[i].getValue()
                    + ": " + arrayOfMapEntries[i].getKey());
        }
    }
}

