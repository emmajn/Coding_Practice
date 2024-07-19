// Name: Emma Nelson
// USC NetID: emmanels
// CS 455 PA1
// Fall 2021

import java.awt.*;
import javax.swing.*;

/**
 This component draws the bar graphs of the coin toss simulator result.

 Note: this version modified from textbook the CarComponent.java.  It is instrumented
 so as you run the program it indicates in the console window every time
 paintComponent gets called.
 */
public class CoinSimComponent extends JComponent
{
    private int callCount; // added instance variable for instrumentation
    // the outcome types in this simulation: There are 3 types of coin toss outcomes
    private int numOutcomeTypes = 3;
    private static final int BAR_WIDTH = 50;
    private static final int BAR_BUFFER = 20;
    private CoinTossSimulator coinToss;
    private static final Color HEAD_HEAD_COLOR = Color.red;
    private static final Color HEAD_TAIL_COLOR = Color.green;
    private static final Color TAIL_TAIL_COLOR = Color.blue;

    // added constructor for instrumentation
    // Note: for old version without instance variables an empty default
    // constructor didn't need to be defined explicitly (see:
    // https://docs.oracle.com/javase/tutorial/java/javaOO/constructors.html
    // for rules about this)
    
    /**
    Constructs and runs the CoinTossSimulator with the user number of trials from the CoinSimViewer.class
    */
    public CoinSimComponent(int inputNumOfTrials) {
        callCount = 0;
        int numOfTrials = inputNumOfTrials;
        coinToss = new CoinTossSimulator();
        coinToss.run(numOfTrials); // Runs simulation
    }
    
    /**
    Draws the bar graphs of the coin toss results 
    */

    public void paintComponent(Graphics g)
    {

        Graphics2D g2 = (Graphics2D) g;

        // the following two lines are for instrumentation
        callCount++;
        System.out.println("Called paintComponent(" + callCount + ")");

        // Scale would be (Window height - 2*buffer)/(total number of trial)
        double scale = (this.getHeight() - 2 * BAR_BUFFER) / (double) coinToss.getNumTrials();

        // Bottom = Frame Height - Buffer
        int bottom = this.getHeight() - BAR_BUFFER;

        // Left would be WindowWidth/(numOutcomeTypes+1) - BarWidth/2;
        int leftTwoHead = this.getWidth()/(numOutcomeTypes +1)-BAR_WIDTH/2;
        int leftHeadTail = 2*this.getWidth()/(numOutcomeTypes +1)-BAR_WIDTH/2;
        int leftTwoTail = 3*this.getWidth()/(numOutcomeTypes +1)-BAR_WIDTH/2;
        // Application unit is a coin toss trial

        // Creates labels for each bar graph of the case outcome
        String twoHeadsLabel = "Two Heads: " + String.valueOf(coinToss.getTwoHeads()) + " (" + String.valueOf(coinToss.getPercentTwoHeads()) + "%)";
        String headTailLabel = "A head and a tail: " + String.valueOf(coinToss.getHeadTails()) + " (" + String.valueOf(coinToss.getPercentHeadTails()) + "%)";
        String twotailsLabel = "Two Tails: " + String.valueOf(coinToss.getTwoTails()) + " (" + String.valueOf(coinToss.getPercentTwoTails()) + "%)";

        // Creates bars for each case
        Bar twoHeadsBar = new Bar(bottom, leftTwoHead, BAR_WIDTH, coinToss.getTwoHeads(), scale, HEAD_HEAD_COLOR, twoHeadsLabel);
        Bar headTailBar = new Bar(bottom, leftHeadTail, BAR_WIDTH, coinToss.getHeadTails(), scale, HEAD_TAIL_COLOR, headTailLabel);
        Bar twoTailBar = new Bar(bottom, leftTwoTail, BAR_WIDTH, coinToss.getTwoTails(), scale, TAIL_TAIL_COLOR,twotailsLabel);
        
        // Draws the bars for each case
        twoHeadsBar.draw(g2);
        headTailBar.draw(g2);
        twoTailBar.draw(g2);
    }
}
