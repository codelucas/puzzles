__author__ = 'louyang'


# You and your K-1 friends want to buy N flowers. Flower number
# i has cost ci. Unfortunately the seller does not like a customer
# to buy a lot of flowers, so he tries to change the price of
# flowers for customer who had bought flowers before.
# More precisely if a customer has already bought x flowers,
# he should pay (x+1)*ci dollars to buy flower number i.

# You and your K-1 friends want to buy all N flowers in
# such a way that you spend the least amount of money possible.
# You can buy the flowers in any order.

# Input:
# The first line of input contains two integers N and K
# (K <= N) next line contains N positive integers


# Output:
# Print the minimum amount of money you (and your friends)
# have to pay in order to buy all N flowers.

# Sample input :
# 3 3
# 2 5 6

# Sample output :
# 13

import heapq

def main():
    num_flowers, num_friends = raw_input().split(' ')
    flowers = [ int(x) for x in raw_input().split(' ') ]
    # A dictionary for every person. Value is another dictionary showing prices for that person
    # f_map = { fid:{ index:f for index, f in enumerate(flowers) } for fid in range(int(num_friends)) }

    friends = [ 0 for i in range(int(num_friends)) ]
    heap = []
    for f in friends:
        heapq.heappush(heap, f)

    # sort biggest flowers first
    flowers.sort(reverse=True)
    total = 0
    for f in flowers:
        x = heapq.heappop(heap)
        total += (x + 1) * f
        heapq.heappush(heap, x+1)
    print total


main()