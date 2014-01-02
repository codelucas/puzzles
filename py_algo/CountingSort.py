__author__ = 'Lucas'

def countingSort(arr):
    maxKey = 0
    for e in arr:
        if e > maxKey: maxKey = e
    N = [0 for x in range(maxKey + 1)]

    # Set up the counts for all of the keys
    for e in arr:
        N[e] += 1
    print "N: counters", N
    # Set starting points
    """
    total = 0
    for e in range(len(N)):
        oldCount = N[e]
        N[e] = total
        total += oldCount
    """
    # Set end points
    for e in range(1, len(N)):
        N[e] += N[e - 1]

    sortedList = [None for x in range(len(arr))]
    print "N acc:", N
    for e in arr:
        loc = N[e] - 1 # Need to subtract one
        sortedList[loc] = e
        N[e] -= 1
    return sortedList

def inPlaceCounting(arr):
    # Find the max key range
    maxKey = 0
    for e in arr:
        if e > maxKey: maxKey = e
    N = [0 for x in range(maxKey + 1)]

    for e in arr:
        N[e] += 1
    index = 0
    for i in range(maxKey + 1):
        for j in range(N[i]):
            arr[index] = i
            index += 1
    return arr

def main():
    lst = [3, 2, 10, 2, 4, 5, 1, 13, 8, 2 ,3]
    sortedList = inPlaceCounting(lst)
    print "Original:", lst
    print "Sorted  :", sortedList

if __name__ == "__main__":
    main()