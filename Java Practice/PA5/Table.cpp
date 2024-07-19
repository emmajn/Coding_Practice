// Name: Emma Nelson
// USC NetID: emmanels
// CSCI 455 PA5
// Fall 2021

// Table.cpp  Table class implementation


#include "Table.h"

#include <iostream>
#include <string>
#include <cassert>

// for hash function called in private hashCode method defined below
#include <functional>

using namespace std;


// listFuncs.h has the definition of Node and its methods.  -- when
// you complete it it will also have the function prototypes for your
// list functions.  With this #include, you can use Node type (and
// Node*, and ListType), and call those list functions from inside
// your Table methods, below.

#include "listFuncs.h"


//*************************************************************************


Table::Table() {
   // initalizes hash table with HASH_SIZE number of bins
   // All bins and entries empty 
   tableData = new ListType[HASH_SIZE]();   
   hashSize =  HASH_SIZE; 
   entriesCount = 0;
   longestChain = 0; 
   nonEmptyBuckets = 0; 
}


Table::Table(unsigned int hSize) {
   // initalizes hash table with hSize number of bins
   // All bins and entries empty   
   tableData = new ListType[hSize]();
   entriesCount = 0; 
   longestChain = 0; 
   nonEmptyBuckets = 0; 
   hashSize = hSize; 
}


// Changes the score of the key (name)
// if the key is not found, this returns false and makes no changes
bool Table::changeScore(const string &key, int newValue) {
   int index; 
   index = hashCode(key); 
   bool valueChanged; 
   ListType currentlist;
   
   // key in table
   if (tableData[index] == 0){
      return false;
   }
   
   // Bin found 
   currentlist = tableData[index];
   
   // Check if key is found in chain
   valueChanged = changeValue(currentlist, key, newValue); 
   
   // Updates value
   tableData[index] = currentlist; 
   
   return valueChanged;   
}

// Looks up the value of the key
// if the key does not exist in the hash table, this returns false
int * Table::lookup(const string &key) {
   int index; 
   index = hashCode(key); 
   int *score; 
   
   // Bin not found
   if (tableData[index] == 0){
      return NULL;
   }
   
   // looks up value in chain since bin was found 
   int value = lookUpValue(tableData[index], key);
   
   score = &value;
   if (value > 0){
      return score; 
   }
   return NULL; 
}

// This removes an entry with the given key from the hash table
// if the key is not found in the hash table it returns false and does nothing to the hash table
bool Table::remove(const string &key) {
   int index; 
   index = hashCode(key); 
   ListType currentlist;
   bool foundInChain; 
   
   // bin not found
   if (tableData[index] == 0){
      return false;
   }

   // bin found 
   currentlist = tableData[index];
  
   // Check if key is found in chain and removes it if it is
   foundInChain = listRemove(currentlist, key);  
   tableData[index] = currentlist; 
   
   // Updates the number of entries
   entriesCount = entriesCount - 1; 
   
   // Updates if bucket got emptied
   if (chainLength(tableData[index]) == 0) { // bucket got emptied
      nonEmptyBuckets = nonEmptyBuckets - 1;    
   }
   
   // Updates the longest chain 
   updateLongestChain();
   return foundInChain;
   
}


// Inserts a key and value in the hashtable. 
// If the key already exists, this returns false and does nothing 
bool Table::insert(const string &key, int value) {
   int index; 
   index = hashCode(key); 
   ListType currentlist;
   
   // Bin not found
   if (tableData[index] == 0){
       // initalizes a list in bin 
      initList(currentlist);
      
      // Update entries and non-empty buckets
      entriesCount = entriesCount + 1; 
      nonEmptyBuckets = nonEmptyBuckets + 1; 
   }
   else {
      // gets current list in bin. 
      currentlist = tableData[index]; 
      
      // check if key exits in chain 
      if (keyInList(currentlist, key)){
         return false;
      }
      
      // updates the number of entries 
      entriesCount = entriesCount + 1;
   }
   
   // adds the new key and value 
   insertFront(currentlist, key, value);
   tableData[index] = currentlist; 
   
   // Updates Longest Chain
   if (chainLength(tableData[index]) > longestChain) { 
      longestChain = chainLength(tableData[index]); 
   }
   return true;  
}

// returns the total number of entries (including ones in a chain) 
int Table::numEntries() const {
   return entriesCount;      // dummy return value for stub
}

// Printes all the entries in the hashtable
void Table::printAll() const {
   for (int i = 0; i < hashSize; i ++) {
     if (tableData[i] != 0){
        printList(tableData[i]);
     }
   }
}

// Checks to see the longest chain by looking at each bin and checking chain length
void Table::updateLongestChain() {
   longestChain = 0; 
   for (int i = 0; i < hashSize; i ++) {
     if (tableData[i] != 0){
         if (chainLength(tableData[i]) > longestChain) { 
            longestChain = chainLength(tableData[i]); 
         }
     }
   }   
}

// prints out the number of buckets, entries, non-empty buckets, and longest chain. 
void Table::hashStats(ostream &out) const {
   out << "number of buckets: " << hashSize << endl; 
   out << "number of entries: " << entriesCount << endl; 
   out << "number of non-empty buckets: " << nonEmptyBuckets << endl;  
   out << "longest chain: " << longestChain << endl;
}


// hash function for a string
// (we defined it for you)
// returns a value in the range [0, hashSize)
unsigned int Table::hashCode(const string &word) const {

   // Note: calls a std library hash function for string (it uses the good hash
   //   algorithm for strings that we discussed in lecture).
   return hash<string>()(word) % hashSize;

}


// add definitions for your private methods here

