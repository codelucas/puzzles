package JavaInterview;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

public class NumberCounts {

    /*
     * We have array that contain integer number, I would like to find the
     * numbers that repeated k time in this array. The array is not sorted, and
     * the numbers are not bounded.
     * 
     * [ 20, 6, 99, 3, 6, 2, 1,11, 41, 31, 99, 6, 7, 8, 99, 10, 99, 6 ] Find the
     * numbers repeated more than 3 times.
     * 
     * Answer: 6, 99
     * 
     * Possible answer using bit wise operations (xor) or combination?
     * Efficiency in running time Big(o) is required as well as the space
     * capacity.
     */

    /*
     * O(n) space and O(n) runtime
     */

    public static void solution(int[] arr, int counts) {

        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

        for (int i = 0; i < arr.length; i++) {

            if (map.containsKey(arr[i]))
                map.put(arr[i], map.get(arr[i]) + 1);

            else
                map.put(arr[i], 1);
        }

        ArrayList<Integer> soln = new ArrayList<Integer>();

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {

            Integer key = entry.getKey();
            Integer val = entry.getValue();

            /*
             * We need to add one because "repeated" means not including the
             * first occurance
             */
            if (val >= counts + 1) {
                soln.add(key);
            }
        }

        System.out.println(soln.toString());
    }

    public static void main(String[] args) {

        int[] list = { 20, 6, 99, 3, 6, 2, 1, 11, 41, 31, 99, 6, 7, 8, 99, 10,
                99, 6 };

        solution(list, 3);
    }
}
