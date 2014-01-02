package JavaAlgo;

import java.util.Random;

// Sorts an arraylist of integers in descending order.
public class QuickSort {
    static Random r = new Random();
    static int listSize = r.nextInt(10) + 5;
    static int[] array = new int[listSize];

    public static void generateTests() {
        for (int i = 0; i < listSize; i++) {
            int randomFact = r.nextInt(41) - 20; // Random integers from -20 to
                                                 // 20
            array[i] = randomFact;
        }

        String stringList = "";
        for (int i = 0; i < array.length; i++)
            stringList += array[i] + " ";
        System.out.println("The unsorted list is: " + stringList);

        long startTime = System.nanoTime();

        inplaceQuickSort(0, array.length - 1);

        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        double seconds = (double) (duration / 1000000000.0);

        stringList = "";
        for (int i = 0; i < array.length; i++)
            stringList += array[i] + " ";
        String output = "The sorted list is " + stringList
                + "\r\nThis was computed in " + seconds + " seconds. \r\n";

        System.out.println(output);
    }

    public static void inplaceQuickSort(int a, int b) {
        if (a > b)
            return;

        int lo = a;
        int hi = b;
        int piv = array[(lo + hi) / 2];

        while (lo <= hi) {
            while (array[lo] > piv)
                lo++;
            while (array[hi] < piv)
                hi--;

            if (lo <= hi) {
                int tmp = array[lo]; // Swap lo and hi, this works bc
                array[lo] = array[hi]; // we already filtered all the
                array[hi] = tmp; // in ordered elements reletive to piv
                lo++;
                hi--;
            }
        }
        inplaceQuickSort(a, hi);
        inplaceQuickSort(lo, b);
    }
}
