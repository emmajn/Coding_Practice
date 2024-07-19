// Name: Emma Nelson
// USC NetID: emmanels
// CS 455 PA4
// Fall 2021

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
   A Rack of Scrabble tiles
 */

public class Rack {

   /**
    * allSubsetsOfWords is an array list >=0 of all the possible unique subset of strings from a given rack
    * letterMltiplicityDict is a TreeMap<String,Integer>() with size >=0 of the frequency of each unique letter in a rack. The key is the unique letter. The value is the frequency
    * rackLetters: String of valid user input letters. Valid meaning no-nonalphabet and it is in the same case as the dictionary
    */
   private ArrayList<String> allSubsetsOfWords;
   private TreeMap<String,Integer> letterMltiplicityDict = new TreeMap<String,Integer>();
   private String rackLetters;


   public Rack(String userInput) {
      rackLetters = userInput;
      String uniqueLetters;
      int[] frequencyOfLetters;

      // Creates a dictionary of with unique letters as keys and frequency of those letters as values
      createLetterMltiplicityDict(rackLetters);
      uniqueLetters = this.getUniqueLetters();
      frequencyOfLetters = this.getFrequency();

      // Creates a list of all subsets for a given rack
      allSubsetsOfWords = allSubsets(uniqueLetters, frequencyOfLetters,0);
   }

   /**
    * Creates a dictionary of with unique letters as keys and frequency of those letters as values
    * @param s: user input string of rackletters
    */
   private void createLetterMltiplicityDict(String s){
      Integer frequencyOfLetter;
      for (int i = 0; i < s.length(); i ++){
         frequencyOfLetter = letterMltiplicityDict.get(s.substring(i,i+1));
         if (frequencyOfLetter == null){
            frequencyOfLetter = 0;
         }
         frequencyOfLetter++;
         letterMltiplicityDict.put(s.substring(i,i+1), frequencyOfLetter);
      }
   }

   /**
    * gets unique letters in a rack
    * @return uniqueLetters: a String of all the unique letters in a rack
    */
   private String getUniqueLetters(){
      String uniqueLetters = "";
      for (Map.Entry<String, Integer> curr: letterMltiplicityDict.entrySet()){
         uniqueLetters = uniqueLetters + curr.getKey();
      }
      return uniqueLetters;
   }

   /**
    * Gets the frequency of the unique letters in a rack.
    * @return frequencyArray: int[] of frequency of unique letters. the Frequency is in alphabetical order
    */
   private int[] getFrequency(){
      int[] frequencyArray = new int[letterMltiplicityDict.size()];
      int index = 0;
      for (Map.Entry<String, Integer> curr: letterMltiplicityDict.entrySet()){
         frequencyArray[index] = curr.getValue();
         index++;
      }
      return frequencyArray;
   }

   /**
    * This method takes the rack ditionary outputs it to string.
    * @return
    */
   public String rackDicttoString(){
      return letterMltiplicityDict.toString();
   }

   /**
    * This method returns a string of the input rack letters
    * @return rackLetters: string of input rack letters
    */
   public String toString(){
      return rackLetters;
   }
   /**
      Finds all subsets of the multiset starting at position k in unique and mult.
      unique and mult describe a multiset such that mult[i] is the multiplicity of the char
           unique.charAt(i).
      PRE: mult.length must be at least as big as unique.length()
           0 <= k <= unique.length()
      @param unique a string of unique letters
      @param mult the multiplicity of each letter from unique.  
      @param k the smallest index of unique and mult to consider.
      @return all subsets of the indicated multiset.  Unlike the multiset in the parameters,
      each subset is represented as a String that can have repeated characters in it.
      @author Claire Bono
    */
   private static ArrayList<String> allSubsets(String unique, int[] mult, int k) {
      ArrayList<String> allCombos = new ArrayList<>();

      if (k == unique.length()) {  // multiset is empty
         allCombos.add("");
         return allCombos;
      }

      // get all subsets of the multiset without the first unique char
      ArrayList<String> restCombos = allSubsets(unique, mult, k+1);

      // prepend all possible numbers of the first char (i.e., the one at position k) 
      // to the front of each string in restCombos.  Suppose that char is 'a'...

      String firstPart = "";          // in outer loop firstPart takes on the values: "", "a", "aa", ...
      for (int n = 0; n <= mult[k]; n++) {
         for (int i = 0; i < restCombos.size(); i++) {  // for each of the subsets 
            // we found in the recursive call
            // create and add a new string with n 'a's in front of that subset
            allCombos.add(firstPart + restCombos.get(i));
         }
         firstPart += unique.charAt(k);  // append another instance of 'a' to the first part
      }

      return allCombos;
   }

   public ArrayList<String> getAllSubsetsOfWords(){
      return this.allSubsetsOfWords;
   }
}
