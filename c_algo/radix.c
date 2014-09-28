#include <stdio.h>
#include <stdlib.h>
#include "common.h"
#include "dbg.h"

#define DIGITS 10

/*
 * Various radix sorts
 *   K = average key length,
 *   n = number of keys
 * Worst run: O(K * n)
 * Worst space: O(K + n)
 */

int get_max(int arr[], int n)
{
    int max = arr[0];

    int i;
    for (i = 1; i < n; i++) {
        if (arr[i] > max) {
            max = arr[i];
        }
    }
    return max;
}

// performs counting sort on the specified digig 
// (exp param), 1 for ones digit, 10 for tens, etc
// a helper function for radix_sort(...)
void counting_sort(int arr[], int n, int exp)
{
    int output[n];
    int count[DIGITS] = {0};

    // store count occurrences
    int i;
    for (i = 0; i < n; i++) {
        count[ (arr[i] / exp) % DIGITS ]++;
    }

    // expand count array so the value at count[i] shows the
    // start position of i
    for (i = 1; i < DIGITS; i++) {
        count[i] += count[i - 1];
    }

    // iterating backwards because count[] elements were incremented
    // when iterating forward, maintain proper ordering
    for (i = n - 1; i >= 0; i--) {
        // subtract 1 from count[...] to convert to indices
        output[count[ (arr[i] / exp) % DIGITS ] - 1] = arr[i];
        // decrement the count after use
        count[ (arr[i] / exp) % DIGITS ]--;
    }
    
    // copy output[] to arr[] so arr[] 
    for (i = 0; i < n; i++) {
        arr[i] = output[i];
    }
}

void radix_sort(int arr[], int n)
{
    // max # (or max key) is needed for arr sizes
    int max_num = get_max(arr, n);

    // perform counting sort for every digit: 1, 10, ...
    for (int exp = 1; (max_num / exp) > 0; exp *= 10) {
        counting_sort(arr, n, exp);
    }
}

// we only test our radix sorts on positive integers
// making radix sort work on negative integers is as easy
// as creating a final bucket for the sign and reversing it
void make_positive(int *arr, int n)
{
    int i;
    for (i = 0; i < n; i++) {
        arr[i] = abs(arr[i]);
    }
}

int main(int argc, char *argv[])
{
    int arr_size = 8;
    // Only works on arrays with only positive integers
    int *arr = random_int_arr(arr_size);
    make_positive(arr, arr_size);
    printf("Array before radix sort: ");
    print_int_arr(arr, arr_size);

    radix_sort(arr, arr_size);

    printf("Array after radix sort: ");
    print_int_arr(arr, arr_size);

    check(is_sorted(arr, arr_size), "Radix sort failed!");
    free(arr);
    return 0;
error:
    free(arr);
    return 1;
}
