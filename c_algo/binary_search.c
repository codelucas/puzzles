#include "dbg.h"

#define NOT_FOUND -1

// given a sorted list of ints, return index of input int
// returns -1 if not found
int binary_search(int *numbers, int lo, int hi, int e)
{
    if (lo > hi) return NOT_FOUND;

    int mid = (lo + hi) / 2;

    if (e < numbers[mid]) {
        return binary_search(numbers, lo, mid - 1, e);
    } else if (e > numbers[mid]) {
        return binary_search(numbers, mid + 1, hi, e);
    } else {
        return mid;
    }
}

void execute_test(int *numbers, int n)
{
    int rc = binary_search(numbers, 0, n - 1, numbers[0]);
    check(rc == 0, "Binary search failed! Recieved %d instead of 0", rc);
    return;
error:;
}

int main(int argc, char *argv[])
{
    // found examples
    int test1[] = {-100, -5, 1, 2, 10, 100, 512};
    execute_test(test1, 7);
    int test2[] = {1, 2};
    execute_test(test2, 2);
    int test3[] = {2};
    execute_test(test3, 1);
    
    // not found example
    int test4[] = {-100, -4, 100, 170, 512};
    int test4_len = sizeof(test4) / sizeof(int);
    int rc = binary_search(test4, 0, test4_len - 1, 777);
    check(rc == -1, "Binary search failed! Recieved %d instead of -1", rc);
    return 0;
error:
    return -1;
}
