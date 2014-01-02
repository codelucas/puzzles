__author__ = 'Lucas'


def reverseString(string):
    if len(string) < 2:
        return string
    return string[len(string) - 1] + reverseString(string[1:len(string) - 1]) + string[0]

def isPalindrome(string):
    if len(string) < 2:
        return True
    return (string[0] == string[len(string) - 1]) and isPalindrome(string[1:len(string) - 1])

def main():
    str = "hello, my name is lucas"
    print "before reverse:", str
    str = reverseString(str)
    print "after reverse:", str

if __name__ == "__main__":
    main()