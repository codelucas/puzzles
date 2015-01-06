#!/usr/bin/python3


def adder(x):
    def inc(i):
        """usage of `nonlocal` is critical here,
        without `nonlocal`, the python interpreter
        isn't able to distinguish that `x` from the
        enclosing `adder(..)` scope is being used versus
        if we are declaring a new `x` var in the the
        `inc(..)` scope
        """
        nonlocal x
        x += i
        return x
    return inc


# output is as expected
x = adder(1)
print(x(5))
