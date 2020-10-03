# Problem Set 4
# Name: Emma Nelson
# Collaborators: Daniel Shtibel
# Time: 

#
# Problem 1
#

def nestEggFixed(salary, save, growthRate, years):
    """
    - salary: the amount of money you make each year.
    - save: the percent of your salary to save in the investment account each
      year (an integer between 0 and 100).
    - growthRate: the annual percent increase in your investment account (an
      integer between 0 and 100).
    - years: the number of years to work.
    - return: a list whose values are the size of your retirement account at
      the end of each year.
    """
    Retire_Amount = [salary*save*0.01]
    for i in range(1,years):
        Retire_Amount.append(Retire_Amount[i-1]*(1+0.01*growthRate) + salary * save * 0.01 )
    return Retire_Amount

def testNestEggFixed():
    salary     = 10000
    save       = 10
    growthRate = 15
    years      = 5
    savingsRecord = nestEggFixed(salary, save, growthRate, years)
    print( savingsRecord)
    # Output should have values close to:
    # [1000.0, 2150.0, 3472.5, 4993.375, 6742.3812499999995]

    # TODO: Add more test cases here.

#
# Problem 2
#
testNestEggFixed()

def nestEggVariable(salary, save, growthRates):
    """
    - salary: the amount of money you make each year.
    - save: the percent of your salary to save in the investment account each
      year (an integer between 0 and 100).
    - growthRate: a list of the annual percent increases in your investment
      account (integers between 0 and 100).
    - return: a list of your retirement account value at the end of each year.
    """
    Retire_Amount = [salary*save*0.01]
    for i in range(1,len(growthRates)):
##        print(growthRates[i-1])
##        print(salary * save * 0.01)
##        print((1+0.01*growthRates[i-1]))
##        print(Retire_Amount[i-1])
        Retire_Amount.append(Retire_Amount[i-1]*(1+0.01*growthRates[i]) + salary * save * 0.01 )
    return Retire_Amount

def testNestEggVariable():
    salary      = 10000
    save        = 10
    growthRates = [3, 4, 5, 0, 3]
    savingsRecord = nestEggVariable(salary, save, growthRates)
    print( savingsRecord)
    # Output should have values close to:
    # [1000.0, 2040.0, 3142.0, 4142.0, 5266.2600000000002]

    # TODO: Add more test cases here.
##testNestEggVariable()
##print([1000.0, 2040.0, 3142.0, 4142.0, 5266.2600000000002])
#
# Problem 3
#

def postRetirement(savings, growthRates, expenses):
    """
    - savings: the initial amount of money in your savings account.
    - growthRate: a list of the annual percent increases in your investment
      account (an integer between 0 and 100).
    - expenses: the amount of money you plan to spend each year during
      retirement.
    - return: a list of your retirement account value at the end of each year.
    """
##    print(1+0.01*growthRates[0])
##    print(savings)
##    print(expenses)
    Retire_Amount = [savings*(1+0.01*growthRates[0])-expenses]
    for i in range(1,len(growthRates)):
##        print(growthRates[i-1])
##        print((1+0.01*growthRates[i-1]))
##        print(Retire_Amount[i-1])
        Retire_Amount.append(Retire_Amount[i-1]*(1+0.01*growthRates[i]) - expenses)
    return Retire_Amount

def testPostRetirement():
    savings     = 100000
    growthRates = [10, 5, 0, 5, 1]
    expenses    = 30000
    savingsRecord = postRetirement(savings, growthRates, expenses)
    print('post retirement')
    print( savingsRecord)
    # Output should have values close to:
    print([80000.000000000015, 54000.000000000015, 24000.000000000015, -4799.9999999999854, -34847.999999999985])

    # TODO: Add more test cases here.
testPostRetirement()
#
# Problem 4
#

def findMaxExpenses(salary, save, preRetireGrowthRates, postRetireGrowthRates,
                    epsilon):
    """
    - salary: the amount of money you make each year.
    - save: the percent of your salary to save in the investment account each
      year (an integer between 0 and 100).
    - preRetireGrowthRates: a list of annual growth percentages on investments
      while you are still working.
    - postRetireGrowthRates: a list of annual growth percentages on investments
      while you are retired.
    - epsilon: an upper bound on the absolute value of the amount remaining in
      the investment fund at the end of
    """
    initial_amount_in_Retirement = nestEggVariable(salary, save, preRetireGrowthRates)
    initial_amount_in_Retirement = initial_amount_in_Retirement[-1]
    lower_bound_money_left_over = 100
    upper_bound_money_left_over = 100
    lower_bound = 0
    upper_bound = initial_amount_in_Retirement
    while min([abs(lower_bound_money_left_over), abs(upper_bound_money_left_over)]) > epsilon:
        search_range = upper_bound - lower_bound
        lower_bound_money_left_over = postRetirement(initial_amount_in_Retirement, postRetireGrowthRates, lower_bound)
        upper_bound_money_left_over = postRetirement(initial_amount_in_Retirement, postRetireGrowthRates, upper_bound)
        lower_bound_money_left_over = lower_bound_money_left_over[-1]
        upper_bound_money_left_over = upper_bound_money_left_over[-1]
##        print("left",[lower_bound_money_left_over,upper_bound_money_left_over])
##        print("bounds",[lower_bound, upper_bound])
        if lower_bound_money_left_over > 0 and upper_bound_money_left_over > 0:
            lower_bound = lower_bound + search_range
            upper_bound = upper_bound + search_range
##            print("both greater")
        elif lower_bound_money_left_over < 0 and upper_bound_money_left_over < 0:
            lower_bound = lower_bound - search_range
            upper_bound = upper_bound - search_range
        else:
            upper_bound = upper_bound - search_range/2
    if abs(lower_bound_money_left_over) < epsilon:
        return lower_bound
    else:
        return upper_bound
    

def testFindMaxExpenses():
    salary                = 10000
    save                  = 10
    preRetireGrowthRates  = [3, 4, 5, 0, 3]
    postRetireGrowthRates = [10, 5, 0, 5, 1]
    epsilon               = .01
    expenses = findMaxExpenses(salary, save, preRetireGrowthRates,
                               postRetireGrowthRates, epsilon)
    print (expenses)
    # Output should have a value close to:
    print(1229.95548986)

    # TODO: Add more test cases here.
testFindMaxExpenses()
