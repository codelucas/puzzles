__author__ = 'louyang'

# Alice is a kindergarden teacher. She wants to give some candies
# to the children in her class. All the children sit in a line and
# each of them has a rating score according to his or her usual
# performance. Alice wants to give at least 1 candy for each child.
# Children get jealous of their immediate neighbors, so if two
# children sit next to each other then the one with the higher rating
# must get more candies. Alice wants to save money, so she
# wants to minimize the total number of candies.

def get_candies(children):
    # Initalize everyone w/ 1
    candies = [1 for x in range(len(children))]
    # Forwards loop from 1 to N-1, relax right
    for i in range(1, len(children)):
        if children[i] > children[i-1]:
            candies[i] = candies[i-1] + 1
    # Backwards loop from N-2 to 0, relax left
    for i in range(len(children)-2, -1, -1):
        if children[i] > children[i+1]:
            # At this moment, candies[i] is already candies[i-1] + 1
            # if it was greater, so we are effectively comparing both adjacents
            # If cur is bigger than both prev and next, we can't set cur to
            # a lower value or the rules will be violated with prev. We must choose
            # the greatest from cur and next + 1, if next + 1 is greater we must set
            # to fulfill the rule
            candies[i] = max(candies[i], candies[i+1] + 1)
    # Sum it all up and return
    return str(sum(candies))


num_children = input()
children = []
for i in range(num_children):
    children.append(input())
print get_candies(children)