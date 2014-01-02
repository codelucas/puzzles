package JavaInterview;

// import java.util.Arrays;
// import java.util.List;

public class EmptyStringLoc {
    /*
     * Given a sorted array of strings which is
     * interspersed with empty strings, write a
     * method to find the location of a given string.
     * 
     * We know that the non empty strings are sorted. We also
     * know that the trivial solution of iterating the list
     * until we find k is O(n). This hints that our solution
     * should try to be O(lg(n)) or less.
     * 
     */
    
    static int _binarysearch(String[] list, String k, int start, int end) {
        /* First impression: Binary search as normal but if an
         * empty string is encountered, search both sides of list.
         * This will be between O(n) and O(lg(n)). Alternatively, this
         * can be solved with moving the mid point to the closest non
         * empty string after an empty string is encountered. It's 
         * O-notation in practice is slightly better but still O(n) worst case. 
         */
        if (end < start) {
            return -1;
        }

        int mid = (start+end)/2;
        if (list[mid].equals(k)) {
            return mid;
        }
        // middle element is the empty string, search both sides.
        else if (list[mid].equals("")) {
            int result = _binarysearch(list, k, start, mid-1);
            if (result == -1) {
                return _binarysearch(list, k, mid+1, end);
            } else {
                return result;
            }
        }
        // middle > k
        else if (list[mid].compareTo(k) > 0) {
            return _binarysearch(list, k, start, mid-1);
        }
        else if (list[mid].compareTo(k) < 0) {
            return _binarysearch(list, k, mid+1, end);
        }
        return -1;
    }
    
    public static void main(String[] args) {
        String[] list = {"car", "", "", "lucas", "", "ou-yang", "sex"};
        String findthis = "ou-yang";
        System.out.println(_binarysearch(list, findthis, 0, list.length));
        // List<String> arrl = Arrays.asList(list);
        // java.util.Collections.sort(arrl);
        // String[] newlist = arrl.toArray(new String[arrl.size()]);
        // List<String> ll = Arrays.asList(list);
        // System.out.println(arrl.toString());
    }
}
