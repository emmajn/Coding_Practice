// Name: Emma Nelson
// USC NetID: emmanels
// CS 455 PA4
// Fall 2021

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;

public class ScoreTable {
    /**
     * scoreTabs: int[26] where the index is the letter of the alphabet (i.e. a = 0, b = 1.. z = 25) and the value at the index is the amount of points the letter is worth.
     * onePointers: (String of 1 point words)-A, E, I, O, U, L, N, S, T, R
     * twoPointers: (String of 2 points words)-D, G
     * threePointers: (String of 3 points words)-B, C, M, P
     * fourPointers: (String of 4 points words)-F, H, V, W, Y
     * fivePointers: (String of 5 points words)-K
     * eightPointers: (String of 8 points words)- J, X
     * tenPointers: (String of 10 points words)-Q, Z
     * This class should work for both upper and lower case versions of the letters, e.g., 'a' and 'A' will have the same score
     */
    private static int[] scoreTabs = new int[26];
    private String onePointers = "AEIOULNSTR";
    private String twoPointers = "DG";
    private String threePointers = "BCMP";
    private String fourPointers = "FHVWY";
    private String fivePointers = "K";
    private String eightPointers = "JX";
    private String tenPointers = "QZ";

    public ScoreTable() {
        // Populates scoreTabs with the values for each letter
        populatePointValues(onePointers,1);
        populatePointValues(twoPointers, 2);
        populatePointValues(threePointers, 3);
        populatePointValues(fourPointers, 4);
        populatePointValues(fivePointers, 5);
        populatePointValues(eightPointers, 8);
        populatePointValues(tenPointers, 10);
    }

    /**
     *
     * @return a String of scoreTabs where the index is the letter of the alphabet (i.e. a = 0, b = 1.. z = 25) and the value at the index is the amount of points the letter is worth.
     */
    public String toString(){
        return Arrays.toString(scoreTabs);
    }

    /**
     * Populates the scoreTabs with the point value for a given letter
     * @param letters: string of letters in a specific point value
     * @param pointValue: int of the value of the letters
     */
    private void populatePointValues(String letters, int pointValue){
        int index;
        for (int i = 0; i < letters.length(); i++){
            index = letters.charAt(i) - 'A';
            scoreTabs[index] = pointValue;
        }
    }

    /**
     * Calculates the score of a specific string
     * @param s: input string
     * @return int: score value
     */
    public int getScore(String s){
        // Convert string to same case as score board
        String convertedString = s.toUpperCase();
        int index;
        int currentScore = 0;
        for (int i = 0; i < convertedString.length(); i ++){
            index = convertedString.charAt(i) - 'A';
            currentScore = currentScore + scoreTabs[index];
        }
        return currentScore;
    }
}


class ScoreCompare implements Comparator<Map.Entry> {
    /**
     * This class assumes we are working with the wordFounds dictionary.
     * wordsFound is a TreeMap<String,Integer> it has >= 0 entries. The keys are the words found from a given rack. the values are the points for that word
     * This comparator allows us to sort the entries by decreasing point values
     * if Entry1 score > Entry2 score;
     *    return negative to indicate Entry1 comes before Entry2
     * if Entry1 score = Entry2 score;
     *    return 0
     * if Entry1 score < Entry2 score:
     *    return positive to indicate Entry1 is after entry2
     * @param entry1
     * @param entry2
     * @return
     */
    @Override
    public int compare(Map.Entry entry1, Map.Entry entry2)  {
        int firstScore = (int) entry1.getValue();
        int secondScre = (int) entry2.getValue();
        return secondScre - firstScore;
    }
}