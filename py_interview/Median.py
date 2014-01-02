__author__ = 'louyang'

# For a sorted set of numbers, the median
# is the middle number if the list is odd,
# or the average of the two middles if the list is even.

# Input:
# The first line is an integer n indicates the number
# of operations. Each of the next n lines is either a x
# or r x which indicates the operation is add or remove.

# Output:
# For each operation: If the operation is add output
# the median after adding x in a single line. If the
# operation is remove and the number x is not in the
# list, output Wrong! in a single line. If the
# operation is remove and the number x is in the list,
# output the median after deleting x in a single line. (if the
# result is an integer DO NOT output decimal point. And if
# the result is a double number , DO NOT output trailing 0s.)

# Input Sample
# 2
# r 2
# a 10

# Output
# Wrong!
# 10

import math
N = input()
nums = []
prev_hole = -999

def get_whole(num):
    if num % 1 == 0:
        return int(str(num).split('.')[0])
    return num

def binary_search(lo, hi, ll, e):
    global prev_hole

    if lo > hi:
        # Here is where we diff from the offical
        # binary search, instead of returning nothing
        # when the element isn't found, we return where
        # it should have been.
        if e > ll[prev_hole]:
            prev_hole += 1
        elif e < ll[prev_hole]:
            prev_hole -= 1
        if prev_hole < 0:
            prev_hole = 0
        elif prev_hole > len(ll) - 1:
            # In other langs be sure to increase list capacity
            a = 0 # do nothing
        return prev_hole

    mid = (lo + hi) / 2
    prev_hole = mid

    if e < ll[mid]:
        return binary_search(lo, mid - 1, ll, e)
    elif e > ll[mid]:
        return binary_search(mid + 1, hi, ll, e)
    else:
        return mid

def m_index(ll):
    length = len(ll)
    if length % 2 == 0:
        a, b = ll[(length / 2) - 1], ll[(length / 2)]
        return get_whole((a + b) / 2.0)
    else:
        a = ll[int(math.ceil(length / 2))]
        return get_whole(a)

for i in range(N):
    s = raw_input().split(' ')
    sign, num = s[0], int(s[1])

    if sign == 'r':
        if num not in nums:
            print 'Wrong!'
        else:
            nums.pop(binary_search(0, len(nums) - 1, nums, num))
            if len(nums) == 0:
                print 'Wrong!'
            else:
                print m_index(nums)
    elif sign == 'a':
        if len(nums) < 1:
            nums.append(num)
        else:
            hole = binary_search(0, len(nums) - 1, nums, num)
            nums.insert(hole, num)
        print nums
        print m_index(nums)




