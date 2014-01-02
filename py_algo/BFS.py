__author__ = 'Lucas'

from collections import deque

# BFS's are implemented well with a queue, while
# DFS are implemented well with recursion/stack

# This method will print the rows of a
# Binary search tree, row by row per line
def printTreeBreadthFirst(tree):
    # Extract root
    root = tree[1]
    # Set up a queue (breadth first search)
    q = deque([])
    q.append(root)
    # Two bookkeeping counters, how we keep track of ending a level
    curLevelNodes = 1
    nextLevelNodes = 0
    s = ""
    while len(q) > 0:
        node = q.popleft()
        curLevelNodes -= 1

        # Code for setting left and right children to nodes,
        # The if statements are to check if we hit out of bounds
        leftChild, rightChild = None, None
        # If leftchild
        if (node[0] * 2) < len(tree):
            leftChild = tree[node[0] * 2]
            q.append(leftChild)
            nextLevelNodes += 1
        # If rightchild
        if (node[0] * 2 + 1) < len(tree):
            rightChild = tree[node[0] * 2 + 1]
            q.append(rightChild)
            nextLevelNodes += 1

        # Add the value of the node to the printed string
        s += str(node[1]) + " "

        if curLevelNodes == 0:
            s += "\r\n"
            # Bookkeeping to reset counters after each level
            curLevelNodes = nextLevelNodes
            nextLevelNodes = 0
    return s

def bfs(graph, s):
    S, queue = set(), deque([])
    queue.append(s)
    while len(queue) > 0:
        node = queue.popleft()
        if node in S:
            continue
        S.add(node)
        adjacents = graph[node]
        for a in adjacents:
            queue.append(a)

def main():
    # Append a dummy None to consume index 0, this tree will
    # be a list with the 2i, 2i+1 children pattern.
    tree = [None] + [(index + 1, val) for index, val in enumerate(range(1, 8))]
    s = printTreeBreadthFirst(tree)
    print s

if __name__ == "__main__":
    main()
