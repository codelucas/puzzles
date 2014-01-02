__author__ = 'louyang'

"""
We have to design a program which helps drawing the skyline of a two-dimensional city given the
locations of the rectangular buildings in the city. All of the buildings are built on a flat
ground (that is they share a common bottom) and each building Bi is represented by a triplet of
(li, ri, hi) where li and ri are the left and right coordinates, respectively, of the ith
building, and hi is the height of the building. In the diagram below there are 8 buildings,
represented from left to right by the triplets (1, 5, 11), (2, 7, 6), (3, 9, 13), (12, 16, 7),
(14, 25, 3), (19, 22, 18), (23, 29, 13) and (24, 28, 4).

Output will be a sequence of P1, P2, ..., Pn pairs of (xi, hi) where Pi(xi, hi)
represents the hi height of the skyline at position xi.
"""

buildings = [
	[1, 11, 5],
	[2, 6, 7],
	[3, 13, 9],
	[12, 7, 16],
	[14, 3, 25],
	[19, 18, 22],
	[23, 13, 29],
	[24, 4, 28],
]

LEFT = 0
HEIGHT = 1
RIGHT = 2

SOLN = [1, 11, 3, 13, 9, 0, 12, 7, 16, 3, 19, 18, 22, 3, 23, 13, 29, 0]

# For each building, insert it into a skyline. It's like worse than O(n^2)
def bruteforce(buildings):
    skyline = [[None, 0] for x in range(1000)]
    for b in buildings:
        for x in range(b[LEFT], b[RIGHT]):
            if b[HEIGHT] > skyline[x][1] or skyline[x][0] is None:
                skyline[x][0], skyline[x][1] = x, b[HEIGHT]
    output = []
    prev_index = None
    count = 1
    for e in skyline[1:]: # chop off extra start, bc default start at 0
        if e[1] != prev_index:
            if e[0] is None:
                e[0] = count
            output += [e[0], e[1]]
            prev_index = e[1]
        count += 1
    return output

#-------------------------------------------------------------------------

def merge_skyline(left, right):
    skyline = []
    l_cnt, r_cnt = 0, 0
    l_height, r_height = 0, 0
    while l_cnt < len(left) - 1 and r_cnt < len(right) - 1:
        left_num, right_num = left[l_cnt], right[r_cnt]
        if right_num < left_num:
            r_height = right[HEIGHT]
            if r_height > l_height:
                skyline += [right_num, r_height]
            r_cnt += 2
        else:
            l_height = left[HEIGHT]
            if l_height > r_height:
                skyline += [left_num, l_height]
            l_cnt += 2

    while l_cnt < len(left):
        left_num, left_height = left[l_cnt], left[l_cnt + 1]
        skyline += [left_num, left_height]
        l_cnt += 2

    while r_cnt < len(right):
        right_num, right_height = right[r_cnt], right[r_cnt + 1]
        skyline += [right_num, right_height]
        r_cnt += 2
    #print skyline
    return skyline


# Just like insertion vs. mergesort, instead of adding
# a building into a skyline, adding a # into a list, we
# split skylines and merge them with eachother.
def div_and_conq(buildings):
    print buildings
    if len(buildings) < 2:
        return [buildings[0][LEFT], buildings[0][HEIGHT],
                     buildings[0][RIGHT], 0]
    mid = len(buildings) / 2
    left, right = buildings[:mid], buildings[mid:]
    left = div_and_conq(left)
    right = div_and_conq(right)
    print left, right
    return merge_skyline(left, right)


if __name__ == '__main__':
    print div_and_conq(buildings)
    assert(bruteforce(buildings) == SOLN)

