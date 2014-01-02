__author__ = 'Lucas'

import random
# Sort a directed graph from the start node
# to the end node, implemented w adj list
# This implementation of topological sort is linear
def topologicalSort(graph):
    inEdgeCount = {x : 0 for x in graph}
    for i in range(len(graph)):
        for j in graph[i]:
            inEdgeCount[j] += 1
    # An ordered list we will return
    ordered = []
    # A list of the nodes with zero edges pointed in (start of path)
    # If there are no such nodes, it's a cyclic graph
    stack = [x for x in graph if inEdgeCount[x] == 0]
    while len(stack) > 0:
        node = stack.pop()
        ordered.append(node)
        for x in graph[node]:
            # Decrement the out-edges
            inEdgeCount[x] -= 1
            if inEdgeCount[x] == 0:
                stack.append(x)
    return ordered

def main():
    graph = {i : [random.randrange(0, 29) for x in range(random.randrange(0, 29))] for i, x in enumerate(range(30))}
    orderedNodes = topologicalSort(graph)
    print orderedNodes

if __name__ == "__main__":
    main()