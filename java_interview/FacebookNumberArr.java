package JavaInterview;

import java.util.ArrayList;

public class FacebookNumberArr {
    /*
     * Generate a new array from an array of numbers. Start 
     * from the beginning. Put the number of consecutive 
     * occurances of some number first, and then that number. 
     * For example, from array 1, 1, 2, 3, 3, 1; You 
     * should get 2, 1, 1, 2, 2, 3, 1, 1 Write a
     * program to solve this problem.
     *
     * First thought was to use a hashtable but thats not
     * possible because our presentation is not with unique
     * numbers. Then, it makes more sense to build the
     * result list while we are iterating.
     * 
     * We keep counters of the prev element and the current
     * count of the element so no space is wasted. O(n) time.
     */
    
    public static void main(String[] args) {
        int[] arr = {1, 1, 2, 3, 3, 1};
        ArrayList<Integer> res = new ArrayList<Integer>();
        
        int curcnt = 1;
        int prev = arr[0];
        for (int i=1; i<arr.length; i++) {
            if (arr[i] != prev) {
                res.add(curcnt);
                res.add(prev);
                curcnt = 0;
            } 
            curcnt++;
            prev = arr[i];
        }
        res.add(curcnt);
        res.add(prev);
        System.out.println(res.toString());
    }
}
