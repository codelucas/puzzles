package JavaInterview;

public class ParenthesisCombos {

    /*
     * Implement an algorithm to print all valid combinations of n-pairs of
     * parenthesis. (Properly opened and closed)
     * 
     * 
     * This solution builds up the combos and prints them. The time complexity
     * is O(2^n)
     * 
     * Can further optimize by using a char[] array instead of attempting to
     * O(n^2) concatenating str's.
     * 
     */

    static void find(String build, int leftparen, int rightparen, int numpairs) {

        /* base case */
        if (leftparen == numpairs && rightparen == numpairs) {
            System.out.println(build);
            return;
        }

        if (leftparen != numpairs) {
            find(build + "(", leftparen + 1, rightparen, numpairs);
        }

        if ((leftparen - rightparen) != 0 && (rightparen != numpairs)) {
            find(build + ")", leftparen, rightparen + 1, numpairs);
        }
    }

    public static void main(String[] args) {
        find("", 0, 0, 1000);
    }
}
