// Name: Emma Nelson
// USC NetID: emmanels
// CS 455 PA5
// Fall 2021

// pa5list.cpp
// a program to test the linked list code necessary for a hash table chain

// You are not required to submit this program for pa5.

// We gave you this starter file for it so you don't have to figure
// out the #include stuff.  The code that's being tested will be in
// listFuncs.cpp, which uses the header file listFuncs.h

// The pa5 Makefile includes a rule that compiles these two modules
// into one executable.

#include <iostream>
#include <string>
#include <cassert>


#include "listFuncs.h"

#include <string>


using namespace std;

void testEmpty() {
   ListType empty;
   initList(empty);
   cout << "an empty list: ";
   printList(empty);

   cout << "printLast of empty list: ";
   printLast(empty);
   cout << endl;
}

void testchainLength() {
   
      
   ListType list;
   initList(list);
   cout << "Empty list [exp: 0]: " << chainLength(list) << endl; 
      
   insertFront(list, "Emma", 100);
   
   cout << "One item list [exp: 1]: " << chainLength(list) << endl; 
   
   insertFront(list, "Lori", 50);
   insertFront(list, "Dan", 25);
   insertFront(list, "Steven", 25);
   insertFront(list, "Stacy", 25);
   insertFront(list, "Eric", 25);
   insertFront(list, "Carol", 25);
   insertFront(list, "Sara", 25);
 
   cout << "Entire list [exp: 8]: " << chainLength(list) << endl;    
}

void testRemoveFront(ListType & list) {
   removeFront(list);
   cout << "after remove front from first list: ";
   printList(list);
   removeFront(list);
   cout << "after remove front from first list: ";
   printList(list);
   removeFront(list);
   cout << "after remove front from first list: ";
   printList(list);
   cout << endl;
}

void testRemoveLast(ListType & list) {
   removeLast(list);
   cout << "after remove last from second list: ";
   printList(list);

   removeLast(list);
   cout << "after remove last from second list: ";
   printList(list);
   cout << endl;
}



int main() {

   testEmpty();

   ListType list;
   initList(list);

   insertFront(list, "Emma", 100);
   insertFront(list, "Lori", 50);
   insertFront(list, "Dan", 25);
   insertFront(list, "Steven", 25);
   insertFront(list, "Stacy", 25);
   insertFront(list, "Eric", 25);
   insertFront(list, "Carol", 25);
   insertFront(list, "Sara", 25); 
   
   cout << "a list: ";
   printList(list);

   Node *list2;
   initList(list2);
   insertFront(list2, "Harry", 12);
   insertFront(list2, "Ron", 17);

   cout << "another list: ";
   printList(list2);

   cout << "printLast of list: ";
   printLast(list);

   cout << "printLast of the other list: ";
   printLast(list2);
   cout << endl;

   testRemoveFront(list);
   testRemoveLast(list2);
   
   cout << "testing remove target Lori: " << endl; 
   
   listRemove(list, "Lori");
   printList(list); 
   
   testchainLength();
   
   cout << "Is Emma in List [exp: 1]: " << keyInList(list, "Emma") << endl;
   cout << "Is Larry in List [exp: 0]: " << keyInList(list, "Larry") << endl;
   
   
   cout << "testing remove target Emma: " << endl; 
   
   listRemove(list, "Emma");
   printList(list);    
   
   cout << "testing remove target Candy: " << endl; 
   
   listRemove(list, "Candy");
   printList(list);   
   
   insertFront(list, "Jenny", 100);
   
   cout << "Looking Up Jenny's Score [exp: 100]: " << lookUpValue(list, "Jenny" ) << endl;
   changeValue(list, "Jenny", 200);
   cout << "Looking Up Jenny's Score [exp: 200]: " << lookUpValue(list, "Jenny" ) << endl;   
   
   return 0;
}
