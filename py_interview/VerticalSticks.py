from __future__ import division
__author__ = 'louyang'

# The values _V(a, b, c) are heights of vertical sticks, rays being shot to the left
# The numbers are the length of the rays (d+e+f = #)

# We have_ V(1,2,3) = 1+2+3 = 6, _V(1,3,2) = 1+2+1 = 4, V(2,1,3) = 1+1+3 = 5,
# V(2,3,1) = 1+2+1 = 4, V(3,1,2) = 1+1+2 = 4, V(3,2,1) = 1+1+1 = 3.
# Average of these values is 4.33.

# Input Format

# First line of input contains a single integer T. _T test cases follow.

# First line of each test-case is a single integer N (1 <= N <= 50).
# Next line contains positive integer numbers y1,, yN separated
# by a single space (0 < yi <= 1000).

# Output Format

# For each test-case output expected value of V(yp1, ypn),
# rounded to two digits after the decimal point.


def find_avg(vals):
    numerator = len(vals) + 1
    # Other elems with greater or equal heights (including self)
    bigger_than = []
    sorted_vals = sorted(vals, reverse=True)
    # Make a new list, elements are other elements greater than current
    for i, v in enumerate(sorted_vals):
        if i > 0:
            bigger_than.append(1 + bigger_than[i - 1])
            x = i
            while x > 0 and sorted_vals[i] >= sorted_vals[x-1]:
                bigger_than[x-1] += 1
                x -= 1
        else:
            bigger_than.append(1)

    total = 0
    for i in range(len(bigger_than)):
        total += (numerator / (bigger_than[i] + 1))
    return total

def main():
    num_cases = input()
    cases = []
    for i in range(num_cases):
        input()
        cases.append(raw_input().strip().split(' '))
    for c in cases:
        pp = find_avg([int(x) for x in c])
        print "%.2f" % round(pp, 2)

main()

