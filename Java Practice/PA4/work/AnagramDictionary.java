// Name: Emma Nelson
// USC NetID: emmanels
// CS 455 PA4
// Fall 2021

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


/**
   A dictionary of all anagram sets. 
   Note: the processing is case-sensitive; so if the dictionary has all lower
   case words, you will likely want any string you test to have all lower case
   letters too, and likewise if the dictionary words are all upper case.
 */
public class AnagramDictionary {
   /**
    * myAnagramDictionary is a TreeMap<String, ArrayList<String>> where they key is string in alphabetical order and the value is an array of all the LEGAL dictionary words that can be created from the string. All the words in the value array are the same length as the key.
    */
   private TreeMap<String, ArrayList<String>> myAnagramDictionary = new TreeMap<String, ArrayList<String>>();

   /**
      Create an anagram dictionary from the list of words given in the file
      indicated by fileName.  
      @param fileName  the name of the file to read from
      @throws FileNotFoundException  if the file is not found
      @throws IllegalDictionaryException  if the dictionary has any duplicate words
    */
   public AnagramDictionary(String fileName) throws FileNotFoundException,
                                                    IllegalDictionaryException {

      File inFile = new File(fileName);
      Scanner a = new Scanner(inFile);

      // Checks to see if file exists
      try (Scanner in = new Scanner(inFile))
      {
         Set<String> uniqueSets = new HashSet<>();
         String currentWord;
         while (in.hasNext()){
            currentWord = in.next();
            if (uniqueSets.contains(currentWord)){
               // throws exception if there are duplicate words
               throw new IllegalDictionaryException("ERROR: Illegal dictionary: dictionary file has a duplicate word: " + currentWord);
            }else{
               uniqueSets.add(currentWord);
               addToAnagramDictionary(currentWord);
            }
         }
      }
   }

   /**
      Get all anagrams of the given string. This method is case-sensitive.
      E.g. "CARE" and "race" would not be recognized as anagrams.
      @param s string to process
      @return a list of the anagrams of s. NOTE: anagrams returned are REAL words
    */
   public ArrayList<String> getAnagramsOf(String s) {
      String sortedWord;
      char charArray[] = s.toCharArray();
      Arrays.sort(charArray);
      sortedWord = new String(charArray);
      if (myAnagramDictionary.get(sortedWord) == null){
         return new ArrayList<String >();
      }
      return myAnagramDictionary.get(sortedWord);
   }

   /**
    * Adds the dictionary word to the myAnagramDictionary
    * This first sorts the letters in the word in alphabetical order.
    * Then it uses the sorted letters as a key and checks if there is already an existing value of all the anagrams for the sorted string.
    * If there isn't a value yet for the key:
    *    an array list is started with the word
    * If there is a value for the key:
    *    the word is appended to the existing array of anagrams
    * @param word: String, dictionary word
    */
   private void addToAnagramDictionary(String word){
      String sortedWord;
      ArrayList<String> existingAnagrams;

      // sort the letters in the word in alphabetical order so that it can be used as a key
      char charArray[] = word.toCharArray();
      Arrays.sort(charArray);
      sortedWord = new String(charArray);

      // Get the value for the sorted letters key
      existingAnagrams = myAnagramDictionary.get(sortedWord);

      // If there are no anagrams yet, create a new list of anagrams
      if (existingAnagrams == null) {
         existingAnagrams = new ArrayList<String>();
      }

      // Add the new word to the anagram array
      existingAnagrams.add(word);
      myAnagramDictionary.put(sortedWord, existingAnagrams);
   }

   /**
    * This is toe get whether the dictionary is upper or lower case letters so that we can later check only same case letters from a rack
    * @return String: caseType
    *    "UPPER" if upper case
    *    "LOWER" if lower case
    */
   public String getCaseType(){
      String firstWordInDict;
      String caseType;

      Map.Entry[] arrayOfMapEntries = myAnagramDictionary.entrySet().toArray(new Map.Entry[0]);
      firstWordInDict = (String) arrayOfMapEntries[0].getKey();
      if (Character.isLowerCase(firstWordInDict.charAt(0))){
         return "LOWER";
      } else {
         return "UPPER";
      }
   }
}
