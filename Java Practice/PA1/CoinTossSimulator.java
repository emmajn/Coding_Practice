// Name: Emma Nelson
// USC NetID: emmanels
// CS 455 PA1
// Fall 2021

import java.util.Random;
/**
 * class CoinTossSimulator
 * 
 * Simulates trials of repeatedly tossing two coins and allows the user to access the
 * cumulative results.
 * 
 * NOTE: we have provided the public interface for this class.  Do not change
 * the public interface.  You can add private instance variables, constants, 
 * and private methods to the class.  You will also be completing the 
 * implementation of the methods given. 
 * 
 * Invariant: getNumTrials() = getTwoHeads() + getTwoTails() + getHeadTails()
 * 
 */
public class CoinTossSimulator {
   private int numberOfTrials;
   private int numberOfTwoHeads;
   private int numberOfTwoTails;
   private int numberOfHeadTail;

   /**
      Creates a coin toss simulator with no trials done yet.
   */
   public CoinTossSimulator() {
      this.run(0);
   }
   /**
      Runs the simulation for numTrials more trials. Multiple calls to this method
      without a reset() between them *add* these trials to the current simulation.
      
      @param numTrials  number of trials to for simulation; must be >= 1
    */
   public void run(int numTrials) {
      for (int i = 1; i <= numTrials; i ++) {
         numberOfTrials ++;
         Random coinTossGenerator = new Random();
         int firstCoinTossResult = coinTossGenerator.nextInt(2); // 0 Heads 1 Tails
         int secondCoinTossResult = coinTossGenerator.nextInt(2); //0 Heads 1 Tails
         if (firstCoinTossResult + secondCoinTossResult == 0){
            numberOfTwoHeads ++;
         }
         else if (firstCoinTossResult + secondCoinTossResult  == 2){
            numberOfTwoTails ++;
         }
         else {
            numberOfHeadTail ++;
         }
      }
   }
   /**
      Get number of trials performed since last reset.
   */
   public int getNumTrials() {
       return numberOfTrials; // DUMMY CODE TO GET IT TO COMPILE
   }
   /**
      Get number of trials that came up two heads since last reset.
   */
   public int getTwoHeads() {
       return numberOfTwoHeads; // DUMMY CODE TO GET IT TO COMPILE
   }
   /**
    Get the percentage of trials that came up two heads since last reset.
    */
   public int getPercentTwoHeads() {
      double convertedNumberOfTrials = numberOfTrials;
      return (int) Math.round(numberOfTwoHeads/convertedNumberOfTrials*100);
   }
   /**
     Get number of trials that came up two tails since last reset.
   */  
   public int getTwoTails() {
       return numberOfTwoTails; // DUMMY CODE TO GET IT TO COMPILE
   }
   /**
    Get the percentage of trials that came up two tails since last reset.
    */
   public int getPercentTwoTails() {
      double convertedNumberOfTrials = numberOfTrials;
      return (int) Math.round(numberOfTwoTails/convertedNumberOfTrials*100);
   }
   /**
     Get number of trials that came up one head and one tail since last reset.
   */
   public int getHeadTails() {
       return numberOfHeadTail; // DUMMY CODE TO GET IT TO COMPILE
   }
   /**
      Get the percentage of trials that came up one head and one tail since last reset.
    */
   public int getPercentHeadTails() {
      double convertedNumberOfTrials = numberOfTrials;
      return (int) Math.round(numberOfHeadTail/convertedNumberOfTrials*100);
   }
   /**
    Resets the simulation, so that subsequent runs start from 0 trials done.
    */
   public void reset() {
      numberOfTrials = 0;
      numberOfTwoHeads = 0;
      numberOfTwoTails = 0;
      numberOfHeadTail = 0;
   }
}
