#include <stdio.h>
#include <assert.h>

#define ONE 'I'
#define FIVE 'V'
#define TEN 'X'
#define FIFTY 'L'
#define ONE_HUNDRED 'C'
#define FIVE_HUNDRED 'D'

/*
 * Convert an integer to roman numeral format.
 * Reference: http://literacy.kent.edu/Minigrants/Cinci/romanchart.htm
 */

void to_roman(int n)
{
    assert(n >= 0);

    int pos = 0;
    char roman[500];

    printf("The roman numeral of %d is: ", n);

    while (n > 0) {
        if ( (n / 500 > 0) || (n + 100 >= 500) ) {
            // order of the clauses matters
            if (n / 500 > 0) {
                int i;
                for (i = 0; i < (n / 500); i++) {
                    roman[pos++] = FIVE_HUNDRED;
                }
                n %= 500;
            } else if (n + 100 >= 500) {
                roman[pos++] = ONE_HUNDRED;
                roman[pos++] = FIVE_HUNDRED;
                n -= 400;
            }
        } else if ( (n / 100 > 0) || (n + 10 >= 100) ) {
            if (n / 100 > 0) {
                int i;
                for (i = 0; i < (n / 100); i++) {
                    roman[pos++] = ONE_HUNDRED;
                }
                n %= 100;
            } else if (n + 10 >= 100) {
                roman[pos++] = TEN;
                roman[pos++] = ONE_HUNDRED;
                n -= 90;
            }
        } else if ( (n / 50 > 0) || (n + 10 >= 50) ) {
            if (n / 50 > 0) {
                int i;
                for (i = 0; i < (n / 50); i++) {
                    roman[pos++] = FIFTY;
                }
                n %= 50;
            } else if (n + 10 >= 50) {
                roman[pos++] = TEN;
                roman[pos++] = FIFTY;
                n -= 40;
            }
        } else if ( (n / 10 > 0) || (n + 1 >= 10) ) {
            if (n / 10 > 0) {
                int i;
                for (i = 0; i < (n / 10); i++) {
                    roman[pos++] = TEN;
                }
                n %= 10;
            } else if (n + 1 >= 10) {
                roman[pos++] = ONE;
                roman[pos++] = TEN;
                n -= 9;
            }
        } else if ( (n / 5 > 0) || (n + 1 == 5) ) {
            if (n / 5 > 0) {
                int i;
                for (i = 0; i < (n / 5); i++) {
                    roman[pos++] = FIVE;
                }
                n %= 5;
            } else if (n + 1 >= 5) {
                roman[pos++] = ONE;
                roman[pos++] = FIVE;
                n -= 4;
            }

        } else if (n > 0) {
            int i;
            for (i = 0; i < n; i++) {
                roman[pos++] = ONE;
            }
            n %= 1;  // always 0
        }
    }

    int i = 0;
    while (i < pos) {
        printf("%c", roman[i++]);
    }
    printf("\n");
}

int main(int argc, char *argv[])
{
    int i;
    for (i = 0; i <= 200; i++) {
        to_roman(i);
    }
    return 0;
}
