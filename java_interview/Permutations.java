package JavaInterview;

import java.util.ArrayList;
import java.util.HashSet;

/*
 * Given a String, generate and print all 
 * of it's permutations 
 */

public class Permutations {
    
    public static ArrayList<String> naive_permute(String s) {
        /* base case */
        if (s.length() <= 1) {
            ArrayList<String> l = new ArrayList<String>();
            l.add(s);
            return l;
        }
        /*
         * permute(cat): cat => c, at 
         * permute(at): at => a, t 
         * permute(t): return [t] "Base Case" 
         * return [at, ta] 
         * return [cat, act, atc, cta, tca, tac]
         */

        String first = s.substring(0, 1);
        String rest = s.substring(1, s.length());
        ArrayList<String> prev_perm = naive_permute(rest);
        ArrayList<String> result = new ArrayList<String>();

        // System.out.println(String.format("first: %s, rest: %s, prev_perm: %s",
        //        first, rest, prev_perm.toString()));

        for (int i = 0; i < prev_perm.size(); i++) {
            String cur = prev_perm.get(i);
            // note the i <= instead of <
            for (int j = 0; j <= cur.length(); j++) {
                String s1 = cur.substring(0, j);
                String s2 = cur.substring(j, cur.length());
                result.add(s1 + first + s2);
            }
        }
        return result;
    }
    
    static void naive_permute_2(String head, String tail) {
        /* Better version of above, does not repeat for distinct 
         * characters. However, still space ineffecient. */
        if (tail.length() == 0) {
            System.out.println(head);
            return;
        }
        
        HashSet<Character> set = new HashSet<Character>();
        for (int i=0; i<tail.length(); i++) {
            if (!set.contains(tail.charAt(i))) {
                set.add(tail.charAt(i));
                naive_permute_2(head + tail.charAt(i), 
                    tail.substring(0, i) + tail.substring(i+1, tail.length()));
            }
        }
    }

    public static void main(String[] args) {
        naive_permute_2("", "car");
        /*
        ArrayList<String> result = naive_permute("car");

        for (String r : result) {
            System.out.println(r);
        }*/
        // System.out.println("test: " + "lucas".substring(0, 0));
    }
}
