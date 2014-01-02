__author__ = 'louyang'


"""
Return true if a BST is complete (every internal node has 2 kids, leaves all balanced).

Write both Recursive and iterative code.
"""


final_depth = None
def recur_complete(tree, cur, depth=0):
    global final_depth
    branch_num = 0
    print "cur", cur

    if len(cur) == 0:                                           # External node
        print "internal", cur
        if final_depth is not None and depth != final_depth:       # Bookkeep final depth
            return False
        final_depth = depth
        return True

    lhs, rhs = False, False
    if len(cur) > 0 and cur[0] is not None:           # If left child exists, incr branch factor and jump in
        left = tree[cur[0]]
        lhs = recur_complete(tree, left, depth+1)
        branch_num += 1

    if lhs is False:                        # If left is already failing, quit earlier
        return lhs

    if len(cur) == 2 and cur[1] is not None:        # If right child exists, incr branch factor, jump in
        right = tree[cur[1]]
        rhs = recur_complete(tree, right, depth+1)
        branch_num += 1

    if branch_num == 1:                     # If only has one child, it's not complete
        return False

    return (lhs and rhs)


def iter_complete(tree, head):
    stack = []
    stack.append([head, 0])                 # We are adding extra var (depth) to node vars to bookkeep
    status = True
    prev_depth = None

    while len(stack) > 0:
        c = stack.pop()
        cur = c[0]
        depth = c[1]
        branch_num = 0

        if len(cur) == 0:                                        # External code, bookkeep depths
            if prev_depth is not None and depth != prev_depth:
                status = False
                break
            prev_depth = depth

        if len(cur) > 0 and cur[0] is not None:                # Left child
            left = tree[cur[0]]
            branch_num += 1
            stack.append([left, depth + 1])

        if len(cur) == 2 and cur[1] is not None:               # Right child
            right = tree[cur[1]]
            branch_num += 1
            stack.append([right, depth + 1])

        if branch_num == 1:
            status = False
            break

    return status




def main():
    tree = {'a': ['b', 'c'], # Isn't complete
            'b': ['d', 'e'],
            'c': [],
            'd': ['f'],
            'e': [],
            'f': []
           }

    tree_two = {'a': ['b', 'c'], # Is complete
            'b': ['d', 'e'],
            'c': ['f', 'g'],
            'd': [],
            'e': [],
            'f': [],
            'g': []
           }

    tree_three = {'a': ['b', 'c'],
                  'b': [None, 'e'],
                  'c': ['f'],
                  'd': [],
                  'e': [],
                  'f': [],
                  }

    print iter_complete(tree_three, tree_three['a'])

main()