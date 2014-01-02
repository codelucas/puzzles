__author__ = 'Lucas'

# Sample Input

# 0 0
# b---d
# -d--d
# --dd-
# --d--
# ----d

# The bot here is positioned at the top left corner
# of a 5*5 grid. Your task is to move the bot
# to clean all the dirty cells.
def pprint(bot_x, bot_y):
    dirt_x = dirty[d_loc][0]
    dirt_y = dirty[d_loc][1]

    if bot_x == dirt_x and bot_y == dirt_y:
        clean()
        print('CLEAN')
    elif bot_x > dirt_x:
        print('UP')
    elif bot_y > dirt_y:
        print('LEFT')
    elif bot_x < dirt_x:
        print('DOWN')
    else:
        print('RIGHT')

def find_all_dirts(board):
    for x_idx in range(5):
        for y_idx in range(5):
            if board[x_idx][y_idx] == 'd':
                dirty.append([x_idx, y_idx])
    global d
    d = True

def find_closest_dirt(bot_x, bot_y):
    min = 9
    for i in range(len(dirty)):
        dirt = dirty[i]
        dist_x = bot_x - dirt[0] if bot_x > dirt[0] else dirt[0] - bot_x
        dist_y = bot_y - dirt[1] if bot_y > dirt[1] else dirt[1] - bot_y
        dist = dist_x + dist_y
        if dist < min:
            min_idx = i
            min = dist

    global d_loc
    d_loc = min_idx

def clean():
    global d_loc
    dirty.pop(d_loc)
    d_loc = -1

dirty = []
d = 0
d_loc = -1
# Head ends here
def next_move(posx, posy, board):
    if d == False:
        find_all_dirts(board)
    if d_loc == -1:
        find_closest_dirt(posx, posy)

    pprint(posx, posy)

# Tail starts here
if __name__ == "__main__":
    pos = [int(i) for i in input().strip().split()]
    board = [[j for j in input().strip()] for i in range(5)]
    next_move(pos[0], pos[1], board)