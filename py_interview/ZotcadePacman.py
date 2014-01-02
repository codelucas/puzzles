__author__ = 'louyang'
import copy


WALL, BIG, SMALL, EMPTY, GHOST, PACMAN = '%', 'o', '.', ' ', 'G', 'P'
BIG_VAL = 30
LITTLE_VAL = 4
GHOST_VAL = -300

class GameState:
    def __init__(self, board, cur_pos):
        self.board = board
        self.cur_pos = cur_pos
        self.ghost_pos = []
        self.ava_moves = []

    def get_available_moves(self):
        x, y = self.cur_pos[0], self.cur_pos[1]
        moves = []
        if x-1 >= 0 and board[x-1][y] != WALL: moves.append([x-1, y]) # Left
        if x+1 < 27 and board[x+1][y] != WALL: moves.append([x+1, y]) # Right
        if y-1 >= 0 and board[x][y-1] != WALL: moves.append([x, y-1]) # Top
        if y+1 < 26 and board[x][y+1] != WALL: moves.append([x, y+1]) # Bottom
        return moves

    # Move is of form [x, y]
    def make_move(self, move):
        clone = copy.copy(self)
        clone.board[clone.cur_pos[0]][clone.cur_pos[1]] = EMPTY
        clone.board[move[0]][move[1]] = 'P'
        clone.cur_pos = move
        return clone

    def get_score(self):
        if self.board[self.cur_pos[0]][self.cur_pos[1]] == GHOST:
            return GHOST_VAL
        elif self.board[self.cur_pos[0]][self.cur_pos[1]] == BIG_VAL:
            return BIG_VAL
        elif self.board[self.cur_pos[0]][self.cur_pos[1]] == LITTLE_VAL:
            return LITTLE_VAL

    def is_gameover(self):
        for g in self.ghost_pos:
            if g == self.cur_pos: return True

U, D, R, L = "UP", "DOWN", "RIGHT", "LEFT"
# Pos are represented as [x, y]
player = input()
# Read Pacman Position
p_pos = [int(x) for x in raw_input().strip().split(' ')]
num_g = input()
g_pos = []
# Read ghosts
for g in range(num_g):
    g_pos.append([int(x) for x in raw_input().strip().split(' ')])

board = []
# Read Board (27 lines by 28 chars each)
for i in range(27):
    board.append(list(raw_input().strip()))

import random

def gave_up():
    counter = 200
    if player == 1:
        while counter > -1:
            r = random.randint(1, 4)
            if r == 1: print U
            if r == 2: print D
            if r == 3: print R
            if r == 4: print L
            counter -= 1
    else:
        while counter > -1:
            r = random.randint(1, 4)
            if r == 1: print ' '.join([U, D, R, L])
            if r == 2: print ' '.join([U, L, R, L])
            if r == 3: print ' '.join([R, L, D, L])
            if r == 4: print ' '.join([U, D, D, U])
            counter -= 1


gave_up()


"""
#print player, p_pos, num_g, g_pos
#for b in board: print b

def eval_move(new, orig):
    if new[0] == orig[0]-1: return L
    if new[0] == orig[0]+1: return R
    if new[1] == orig[1]-1: return U
    if new[1] == orig[1]-1: return D
    else: return D


def game():
    game_state = GameState(board, p_pos)
    cur_move = p_pos
    while not game_state.is_gameover():
        move = minimax(game_state)
        print move
        #print eval_move(move, cur_move)
        cur_move = move


def minimax(game_state):
    moves = game_state.get_available_moves() # [x, y] form
    print moves
    best_move = moves[0]
    best_score = float('-inf')
    for move in moves:
        clone = game_state.make_move(move)
        score = min_play(clone, 3)
        if score > best_score:
            best_move = move
            best_score = score
    return best_move

def min_play(game_state, depth):
    if depth == 0 or game_state.is_gameover():
        return game_state.get_score()

    moves = game_state.get_available_moves()
    lo_score = float('inf')
    for move in moves:
        clone = game_state.make_move(move)
        score = max_play(clone, depth-1)
        if score < lo_score:
            lo_score = score
    return lo_score

def max_play(game_state, depth):
    if depth == 0 or game_state.is_gameover():
        return game_state.get_score()

    moves = game_state.get_available_moves()
    hi_score = float('-inf')
    for move in moves:
        clone = game_state.make_move(move)
        score = min_play(clone, depth-1)
        if score > hi_score:
            hi_score = score
    return hi_score


game()
"""