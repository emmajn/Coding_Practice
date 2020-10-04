# Problem Set 5: Ghost
# Name: 
# Collaborators: 
# Time: 
#

import random

# -----------------------------------
# Helper code
# (you don't need to understand this helper code)
import string

WORDLIST_FILENAME = "words.txt"

def load_words():
    """
    Returns a list of valid words. Words are strings of lowercase letters.
    
    Depending on the size of the word list, this function may
    take a while to finish.
    """
    print ("Loading word list from file...")
    # inFile: file
    inFile = open(WORDLIST_FILENAME, 'r')
    # wordlist: list of strings
    word_list = []
    for line in inFile:
        word_list.append(line.strip().lower())
    print ("  ", len(word_list), "words loaded.")
    return word_list

def stop_game(string_fragment, word_list):
    """
    Returns True if word is in the word_list. Otherwise, returns False.
    Does not mutate hand or word_list.
    
    word: string
    word_list: list of lowercase strings
    """
    string_fragment = string_fragment.lower()
    print(string_fragment)
    
    for word in word_list:
        # Checks each word  in word list to see if it has the string fragment's beginning
        word_found = word.find(string_fragment)
        if word_found == 0 and len(string_fragment) < 4:
            # if it does first check if the fragment is larger than 
            return False
        if word_found == 0 and string_fragment in word_list:
            return True
        if word_found == 0:
            return False
    return True

    


def play_ghost(word_list):
    string_fragment = ''
    last_player = 2
    print("Welcome to Ghost!")
    while not stop_game(string_fragment, word_list):
        if last_player == 2:
            print("Player's 1 turn.")
            print("Current word fragment: ", string_fragment)
            valid_input = False
            while not valid_input:
                player_1_input = input("Player 1 enter a VALID letter: ")
                valid_input = player_1_input in string.ascii_letters
            string_fragment = string_fragment + player_1_input
            last_player = 1
        else:             
            print("Player 2's turn.")
            print("Current word fragment: ", string_fragment)
            valid_input = False
            while not valid_input:
                player_2_input = input("Player 2 enter a VALID letter: ")
                valid_input = player_2_input in string.ascii_letters
            last_player = 2
            string_fragment = string_fragment + player_2_input         
    if last_player == 1 and string_fragment in word_list:
        print("Player 1 loses because ",string_fragment," is a word!")
        print("Player 2 wins!")
    elif last_player == 2 and string_fragment in word_list:
        print("Player 2 loses because ",string_fragment," is a word!")
        print("Player 1 wins!")
    elif last_player == 1:
        print("Player 1 loses because ",string_fragment," is not a word!")
        print("Player 2 wins!")        
    else:
        print("Player 2 loses because ",string_fragment," is not a word!")
        print("Player 1 wins!")

# (end of helper code)
# -----------------------------------

# Actually load the dictionary of words and point to it with 
# the wordlist variable so that it can be accessed from anywhere
# in the program.
word_list = load_words()
play_ghost(word_list)
# TO DO: your code begins here!
