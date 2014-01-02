package JavaInterview;

import java.util.Stack;

public class TowersOfHanoi {
    /*
     * You have 3 towers and N disks of diff sizes. 
     * Start with disks sorted in ascending order 
     * on one stack. 
     * 1.) One disk can move at a time.
     * 2.) A disk is slipped off top of one tower to next.
     * 3.) Disks can only be placed on larger disks.
     * 
     * Write a program to move the disks from one tower to
     * another.
     */
    
    static void hanoi(Stack<Integer> start, Stack<Integer> buf, Stack<Integer> end, int N) {
        if (N <= 0)
            return;
        hanoi(start, end, buf, N-1);
        end.push(start.pop());
        hanoi(buf, start, end, N-1);
    }
    
    public static void main(String[] args) {
        Stack<Integer> start = new Stack<Integer>();
        Stack<Integer> end = new Stack<Integer>();
        Stack<Integer> buf = new Stack<Integer>();
        start.add(3);
        start.add(2);
        start.add(1);
        hanoi(start, buf, end, start.size());
        System.out.println(start.toString() + " " + buf.toString() + " " + end.toString());
    }
}
