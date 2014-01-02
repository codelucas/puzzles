package JavaInterview;

import java.util.ArrayDeque;
import java.util.Queue;

public class ClosestKPoints {
    /* There are n points on a 2D plane, find 
     * the k points that are closest to origin 
     * (x=0, y=0) 
     * 
     * First thought: BFS with a counter on k
     * which is constantly decrementing if
     * something is found. O(n) worse case time.
     * 
     * ^ That didn't work for various reasons.
     * Second thought.. Iterate the list and
     * create a new list with nodes containing
     * (dist from orig, 0-1 status), only keep 1's.
     * Perform QuickSelect on this list O(n) time
     * and we get the Kth farthest element.
     * 
     * Every element to the left of this element on
     * the list is our answer!
     * */
    
    static String arrstr(int[] arr) {
        String s = "[";
        for (int i : arr) {
            s += " "+i;
        }
        return s+" ]";
    }
    /* 
    static boolean inbounds(int[][] plane, int x, int y) {
        if (x >= 0 && y >= 0 && 
            x < plane[0].length && y < plane.length) {
            return true;
        }
        return false;
    }
    
    static int[][] closestk(int[][] plane, int k) {
        Queue<int[]> q = new ArrayDeque<int[]>();
        int[][] found = new int[k][2];
        int n=0;
        int x=0;
        int y=0;
        q.add(new int[]{x, y});
        while (!q.isEmpty()) {
            int[] tup = q.remove();
            int tmpx = tup[0];
            int tmpy = tup[1];
            int e = plane[tmpy][tmpx];
            if (e == 1) {
                found[n++] = tup;
            } if (n == k) {
                return found;
            }
            // NOTE: The order of the following 3
            // if statements are crutial. The diagnol
            // square is the "farthest" from our current point
            if (inbounds(plane, x+1, y)) {
                q.add(new int[]{x+1, y});
            }
            if (inbounds(plane, x, y+1)) {
                q.add(new int[]{x, y+1});
            }
            if (inbounds(plane, x+1, y+1)) {
                q.add(new int[]{x+1, y+1});
            }
            x++;
            y++;
        }
        return found;
    }
    */
    public static void main(String[] args) {
        int[][] plane = new int[][] {
                {1, 0, 0, 1, 0, 1},
                {1, 1, 1, 1, 1, 0},
                {0, 0, 1, 1, 1, 1}
        };
        int k = 3;
        int[][] res = closestk(plane, k);
        for (int[] a : res) {
            System.out.println(arrstr(a));
        }
    }
}
