__author__ = 'louyang'

# Given a character array, replace all the
# spaces with '%20'"s, assume array is big enough to
# hold additional characters


# O(n) two pass runtime soln, O(1) space
def better_insert20(arr, len_we_know):
    num_spaces = 0

    for c in arr:
        if c == ' ': num_spaces += 1

    # %20 is 3 chars, but one stays in place
    new_len = len_we_know + (num_spaces) * 2
    i = new_len - 1

    while i >= 0:
        if arr[len_we_know-1] == ' ':
            arr[i] = "0"
            arr[i-1] = "2"
            arr[i-2] = "%"
            i -= 3
        else:
            arr[i] = arr[len_we_know-1]
            i -= 1
        len_we_know -= 1
    return arr


# Hashing solution, O(n) runtime, O(n) space
def insert20(arr, orig_len):
    indices = {}
    num_spaces = 0
    for i in range(orig_len):
        if arr[i] == ' ':
            indices[i + (num_spaces * 2)] = "%"
            indices[i+1 + (num_spaces * 2)] = "2"
            indices[i+2 + (num_spaces * 2)] = "0"
            num_spaces += 1
        else:
            indices[i + (num_spaces * 2)] = arr[i]

    for k, v in indices.items():
        arr[k] = v
    return arr



def main():
    arr = ['l', 'u', 'c', ' ', 'a', 's', ' ', ' ', 'c', 'f', 'f', None, None, None ,None, None,
            None, None, None ,None, None, None, None, None, None, None ,None, None, None]
    #print insert20(arr, 8)
    print better_insert20(arr, 11) # Input is the length of the contains elements

main()