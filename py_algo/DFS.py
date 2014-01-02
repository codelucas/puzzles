__author__ = 'Lucas'


def dfs(graph, s, S=None):
    if S is None:
        S = set()
    S.add(s)
    adjacents = graph[s]
    for node in adjacents:
        if node in S: # Already visited, or is itself
            continue
        print node # Visit
        dfs(graph, node, S)

def dfs_iter(graph, s):
    S, stack = set(), []
    stack.append(s)
    while len(stack) > 0:
        node = stack.pop()
        if node in S:
            continue
        S.add(node)
        print node # "Visit"
        for n in graph[node]:
            stack.append(n)

