#include <stdlib.h>
#include "dbg.h"
#include "common.h"

/*
 * Inplace quicksort
 * Avg run: O(nlgn)
 * Extra space: O(lgn) (call stack)
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
    int piv = numbers[lo];

    while (cur <= b) {
        // swap with the beginning
        if (numbers[cur] < piv) {
            swap(&numbers[a++], &numbers[cur++]);
        }
        // swap with the end
        else if (numbers[cur] > piv) {
            swap(&numbers[cur], &numbers[b--]);
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
    if (lo >= hi) return;

    int piv = partition(numbers, lo, hi);
    quicksort(numbers, lo, piv - 1);
    quicksort(numbers, piv + 1, hi);
}

void execute_test(int *numbers, int n, int test_num)
{
    quicksort(numbers, 0, n - 1);
    check(is_sorted(numbers, n), "Quicksort failed!");
    free(numbers);
error:;
}

int main(int argc, char *argv[])
{
    execute_test(random_int_arr(7), 7, 1);
    execute_test(random_int_arr(1), 1, 2);
    execute_test(random_int_arr(3), 3, 3);
    execute_test(random_int_arr(2), 2, 4);

    return 0;
}
