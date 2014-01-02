__author__ = 'Lucas Ou-Yang'

# Implement a very basic regular expression checker
# which given a string and a regex, returns true or
# false. Should consider 'a'-'z','.', and '*'.
# . = match exactly one anything
# * = match anything for any iteration
# [a-z] = matches as is

def is_match(reg, txt, a, b):
    """To save memory, we will have two pointers
    pointing at reg and txt instead of substring them"""

    # --- Base cases ---
    # Both the regex and the txt are gone, matched
    if a == len(reg) and b == len(txt):
        return True
    # The regex is gone but we still have text, matched
    elif a == len(reg):
        return True
    # We still have no regex but the text is still here, failed
    elif b == len(txt):
        return False

    # Match one of anything, pass as long as txt has >0 chars
    if reg[a] == '.':
        return is_match(reg, txt, a+1, b+1)
    # Match anything! We use recursion to expand out possibilities 2^n
    if reg[a] == '*':
        mov_both = is_match(reg, txt, a+1, b+1) # we matched!
        mov_one = is_match(reg, txt, a, b+1) # match 'anything'
        return (mov_both or mov_one)
    else:
        # If we are on a regular alphanumeric case
        if reg[a] == txt[b]:
            return is_match(reg, txt, a+1, b+1)
        return False


def main():
    print is_match('l*s', 'lucas', 0, 0)
    print is_match('l*sa', 'lucas', 0, 0)

main()