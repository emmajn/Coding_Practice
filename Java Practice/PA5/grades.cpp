// Name: Emma Nelson
// USC NetID: emmanels
// CSCI 455 PA5
// Fall 2021

/*
 * grades.cpp
 * A program to test the Table class.
 * How to run it:
 *      grades [hashSize]
 * 
 * the optional argument hashSize is the size of hash table to use.
 * if it's not given, the program uses default size (Table::HASH_SIZE)
 *
 */

#include "Table.h"

// cstdlib needed for call to atoi
#include <cstdlib>

using namespace std;

int processingUserINput (std::string const& inString) {
   // Returns the case number for the switch cases in integers
   // returtns negative the command not valid
   if (inString == "insert") return 1;
   if (inString == "change") return 2;
   if (inString == "lookup") return 3;
   if (inString == "remove") return 4;
   if (inString == "print") return 5;
   if (inString == "size") return 6;
   if (inString == "stats") return 7;
   if (inString == "help") return 8;
   if (inString == "quit") return 9;
   return -1; 
}

int main(int argc, char * argv[]) {

   // gets the hash table size from the command line

   int hashSize = Table::HASH_SIZE;

   Table * grades;  // Table is dynamically allocated below, so we can call
   // different constructors depending on input from the user.

   if (argc > 1) {
      hashSize = atoi(argv[1]);  // atoi converts c-string to int

      if (hashSize < 1) {
         
         cout << "Command line argument (hashSize) must be a positive number" 
              << endl;
         return 1;
      }

      grades = new Table(hashSize);

   }
   else {   // no command line args given -- use default table size
      grades = new Table();
   }


   grades->hashStats(cout);
   
   string usercommand;
   string name;
   int score; 
   
   bool keepgoing = true;
   int caseNumber; 



   do {
      cout << "cmd>";
      
      // gets user command and sees which case to execute depending on command
      cin >> usercommand;
      caseNumber = processingUserINput(usercommand);

 
      if (cin.fail()) {
         cout << "ERROR: input stream failed." << endl;
         keepgoing = false;
      }
      
      else {
         switch (caseNumber) {
         case 1: //Insert this name and score in the grade table. If this name was already present, print a message to that effect, and don't do the insert.
            cin >> name;
            cin >> score;
            grades->insert(name, score); 
            break;               
         case 2: //Change the score for name. Print an appropriate message if this name isn't present.
            cin >> name;
            cin >> score;
            grades->changeScore(name, score);             
            break;
         case 3: //Lookup the name, and print out his or her score, or a message indicating that student is not in the table.
            cin >> name;
            cout << "Grades for " << name << " " << *grades->lookup(name) << endl; 
            break;
         case 4: //Remove this student. If this student wasn't in the grade table, print a message to that effect.
            cin >> name;
            grades->remove(name);
            break;
         case 5: //Prints out all Names and Scores in the Table
            grades->printAll();
            break;
         case 6: // Prints out Number of Entries
            grades->numEntries(); 
            break;
         case 7: // Prints out stats
            grades->hashStats(cout);
            break;
         case 8: //Prints out a brief command summary.
            cout << "The following are Valid Commands" << endl;
            cout << "insert: Insert this name and score in the grade table. If this name was already present, print a message to that effect, and don't do the insert." << endl;
            cout << "change name newscore: Change the score for name. Print an appropriate message if this name isn't present." << endl;
            cout << "lookup name: Lookup the name, and print out his or her score, or a message indicating that student is not in the table." << endl; 
            cout << "remove name: Remove this student. If this student wasn't in the grade table, print a message to that effect."<< endl;
            cout << "print: Prints out all names and scores in the table." << endl;
            cout << "size: Prints out the number of entries in the table." << endl; 
            cout << "stats: Prints out statistics about the hash table at this point." << endl;
            cout << "quit: Exits the program."<< endl; 
            break;
         case 9: //Exits the program.
            keepgoing = false;
            break;
         default: //Not a valid command
            cout << "ERROR: invalid command" << endl;
            break;
         }
      }
    
    
   } while (keepgoing);

   return 0;   
}
