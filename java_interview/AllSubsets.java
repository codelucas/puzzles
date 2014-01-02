package JavaInterview;

import java.util.ArrayList;
import java.util.List;

public class AllSubsets {
    /*
     * Write a function that prints out all 
     * subsets of a given set of numbers.
     * 
     * Time complexity is set in stone: O(2^n)
     * This is a property of the powerset. The space
     * is identical.
     */
    static List<List<Integer>> subsets(List<Integer> arr) {
        /* Base case: {}=>{{}} */
        if (arr.size()==0) {
            List<List<Integer>> r = 
                new ArrayList<List<Integer>>();
            r.add(new ArrayList<Integer>());
            return r;
        }
        /* Inductive step: subs = {{}}, first = a,
         *                 results = {{}, {a}} */
        int first = arr.get(0); // 1
        List<Integer> rest = new ArrayList<Integer>(); // [2, 3]
        if (arr.size() > 0) {
            rest = arr.subList(1, Math.max(1, arr.size()));
        }
        System.out.println("first, build: "+first+" "+rest);
        List<List<Integer>> subs = subsets(rest);
        List<List<Integer>> results = new ArrayList<List<Integer>>();
        results.addAll(subs);
        System.out.println("subs: "+subs);
        for (int i=0; i<subs.size(); i++) {
            List<Integer> subset = // new list object to dereference
                 new ArrayList<Integer>(subs.get(i));
            subset.add(first);
            results.add(subset);
        }
        System.out.println("results: "+results);
        return results;
    }
    
    public static void main(String[] args) {
        List<Integer> arr = new ArrayList<Integer>();
        arr.add(1);
        arr.add(2);
        arr.add(3);
        // ans: { {}, {1}, {2}, {3}, {1,2}, {1,3}, {2,3}, {1,2,3} }
        List<List<Integer>> subsets = subsets(arr);
        System.out.println(subsets.toString());
    }
}
