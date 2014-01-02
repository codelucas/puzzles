__author__ = 'Lucas'

import random

# Find the first celebrity in a graph of people. Celebrities know nobody
# Everyone knows the celebrity
def naiveCelebrity(graph):
    n = len(graph)
    # For all potential celebrities
    for i in range(n):
        eliminated = False
        # For every other person
        for j in range(n):
            if not eliminated:
                if i == j: # Same person
                    continue
                # If the possible celebrity knows someone, it's not a a celebrity
                # If somebody does not know the possible celebrity, its not a celebrity
                if graph[i][j] or not graph[j][i]:
                    eliminated = True
        if not eliminated:
            return i # If no breaks were encountered, we make it here and return the celeb

# This problem can be reduced by the fact that
# if graph[u][v], then we know u is not a celebrity,
# if not graph[u][v], then we know that v is not a celebrity
# This means we can start eliminating these non celebrities
# In a conveyer belt
def reducedCelebrity(graph):
    n = len(graph)
    # The first two people, we begin eliminating
    u, v = 0, 1
    # We go up to 0 -- n instead of n-1 bc we are not actually
    # gonna call graph[n], that's an index error. We just need something to
    # replace the last person with.
    for i in range(2, n + 1):
        # u knows someone, not a celeb
        if graph[u][v]:
            u = i
        # v is not known, not a
        else:
            v = i

    # As we iterated above, someone was always getting replaced/eliminated as
    # not a celeb, the last person to get eliminated is obviously not a celeb,
    # so the person besides from the last person (we're keeping track of 2 people)
    # has a chance of being a celebrity, if at least one exists actually.
    celeb = None
    if u == n:
        celeb = v
    else:
        celeb = u
    eliminated = False
    for person in range(n):
        if person == celeb:
            continue
        if graph[celeb][person] or not graph[person][celeb]:
            eliminated = True
    if not eliminated:
        return celeb
    return None

def main():
    graph = [[random.randint(0, 1)]*100 for i in range(100)]
    for i in range(random.randint(0, len(graph) - 1)):
        for j in range(len(graph)):
            graph[j][i] = 1
            graph[i][j] = 0

    celeb = naiveCelebrity(graph)
    celeb2 = reducedCelebrity(graph)
    print "Naive celeb:", celeb, "\r\n", "Reduced celeb:", celeb2

if __name__ == "__main__":
    main()