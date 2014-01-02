package JavaAlgo;


public class KthElement {
    /**
     * Sometimes, an interviewer will ask you to describe an algorithm to
     * identify the kth smallest element in an array of n elements. To do this,
     * you select a random pivot and partition the array as you would in the
     * quicksort algorithm. Then, based on the index of the pivot element, you
     * know which half of the array the desired element lies in. For example,
     * say k = 15 and n = 30, and after you select your pivot and partition the
     * array, the first half has 10 elements (the half before the pivot). You
     * know that the desired element is the 4th smallest element in the larger
     * half. To identify the element, you partition the second half of the array
     * and continue recursively. The reason that this is not O(n log n) is that
     * the recursive partition call is only on one half of the array, so the
     * expected running time is n + (n/2) + (n/4) + (n/8) + ... = O(n). Note
     * that finding the median of an array is a special case of this where k = n
     * / 2. This is a very important point, as an interviewer will often ask you
     * to find a way to get the median of an array of numbers.
     * 
     */
    public static int[] list = { 1, 10, -20, 15, 3, 17, 5, 13 };

    // sorted {-20, 1, 3, 5, 10, 13, 15, 17}
    public static void main(String[] args) {
        int kth = quickSort(list, 1, 0, list.length - 1);
        System.out.println(kth);
        /*
        String s = "";
        for (int i = 0; i < list.length; i++) {
            s += " " + list[i];
        }
        System.out.println(s);
        */
    }// 949 854 0900
     // This algo finds the kth smallest number of an unsorted list
     // Using the qsort partion.

    public static int quickSort(int[] list, int k, int a, int b) {
        if (b < a)
            return -999;
        String s = "";
        for (int i = 0; i < list.length; i++) {
            s += " " + list[i];
            if (i == b)
                s += "*";
        }
        System.out.println(s);
        int piv = list[b];
        int lo = 0;
        int hi = b;
        int counter = 0;

        while (counter <= hi) {
            if (list[counter] < piv) {
                int tmp = list[counter];
                list[counter] = list[lo];
                list[lo] = tmp;
                counter++;
                lo++;
            } else if (list[counter] > piv) {
                int tmp = list[counter];
                list[counter] = list[hi];
                list[hi] = tmp;
                hi--;
            } else {
                counter++;
            }
        }
        System.out.println("lo: " + (lo - 1) + " hi: " + (hi + 1) + " k: " + k
                + "\n");
        if (k == lo)
            return list[lo];

        // Remember, lo and hi indicies are 1 off from above.
        if (k <= lo - 1)
            return quickSort(list, k, 0, lo - 1); // First half
        else
            return quickSort(list, k, hi + 1, b); // Second half

    }
}
