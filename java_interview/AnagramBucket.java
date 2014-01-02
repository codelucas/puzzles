package JavaInterview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnagramBucket {
    /*
     * Input - List<String> ["star", "rats", "ice", "cie", "arts"] print all
     * anagrams in buckets: ["star", "rats", "arts"] ["ice", "cie"]
     * 
     * Our implementation will take advantage of hashing and string sorting.
     * This is O(n*mlog(m)) where m is the length of average string, n=#
     * strings. We first sort each string using quicksort on the characters.
     * Then, hash the sorted strings into numbers which takes O(n*m). Then we
     * make one more pass and dump them into buckets.
     */
    static String sortstr(String str) {
        /* encapsulating original sortstr method */
        char[] chararr = str.toCharArray();
        sortstr(chararr, 0, str.length() - 1);
        return new String(chararr);
    }

    static void sortstr(char[] arrchar, int start, int end) {
        /* inplace quicksort, car=>acr */
        if (start > end)
            return;

        int piv = arrchar[end];
        int lo = start;
        int hi = end;
        while (lo <= hi) {
            while (arrchar[lo] < piv)
                lo += 1;
            while (arrchar[hi] > piv)
                hi -= 1;
            if (lo <= hi) {
                char tmp = arrchar[lo];
                arrchar[lo] = arrchar[hi];
                arrchar[hi] = tmp;
                lo += 1;
                hi -= 1;
            }
        }
        sortstr(arrchar, start, hi);
        sortstr(arrchar, lo, end);
    }

    static void anagrambuckets(List<String> input) {
        HashMap<String, List<String>> map = new HashMap<String, List<String>>();

        for (int i = 0; i < input.size(); i++) {
            String srtedstr = sortstr(input.get(i));
            if (map.containsKey(srtedstr)) {
                map.get(srtedstr).add(input.get(i));
            } else {
                List<String> z = new ArrayList<String>();
                z.add(input.get(i));
                map.put(srtedstr, z);
            }
        }

        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            System.out.println("Key " + entry.getKey() + " bucket: "
                    + entry.getValue().toString());
        }
    }

    public static void main(String[] args) {
        List<String> input = new ArrayList<String>();
        input.add("star");
        input.add("rats");
        input.add("ics");
        input.add("cie");
        input.add("arts");
        anagrambuckets(input);
    }
}
