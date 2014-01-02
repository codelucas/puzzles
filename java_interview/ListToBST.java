package JavaInterview;

import java.util.Iterator;
import java.util.LinkedList;

class TreeNode {
    TreeNode left;
    TreeNode right;
    int data;
    public TreeNode(int data) {
        this.data = data;
    }
}

public class ListToBST {
    /* Convert a sorted linked list into
     * a binary search tree.
     * 
     *  Observations:
     *  If we had an array with random access, 
     *  this can be done in O(n) with binary
     *  searches on both sides of the list. While
     *  building a tree from top down.
     *  
     *  Naively, we can just binary search the
     *  linked list like above but iterate to 
     *  the middle each time O(n^2).
     *  
     *  I'm aware that there is a fancy complicated 
     *  way to build the tree from bottom up, but 
     *  maybe later... O(n) time O(1) memory.
     *  
     *  How about, taking a linked list, doing an O(n)
     *  pass and building an array. Then just using
     *  that array to build out BST?
     *  It's a compromise, O(n) time O(n) space.
     */
    
    static TreeNode buildtree(int[] arr, int a, int b) {
        if (b < a) {
            return null;
        }
        // following is incredibally important.
        // because we want a compelete tree, elements
        // should be filled as far to the right as
        // possible in the bottom row, we should then
        // take the ceiling if it is a even sized list.
        int m = (int) Math.ceil((a+b)/2);
        TreeNode root = new TreeNode(arr[m]);
        TreeNode left = buildtree(arr, a, m-1);
        if (left != null) {
            root.left = left;
        }
        TreeNode right = buildtree(arr, m+1, b);
        if (right != null) {
            root.right = right;
        }
        return root;
    }
    
    static TreeNode solution(LinkedList<Integer> l) {
        int[] arr = new int[l.size()];
        Iterator<Integer> i = l.iterator();
        int cnt = 0;
        while (i.hasNext()) {
            arr[cnt++] = i.next();
        }
        // for (int v : arr) System.out.print(v + " ");
        return buildtree(arr, 0, arr.length-1);
    }
    
    static void inordertrav(TreeNode root) {
        if (root.left != null)
            inordertrav(root.left);
        System.out.println(root.data);
        if (root.right != null)
            inordertrav(root.right);
    }
    
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<Integer>();
        for (int i=0; i<10; i++) {
            list.add(i);
        }
        TreeNode root = solution(list);
        inordertrav(root);
    }
}
