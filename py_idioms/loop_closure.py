from __future__ import print_function

funcs = []

# the `i` var is created globally here
for i in range(4):
    funcs.append(lambda: print(i))

# `i` is also visible here, the value doesn't leak
# out of the loop body, but has always existed and
# was declared globally

for f in funcs:
    f()

# four 3s will be outputted instead of the expected
# sequence of 0, 1, 2, 3. this is because in the
# above code, the `i` variables within the for-loop
# body are all actually the _same_ variable, `i` is
# just being reassigned on every iteration

print('\n-\n')


def f_factory(i):
    """var `i` is given a new scope here because
    vars in python are function scoped, the `i` now
    is a new variable due to pass by value
    """
    return lambda: print(i)


funcs = []
for i in range(4):
    funcs.append(f_factory(i))

for f in funcs:
    f()

# now, in the preceding block of code, the sequence we
# originally expected: 0, 1, 2, 3 is outputted.
