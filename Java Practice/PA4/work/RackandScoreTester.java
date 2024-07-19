// Name: Emma Nelson
// USC NetID: emmanels
// CS 455 PA4
// Fall 2021

public class RackandScoreTester {
    public static void main(String[] args){
        testRackDictionary("cca");
        testScoreConstructor();
        testScoreCounter();
    }
    private static void testRackDictionary(String s){
        Rack myTestRack = new Rack(s);
        System.out.println(myTestRack.toString());
        System.out.println(myTestRack.getAllSubsetsOfWords());
    }
    private static void testScoreConstructor(){
        ScoreTable myScoreTable = new ScoreTable();
        System.out.println(myScoreTable.toString());
    }
    private static void testScoreCounter(){
        ScoreTable myScoreTable = new ScoreTable();
        System.out.println("Points for AEIOULNSTR [exp: 10]: " + myScoreTable.getScore("AEIOULNSTR"));
        System.out.println("Points for DG [exp: 4]: " + myScoreTable.getScore("DG"));
        System.out.println("Points for BCMP [exp: 12]: " + myScoreTable.getScore("BCMP"));
        System.out.println("Points for FHVWY [exp: 20]: " + myScoreTable.getScore("FHVWY"));
        System.out.println("Points for K [exp: 5]: " + myScoreTable.getScore("K"));
        System.out.println("Points for JX [exp: 16]: " + myScoreTable.getScore("JX"));
        System.out.println("Points for QZ [exp: 20]: " + myScoreTable.getScore("QZ"));
        System.out.println("Points for qz [exp: 20]: " + myScoreTable.getScore("qz"));
        System.out.println("Points for clam [exp: 8]: " + myScoreTable.getScore("clam"));
        System.out.println("Points for house [exp: 8]: " + myScoreTable.getScore("house"));
    }
}
