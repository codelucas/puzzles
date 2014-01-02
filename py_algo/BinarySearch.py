__author__ = 'Lucas'


# O(lg(n)) Average, Worst
def binarySearch(arr, elem, lo, hi):
    if hi < lo:
        return -999
    mid = (lo + hi) / 2
    if elem == arr[mid]:
        return arr[mid]
    elif elem < arr[mid]:
        return binarySearch(arr, elem, lo, mid - 1)
    else: # elem > arr[mid]
        return binarySearch(arr, elem, mid + 1, hi)

def iterBinarySearch(arr, elem):
    lo, hi = 0, len(arr) - 1
    while lo <= hi:
        mid = (lo + hi) / 2
        if elem == arr[mid]:
            return arr[mid]
        elif elem < arr[mid]:
            hi = mid - 1
        elif elem > arr[mid]:
            lo = mid + 1
    return None

# Here is where we diff from the offical
# binary search, instead of returning nothing
# when the element isn't found, we return where
# it should have been.
prev_hole = -999
def location_b_search(lo, hi, ll, e):
    global prev_hole

    if lo > hi:
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
        return location_b_search(lo, mid - 1, ll, e)
    elif e > ll[mid]:
        return location_b_search(mid + 1, hi, ll, e)
    else:
        return mid


# O(lg(lg(n)) Average, O(n) Worst case
def interpolSearch(arr, elem, lo, hi):
    pass

def main():
    smooth_ordered_list = [-5, -2, -1, 0, 1,2,3,4,5,5,6]
    rough_list = [-10000, -50, 1, 2, 3, 4,5, 6] # This will fuck up interpolation search
    print "list:", smooth_ordered_list
    print "is it in there?", binarySearch(smooth_ordered_list, 6, 0, len(smooth_ordered_list) - 1)
    print "is it in there?", iterBinarySearch(smooth_ordered_list, 6)

if __name__ == "__main__":
    main()