package JavaInterview;

public class CoinArrangement {
    /*
     * Given an infinite amount of quarters, dimes, nickels, and pennies, write
     * code to calculate # of ways to represent n cents.
     * 
     * Time complexity: O(n)
     */

    static int fcoins(int n, int coin) {
        if (n < 0)
            return 0;

        int newcoin = 0;

        if (coin == 25)
            newcoin = 10;
        else if (coin == 10)
            newcoin = 5;
        else if (coin == 5)
            newcoin = 1;
        else
            return 1;

        /* How many current coins can we fit into n? Start from 0 */
        int combos = 0;

        for (int i = 0; i * coin <= n; i++) {
            int remainder = n - i * coin;
            combos += fcoins(remainder, newcoin);
        }
        return combos;
    }

    public static void main(String[] args) {
        System.out.println(fcoins(10, 25));
    }
}
