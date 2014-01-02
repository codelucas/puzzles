__author__ = 'louyang'

# Given a matrix that is NxN, rorate it 90 degrees to the right.
# Do it in place
import math

def rotate(m):
    n = len(m) # or len(m[0])
    # Translate it by moving edges to the right,
    # we do it in layers, starting from the outside

    for start in range(int(math.floor(n / 2))):
        end = n - 1 - start

        # IMPORTANT: Notice how we are missing the last index for end...
        # This is because we skip the last of every row, it will be covered
        for j in range(start, end):
            tmp = m[start][j]               # tmp <- Top
            m[start][j] = m[end-j][start]   # Top <- Left
            m[end-j][start] = m[end][end-j] # Left <- Bottom
            m[end][end-j] = m[j][end]       # Bottom <- Right
            m[j][end] = tmp                 # Right <- tmp

def main():
    m = [
      [1,2,3,4],
      [5,6,7,8],
      [9,10,11,12],
      [13,14,15,16]
    ]

    rotate(m)
    for i in m:
        print i

main()
