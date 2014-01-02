"""
    Given a string, sort all of the
    words in this string, delimited by spaces.

    ex. "abc_defg" -> "bca_gfed"
    note that we do not reverse the entire thing!
"""

def indiv_rev(s, a, b):
    """reverse some string in the indices
    starting at a and ending at b"""
    if b <= a:
        return
    tmp = s[a]
    s[a] = s[b]
    s[b] = tmp
    indiv_rev(s, a+1, b-1)

def nonrecur_rev(s, a, b):
    pass

def rev(s):
    lo, hi = 0, 0
    cc = [c for c in s]
    
    while hi < len(s):
        if cc[hi] != '_':
            hi += 1
        else:
            indiv_rev(cc, lo, hi-1)
            # skip to a spot where next word begins
            while hi < len(s) and cc[hi] == '_':
                hi += 1
            lo = hi
    indiv_rev(cc, lo, hi-1)
    return ''.join(cc)

def main():
    s = 'abc__defg_awesome__'
    s = rev(s)
    print s

main()
