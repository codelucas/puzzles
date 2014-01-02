__author__ = 'louyang'


# I believe there's a way to find the kth largest element in an unsorted
# array of length n in O(n). Or perhaps it's "expected" O(n) or
# something. How can we do this?

def get_kth(unsorted, k, a, b):
    if a > b:
        return -999
    piv = (a + b) / 2
    lo, hi = a, b
    # partition it
    print unsorted
    while lo <= hi:
        while unsorted[lo] < unsorted[piv]:
            lo += 1
        while unsorted[hi] > unsorted[piv]:
            hi -= 1
        if lo <= hi:
            tmp = unsorted[lo]
            unsorted[lo] = unsorted[hi]
            unsorted[hi] = tmp
            lo += 1
            hi -= 1
    print unsorted
    print lo, hi
    if k <= hi: # hi is now the leftmost pointer (intended)
        return get_kth(unsorted, k, a, hi)
    elif k == lo - 1:
        return unsorted[lo-1]
    elif k >= lo:
        return get_kth(unsorted, k, lo, b)




def main():
    # sorted = [-10, 5, 7, 10, 19, 20]
    # find the 1st smallest element (0 based), so it's 5
    unsorted = [10, 5, 7, 19, 20, -10]
    elem = get_kth(unsorted, 3, 0, len(unsorted) - 1)
    print elem

main()