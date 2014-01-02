__author__ = 'louyang'


# Return nth fib


dd = {}
def fib(n):
    if n == 1:
        return 0
    if n == 2:
        return 1

    if not dd.get(n):
        dd[n] = fib(n-2) + fib(n-1)

    return dd[n]
    #return fib(n-2) + fib(n-1)

def main():
    n = fib(13)
    print n

main()