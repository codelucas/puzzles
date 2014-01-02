package JavaInterview;

import java.util.ArrayList;
import java.util.HashMap;

public class LargePrimes {
    /*
     * Print out a list of all primes up to n, given n >= 1.
     * 
     * After each number we store all future factors of that number into a big
     * list (bitset to save memory). If future numbers are in that list, we know
     * it's not prime. We also skip all even numbers, they are prime.
     */

    public static void main(String[] args) {
        
        int input = 1000000;
        HashMap<Integer, Boolean> map = new HashMap<Integer, Boolean>();
        ArrayList<Integer> primes = new ArrayList<Integer>();
        primes.add(1); // 1 is the first prime

        for (int i = 2; i <= input; i++) {
            // constant
            if (map.get(i) != null)
                continue;
            else
                primes.add(i);

            for (int j = i; j <= input; j += i)
                map.put(j, true);
        }
        System.out.println(primes.toString());
    }
}
