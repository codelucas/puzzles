__author__ = 'Lucas'
import random
from AlgoGoodies import isOrdered

def quickSort(arr):
    if len(arr) < 2:
        return arr

    piv = arr[random.randint(0, len(arr) - 1)]
    smaller = []
    equal = []
    bigger = []
    index = len(arr) - 1
    while index >= 0:
        if arr[index] > piv:
            bigger.append(arr[index])
        elif arr[index] == piv:
            equal.append(arr[index])
        else:
            smaller.append(arr[index])
        index -= 1

    sm = quickSort(smaller)
    bg = quickSort(bigger)
    return bg + equal + sm

def main():
    list = []
    for i in range(random.randint(10, 50)):
        list.append(random.randint(-100, 100))

    print "Before list:", str(list)
    list = quickSort(list)
    print "After list:", str(list)
    print "Is the list in descending order?", isOrdered(list)


if __name__ == "__main__":
    main()