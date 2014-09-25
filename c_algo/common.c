#include <stdio.h>

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

