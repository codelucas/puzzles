package JavaInterview;

public class MagicIndex {
    /*
     * A maginx index in an Array A[1 ... n-1] is defined to be an index such
     * that A[i] = i. Given an array of distinct integers, write a method to
     * find a magix index if one exists in A.
     * 
     * Follow up: What if the values are not distinct?
     */

    public static int find_magic(int[] arr, int start, int end) {

        if (end < start)
            return -999; /* fail */

        int mid = (start + end) / 2;
        int elem = arr[mid];

        if (elem > mid)
            return find_magic(arr, start, mid - 1);

        else if (elem < mid)
            return find_magic(arr, mid + 1, end);

        else
            return mid; /* magic index */
    }

    public static int find_magic_distinct(int[] arr, int start, int end) {

        if (end < start || start < 0 || end >= arr.length) {
            // System.out.println(String.format("%d end %d start", end, start));
            return -999;
        }

        int mid = (start + end) / 2;
        int elem = arr[mid];

        if (elem > mid) {
            /*
             * It may be on the right side but we definitely must search the
             * left side
             */
            int fst = find_magic_distinct(arr, start, mid - 1);
            if (fst == -999)
                return find_magic_distinct(arr, Math.max(mid + 1, elem), end);
            return fst;
        }

        else if (elem < mid) {
            int fst = find_magic_distinct(arr, mid + 1, end);
            if (fst == -999)
                return find_magic_distinct(arr, start, Math.min(mid - 1, elem));
            return fst;
        }

        else {
            return mid;
        }

    }

    public static void main(String[] args) {
        /* Index: 0 1 2 3 4 5 6 7 8 */
        int[] arr = { -2, -1, 0, 1, 6, 6, 7, 7, 9 };

        /*
         * The Magic index is on index 3. We can see that the interesting
         * property is that this list is sorted.. If we perform a binary search
         * and the element > index, then the magic index, if existing must be
         * before. If element < index, the magic index must be after.
         * 
         * If the values are distinct, straight up binary search won't work
         * anymore because values can just stall on one value and catch up to an
         * index automatically. So this means we still absolutly must bsearch
         * one half, but the other half we must either search completly or
         * search a chunk.
         * 
         * It turns out we can skip a chunk of the other half of the list due to
         * the property that the middle index value is usually larger or smaller
         * than surrounding values so we can skip a lot
         */
        System.out.println(find_magic_distinct(arr, 0, arr.length - 1));
    }
}
