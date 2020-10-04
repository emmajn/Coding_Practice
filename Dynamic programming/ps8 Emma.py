# 6.00 Problem Set 8
#
# Intelligent Course Advisor
#
# Name:
# Collaborators:
# Time:
#

import time

SUBJECT_FILENAME = "subjects.txt"
VALUE, WORK = 0, 1
subjects = {}

#
# Problem 1: Building A Subject Dictionary
#
def loadSubjects(filename):
    """
    Returns a dictionary mapping subject name to (value, work), where the name
    is a string and the value and work are integers. The subject information is
    read from the file named by the string filename. Each line of the file
    contains a string of the form "name,value,work".

    returns: dictionary mapping subject name to (value, work)
    """

    # The following sample code reads lines from the specified file and prints
    # each one.
    inputFile = open(filename)
    
    for line in inputFile:
        course_info =  line.split(',')
        subjects[course_info[0]] = [int(x) for x in course_info[1:] ]
    return(subjects)

    # TODO: Instead of printing each line, modify the above to parse the name,
    # value, and work of each subject and create a dictionary mapping the name
    # to the (value, work).
subjects = loadSubjects(SUBJECT_FILENAME)

def printSubjects(subjects):
    """
    Prints a string containing name, value, and work of each subject in
    the dictionary of subjects and total value and work of all subjects
    """
    totalVal, totalWork = 0,0
    if len(subjects) == 0:
        return 'Empty SubjectList'
    res = 'Course\tValue\tWork\n======\t====\t=====\n'
    subNames = subjects.keys()
    subNames = sorted(subNames)
    for s in subNames:
        val = subjects[s][VALUE]
        work = subjects[s][WORK]
        res = res + s + '\t' + str(val) + '\t' + str(work) + '\n'
        totalVal += val
        totalWork += work
    res = res + '\nTotal Value:\t' + str(totalVal) +'\n'
    res = res + 'Total Work:\t' + str(totalWork) + '\n'
    print (res)
    
printSubjects(subjects)
def cmpValue(subInfo1, subInfo2):
    """
    Returns True if value in (value, work) tuple subInfo1 is GREATER than
    value in (value, work) tuple in subInfo2
    """
    val1 = subInfo1[VALUE]
    val2 = subInfo2[VALUE]
    return  val1 > val2

def cmpWork(subInfo1, subInfo2):
    """
    Returns True if work in (value, work) tuple subInfo1 is LESS than than work
    in (value, work) tuple in subInfo2
    """
    work1 = subInfo1[WORK]
    work2 = subInfo2[WORK]
    return  work1 < work2

def cmpRatio(subInfo1, subInfo2):
    """
    Returns True if value/work in (value, work) tuple subInfo1 is 
    GREATER than value/work in (value, work) tuple in subInfo2
    """
    val1 = subInfo1[VALUE]
    val2 = subInfo2[VALUE]
    work1 = subInfo1[WORK]
    work2 = subInfo2[WORK]
    return float(val1) / work1 > float(val2) / work2
    
#
# Problem 2: Subject Selection By Greedy Optimization
#
def greedyAdvisor(subjects, maxWork, comparator):
    """
    Returns a dictionary mapping subject name to (value, work) which includes
    subjects selected by the algorithm, such that the total work of subjects in
    the dictionary is not greater than maxWork.  The subjects are chosen using
    a greedy algorithm.  The subjects dictionary should not be mutated.

    subjects: dictionary mapping subject name to (value, work)
    maxWork: int >= 0
    comparator: function taking two tuples and returning a bool
    returns: dictionary mapping subject name to (value, work)
    """
    nameList = list(subjects.keys())
    tupleList = list(subjects.values())    
    classes_to_take = {}
    current_work_load = 0
    while current_work_load < maxWork:
        subject_to_add = nameList[0]
        subject_work_load = tupleList[0][1]
        subInfo1 = tupleList[0]
        pop_index = 0
        for a in range(1,len(nameList)):
            subInfo2 = tupleList[a]
            if not comparator(subInfo1, subInfo2):
                subInfo1 = subInfo2
                subject_to_add = nameList[a]
                subject_work_load = tupleList[a][1]
                pop_index = a
        nameList.pop(pop_index)
        tupleList.pop(pop_index)
        current_work_load = current_work_load + subject_work_load
        if current_work_load < maxWork:
            classes_to_take[subject_to_add] = tuple(subInfo1)
    return classes_to_take

smallCatalog = {'6.00': (16, 8), '1.00': (7, 7),'6.01': (5, 3),'15.01': (9, 6),'16.00':(5,1)} 
smallCatalog = {'2.00': [5, 9], '2.01': [2, 2], '2.02': [1, 17], '2.03': [6, 1], '2.04': [3, 2], '2.05': [2, 2], '2.06': [2, 1], '2.07': [1, 1], '2.08': [5, 5], '2.09': [5, 2], '2.10': [6, 12], '2.11': [9, 19]}
##smallCatalog = {'2.00': [5, 9], '2.01': [2, 2], '2.02': [1, 17], '2.03': [6, 1], '2.04': [3, 2], '2.05': [2, 2], '2.06': [2, 1], '2.07': [1, 1], '2.08': [5, 5], '2.09': [5, 2]}
#print(greedyAdvisor( subjects, 15, cmpWork))

def bruteForceAdvisor(subjects, maxWork):
    """
    Returns a dictionary mapping subject name to (value, work), which
    represents the globally optimal selection of subjects using a brute force
    algorithm.

    subjects: dictionary mapping subject name to (value, work)
    maxWork: int >= 0
    returns: dictionary mapping subject name to (value, work)
    """
    nameList = list(subjects.keys())
    tupleList = list(subjects.values())

    bestSubset, bestSubsetValue = \
            bruteForceAdvisorHelper(tupleList, maxWork, 0, None, None, [], 0, 0)
    end_time = time.time()

    outputSubjects = {}
    for i in bestSubset:
        outputSubjects[nameList[i]] = tupleList[i]
    print(bestSubsetValue)
    return outputSubjects

def bruteForceAdvisorHelper(subjects, maxWork, i, bestSubset, bestSubsetValue,
                            subset, subsetValue, subsetWork):
  
    # Hit the end of the list.
    if i >= len(subjects):
        if bestSubset == None or subsetValue > bestSubsetValue:
            # Found a new best.
##            print('end here', i)
            return subset[:], subsetValue
        else:
            # Keep the current best.
            return bestSubset, bestSubsetValue
    else:
        s = subjects[i]
        # Try including subjects[i] in the current working subset.
        if subsetWork + s[WORK] <= maxWork:
            subset.append(i)
##            print(i)
            bestSubset, bestSubsetValue = bruteForceAdvisorHelper(subjects,
                    maxWork, i+1, bestSubset, bestSubsetValue, subset,
                    subsetValue + s[VALUE], subsetWork + s[WORK])
            subset.pop()
##            print('end of recursion' ,subset)
##        print(bestSubset)
##        print(bestSubsetValue)
        bestSubset, bestSubsetValue = bruteForceAdvisorHelper(subjects,
                maxWork, i+1, bestSubset, bestSubsetValue, subset,
                subsetValue, subsetWork)
##        print(i,subsetWork, bestSubset, bestSubsetValue)
        return bestSubset, bestSubsetValue

#
# Problem 3: Subject Selection By Brute Force
#

def bruteForceTime():
    """
    Runs tests on bruteForceAdvisor and measures the time required to compute
    an answer.
    """
    start_time = time.time()
    print(bruteForceAdvisor(smallCatalog, 150))
    end_time = time.time()
    print(end_time - start_time)
    start_time = time.time()
    print(greedyAdvisor( smallCatalog, 15, cmpValue))
    end_time = time.time()
    print(end_time - start_time)

##bruteForceTime()
# Problem 3 Observations
# ======================
#
# TODO: write here your observations regarding bruteForceTime's performance

#
# Problem 4: Subject Selection By Dynamic Programming
#
def dpAdvisor(subjects, maxWork):
    """
    Returns a dictionary mapping subject name to (value, work) that contains a
    set of subjects that provides the maximum value without exceeding maxWork.

    subjects: dictionary mapping subject name to (value, work)
    maxWork: int >= 0
    returns: dictionary mapping subject name to (value, work)
    """

    nameList = list(subjects.keys())
    tupleList = list(subjects.values())

    dp = [(x,y) for x in range(maxWork+1) for y in range(len(nameList)+1)]
    memo_dict = {}
    for i in range(0, len(dp)):
        memo_dict[dp[i]] = [-1, -1]
    bestSubset, bestSubsetValue = \
            dpAdvisor_helper(memo_dict, tupleList, maxWork, 0, None, None, [], 0, 0)
    outputSubjects = {}
    for i in bestSubset:
        outputSubjects[nameList[i]] = tupleList[i]
    print('final value', bestSubsetValue)
##    print(memo_dict)
    return outputSubjects

def dpAdvisor_helper(memo_dict, subjects, maxWork, i, bestSubset, bestSubsetValue,
                                subset, subsetValue, subsetWork):
    
    # Hit the end of the list.
##    print('function inputs', i, subsetWork, memo_dict[(subsetWork,i)][1], bestSubsetValue)
    if bestSubsetValue != None and memo_dict[(subsetWork,i)][1] >= bestSubsetValue:
##        print('i', i,'work',subsetWork,bestSubset, 'found in dict',memo_dict[(subsetWork,i)])

##        print('hi')
        return memo_dict[(subsetWork,i)][0], memo_dict[(subsetWork,i)][1]
    elif i >= len(subjects):
        if bestSubset == None or subsetValue > bestSubsetValue:          
            bestSubset = subset[:]
            bestSubsetValue = subsetValue
            return bestSubset, bestSubsetValue
        else:
            return bestSubset, bestSubsetValue
    else:
        s = subjects[i]
        # Try including subjects[i] in the current working subset.
        if subsetWork + s[WORK] <= maxWork:
            subset.append(i)
            subset1 = subset
            subsetValue1 = subsetValue + s[VALUE]
            subsetWork1 = subsetWork + s[WORK]
            if subsetValue1 > memo_dict[(subsetWork1,i)][1]:
##                print('put in dict', i, subsetWork1, subset1, subsetValue1)
                memo_dict[(subsetWork1,i)] = [tuple(subset1), subsetValue1]
            bestSubset, bestSubsetValue = dpAdvisor_helper(memo_dict, subjects,
                    maxWork, i+1, bestSubset, bestSubsetValue, subset,
                    subsetValue + s[VALUE], subsetWork + s[WORK])
            subset.pop() 
        subset2 = subset
        subsetValue2 = subsetValue
        subsetWork2 = subsetWork
        if subsetValue2 > memo_dict[(subsetWork2,i)][1]:
##            print('put in dict', i, subsetWork2, subset2, subsetValue2)
            memo_dict[(subsetWork2,i)] = [tuple(subset2), subsetValue2]
        bestSubset, bestSubsetValue = dpAdvisor_helper( memo_dict, subjects,
                maxWork, i+1, bestSubset, bestSubsetValue, subset,
                subsetValue, subsetWork)
##        print(i,subsetWork, bestSubset, bestSubsetValue)
        return bestSubset, bestSubsetValue
        
##subjects = {'2.00': [5, 9], '2.01': [2, 2], '2.02': [1, 17], '2.03': [6, 1], '2.04': [3, 2], '2.05': [2, 2], '2.06': [2, 1], '2.07': [1, 1], '2.08': [5, 5], '2.09': [5, 2], '2.10': [6, 12], '2.11': [9, 19], '2.12': [10, 4], '2.13': [5, 20], '2.14': [8, 19], '2.15': [9, 19], '2.16': [9, 20], '2.17': [10, 20], '2.18': [6, 16], '2.19': [6, 19], '3.00': [1, 10], '3.01': [2, 19], '3.02': [8, 7], '3.03': [7, 12], '3.04': [4, 5], '3.05': [10, 15], '3.06': [1, 6], '3.07': [5, 11], '3.08': [4, 14], '3.09': [8, 18], '3.10': [8, 19], '3.11': [3, 11], '3.12': [5, 4], '3.13': [5, 4], '3.14': [7, 6], '3.15': [7, 17], '3.16': [8, 6], '3.17': [3, 16], '3.18': [7, 6], '3.19': [7, 19], '4.00': [4, 11], '4.01': [2, 5], '4.02': [5, 13], '4.03': [6, 16], '4.04': [3, 7], '4.05': [7, 13], '4.06': [7, 15], '4.07': [5, 4], '4.08': [6, 4], '4.09': [5, 12]}        
        

#
# Problem 5: Performance Comparison
#
def dpTime():
    """
    Runs tests on dpAdvisor and measures the time required to compute an
    answer.
    """
    print('hi')

print('DP Advisor')
start_time = time.time()
print(dpAdvisor( subjects, 7))
end_time = time.time()
print(end_time - start_time)

start_time = time.time()
print(bruteForceAdvisor(subjects, 7-))
end_time = time.time()
print(end_time - start_time)
# Problem 5 Observations
# ======================
#
# TODO: write here your observations regarding dpAdvisor's performance and
# how its performance compares to that of bruteForceAdvisor.
