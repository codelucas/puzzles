package JavaInterview;

public class PalindromeGroup {
    /*
     * Write a method to sort an array of strings so that all the palindromes
     * are next to eachother.
     * 
     * Before starting it's probably a good idea to write a utility method for
     * detecting of a String is an palindrome.
     * 
     * After we mark each string as palindrome or not, perform either a bucket
     * sort or a partition on the list to group by palindrome/non-palindrome.
     * 
     * We end up going with the partition.
     */

    static boolean is_palindrome(String s) {
        /* using iteration */
        for (int i = 0; i < Math.floor(s.length() / 2); i++) {
            int lastindex = s.length() - 1 - i;
            String endchar = s.substring(lastindex, lastindex + 1);
            String startchar = s.substring(i, i + 1);
            if (!startchar.equals(endchar)) {
                return false;
            }
        }
        return true;
    }

    static String[] group_palindromes(String[] list) {
        /*
         * We will perform the grouping in a similar fashion to quicksort's
         * partition. This is inplace and O(nlogn)
         * 
         * Palindrome will be grouped near the start.
         */

        int locnt = 0;
        int hicnt = list.length - 1;

        while (locnt <= hicnt) {

            while (is_palindrome(list[locnt]))
                locnt++;

            while (!is_palindrome(list[hicnt]))
                hicnt--;

            if (locnt <= hicnt) {
                String tmp = list[locnt];
                list[locnt] = list[hicnt];
                list[hicnt] = tmp;
                locnt++;
                hicnt--;
            }
        }
        return list;
    }

    public static void main(String[] args) {

        String[] listing = { "lucas", "ana", "john", "graham",
                "big big gib gib", "globbolg" };

        String[] grouped = group_palindromes(listing);

        for (String g : grouped)
            System.out.println(g);
    }

}
