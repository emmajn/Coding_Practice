# This function lists the 1000th prime number

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
## This Script Verifies that the product of prime numbers 2 through the nth prime number is less than e^n

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
            print(prime_log_sum/prime_candidate) # this value will be less than 1
        else:
            pass
    else:
        pass
    prime_candidate = prime_candidate + 1


