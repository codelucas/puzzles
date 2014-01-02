package JavaInterview;

public class BinarySearchRotated {
    /*
     * Given a sorted array of integers that has
     * been rotated an unknown number of times, 
     * write code to find an element in an array. 
     * You may assume that the array was originally
     * sorted in increasing order.
     * 
     */
    
    static int binarysearch(int[] list, int k, int start, int end) {
        if (end < start) {
            return -1;
        }
        int mid = (end+start)/2;
        
        if (list[mid] == k) {
            return mid;
        }
        else if (k < list[mid]) {
            return binarysearch(list, k, start, mid-1);
        }
        else {
            return binarysearch(list, k, mid+1, end);
        }
    }
    
    static int rotatedsearch(int[] list, int k, int start, int end) {
        /* We can't really do binarysearch because it's not
         * totally in order. However, we do know that at any
         * given time, at least a half of the list must be
         * in order, while there is a chunk that is not. 
         * 
         *  Our strategy will be to allocate this sorted chunk
         *  into a standard binarysearch. The other misfit chunk
         *  will be fed back into this method for a repeated process. */
        
        if (end < start) {
            return -1;
        }
        int mid = (start+end)/2;
        
        if (list[mid] == k) {
            return mid;
        }
        /* This partition of the list is sorted */
        else if (list[mid] > list[start]) {
            /* k is inside the sorted section */
            if (k <= list[mid] && k >= list[start]) {
                return binarysearch(list, k, start, mid-1);
            }
            /* k is not in sorted section, so it 
             * must be in unsorted section */
            else {
                return rotatedsearch(list, k, mid+1, end);
            }
        }
        else if (list[mid] < list[start]) {
            if (k >= list[mid] && k <= list[end]) {
                return binarysearch(list, k, mid+1, end);
            }
            /* k is not in sorted section, so it 
             * must be in unsorted section */
            else {
                return rotatedsearch(list, k, start, mid-1);
            }
        }
        /* left side is all duplicates, search right if possible 
         * with this property, if a list had a lot of duplicates,
         * the O(nlogn) implementation degrades down to O(n). */
        else if (list[mid] == list[start]) {
            if (list[mid] != list[end]) {
                return rotatedsearch(list, k, mid+1, end);
            }
            else {
                /* search both halves as the left is dupes 
                 * and right we don't know */
                int result = rotatedsearch(list, k, start, mid-1);
                if (result == -1) {
                    return rotatedsearch(list, k, mid+1, start);
                }
                else {
                    return result;
                }
            }
        }
        return -1;
    }
    
    public static void main(String[] args) {
        int[] list = { 4, 5, 6, 7, -2, -1, 0, 2 };
        int findthis = 6;
        System.out.println(rotatedsearch(list, findthis, 0, list.length-1));
    }
    
}
