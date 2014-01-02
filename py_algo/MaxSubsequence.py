__author__ = 'louyang'



#    The maximum subsequence sum is defined to be 0 if all the integers are negative.
#    For example, given the sequence -2, 11, -4, 13, -5, -2, the maximum subsequence sum is 20:
#    a2 through a4.

# O(n) runtime, O(1) space
def maxsub(ll):
    cur_max = -99999
    standing_max = 0
    for i in range(len(ll)):
        standing_max += ll[i]
        if standing_max > cur_max:
            cur_max = standing_max
        # As long as a subseq is > 0, we
        # can just tag it on, it wont
        # hurt our sum!!!
        if standing_max < 0:
            standing_max = 0
    return cur_max

def main():
    print maxsub([-2, 11, -4, 13, -5, -2])

main()