__author__ = 'louyang'


# Given two strings, write a method to determine
# if one is a permutation of the other.

# If space was an issue, we can sort both strings
# and then iterate on one pass...
def is_perms(s1, s2):
    if len(s1) != len(s2): # Must be same length to even be considered
        return False
    h1 = {}
    for c in s1:
        if h1.get(c):
            h1[c] += 1
        else:
            h1[c] = 1

    for c in s2:
        if h1.get(c):
            h1[c] -= 1
        else:
            return False
    for v in h1.values():
        if v != 0:
            return False
    return True


def main():
    s1 = 'lucas yang'
    s2 = 'lucas ynng'
    print is_perms(s1, s2)

main()