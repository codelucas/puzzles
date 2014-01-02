package JavaInterview;

public class MinMaxDivConquer {
    /*
     * Write a function that finds the minimum and 
     * maximum values within an unsorted array using 
     * divide-and-conquer.
     * 
     * Recurse until base case of two elemensts, then 
     * compare the two elements for the max (and min).
     * Return both. Repeat process.
     * This will end up being O(n).
     */
    static int[] maxmin(int[] arr, int a, int b) {
        /* This function returns a tuple of size 2. 
         * First elem is min, second max. */
        if ((b-a)==2) {
            return new int[] 
                  {Math.min(arr[a], arr[a+1]), 
                   Math.max(arr[a], arr[a+1])};
        } else if ((b-a) < 2) {
            return new int[] {arr[a], arr[a]};
        }
        int mid = (a+b)/2;
        int[] tupx = maxmin(arr, a, mid);
        int[] tupy = maxmin(arr, mid+1, b);
        int max = Math.max(tupx[1], tupy[1]);
        int min = Math.min(tupx[0], tupx[0]);
        return new int[] {min, max};
    }
    
    public static void main(String[] args) {
        int[] arr= {20, 36, 107, 2, 8, 1000, 55};
        int[] tup = maxmin(arr, 0, arr.length-1);
        System.out.println("min: "+tup[0]+" max: "+tup[1]);
    }
}

