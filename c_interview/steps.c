#include <stdio.h>

/*
 * A child is running up a staircase with n steps. He can go 1, 2, or 3
 * steps at a time. Count how many possible ways he can run up the stairs.
 * 
 * Solve both naively and with DP
 */

// O(3^n) runtime
int naive_steps(int n)
{
    // negative steps indicate a failed attempt (or bad input)
    if (n < 0) {
        return 0;
    }

    // n being 0 indicates a successful step attempt
    // *or no steps, which also yields one way to run up the stairs! :)
    else if (n == 0) {
        return 1;
    }

    // try all three step options, return the summation of
    // the possibilities
    int t1 = naive_steps(n - 1);
    int t2 = naive_steps(n - 2);
    int t3 = naive_steps(n - 3);

    return t1 + t2 + t3;
}

// use dp to cache old values
int steps(int n, int cache[])
{
    if (n < 0)
        return 0;

    else if (n == 0)
        return 1;

    // only compute the step sum if it's not already cached
    if (cache[n] == -1) {
        cache[n] = steps(n - 1, cache) +
                   steps(n - 2, cache) +
                   steps(n - 3, cache);
    }
    // already cached, return cached value
    return cache[n];
}

int main(int argc, char *argv[])
{
    int i;
    for (i = 0; i <= 30; i++) {
        printf("The number of ways to naive traverse %d steps is %d.\n",
                i, naive_steps(i));
    }

    int cache[31];
    for (i = 0; i <= 30; i++) {
        cache[i] = -1;
    }

    for (i = 0; i <= 30; i++) {
        printf("The number of ways to dp traverse %d steps is %d.\n",
                i, steps(i, cache));
    }

    return 0;
}
