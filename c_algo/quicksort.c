#include <stdio.h>
#include "dbg.h"
#include "common.h"

/*
 * Inplace quicksort
 * Avg run: O(nlgn)
 * Extra space: O(lgn) *call stack
 */

void swap(int *a, int *b)
{
    int tmp = *a;
    *a = *b;
    *b = tmp;
}

// inplace array partition, returns the pivot
// array index after the list is split into
// [ X <= mid | X > mid], with the pivot being the
// last element in the X <= mid portion
int partition(int *numbers, int lo, int hi)
{
    int a = lo;
    int b = hi;
    int cur = lo;
    int mid = numbers[lo];    

    while (cur <= b) {
        // swap with the beginning
        if (numbers[cur] < mid) {
            swap(&numbers[a], &numbers[cur]);      
            a++;
            cur++;
        }
        // swap with the end
        else if (numbers[cur] > mid) {
            swap(&numbers[cur], &numbers[b]);
            b--;
        }
        // if numbers[cur] == mid
        else {
            cur++;
        }
    }
    return b;
}

// takes the partitioned list and quicksorts
// both partitions:
// quicksort( [ X <= mid ] )
// quicksort( [ X >  mid ] )
void quicksort(int *numbers, int lo, int hi)
{
    if ((hi - lo) < 2) return;

    int piv = partition(numbers, lo, hi); 
    quicksort(numbers, lo, piv);
    quicksort(numbers, piv + 1, hi);
}

void execute_test(int *numbers, int n, int test_num)
{
    printf("test #%d prior to sort:\t", test_num); 
    print_int_arr(numbers, n);
    quicksort(numbers, 0, n-1);
    printf("test #%d after sort:\t", test_num);
    print_int_arr(numbers, n);
}

int main(int argc, char *argv[])
{
    int test1[] = {10, -4, 7, 100, 10000, -512, 512};
    int test2[] = {10};
    int test3[] = {100, 2, 100};

    execute_test(test1, sizeof(test1) / sizeof(int), 1);
    execute_test(test2, sizeof(test2) / sizeof(int), 2);
    execute_test(test3, sizeof(test3) / sizeof(int), 3);

    return 0;
}
