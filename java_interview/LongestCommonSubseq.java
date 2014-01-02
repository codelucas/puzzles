package JavaInterview;

public class LongestCommonSubseq {
    /* Given two arrays of integers. Find the
     * longest common subsequence of integers in the
     * two arrays. */
    
    static int[] longestcommon(int[] X, int[] Y) {
        /* This DP solution is O(m*n) time when
         * m=len(X) and n=len(Y). Unfortunatly it costs
         * O(m*n) space but we can reduce this with knowledge
         * that at each state we only need to know 1-2 
         * rows/cols of info. */
        int[][] dp = new int[X.length+1][Y.length+1];
        
        // note that we are going from 0->length in the loop!
        for (int i=0; i<=X.length; i++) {
            for (int j=0; j<=Y.length; j++) {
                // Initialize all to zeros because DP means
                // we start at indices 1 and check backwards.
                if (i==0 || j==0) {
                    dp[i][j] = 0;
                } 
                else if (X[i-1] == Y[j-1]) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else if (dp[i-1][j] >= dp[i][j-1]) {
                    dp[i][j] = dp[i-1][j];
                } else if (dp[i-1][j] < dp[i][j-1]) {
                    dp[i][j] = dp[i][j-1];
                }
            }
        }

        System.out.println("The length of our common subseq is: " 
                + dp[X.length][Y.length]);
        
        // We backtraack in our dp array to find the sequence
        int[] seq = new int[Math.min(X.length, Y.length)];
        int a = X.length;
        int b = Y.length;
        int cnt = 0;
        
//     // print matrix for debugging
//        for (int i=0; i<dp.length; i++) {
//            for (int j=0; j<dp[0].length; j++) {
//                System.out.print(dp[i][j]+" ");
//            }
//            System.out.print("\r\n");
//        }
        
        while (a > 0 && b > 0) {
            if (X[a-1] == Y[b-1]) {
                seq[cnt++] = X[a-1];
                a--;
                b--;
            } else if (dp[a-1][b] >= dp[a][b-1]) {
                a--;
            } else {
                b--;
            }
        }
        return seq;
    }
    
    public static void main(String[] args) {
        int[] X = {6, 2, 3 , 7, 100, 12, 14};
        int[] Y = {2, 12, 3, 7, 100, 14};
        int[] longest = longestcommon(X, Y);
        System.out.print("Our common subseq is: [ ");
        for (int i : longest) {
            System.out.print(i + " ");
        }
        System.out.print("]");
    }
}
