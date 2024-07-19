# PSET 3
# Emma Nelson
# Daniel Sthibel

from string import *

# Problem 1

def countSubStringMatch(target,key):
    # This function counts number of instances key is in the target string
    target_index = target.find(key)
    counter = 0
    while target_index >= 0:
        if target_index >= 0:
            counter = counter + 1
        target = target[target_index + 1:]
        target_index = target.find(key)
    return counter

def countSubStringMatchRecursive (target, key):
    # This function recursively counts number of instances key is in the target string
    target_index = target.find(key)
    if target_index < 0:
        return 0
    else:
        return 1 + countSubStringMatchRecursive(target[target_index+1:],key)

        
##print(countSubStringMatch("atgacatgcacaagtatgcat", "atgc"))
##print(countSubStringMatch("atgacatgcacaagtatgcat", "at"))

##print(countSubStringMatchRecursive("atgacatgcacaagtatgcat", "atgc"))
##print(countSubStringMatchRecursive("atgacatgcacaagtatgcat", "at"))
        

# Problem 2


def subStringMatchExact(target,key):
    # This function returns the starting index of each key in target
    target_index = target.find(key)
    list_of_index = []
    while target_index >= 0:
        if target_index >= 0:
            list_of_index.append(target_index)
        target_index = target.find(key, target_index+1)
    return tuple(list_of_index)

##print(subStringMatchExact("atgacatgcacaagtatgcat", "atgc"))
##print(subStringMatchExact("atgacatgcacaagtatgcat", "at"))

# Problem 3
# this is a code file that you can use as a template for submitting your
# solutions


# these are some example strings for use in testing your code

#  target strings

target1 = 'atgacatgcacaagtatgcat'
target2 = 'atgaatgcatggatgtaaatgcag'

# key strings

key10 = 'a'
key11 = 'atg'
key12 = 'atgc'
key13 = 'atgca'



### the following procedure you will use in Problem 3
def constrainedMatchPair(match1, match2, key1_length):
    filtered = ()
    for i in match1:
        if i + key1_length + 1 in match2:
            filtered = filtered + (i,)
    return filtered
    

def subStringMatchOneSub(key,target):
    """search for all locations of key in target, with one substitution"""
    allAnswers = ()
    for miss in range(0,len(key)):
        # miss picks location for missing element
        # key1 and key2 are substrings to match
        key1 = key[:miss]
        key2 = key[miss+1:]
##        print(miss)
##        print(key1)
##        print(key2)
##        print( 'breaking key',key,'into',key1,key2)
        # match1 and match2 are tuples of locations of start of matches
        # for each substring in target
        match1 = subStringMatchExact(target,key1)
##        print(match1)
        match2 = subStringMatchExact(target,key2)
##        print(match2)
        # when we get here, we have two tuples of start points
        # need to filter pairs to decide which are correct
        filtered = constrainedMatchPair(match1,match2,len(key1))
        allAnswers = allAnswers + filtered
##        print('match1',match1)
##        print('match2',match2)
##        print('possible matches for',key1,key2,'start at',filtered)
    unique_answers = ()
    for i in allAnswers:
        if i in unique_answers:
            pass
        else:
            unique_answers = unique_answers + (i,)
    return sorted(unique_answers)
        

print(subStringMatchOneSub(key10, target1))
print(key10)
print(target1)

print(subStringMatchOneSub(key11, target1))
print(key11)
print(target1)

print(subStringMatchOneSub(key12, target1))
print(key12)
print(target1)

print(subStringMatchOneSub(key13, target1))
print(key13)
print(target1)

# Problem 4

def subStringMatchExactlyOneSub(key, target):
    all_matches = subStringMatchOneSub(key, target)
    exact_matches = subStringMatchExact(target, key)
    print(all_matches)
    print(exact_matches)
    all_but_one_matches = ()
    for i in all_matches:
        if i in exact_matches:
            pass
        else:
            all_but_one_matches = all_but_one_matches + (i,)
    return all_but_one_matches
            
print(subStringMatchExactlyOneSub(key10, target1))
print(key10)
print(target1)

print(subStringMatchExactlyOneSub(key11, target1))
print(key11)
print(target1)

print(subStringMatchExactlyOneSub(key12, target1))
print(key12)
print(target1)

print(subStringMatchExactlyOneSub(key13, target1))
print(key13)
print(target1)


