package JavaAlgo;

import java.util.Random;

// Sorted in descending order, with integers.
public class InsertionSort {
    public static void generateTests() {
        Random r = new Random();
        int listSize = r.nextInt(10) + 5;
        int[] testArr = new int[listSize];

        for (int i = 0; i < listSize; i++) {
            int randomFact = r.nextInt(41) - 20; // Random integers from -20 to
                                                 // 20
            testArr[i] = randomFact;
        }

        String stringList = "";
        for (int i = 0; i < testArr.length; i++)
            stringList += testArr[i] + " ";
        System.out.println("The unsorted list is: " + stringList);

        long startTime = System.nanoTime();

        insertionSort(testArr);

        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        double seconds = (double) (duration / 1000000000.0);

        stringList = "";
        for (int i = 0; i < testArr.length; i++)
            stringList += testArr[i] + " ";
        String output = "The sorted list is " + stringList
                + "\r\nThis was computed in " + seconds + " seconds. \r\n";

        System.out.println(output);
    }

    public static int[] insertionSort(int[] array) {
        if (array.length < 2)
            return array;
        for (int i = 1; i < array.length; i++) {
            int hole = i;
            int cur = array[i];
            while (hole > 0 && cur > array[hole - 1]) {
                array[hole] = array[hole - 1];
                hole--;
            }
            array[hole] = cur;
        }
        return array;
    }
}
