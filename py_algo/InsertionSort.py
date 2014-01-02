__author__ = 'Lucas'
import random
from AlgoGoodies import isOrdered

def insertionSort(arr):
    if len(arr) < 2:
        return arr
    index = 1
    while index < len(arr):
        val = arr[index]
        hole = index
        while hole > 0 and val > arr[hole - 1]:
            arr[hole] = arr[hole - 1]
            hole -= 1
        arr[hole] = val
        index += 1
    return arr

def binaryInsertionSort(arr):
    if len(arr) < 2:
        return arr

    index = 1
    while index < len(arr):
        lo = 0
        hi = index
        val = arr[index]
        while lo < hi:
            # IMPORTANT: for these general middle index
            # calculations, if we are flooring, the lower
            # bound will be set equal to middle index + 1,
            # while the upper gets set to middle, opposite of ceiling.
            mid = (lo + hi) / 2
            if val >= arr[mid]:
                hi = mid
            else:
                lo = mid + 1
        jIndex = index
        while jIndex > lo:
            arr[jIndex], arr[jIndex - 1] = arr[jIndex - 1], arr[jIndex]
            jIndex -= 1
        index += 1
    return arr


def main():
    list = []
    for i in range(random.randint(10, 50)):
        list.append(random.randint(-100, 100))

    print "Before list:", str(list)
    list = binaryInsertionSort(list)
    print "After list:", str(list)
    print "Is the list in descending order?", isOrdered(list)

if __name__ == "__main__":
    main()