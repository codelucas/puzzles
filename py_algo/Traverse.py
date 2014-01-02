__author__ = 'Lucas'

# O(Edges + Verticies) implementation (Good)

# Depth first traversal
def traverse(graph, start, S=set()):
    prevs, stack = {}, set()
    prevs[start] = None
    stack.add(start)
    while len(stack) > 0:
        node = stack.pop()
        adjacents = graph[node]
        for a in adjacents:
            prevs[a] = node
            stack.add(a)
    return prevs

# Identify the starting points (no in-edges), and traverse
def trav_components(graph):
    comps = []
    seen = set()
    for v in graph:
        if v in seen:
            continue
        comp = traverse(graph, v)
        comps.append(comp)
        seen = comp.keys()
    return comps

def main():
    pass

if __name__ == "__main__":
    main()



