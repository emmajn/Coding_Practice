// Name: Emma Nelson
// USC NetID: emmanels
// CSCI 455 PA5
// Fall 2021


//*************************************************************************
// Node class definition 
// and declarations for functions on ListType

// Note: we don't need Node in Table.h
// because it's used by the Table class; not by any Table client code.

// Note2: it's good practice to *not* put "using" statement in *header* files.  Thus
// here, things from std libary appear as, for example, std::string

#ifndef LIST_FUNCS_H
#define LIST_FUNCS_H

#include <string>

using namespace std;

struct Node {
   std::string key;
   int value;

   Node *next;

   Node(const std::string &theKey, int theValue);

   Node(const std::string &theKey, int theValue, Node *n);
};


typedef Node * ListType;

//*************************************************************************
//add function headers (aka, function prototypes) for your functions
//that operate on a list here (i.e., each includes a parameter of type
//ListType or ListType&).  No function definitions go in this file.

// intializes and empty list
void initList(ListType &list);

// adds an item to the list in the front
void insertFront(ListType &list, const string &theKey, int theValue);

// prints the list in the following format
// key1 value1
// key2 value2
// key3 value3
//  ...
// keyn valuen
void printList(ListType list);

// prints the last key/value pair in the list
void printLast(ListType list);

// reoves the first key/value pair in the list
void removeFront(ListType &list);

// removes the last key/value pair inj the list
void removeLast(ListType &list);

// returns the number of key/values in the list
int chainLength(ListType & list);

// returns whether the key exists in the list
bool keyInList(ListType & list, const string & target);

// removes the key in the list
// if the key does not exist in the list, return false
bool listRemove(ListType & list, const string & target); 

// returns the value of the key
// if the key is not found, it returns -1
// this assumes all values are > 0
int lookUpValue(ListType & list, const string & target);

// Changes the value for a given key
// if the key does not exist return false
bool changeValue(ListType & list, const string & target, int newvalue);


// keep the following line at the end of the file
#endif
