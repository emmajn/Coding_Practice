# Problem Set 1
# Name: Emma Nelson

# Problem 1
# List the 1000th prime number

import time

prime_candidate = 2
nth_prime = 1
while nth_prime < 1000:
    if prime_candidate > 2:
        is_prime = 1
        for i in range(2,round(prime_candidate/2)+1):
            if prime_candidate%i == 0:
                if prime_candidate == i:
                    pass
                else:
                    is_prime = 0
                    break
        if is_prime == 1:
            nth_prime = nth_prime + 1;
##            print(nth_prime)
##            print(prime_candidate)
        else:
            pass
    else:
        pass
    prime_candidate = prime_candidate + 1


# Problem 1 E.C
from math import *
prime_log_sum = log(2)
prime_candidate = 2
nth_prime = 1
while nth_prime < 1000:
    if prime_candidate > 2:
        is_prime = 1
        for i in range(2,round(prime_candidate/2)+1):
            if prime_candidate%i == 0:
                if prime_candidate == i:
                    pass
                else:
                    is_prime = 0
                    break
        if is_prime == 1:
            nth_prime = nth_prime + 1;
            prime_log_sum = prime_log_sum + log(prime_candidate)

            print(prime_log_sum/prime_candidate)
        else:
            pass
    else:
        pass
    prime_candidate = prime_candidate + 1

# Problem 2

