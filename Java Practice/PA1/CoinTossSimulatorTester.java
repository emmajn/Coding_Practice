// Name: Emma Nelson
// USC NetID: emmanels
// CS 455 PA1
// Fall 2021

/**

 */
public class CoinTossSimulatorTester {
   /**
   This main method inputs various number of runs in coin toss simulator
   */
    public static void main(String[] args) {
        int caseNumber = 0;
        CoinTossSimulator testSimulatorRuns = new CoinTossSimulator();
        System.out.println("After constructor:");
        caseNumber = testCase(0,testSimulatorRuns,caseNumber);

        caseNumber = testCase(1,testSimulatorRuns,caseNumber);
        caseNumber = testCase(10,testSimulatorRuns,caseNumber);
        caseNumber = testCase(100,testSimulatorRuns,caseNumber);
        caseNumber = testCase(20,testSimulatorRuns,caseNumber);
        caseNumber = testCase(1,testSimulatorRuns,caseNumber);

        testSimulatorRuns.reset();
        caseNumber = 0;
        System.out.println("After reset:");
        caseNumber = testCase(0,testSimulatorRuns,caseNumber);

        caseNumber = testCase(12,testSimulatorRuns,caseNumber);
        caseNumber = testCase(134,testSimulatorRuns,caseNumber);
        caseNumber = testCase(110,testSimulatorRuns,caseNumber);
        caseNumber = testCase(203,testSimulatorRuns,caseNumber);
        caseNumber = testCase(2,testSimulatorRuns,caseNumber);
    }
   /** This testCase method runs test case and checks whether all the coin toss results add up to the total number of trials
   */
    private static int testCase(int nunRuns, CoinTossSimulator testSimulatorRuns, int caseNumber) {
        if (nunRuns < 1){
           // Do Not Run Simulation
        }
        else {
            testSimulatorRuns.run(nunRuns);
            System.out.println("After run("+nunRuns+"):");
        }
        caseNumber = caseNumber + nunRuns;
        System.out.println("Number of trials [exp:"+ caseNumber +"]: " + testSimulatorRuns.getNumTrials());
        System.out.println("Two-head tosses: " + testSimulatorRuns.getTwoHeads());
        System.out.println("Two-tail tosses: " + testSimulatorRuns.getTwoTails());
        System.out.println("One-head one-tail tosses: " + testSimulatorRuns.getHeadTails());
        if (testSimulatorRuns.getNumTrials() == testSimulatorRuns.getTwoHeads() + testSimulatorRuns.getTwoTails() + testSimulatorRuns.getHeadTails()) {
            System.out.println("Tosses add up correctly? true");
        }
        else {
            System.out.println("Tosses add up correctly? false");
        }
        System.out.println("");
        return caseNumber;
    }
}

