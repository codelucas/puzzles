package JavaInterview;


public class LongestIncreasingSubseq {
    
    static int increasingsubseq(int[] list) {
        /* O(n^2) implementation of longest increasing subseq 
         * This problem quickly stands out as a DP problem. 
         * Iterate the list and hash elements with their longest subseq. 
         * */
        int max = 0;
        // index->subseqlength
        int[] map = new int[list.length]; 
        map[0] = 1;
        for (int i=1; i<list.length; i++) {
            map[i]=1;
            for (int j=0; j<i; j++) {
                if (list[j] < list[i]) {
                    if (map[j]+1 > map[i]) { // append 1 because map[i]=1
                        map[i] = map[j]+1;
                    }
                }
            }
            if (map[i] > max) {
                max = map[i];
            }
        }
        return max;
    }
    
    static int binary_search(int[] c, int sz, int i) {
        /* Todo later. */
        return -1;
    }
    
    static int _increasingsubseq(int[] array) {
        /* O(nlogn) implementation of this problem which
         * I don't understand right now. Here is it's
         * code for future reference. 
         * 
         * Now the improvement happens at the second loop, improve 
         * the speed w/ binary search. Besides the array dp[], 
         * let's have another array c[], c is pretty special, c[i] means: 
         * the minimum value of the last element of the longest 
         * increasing sequence whose length is i.
         * */
        int[] c = new int[array.length];
        int[] dp = new int[array.length];
        int sz = 1;
        int max = 0;
        /* at this point, the minimum value of the last 
         * element of the size 1 increasing sequence 
         * must be array[0] */
        c[1] = array[0]; 
        dp[0] = 1;
        for (int i=1; i<array.length; i++) {
           /* you have to update the minimum value right now */
           if(array[i] < c[1]) {
              c[1] = array[i]; 
              dp[i] = 1;
           }
           else if (array[i] > c[sz]) {
              c[sz+1] = array[i];
              dp[i] = sz+1;
              sz++;
           }
           else {
              /* you want to find k so that c[k-1]<array[i]<c[k] */
              int k = binary_search(c, sz, array[i]); 
              c[k] = array[i];
              dp[i] = k;
           }
           if (dp[i] > max) {
               max = dp[i];
           }
        }
        return max;
    }
    
    public static void main(String[] args) {
        int[] list = {3, 2, 1, 5, 3, 20, 2, 3, 4, 5, 6, 3, 100, 4};
        int ans = increasingsubseq(list);
        System.out.println("ans is: " + ans);
    }
}
