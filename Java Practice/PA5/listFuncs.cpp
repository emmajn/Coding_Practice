// Name: Emma Nelson
// USC NetID: emmanels
// CSCI 455 PA5
// Fall 2021


#include <iostream>

#include <cassert>

#include "listFuncs.h"

#include <string>

using namespace std;

Node::Node(const string &theKey, int theValue) {
   key = theKey;
   value = theValue;
   next = NULL;
}

Node::Node(const string &theKey, int theValue, Node *n) {
   key = theKey;
   value = theValue;
   next = n;
}




//*************************************************************************
// put the function definitions for your list functions below



typedef Node* ListType;


void initList(ListType &list) {
   list = NULL;
}


// PRECONDITION: for all list functions except initList
//   param list must be a well-formed list.
// Well-formed list means list itself is NULL, or points to a chain
//   of linked nodes such that the last one has NULL in its next field.


// adds an item to the list in the front
void insertFront(ListType &list, const string &theKey, int theValue) {

   Node *newlist = new Node(theKey, theValue, list);

   list = newlist;

}


// prints the list in the following format
// key1 value1
// key2 value2
// key3 value3
//  ...
// keyn valuen
void printList(ListType list) {

   if (list == NULL) {
      cout << "<empty>";
   }

   Node *p = list;
   while (p != NULL) {
      cout << p->key << " " << p->value << endl;
      p = p->next;
   }
}

// prints the last key/value pair in the list
void printLast(ListType list) {

   if (list == NULL) {
      cout << "<empty>" << endl;
      return;
   }

   Node *p = list;
   while (p->next != NULL) {  // stop when p points to the last element in the list
      p = p->next;
   }

   cout << p->key << endl;
}

// reoves the first key/value pair in the list
void removeFront(ListType &list) {
   if (list != NULL) {
      Node *save = list->next;
      delete list;
      list = save;
   }
}


// removes the last key/value pair inj the list
void removeLast(ListType &list) {

   if (list == NULL) {  // no elements in list
      return;
   }

   if (list->next == NULL) {   // one element in list
      delete list;
      list = NULL;
      return;
   }
  
   // we know here that the list has at least 2 elements

   Node *p = list;
   while (p->next->next != NULL) {
      p = p->next;
   }

   // p currently points to second to last element

   Node *lastitem = p->next;
   p->next = NULL;  // p becomes the last element in the list
   delete lastitem;

}


// returns the number of key/values in the list
int chainLength(ListType & list){
   int currChainLength; 
   currChainLength = 1; 

   Node *p = list;
   
   if (list == NULL) {  // no elements in list
      return 0;
   }   
   
   while (p->next != NULL) {  // stop when p points to the last element in the list
      p = p->next;
      currChainLength = currChainLength + 1; 
   }
   return currChainLength; 
}

// returns whether the key exists in the list
bool keyInList(ListType & list, const string & target) { 
   Node *p = list;
      
   if (list == NULL) {  // no elements in list
      return false;
   }
   
   while (p->next != NULL) {
      if (p->key == target){
         return true; 
      }
      p = p->next;
   }
   if (p->key == target){
      return true; 
   }   
   return false;    
}

// removes the key in the list
bool listRemove(ListType & list, const string & target) {
   Node *p = list;
   string currentKey; 

   if (list == NULL) {  // no elements in list
      return false;
   }
   
   if (list->next == NULL) {   // one element in list
      delete list;
      list = NULL;
      return true;
   }
   
   while (p->next != NULL) {
      if (p->next->key == target){
         Node *targetNode = p->next;
         Node *restOfList = p->next->next; 
         p->next = restOfList;  // p becomes the last element in the list
         delete targetNode;
         return true; 
      }
      p = p->next;
   }
   // p currently points to element before target
   return false;

}

// returns the value of the key
// if the key is not found, it returns -1
// this assumes all values are > 0
int lookUpValue(ListType & list, const string & target){
   Node *p = list;
      
   if (list == NULL) {  // no elements in list
      return -1;
   }
   
   while (p->next != NULL) {
      if (p->key == target){
         return p->value; 
      }
      p = p->next;
   }
   if (p->key == target){
      return p->value; 
   }   
   return -1;   
}

// Changes the value for a given key
// if the key does not exist return false
bool changeValue(ListType & list, const string & target, int newvalue){
   Node *p = list;
      
   if (list == NULL) {  // no elements in list
      return false;
   }
   
   while (p->next != NULL) {
      if (p->key == target){
         p->value = newvalue; 
         return true; 
      }
      p = p->next;
   }
   if (p->key == target){
      p->value = newvalue; 
      return true; 
   }   
   return false;   
}