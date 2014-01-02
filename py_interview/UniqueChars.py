__author__ = 'louyang'

# Write an algorithm to determine if a string has all
# unqiue characters, do with and without addn data structs.

def uniq(string):
    '''
    With data structures:

    dd = {}
    for c in string:
        if dd.get(c):
            return False
        dd[c] = True
    return True
    '''
    for i in range(len(string)):
        for j in range(i + 1, len(string)):
            if string[i] == string[j]:
                return False
    return True

def bitwise(s):
    checker = 0
    for i in range(len(s)):
        val = ord(s[i]) - ord('a')
        print 'val:', bin(val)
        print 'cher:', bin(checker)
        if (checker & (val << 1)) > 0:
            return False
        checker |= (val << 1)
    return True

def main():
    print uniq('lucasoyng')

main()