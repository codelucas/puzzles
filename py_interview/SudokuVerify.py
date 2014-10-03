#!/bin/env python

# Sudoku verifier
# ---------------
# Reads one line of integers from stdin as the board,
# outputs 1 if it's a valid board, 0 otherwise.

# This code isn't meant to be performant (it blows a lot
# of space), but it's very understandable!

ROW_SIZE = 9


def make_grid(list_ints):
    """yield n sized chunks from input list l
    """
    grid = []
    for i in xrange(0, len(list_ints), ROW_SIZE):
        grid.append(list_ints[i:i+ROW_SIZE])
    return grid


def verify_cols(grid):
    """verify all columns have no duplicates
    """
    for i in xrange(ROW_SIZE):
        col = [row[i] for row in grid]
        if len(set(col)) != ROW_SIZE:
            return False
    return True


def verify_rows(grid):
    """verify all rows have no duplicates
    """
    for row in grid:
        if len(set(row)) != ROW_SIZE:
            return False
    return True


def verify_square(square):
    """verify input subgrid has no grids
    """
    flat_sq = [e for row in square for e in row]
    if len(set(flat_sq)) != ROW_SIZE:
        return False
    return True


def verify_squares(grid):
    """takes a 9x9 grid and splits it into logical 3x3
    squares and then performs duplicate verification on
    each square
    """
    for i in xrange(ROW_SIZE, 3):
        for j in xrange(ROW_SIZE, 3):
            sq = [row[j:j+3] for row in grid[i:i+3]]
            if not verify_square(sq):
                return False
    return True


def main():
    str_grid = raw_input()
    list_ints = [int(x) for x in list(str_grid)]
    grid = make_grid(list_ints)

    if (verify_rows(grid) and verify_cols(grid) and
            verify_square(grid)):
        print "1"
    else:
        print "0"


if __name__ == '__main__':
    main()
