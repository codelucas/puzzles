__author__ = 'louyang'

# Enter your code here. Read input from STDIN. Print output to STDOUT
def topo_sort(war_c, graph):
    ins = {str(key) : 0 for key in range(1, int(war_c) + 1)}
    for k, v in graph.items():
        for adj in v:
            ins[adj] += 1

    zeros = [k for k, v in ins.items() if v == 0]
    topo_order = []
    error = True

    while len(zeros) > 0:
        elem = zeros.pop()
        if elem == war_c:
            error = False

        topo_order.append(elem)
        adjs = []
        if graph.get(elem):
            adjs = graph[elem]
        for adj in adjs:
            ins[adj] -= 1
            if ins[adj] == 0:
                zeros.append(adj)
    if error:
        return None
    return topo_order

# Reverse a graph's edges (for backtracking purposes from the war city)
def rev_graph(graph):
    new_graph = {}
    for key, adj_list in graph.items():
        for elem in adj_list:
            if not new_graph.get(elem):
                new_graph[elem] = [key]
            else:
                new_graph[elem].append(key)
    return new_graph

# We use dfs to track which nodes are reachable from the war city 'war_c'
def dfs(war_c, rev_graph, touched = set()):
    seen = {str(key) : False for key in range(1, int(war_c) + 1)}
    stack = []
    stack.append(war_c)
    while len(stack) > 0:
        elem = stack.pop()
        # Don't repeat already visited nodes
        if elem in touched:
            continue
        touched.add(elem)
        seen[elem] = True
        adjs = []
        if rev_graph.get(elem):
            adjs = rev_graph[elem]
        for adj in adjs:
            stack.append(adj)
    reachable = [key for key, r in seen.items() if r == True]
    return reachable

"""
def get_pths(war_c, rev_graph, path_dict):
    if path_dict.get(war_c):
        return path_dict[war_c]
    adjs = []
    if rev_graph.get(war_c):
        adjs = rev_graph[war_c]
    path_c = 0
    for a in adjs:
        path_c += get_pths(a, rev_graph, path_dict)
    path_dict[war_c] = path_c
    return path_c
"""

# Enter your code here. Read input from STDIN. Print output to STDOUT
def find_paths(war_c, graph):
    reversed_graph = rev_graph(graph)
    reachable = dfs(war_c, reversed_graph)
    if '1' not in reachable:
        print "0"
        exit()

    new_graph = {}
    for r in reachable:
        if graph.get(r):
            new_graph[r] = graph[r]

    topo_order = topo_sort(war_c, new_graph)
    if not topo_order:
        print "INFINITE PATHS"
        exit()

    path_dict = {str(num) : 0 for num in range(1, int(war_c) + 1)}
    path_dict['1'] = 1
    for n in topo_order:
        if new_graph.get(n):
            adjs = new_graph[n]
            for a in adjs:
                path_dict[a] = (path_dict[a] + path_dict[n]) % (10**9)

    print str(path_dict[war_c])

war_c, n = raw_input().strip().split(' ')
graph = {}
for i in range(int(n)):
    a, b = raw_input().strip().split(' ')
    if a not in graph:
        graph[a] = [b]
    else:
        graph[a].append(b)

find_paths(war_c, graph)
