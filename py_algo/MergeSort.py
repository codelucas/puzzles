__author__ = 'Lucas'
import random
import math
from AlgoGoodies import isOrdered

def mergeSort(arr):
    if len(arr) < 2:
        return arr

    mid = int(math.floor(len(arr) / 2))
    left = arr[:mid]
    right = arr[mid:]

    l = mergeSort(left)
    r = mergeSort(right)
    return merge(l, r)

def merge(left, right):
    i, j = 0, 0
    merged = []
    while len(left) > i or len(right) > j:
        if len(left) > i and len(right) > j:
            if left[i] >= right[j]:
                merged.append(left[i])
                i += 1
            else:
                merged.append(right[j])
                j += 1
        elif len(left) > i:
            merged.append(left[i])
            i += 1
        elif len(right) > j:
            merged.append(right[j])
            j += 1
    return merged

def main():
    list = []
    for i in range(random.randint(10, 50)):
        list.append(random.randint(-100, 100))

    print "Before list:", str(list)
    list = mergeSort(list)
    print "After list:", str(list)
    print "Is the list in descending order?", isOrdered(list)


if __name__ == "__main__":
    main()

