package JavaInterview;

import java.util.Stack;

public class SortedStack {
    /*
     * Sort a stack in ascending order with largest  
     * elements on top. You may use one additional 
     * stack to store elements. Stacks support push(),
     * pop(), seek(), isEmpty().
     */
       
    static void sort(Stack<Integer> m, Stack<Integer> buf) {
        /* Iterate stack until find smallest elem.
         * Store smallest in a var. Push everything onto one stack.
         * Push smallest onto bottom of other stack. Repeat process.
         * Time: O(n^2) */
        int size = 0;
        
        while (!m.isEmpty()) {
            int tmin = Integer.MAX_VALUE;
            while (!m.isEmpty()) {
                int e = m.pop();
                buf.push(e);
                if (e < tmin)
                    tmin = e;
                size++;
            }
            // Everything is in buf now.
            while (size > 0) {
                int e = buf.pop();
                if (e == tmin) {
                    // If we hit the min, don't push
                    // anything into m as we already are
                    // storing the min in a var.
                } else {
                    m.push(e);
                }
                size--;
            }
            buf.push(tmin);
        }
    }
    
    public static void main(String[] args) {
        Stack<Integer> m = new Stack<Integer>();
        Stack<Integer> buf = new Stack<Integer>();
        m.add(4);
        m.add(2);
        m.add(3);
        m.add(1);
        System.out.println(m.toString()+" "+buf.toString());
        sort(m, buf);
        System.out.println(m.toString()+" "+buf.toString());
        // [4, 2, 3, 1] []
        // [] [1, 2, 3, 4]
    }
}
