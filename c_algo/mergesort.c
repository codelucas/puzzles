#include <stdio.h>
#include <stdlib.h>
#include "common.h"

/*
 * Mergesort
 * Avg run: O(nlgn)
 * Extra space: O(n) (helper arrays)
 */

// inputs two sorted lists and churns them together into
// one big sorted list
void merge(int *numbers, int lo, int mid, int hi, int n)
{
    // copy into helper arrays, stack allocated > heap in this case
    int i;
    int left[mid - lo + 1];
    int right[hi - (mid + 1) + 1];

    for (i = lo; i <= mid; i++) {
        left[i - lo] = numbers[i];
    }
    for (i = mid + 1; i <= hi; i++) {
        right[i - (mid + 1)] = numbers[i];
    }

    // merge two lists together, comparing all elements
    int cur = lo;
    int lo_ref = lo;
    int hi_ref = mid + 1;

    while (lo_ref <= mid && hi_ref <= hi) {
        if (left[lo_ref - lo] <= right[hi_ref - (mid + 1)]) {
            numbers[cur++] = left[lo_ref++ - lo];
        } else {
            numbers[cur++] = right[hi_ref++ - (mid + 1)];
        }
    }

    // copy remainders of the longer sublist over
    while (lo_ref <= mid) {
        numbers[cur++] = left[lo_ref++ - lo];
    }
    while (hi_ref <= hi) {
        numbers[cur++] = right[hi_ref++ - (mid + 1)];
    }
}

// prefix _ to avoid overriding the `mergesort` function in
// stdlib.h
void _mergesort(int *numbers, int lo, int hi, int n)
{
    if (lo >= hi) return;

    int mid = (lo + hi) / 2;
    _mergesort(numbers, lo, mid, n);
    _mergesort(numbers, mid + 1, hi, n);
    merge(numbers, lo, mid, hi, n);
}

// TODO: I'd love for this function to be put into the common.c
// generic file for all algorithms but we'd need to pass in a
// function pointer and different sorting functions have different
// signatures.
void execute_test(int *numbers, int n, int test_num)
{
    printf("test #%d prior to sort:\t", test_num);
    print_int_arr(numbers, n);
    _mergesort(numbers, 0, n-1, n);
    printf("test #%d after sort:\t", test_num);
    print_int_arr(numbers, n);
    printf("VERDICT: %s\n", 
        is_sorted(numbers, n) ? "It's sorted" : "It's not sorted!");
    free(numbers);
}

int main(int argc, char *argv[])
{
    execute_test(random_int_arr(7), 7, 1);
    execute_test(random_int_arr(1), 1, 2);
    execute_test(random_int_arr(3), 3, 3);
    execute_test(random_int_arr(2), 2, 4);

    return 0;
}
