#include <stdio.h>
#include <math.h>

#define ERROR 0.000001

/*
 * Calculate the square root of a number without
 * using any form of a square root builtin function.
 */

double double_abs(double n)
{
    if (n < 0) {
        return n * -1;
    }
    return n;
}

double _sqrt(int n, double lo, double hi)
{
    double mid = (hi + lo) / 2;
    double difference = double_abs((mid * mid) - n);
    if (difference < ERROR) {
        return mid;
    }

    // the case of mid * mid == n will *never* happen,
    // as it is handled above

    // too big, decrease candidate size
    if (mid * mid > n) {
        return _sqrt(n, lo, mid);
    }
    // not big enough, increase candidate size
    else {
        return _sqrt(n, mid, hi);
    }
}

double sqrt_helper(int n)
{
    return _sqrt(n, 0, n);
}

int main(int argc, char *argv[])
{
    int test1 = 2;
    int test2 = 4;
    int test3 = 0;
    int test4 = 7;
    
    printf("Our square root of %d is %f, the actual square root is %f!\n",
            test1, sqrt_helper(test1), sqrt(test1));
    printf("Our square root of %d is %f, the actual square root is %f!\n",
            test2, sqrt_helper(test2), sqrt(test2));
    printf("Our square root of %d is %f, the actual square root is %f!\n",
            test3, sqrt_helper(test3), sqrt(test3));
    printf("Our square root of %d is %f, the actual square root is %f!\n",
            test4, sqrt_helper(test4), sqrt(test4));

    return 0;
}
