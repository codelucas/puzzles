#!/usr/bin/python3


def outer(start):
    """an illustration of a function closure in python
    """
    def middle():
        """an extra middle layer is added just to make this
        example more convoluted to show that it _still_ works
        """
        end = 'World'

        def alert(n):
            """the inner function `alert` still has access to
            the `start` and `end` vars despite enclosing procedures
            already having finished executing and thus being out
            scope when `alert` is returned as a var
            """
            for i in range(n):
                print(''.join([start, ' ', end, '!']))

        return alert
    return middle


# the output is as expected
fn = outer('Hello')()
fn(3)
