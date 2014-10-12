#include <stdio.h>
#include <ctype.h>

#define STAR '*'
#define DOT '.'

// toggle if needed
#define DEBUG 0

/*
 * Write your own regular expression parser for following condition:
 * az*b can match any string that starts with az and ends with b and 0
 * or more characters of any value between. e.g. azb, azsdfnsjdk3b etc.
 * 
 * a.b can match a string starting with a, ending with b with one character
 * of anything inbetween. e.g. a1b, abb, acb
 * 
 * Write a function which takes a regex and a string and returns if
 * the string satisfies the regex condition.
 */
 
// assumes strings are passed in, not char[] arrays due to
// usage of null termination char as bounds
int regex_parse(char *regex, char *string)
{
    if (DEBUG) {
        printf("current state: regex char: %c, string char %c\n",
                *regex, *string);
    }

    // base case, an empty regex must pair with an empty string
    if (*regex == '\0') {
        return (*string == '\0');    
    }

    if (*regex != STAR) {
        return ((*regex == *string) ||
                (*regex == DOT && *string != '\0')) &&
               regex_parse(regex + 1, string + 1);
    }
    else {
        // star matches nothing
        int try1 = regex_parse(regex + 1, string); 
        // star sucks one character from the string
        int try2 = 0;
        if (!try1 && *string != '\0') {
            try2 = regex_parse(regex, string + 1);
        }
        return try1 || try2;
    }
}

void test_regex(char *regex, char *string, int verdict)
{
    int our_answer = regex_parse(regex, string);
    if (our_answer != verdict) {
        printf("Our parser took regex: %s and string: %s, the correct "
               "answer is: %d and our answer was %d!\n",
               regex, string, verdict, our_answer);
    }
}

// nothing printed to STDOUT indicates success
int main(int argc, char *argv[])
{
    char *regex1 = "az*b";
    test_regex(regex1, "azb", 1);
    test_regex(regex1, "azzzb", 1);
    test_regex(regex1, "ab", 0);
    test_regex(regex1, "", 0);
    test_regex(regex1, "czzzb", 0);

    char *regex2 = "*";
    test_regex(regex2, "", 1);
    test_regex(regex2, "sdfdgfdsg768", 1);

    char *regex3 = "*d*";
    test_regex(regex3, "d", 1);
    test_regex(regex3, "", 0);
    test_regex(regex3, "asdfafasdfsadf", 1);

    char *regex4 = "abc..d*z";
    test_regex(regex4, "abcsazfgz", 0);
    test_regex(regex4, "abc12dz", 1);
    test_regex(regex4, "abc00d241243zzzz", 1);

    return 0;
}
