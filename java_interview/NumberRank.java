package JavaInterview;


public class NumberRank {
    /*
     * You are reading in a stream of integers.
     * At any time, you may want to look up the
     * rank of a number x. rank(x) being the #
     * of numbers less than or equal to x not 
     * including x.
     * 
     * Here are our options:
     * Keep an unsorted list with quicksort
     *      O(1) insertion, O(nlogn) read time
     * Keep an unsorted list but use radix sort? 
     *      O(1) inesrtion, O(n) read time, O(n) extra memory
     * Keep a binary tree?
     *      O(logn) insertion, O(logn) read,
     */
    class Node {
        Node left;
        Node right;
        /* we bookkeep the # of nodes in the
         * left subtree of every node for
         * effecient rank evaluation when doing 
         * a traversal 
         * */
        int nodesinleft = 0;
        int data;
        public Node(int data) {
            this.data = data;
        }
    }
    
    Node binarytree;
    
    public NumberRank(int firstelem) {
        binarytree = new Node(firstelem);
    }
    
    private void insert(Node n, int x) {     
        /* O(nlgn) */
        if (x <= n.data) {
            n.nodesinleft += 1;
            if (n.left == null) {
                Node _new = new Node(x);
                n.left = _new;
                return;
            } else {
                // If we recurse to the left, 
                // increment cur nodes left subtree
                insert(n.left, x);
            }
        } else {
            if (n.right == null) {
                Node _new = new Node(x);
                n.right = _new;
                return;
            } else {
                insert(n.right, x);
            }
        }
    }
    
    private int traverse(Node n, int x, int totalrank) {
        if (x > n.data) {
            if (n.right != null) {
                int ret = traverse(n.right, x, 
                        totalrank+n.nodesinleft);
                return (ret == -1 ? -1 : ret);
            } else {
                return -1;
            }
        } else if (x < n.data) {
            if (n.left != null) {
                int ret = traverse(n.left, x, totalrank);
                return (ret == -1 ? -1 : ret);
            } else {
                return -1;
            }
        } else {
            return n.nodesinleft+totalrank+1;
        }
    }
    
    public void track(int x) {
        /* Called once after each number is read */
        this.insert(binarytree, x);
    }
    
    public int getrank(int x) {
        /* Returns # of elements <= to x, 
         * not including x */
        return traverse(binarytree, x, 0);
    }
    
    
    public static void main(String[] args) {
        int[] stream = {4, 2, 10, -4, 3, 6, 8, 30, 2, 100};
        NumberRank obj = new NumberRank(stream[0]);
        for (int i=1; i<stream.length; i++) {
            obj.track(stream[i]);
            System.out.println("just inserted "+stream[i]+
                 "  "+obj.getrank(10));
        }
    }
}
