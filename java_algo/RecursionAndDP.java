package JavaAlgo;

import java.util.Arrays;

public class RecursionAndDP {

    /*
     * The following will be a list of methods which are my solutions to
     * problems on chapter 9 on Cracking the Coding Interview
     */

    /*
     * A child is running up a staircase with n steps. He can go 1, 2, or 3
     * steps at a time. Count how many possible ways he can run up the stairs.
     */
    public static int naiveSteps(int n) {
        /* This naive solution is O(3^n) */

        if (n < 2)
            return 1;

        if (n < 3)
            return naiveSteps(n - 2) + naiveSteps(n - 1);

        return naiveSteps(n - 3) + naiveSteps(n - 2) + naiveSteps(n - 1);
    }

    public static int smartSteps(int n, int[] memo) {
        /* It's linear with DP */

        if (n < 2)
            return 1;

        if (memo[n] > -1) {
        }

        else {
            if (n < 3) {
                memo[n] = smartSteps(n - 2, memo) + smartSteps(n - 1, memo);
            } else {
                memo[n] = smartSteps(n - 3, memo) + smartSteps(n - 2, memo)
                        + smartSteps(n - 1, memo);
            }
        }

        return memo[n];
    }

    public static void main(String[] args) {
        System.out.println(naiveSteps(3));
        int[] arr = new int[4];
        Arrays.fill(arr, -1);
        System.out.println(smartSteps(3, arr));
    }
}
