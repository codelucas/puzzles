__author__ = 'louyang'



# Store indices which contain zero, set after
# A more efficient solution would be to only
# store the rows and columns!
def set_zeros(m):
    dd = {} # Hash the indicies
    for i in range(len(m)):
        for j in range(len(m[i])):
            if int(m[i][j]) == 0:
                cur_i, cur_j = 0, 0
                while cur_j < len(m[i]): # Set the row
                    dd[(i, cur_j)] = True
                    cur_j += 1
                while cur_i < len(m):
                    dd[(cur_i, j)] = True # Set the column
                    cur_i += 1

    for k,v in dd.items():
        m[k[0]][k[1]] = 0
    return m


def main():
    m = [
            [10, 2, 3, 7],
            [1, 3, 0, 19],
            [10, 13, -5, 400]
        ]
    for row in set_zeros(m):
        print row
main()