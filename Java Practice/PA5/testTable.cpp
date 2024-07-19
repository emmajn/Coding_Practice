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

   // add more code here
   // Reminder: use -> when calling Table methods, since grades is type Table*

   // insert a new pair into the table
   // return false iff this key was already present 
   //         (and no change made to table)
   grades->insert("Emma", 100);
   grades->hashStats(cout);
   
   grades->insert("Emma", 100);
   grades->hashStats(cout);
   
   grades->insert("Sue", 23);
   
   grades->insert("Ella", 1);
   
   grades->insert("Ken", 99);
   
   grades->insert("Steven", 33);
   
   grades->insert("Allie", 44);

   grades->insert("Nick", 79);
   
   grades->hashStats(cout);
   // returns the address of the value that goes with this key
   // or NULL if key is not present.
   //   Thus, this method can be used to either lookup the value or
   //   update the value that goes with this key.
//   int *lookup(const std::string &key);

   // remove a pair given its key
   // return false iff key wasn't present
   //         (and no change made to table)
    
   grades->printAll();
   if (grades->remove("Emma")){
      cout << "Emma Removed"<< endl;
   }
   if (!grades->remove("Dori")){
      cout << "Dori Not Found in Hashtable"<< endl;
   }
   grades->printAll();
   
   int* score = grades->lookup("Allie");
   cout << "Grades for Allie [exp: 44]: " << *score << endl; 
   cout << "Grades for Aaron [exp: null]: " << grades->lookup("Aaron") << endl; 
   return 0;
}
