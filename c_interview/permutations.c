#include <stdio.h>

/*
 * Given a string, generate and print all of
 * its permutations
 */

void swap(char *a, char *b)
{
    char tmp = *a;
    *a = *b;
    *b = tmp;
}

void permute(char *str, int lo, int hi)
{
    // null terminating char automatically handled
    if (lo == hi) {
        printf("%s\n", str);
        return;
    }

    int i;
    for (i = lo; i <= hi; i++) {
        swap(&str[lo], &str[i]);
        permute(str, lo + 1, hi);
        // all operations are on one string object
        // in memory, so backtrack
        swap(&str[lo], &str[i]);
    }
}

int main(int argc, char *argv[])
{
    char str[] = "car";
    // -1 to convert to indices and -1 to trim off the
    // null termination char
    int last_index = sizeof(str) - 2;
    permute(str, 0, last_index);

    return 0;
}
