__author__ = 'louyang'

# Sunny and Johnny together have C dollars which they intend
# to use at the ice cream parlour. Among L flavors available,
# they have to choose two distinct flavors whose cost equals
# C. Given a list of cost of L flavors, output the indices of two
# items whose sum equals C. The cost of a flavor (Li) will be no
# more than 10000.

def find_indicies(C, LL):
    a, b = None, None
    DD = {cost:i+1 for i, cost in enumerate(LL)}
    for i, cost in enumerate(LL):
        if DD.get(C - cost) and i+1 != DD.get(C - cost):
            a, b = i+1, DD.get(C - cost)
            break
    return str(a) + " " + str(b)


num_tests = input()
for i in range(num_tests):
    C = input()
    L = input()
    LL = [int(x) for x in raw_input().strip().split(' ')]
    print find_indicies(C, LL)

