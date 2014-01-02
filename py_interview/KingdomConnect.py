# We are given a starting node
# an ending node and a list of edges. We need
# to find the number of distinct paths from
# the start to the end. There may be infinite
# many paths (cycles), if that's the case print infinite.

# The first line contains two integers N and M.

# M lines follow, each containing two integers x
# and y, indicating there is a road from city x to
# city y (1<=x,y<=N)

# Sample Input #1
# 5 5
# 1 2
# 2 4
# 2 3
# 3 4
# 4 5

# Sample Output #1
# 2

__author__ = 'Lucas'
# Ending Vertex, Number of Edges
dest, N = [int(x) for x in raw_input().strip().split(' ')]
# DP hash, adjacency list
paths, graph = {}, {}
is_hit, is_cycle  = [False for i in range(dest + 1)], [False for i in range(dest + 1)]

def init():
    global is_hit, is_cycle, dest, N, paths
    is_hit[1] = True
    find_paths(1, dest)
    print paths[1]

def find_paths(start, dest):
    global is_hit, is_cycle, paths, graph
    # Base case, if we hit the destination
    # return a path length of 1
    if start == dest: return 1
    # In the case that our start point isn't
    # in the graph, return 0 possible paths
    if not graph.get(start): return 0

    for edge in graph[start]:
        if is_hit[edge]:
            is_cycle[edge] = True
            continue

        is_hit[edge] = True
        # If the weight is stored via dynamic programing
        # take it, if it's not recurse and find it
        if paths.get(edge):
            weight = paths[edge]
        else:
            weight = find_paths(edge, dest)

        # We must have this init check here and not all
        # at once at the beginning because we rely on checking
        # if a path[key] exists to determine to use DP or not.
        if not paths.get(start):
            paths[start] = 0

        paths[start] = (paths[start] + weight) % (10**9)

        # If the cur node is reachable via the destination
        # "paths[start] > 0", and if there is a cycle
        # "is_hit" true twice, then we are in a loop AND
        # that loop is on a road from start to dest, so we
        # now have infinite paths!
        if paths[start] > 0 and is_cycle[start]:
            print "INFINITE PATHS"
            quit()
        is_hit[edge] = False
    return paths[start]

for i in range(int(N)):
    a, b = [int(x) for x in raw_input().strip().split(' ')]
    if a not in graph:
        graph[a] = [b]
    else:
        graph[a].append(b)

init()

