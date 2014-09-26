#include <stdio.h>
#include <time.h>
#include <stdlib.h>

/*
 * General purpose functions for C algorithms
 */

// prints an integer array to stdout
void print_int_arr(int *arr, int n)
{
    int i;
    for (i = 0; i < n; i++) {
        printf("%d ", arr[i]); 
    }
    printf("\n");
}

// returns 1 if input int[] is sorted ascending, 0 otherwise
int is_sorted(int *arr, int n)
{
    if (n < 2) return 1;

    int i;
    for (i = 1; i < n; i++) {
        if (arr[i] < arr[i-1]) return 0; 
    }
    return 1;
}

// generates an array with random integers of length n
int *random_int_arr(int n)
{
    srand(time(NULL));
    int *randoms = (int *)malloc(n * sizeof(int));
    int i;
    for (i = 0; i < n; i++) {
        // range: -100 - 100
        randoms[i] = (rand() % 200) - 100;
    }
    return randoms;
}
