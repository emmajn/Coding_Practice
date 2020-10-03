# Problem Set 2
# Name: Emma 

# Problem 1
# 6a + 9b + 20c = n
# n = 50 thru 55
# n = 55,
a = 1
b = 1
c = 2
#print(6*a + 9*b + 20*c)
# n = 54,
a = 6
b = 2
c = 0
#print(6*a + 9*b + 20*c)
# n = 53,
a = 1
b = 3
c = 1
#print(6*a + 9*b + 20*c)
# n = 52
a = 2
b = 0
c = 2
#6*a + 9*b + 20*c
# n = 51,
a = 1
b = 5
c = 0
#print(6*a + 9*b + 20*c)
# n = 50,
a = 2
b = 2
c = 1
#print(6*a + 9*b + 20*c)

# Problem 2
# 6a + 9b + 20c = x)       
# 6a + 9b + 20c = x + 1
# 6a + 9b + 20c = x + 2
# 6a + 9b + 20c = x + 3
# 6a + 9b + 20c = x + 4
# 6a + 9b + 20c = x + 5 

# 6a + 9b + 20c = x + 6 combination of x + 1 and x + 5
# 6a + 9b + 20c = x + 7 combination of x + 2 and x + 5
# Any comibnation of 1 thru five can add up to 10 and then just
# subtract n*x to get the number

# Problem 3


def find_consecutive(l):
    # INPUTS: l, a list 
    # This function looks at list with 6 number
    # and checks if those numbers are consecutive
    if l[0:6] == list(range(l[0], l[0]+6)):
        return True
    return False

def finding_munuggets(n, l, non_solutions):
    a = 0
    b = 0
    c = 0
    if n < 6:
        return non_solutions.append(n)
    if n%3 == 0:
        l.append(n)
        three_factor = n/3
        c = 0
        if three_factor%2 == 0:
            a = n/6
            b = 0
        if three_factor%2 == 1:
            b = 1
            a = (n-9)/6
        return [int(a), int(b), int(c)]
    if n%3 != 0 and n < 20:
        return non_solutions.append(n)
    if n >= 20:
        number_of_20s = int((n - n%20)/20)
        for c in range(1,number_of_20s+1):
            new_n = n - c*20
            if new_n == 0:
                l.append(n)
                return [int(a), int(b), int(c)]
            if new_n < 6:
                return non_solutions.append(n)
            if new_n%3 == 0:
                l.append(n)
                three_factor = new_n/3
                if three_factor%2 == 0:
                    a = new_n/6
                    b = 0
                if three_factor%2 == 1:
                    b = 1
                    a = (new_n-9)/6
                return [int(a), int(b), int(c)]
            print(new_n%3 != 0)
            if new_n%3 != 0 and new_n < 20:
                return non_solutions.append(n)

six_in_a_row = 0
l = []
n = 0
non_solutions = []

while six_in_a_row == 0:
    n = n + 1
    list1 = finding_munuggets(n, l, non_solutions)    
    nugget_gs = [6, 9, 20]
    if list1 != None:
        products = [x * y for x, y in zip(list1, nugget_gs)]
    if len(l)>5:
        if find_consecutive(l[-6:]):
            six_in_a_row = 1
print(l)
print(non_solutions)

# Problem 4

def finding_packages(n, l, non_solutions, packages):
    for a in range(0, round(n/packages[0])+2):
        for b in range(0, round(n/packages[1])+2):
            for c in range(0, round(n/packages[2])+2):
                coeff = [a, b, c]
                products = sum([x * y for x, y in zip(list(packages), coeff)])
                if products == n:
                    l.append(products)
                    return coeff
    return non_solutions.append(n)


six_in_a_row = 0
packages = (6, 9, 20)
l = []
n = 0
non_solutions = []
while six_in_a_row == 0:
    n = n + 1
    list1 = finding_packages(n, l, non_solutions, packages)    
    if list1 != None:
        products = [x * y for x, y in zip(list1, list(packages))]
    if len(l)>5:
        if find_consecutive(l[-6:]):
            six_in_a_row = 1
print(l)
print(non_solutions)


###
### template of code for Problem 4 of Problem Set 2, Fall 2008
###

bestSoFar = 0     # variable that keeps track of largest number
                  # of McNuggets that cannot be bought in exact quantity
packages = (6,9,11)   # variable that contains package sizes
six_in_a_row = 0
l = []
n = 0
non_solutions = []
for n in range(1, 200):   # only search for solutions up to size 200
    ## complete code here to find largest size that cannot be bought
    ## when done, your answer should be bound to bestSoFar
    list1 = finding_packages(n, l, non_solutions, packages)    
    if list1 == None:
        bestSoFar = n
        print(bestSoFar)
 



