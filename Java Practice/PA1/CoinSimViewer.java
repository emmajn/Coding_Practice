// Name: Emma Nelson
// USC NetID: emmanels
// CS 455 PA1
// Fall 2021

import javax.swing.JFrame;
import java.util.Scanner;

public class CoinSimViewer
{
        /**
         * This main method prompts for number of trials in the coin toss simulator
         * Enter number of trials: -5
         * ERROR: Number entered must be greater than 0.
         * Enter number of trials: 0
         * ERROR: Number entered must be greater than 0.
         * Enter number of trials: 100
         * After the prompt it runs the simulation of how many head-heads, head-tails, and tails-tails with the specified number of trials
         * Then it plots those trials
         */
    public static void main(String[] args)
    {
        // Prompts the user for number of trials to simulate
        Scanner userInput = new Scanner(System.in);
        System.out.print("Enter number of trials:");
        int numOfTrials = userInput.nextInt();
       
       // This forces the User to Enter a valid number of trials that is greater than 0
        while (numOfTrials < 1) {
            System.out.println("ERROR: Number entered must be greater than 0.");
            System.out.print("Enter number of trials:");
            numOfTrials = userInput.nextInt();
        }

        // Creates a new window
        JFrame frame = new JFrame();
        frame.setSize(500, 800);
        frame.setTitle("CoinSim");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
        // Creates a CoinSimComponent with the user input numberOfTrials
        CoinSimComponent component = new CoinSimComponent(numOfTrials);

        // Adds Bar Graphs and makes windows visible
        frame.add(component);
        frame.setVisible(true);
    }
}
