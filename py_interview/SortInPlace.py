__author__ = 'louyang'

def swap(chars, a, b):
    tmp = chars[a]
    chars[a] = chars[b]
    chars[b] = tmp

def qsort(s):
    chars = [char for char in s]
    piv = s[0]
    a, b = 0, len(chars) - 1
    while a < b:
        while chars[a] < piv:
            a += 1
        while chars[b] > piv:
            b -= 1
        if a <= b:
            swap(chars, a, b)
            a += 1
            b -= 1

    return ''.join(chars)


def main():
    string = 'lucas yang'
    print qsort(string)

main()
