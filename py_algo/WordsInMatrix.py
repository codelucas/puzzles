__author__ = 'louyang'


"""
 Given a 2d matrix with characters and a dictionary. Find
 all the valid words in the 2d matrix. The words
 can be towards right, left , up or down. Exact code to be given.
"""

matrix = [ ['c', 'a', 't'],
           ['g', 'o', 'd'],
           ['f', 'b', 'r']]

dd = {  'cat': 1,
        'god': 1,
        'go': 1,
        'dog': 1,
        'do': 1,
        'boa':1
     }

def build_trie(dictionary):
    trie = {}
    for word in dictionary.keys():
        root = trie
        for char in word:
            if root.get(char) is None:
                root[char] = {}
            root = root[char]
        root['empty'] = 'empty'
        #trie = root
    print trie


# Low memory usage, very little extra storage, however slow runtime
# We can easily improve with a trie
def naive_find_words(matrix, dd):
    words = []
    for i in range(len(matrix)):
        for j in range(len(matrix[0])):

            word = matrix[i][j]
            tmpi, tmpj = i, j + 1
            if word in dd:      # Check current char
                words.append(word)

            while tmpj < len(matrix[0]): # Check cur char to the right
                word += matrix[tmpi][tmpj]
                tmpj += 1
                if word in dd:
                    words.append(word)

            word = matrix[i][j] # Reset
            tmpi, tmpj = i, j - 1

            while tmpj >= 0:        # Check cur char to the left
                word += matrix[tmpi][tmpj]
                tmpj -= 1
                if word in dd:
                    words.append(word)

            word = matrix[i][j] # Reset
            tmpi, tmpj = i - 1, j

            while tmpi >= 0:        # Check cur char to the top
                word += matrix[tmpi][tmpj]
                tmpi -= 1
                if word in dd:
                    words.append(word)

            word = matrix[i][j] # Reset
            tmpi, tmpj = i + 1, j

            while tmpi < len(matrix):   # Check cur char to the bottom
                word += matrix[tmpi][tmpj]
                tmpi += 1
                if word in dd:
                    words.append(word)

    print words

def find_words_fast(matrix, dd):
    trie = build_trie(dd)
    words = []


naive_find_words(matrix, dd)