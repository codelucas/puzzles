#!/bin/python
__author__ = 'Lucas'
# Find shortest path to save
# the princess, output the steps
# taken, DOWN, UP, RIGHT, LEFT

# Sample Input
# 3
# ---
# -m-
# p--

class Node:
    def __init__(self, char):
        self.char = char
        self.prev = None
        self.dist = 1000000 # 1 Million = Inf
        self.d, self.u, self.r, self.l = None, None, None, None

    def set_down(self, d):
        self.d = d
    def set_up(self, u):
        self.u = u
    def set_right(self, r):
        self.r = r
    def set_left(self, l):
        self.l = l

    def get_adj(self):
        adj = []
        if self.d: adj.append(self.d)
        if self.u: adj.append(self.u)
        if self.l: adj.append(self.l)
        if self.r: adj.append(self.r)
        return adj

    def to_string(self):
        string = ""
        string += "cur " + self.char
        if self.d: string += " down " + self.d.char
        if self.u: string +=  " up " + self.u.char
        if self.r: string += " right " + self.r.char
        if self.l: string += " left " + self.l.char
        return string

def within_bounds(grid, row, index, row_index):
    rows = len(grid)
    total = rows * rows
    if index >= 0 and index < total and row_index >= 0 and row_index < len(row):
        return True
    return False

# Head ends here
def displayPathtoPrincess(m, grid):
    for i, row in enumerate(grid):
        grid[i] = list(row)
        node_row = []
        for c in grid[i]:
            node_row.append(Node(c))
        grid[i] = node_row

    # Logic for decides what are the four
    # sides of a node:

    # Right = cur_index + 1 if exists
    # Left = cur_index - 1 if exists
    # Down = cur_index + m if exists
    # Top = cur_index - m if exists

    # We start at index 1, not 0!
    nodes = []
    start, end = None, None
    for i, row in enumerate(grid):
        for j, node in enumerate(row):
            num = (i * m) + j
            if within_bounds(grid, row, num - 1, j - 1):
                grid[i][j].set_left(row[j - 1])
            if within_bounds(grid, row, num + 1, j + 1):
                grid[i][j].set_right(row[j + 1])
            if within_bounds(grid, row, num + m, j):
                grid[i][j].set_down(grid[i + 1][j])
            if within_bounds(grid, row, num - m, j):
                grid[i][j].set_up(grid[i - 1][j])

    for row in grid:
        for element in row:
            nodes.append(element)
            if element.char == 'm':
                start = nodes[len(nodes) - 1]
            if element.char == 'p':
                end = nodes[len(nodes) - 1]
            # print element.to_string()

    heap = []
    start.dist = 0
    heap.append(start)
    while len(heap) > 0:
        node, heap = get_smallest(heap)
        if node.dist == 1000000:
            break
        adjs = node.get_adj()
        for adj_node in adjs:
            if adj_node.dist > 1 + node.dist:
                adj_node.dist = 1 + node.dist
                adj_node.prev = node
                heap.append(adj_node)

    #print end.dist
    path = []
    while end.prev is not None:
        if end.prev is end.d:
            path.append("UP")
        elif end.prev is end.u:
            path.append("DOWN")
        elif end.prev is end.r:
            path.append("LEFT")
        elif end.prev is end.l:
            path.append("RIGHT")
        end = end.prev

    path.reverse()
    print  '\n'.join(path)

def get_smallest(heap):
    minimal, min_node, min_i = 100000, None, 9999
    for i, node in enumerate(heap):
        if node.dist < min:
            minimal, min_node, min_i = node.dist, node, i
    del heap[min_i]
    return min_node, heap

m = input()
grid = []
for i in xrange(0, m):
    grid.append(raw_input().strip())

displayPathtoPrincess(m, grid)

