__author__ = 'Lucas'

def moorMajority(arr):
    """
    cur = None
    index = 0
    for e in arr:
        if index == 0:
            cur = e
            index += 1
        elif cur == e:
            index += 1
        elif cur != e:
            index -= 1

        if index == 0:
            cur = None
    return cur
    """
    mapper = {}
    for e in arr:
        if not mapper.get(e, False):
            mapper[e] = 1
        else:
            mapper[e] += 1

    maxOccur = (None, -100000)
    for tuple in mapper.items():
        if tuple[1] > maxOccur[1]:
            maxOccur = tuple
    maxVal = maxOccur[0]
    return maxVal


def main():
    list = [1, 2, 2, 2, 2, 1, 7, 1, 8]
    major = moorMajority(list)
    print "List:", list
    print "Majority elem:", major

if __name__ == "__main__":
    main()