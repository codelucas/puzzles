__author__ = 'Lucas'

# Weird version from the textbook
perms = []
def allPermutations(arr, start):
    if start == len(arr):
        perms.append(''.join(arr))
        return
    i = start
    while i < len(arr):
        arr[start], arr[i] = arr[i], arr[start]
        allPermutations(arr, start + 1)
        # Swap it back
        arr[start], arr[i] = arr[i], arr[start]
        i += 1

# My own version! However, the space complexity is much greater.
def lucasPerms(string):
    if len(string) < 2:
        return string
    new_perms = []
    lst, tail = string[:len(string) - 1], string[len(string) - 1:]
    old_perms = lucasPerms(lst)
    for perm in old_perms:
        for i in range(len(perm) + 1):
            new_perms.append(perm[:i] + tail + perm[i:])
    return new_perms

def main():
    string = "car"
    allPermutations(list(string), 0)
    print "Book's perms: "
    print perms
    my_perms = lucasPerms(string)
    print "Lucas's perms: "
    print my_perms

if __name__ == "__main__":
    main()
