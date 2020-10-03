# Problem Set 5: 6.00 Word Game
# Name: Emma Nelson 
# Collaborators: Daniel Shtibel
# Time: 
#

import random
import string
import time
import itertools
VOWELS = 'aeiou'
CONSONANTS = 'bcdfghjklmnpqrstvwxyz'
HAND_SIZE = 7
TIME_ALLOWED = 30
SCRABBLE_LETTER_VALUES = {
    'a': 1, 'b': 3, 'c': 3, 'd': 2, 'e': 1, 'f': 4, 'g': 2, 'h': 4, 'i': 1, 'j': 8, 'k': 5, 'l': 1, 'm': 3, 'n': 1, 'o': 1, 'p': 3, 'q': 10, 'r': 1, 's': 1, 't': 1, 'u': 1, 'v': 4, 'w': 4, 'x': 8, 'y': 4, 'z': 10
}
rearrange_dict = {}
# -----------------------------------
# Helper code
# (you don't need to understand this helper code)

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
    wordlist = []
    for line in inFile:
        wordlist.append(line.strip().lower())
    print ("  ", len(wordlist), "words loaded.")
    return wordlist


def get_words_to_points(word_list):
     """
     Return a dict that maps every word in word_list to its point value. 
     """
     points_dict = {}
     for word in word_list:
         points_dict[word] = get_word_score(word, HAND_SIZE)
     return points_dict

def get_rearrange_dict(word_list):
    for word in word_list:
        sorted_letters = ''
        rearrange_dict[sorted_letters.join(sorted(word))] = word
    return rearrange_dict
        
def pick_best_word_faster(hand, rearrange_dict):
    """
    faster version of pick_best_word
    Return the highest scoring word from points_dict that can be made with the
    given hand.
    Return '.' if no words can be made with the given hand.
    """
    hand_letters = display_hand(hand)
    word_score = 0
    word = '.'
    for n in range(HAND_SIZE):
        word_letter_sets = list(itertools.combinations("".join(sorted(hand_letters)),n+1))
        for k in range(len(word_letter_sets)):
            if "".join(word_letter_sets[k]) in rearrange_dict.keys():
                word = rearrange_dict["".join(word_letter_sets[k])]
                new_word_score = points_dict[word]
                if new_word_score > word_score:
                    word_score = new_word_score
                    winning_word = word
    return word  
def pick_best_word(hand, points_dict):
    """
     Return the highest scoring word from points_dict that can be made with the
     given hand.
     Return '.' if no words can be made with the given hand.
    """
    word_score = 0
    winning_word = ''
    for word in points_dict.keys():
        if is_valid_word(word, hand, points_dict):
            new_word_score = points_dict[word]
            if new_word_score > word_score:
                word_score = new_word_score
                winning_word = word
    if word_score > 0:
        return winning_word
    else:
        return '.'

def get_frequency_dict(sequence):
    """
    Returns a dictionary where the keys are elements of the sequence
    and the values are integer counts, for the number of times that
    an element is repeated in the sequence.

    sequence: string or list
    return: dictionary
    """
    # freqs: dictionary (element_type -> int)
    freq = {}
    for x in sequence:
        freq[x] = freq.get(x,0) + 1
    return freq

def get_time_limit(points_dict, k):
    """
    Return the time limit for the computer player as a function of the
    multiplier k.
    points_dict should be the same dictionary that is created by
    get_words_to_points.
    """
    start_time = time.time()
    # Do some computation. The only purpose of the computation is so we can
    # figure out how long your computer takes to perform a known task.
    for word in points_dict:
        get_frequency_dict(word)
        get_word_score(word, HAND_SIZE)
        end_time = time.time()
    return (end_time - start_time) * k         

# Problem #1: Scoring a word
#
def get_word_score(word, n):
    """
    Returns the score for a word. Assumes the word is a
    valid word.

    The score for a word is the sum of the points for letters
    in the word, plus 50 points if all n letters are used on
    the first go.

    Letters are scored as in Scrabble; A is worth 1, B is
    worth 3, C is worth 3, D is worth 2, E is worth 1, and so on.

    word: string (lowercase letters)
    returns: int >= 0
    """
    score = 0
    for i in range(0,len(word)):
        score = score + SCRABBLE_LETTER_VALUES[word[i]]
    if len(word) == n:
        score = score + 50
    return score


def display_hand(hand):
    """
    Displays the letters currently in the hand.

    For example:
       display_hand({'a':1, 'x':2, 'l':3, 'e':1})
    Should print out something like:
       a x x l l l e
    The order of the letters is unimportant.

    hand: dictionary (string -> int)
    """
    output_str = ''
    for letter in hand.keys():
        for j in range(hand[letter]):
            output_str = output_str + letter
    print(output_str)
    return output_str


def deal_hand(n):
    """
    Returns a random hand containing n lowercase letters.
    At least n/3 the letters in the hand should be VOWELS.

    Hands are represented as dictionaries. The keys are
    letters and the values are the number of times the
    particular letter is repeated in that hand.

    n: int >= 0
    returns: dictionary (string -> int)
    """
    hand={}
    num_vowels = round(n / 3)+1
    
    for i in range(num_vowels):
        x = VOWELS[random.randrange(0,len(VOWELS))]
        hand[x] = hand.get(x, 0) + 1
        
    for i in range(num_vowels, n):    
        x = CONSONANTS[random.randrange(0,len(CONSONANTS))]
        hand[x] = hand.get(x, 0) + 1
    return hand

#
# Problem #2: Update a hand by removing letters
#
def update_hand(hand, word):
    """
    Assumes that 'hand' has all the letters in word.
    In other words, this assumes that however many times
    a letter appears in 'word', 'hand' has at least as
    many of that letter in it. 

    Updates the hand: uses up the letters in the given word
    and returns the new hand, without those letters in it.

    Has no side effects: does not mutate hand.

    word: string
    hand: dictionary (string -> int)    
    returns: dictionary (string -> int)
    """
    for letter in word:
        hand[letter] = hand.get(letter)-1
    return hand
    

#
# Problem #3: Test word validity
#
def is_valid_word(word, hand, points_dict):
    """
    Returns True if word is in the word_list and is entirely
    composed of letters in the hand. Otherwise, returns False.
    Does not mutate hand or word_list.
    
    word: string
    hand: dictionary (string -> int)
    word_list: list of lowercase strings
    """
    if word not in points_dict.keys():
        return False
    for letter in set(word):
        if hand.get(letter,0) == 0:
            return False
        if word.count(letter) > hand[letter]:
            return False
    return True

#
# Problem #4: Playing a hand
#
  

def play_hand(hand, points_dict):
    """
    Allows the user to play the given hand, as follows:

    * The hand is displayed.
    
    * The user may input a word.

    * An invalid word is rejected, and a message is displayed asking
      the user to choose another word.

    * When a valid word is entered, it uses up letters from the hand.

    * After every valid word: the score for that word and the total
      score so far are displayed, the remaining letters in the hand 
      are displayed, and the user is asked to input another word.

    * The sum of the word scores is displayed when the hand finishes.

    * The hand finishes when there are no more unused letters.
      The user can also finish playing the hand by inputing a single
      period (the string '.') instead of a word.

    * The final score is displayed.

      hand: dictionary (string -> int)
      word_list: list of lowercase strings
    """
    
    user_input = ''
    score = 0
    total_allocated_time = TIME_ALLOWED
    keep_playing = True
    while keep_playing:
        print("Current Hand: ",display_hand(hand))
        start_time = time.time()
        #user_input = input("Enter word, or a . to indicate that you are finished: ")
        user_input = pick_best_word(hand, points_dict)
        end_time = time.time()
        total_time = end_time - start_time
        print (user_input,'Slow get best word: It took %0.2f to seconds to provide an answer' % total_time)
        
        start_time = time.time()
        pick_best_word_faster(hand, rearrange_dict)
        end_time = time.time()
        total_time = end_time - start_time
        print (user_input,'Fast get best word: It took %0.2f to seconds to provide an answer' % total_time)

        total_allocated_time = total_allocated_time - total_time
        if total_allocated_time < 0:
            print('Total time exceeds %0.2f seconds' % TIME_ALLOWED)
            keep_playing = False
        elif total_allocated_time >= 0:
            print('You have %0.2f seconds remaining.' % total_allocated_time)
            if total_allocated_time == 0:
                keep_playing = False
        if user_input == '.':
            keep_playing = False
        if keep_playing == False:
            print("Thank you for playing!")
            break
        if is_valid_word(user_input, hand, points_dict):
            round_score = get_word_score(user_input, HAND_SIZE)
            score = score + round_score
            print(user_input," earned ",round_score,"points. Total: ",score," points")
            hand = update_hand(hand, user_input)
        else:
            print('Word not Valid')

#       
# Problem #5: Playing a game
# Make sure you understand how this code works!
# 
def play_game(points_dict):
    """
    Allow the user to play an arbitrary number of hands.

    * Asks the user to input 'n' or 'r' or 'e'.

    * If the user inputs 'n', let the user play a new (random) hand.
      When done playing the hand, ask the 'n' or 'e' question again.

    * If the user inputs 'r', let the user play the last hand again.

    * If the user inputs 'e', exit the game.

    * If the user inputs anything else, ask them again.
    """
    # TO DO ...
##    print( "play_game not implemented."     )    # delete this once you've completed Problem #4
##    play_hand(deal_hand(HAND_SIZE), word_list) # delete this once you've completed Problem #4
    
    ## uncomment the following block of code once you've completed Problem #4
    hand = deal_hand(HAND_SIZE) # random init
    while True:
        cmd = input('Enter n to deal a new hand, r to replay the last hand, or e to end game: ')
        if cmd == 'n':
            hand = deal_hand(HAND_SIZE)
            play_hand(hand.copy(), points_dict)
            print
        elif cmd == 'r':
            play_hand(hand.copy(), points_dict)
            print
        elif cmd == 'e':
            break
        else:
            print ("Invalid command.")

#
# Build data structures used for entire session and play game
#
if __name__ == '__main__':
    word_list = load_words()
    points_dict = get_words_to_points(word_list)
    TIME_ALLOWED = get_time_limit(points_dict, 2)
    rearrange_dict = get_rearrange_dict(word_list)
    play_game(points_dict)
    
## Problem 5 ##
# your response here.
# as many lines as you want. 
##pick_best_word
##O(len(word_list))
##there are 83667 words
##
##pick_best_word_faster
##O(C(HAND_SIZE, HAND_SIZE,0)+...+C(HAND_SIZE, 1))
##
##O(len(HAND_SIZE))*O(C(HAND_SIZE, HAND_SIZE/2))
##where C(n,r) = C(n,r)=n!/(n−r)! r!
