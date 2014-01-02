__author__ = 'Lucas Ou-Yang'

class Node:
    def __init__(self, data):
        self.left = None
        self.right = None
        self.data = data

def allpaths(root, pth):
    """dfs approach to save memory"""
    if not root:
        return

    pth.append(root.data)

    if not root.left and not root.right:
        print pth

    # Clone list! remember they are mutable
    allpaths(root.left, list(pth))
    allpaths(root.right, list(pth))


def uprint(arr, limit):
    print ', '.join(arr[:limit+1])


def effecient_allpaths(root, pth, pindex):
    """efficient version of above, maintain
    what to print out with an index instead of
    generating new list objects"""
    if not root:
        return
    pth[pindex] = str(root.data)
    if not root.left and not root.right:
        uprint(pth, pindex)
        return
    effecient_allpaths(root.left, pth, pindex+1)
    effecient_allpaths(root.right, pth, pindex+1)

def numpaths(root):
    if not root:
        return 0
    # leaf node
    if not root.left and not root.right:
        return 1
    return numpaths(root.left) \
           + numpaths(root.right)

def buildtree(ll, a, b):
    if b < a:
        return None
    m = (a+b)/2
    root = Node(ll[m])
    root.left = buildtree(ll, a, m-1)
    root.right = buildtree(ll, m+1, b)
    return root

def inorder(root):
    if not root:
        return None
    inorder(root.left)
    print 'in order:', root.data
    inorder(root.right)

def main():
    ll = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
    # Constructed binary tree is
    #         10
    #       /   \
    #     8      2
    #   /  \    /
    # 3     5  2
    root = buildtree(ll, 0, len(ll)-1)
    # inorder(root)
    # print root.data, root.left.data
    # root             = Node(10)
    # root.left        = Node(8)
    # root.right       = Node(2)
    # root.left.left   = Node(3)
    # root.left.right  = Node(5)
    # root.right.left  = Node(2)
    allpaths(root, [])
    effecient_allpaths(root, ['' for i in xrange(len(ll))], 0)
    print 'confirm above path has:', numpaths(root)

main()
