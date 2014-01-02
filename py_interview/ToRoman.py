__author__ = 'louyang'

# Given a base 10 number, convert it into roman numerals.
# I, V, X, C, D, M

coding = zip(
    [1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1],
    ['M', 'CM', 'D', 'CD', 'C', 'XC', 'L', 'XL', 'X', 'IX', 'V', 'IV', 'I']
)
decs = [ tup[0] for tup in coding ]
roms = [ tup[1] for tup in coding ]

def to_roman(num):
    if num <= 0 or num >= 4000:
        print 'range is between 1 - 3999'
        return
    result = []
    for d, r in coding:
        while num >= d:
            result.append(r)
            num -= d
    return ''.join(result)

def recur_roman(num, s, a):
    """a, is an index marking spots
    in our two lists. s is the string"""
    # We hit end of list, return
    if a >= len(decs):
        return s
    # While we can decrement chunks, maintain chunk
    if num >= decs[a]:
        return recur_roman(num-decs[a], s+roms[a], a)
    # Once num is too small to dec more, advance the chunk
    else: return recur_roman(num, s, a+1)

print to_roman(3999)
print recur_roman(3999, '', 0)
print 'ans should be MMMCMXCIX'