__author__ = 'louyang'


# Devise an algorithm that finds the longest palindrome in
# a string of text, print it out
# O(n^2) runtime, O(1) space

def expand(string, a, b):
    a -= 1
    b += 1
    while a >= 0 and b < len(string) and string[a] == string[b]:
        a -= 1
        b += 1
    return (a + 1, b)

def longest_palin(string):
    maxx = -9999
    ret_tup = (0, 0)
    for i in range(len(string)):
        tup = expand(string, i, i)
        if tup[1] - tup[0] > maxx:
            maxx = tup[1] - tup[0]
            ret_tup = tup

        if i + 1 < len(string) and string[i] == string[i+1]:
            second_tup = expand(string, i, i + 1)
            if second_tup[1] - second_tup[0] > maxx:
                maxx = second_tup[1] - second_tup[0]
                ret_tup = second_tup

    return string[ret_tup[0]:ret_tup[1]]

def main():
    string = "hello my nameeman is joojnas"
    palin = longest_palin(string)
    print palin

main()
