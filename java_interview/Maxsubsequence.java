package JavaInterview;

public class Maxsubsequence {
    /*
     * Find the maximum subsequence sum of an array of integers which contains
     * both positive and negative numbers and return the starting and ending
     * indices within the array. For example:
     * 
     * int array[] = {1, -2, -3, 4, 5, 7, -6}
     * 
     * The max subsquence sum is 4+5+7= 16 and start index is at 3 and end index
     * is at 5.
     */
    static int[] maxsubseq(int[] list) {
        /* O(n) implementation of max subsequence. We make 
         * one pass through the array while keeping track of (x, y)
         * coords of current largest and max largest. */
        int cur = 0;
        int max = 0;
        int[] maxcoords = new int[2];
        int[] curcoords = new int[2];
        boolean newtry = true;
        for (int i=0; i<list.length; i++) {
            curcoords[1] = i;
            if (newtry) {
                curcoords[0] = i;
                curcoords[1] = i;
                newtry = false;
            }
            cur += list[i];
            if (cur < 0) {
                cur = 0;
                newtry = true;
            }
            else if (cur > max) {
                max = cur;
                maxcoords[0] = curcoords[0];
                maxcoords[1] = curcoords[1];
                // System.out.println(maxcoords[0] + " " + maxcoords[1]);
            }
        }
        System.out.println("max val: " + max);
        return maxcoords;
    }
    
    public static void main(String[] args) {
        int[] list = {3, 2, -1, 5, 3, -20, 2, 3, 4, 5, 6, -3, -100, 4};
        // ans: 6 - 10
        int[] ans = maxsubseq(list);
        System.out.println("ans is: " + ans[0]+" "+ans[1]);
    }
}
