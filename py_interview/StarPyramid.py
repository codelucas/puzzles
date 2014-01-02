"""
    Given a number, print out it's 'star pyramid'.
    ex.
    starpy(3)
    >>> *
    >>> **
    >>> ***

"""

# This helper function takes a number and prints out
# that number of stars on one line w/o newline
def print_line(num):
    if num < 1:
        return
    if num == 1:
        # comma gets rid of newline
        print('*'),
        return
    print('*'), 
    print_line(num-1)

def starpy(start, num):
    if start == num:
        print_line(num)
        return
    print_line(start)
    print('\r\n'),
    starpy(start+1, num) 

# Always call function with start = 1
starpy(1, 5)
