package JavaAlgo;

import java.util.Random;

public class BinarySearch {
    public static void generateTests() {
        Random r = new Random();
        int listSize = r.nextInt(10) + 5;
        int[] test1 = new int[listSize];

        int randomFact = r.nextInt(10) + 10;

        for (int i = 0; i < listSize; i++)
            test1[i] = i * randomFact;

        boolean doesContain = false;
        int element;

        if (Math.random() < 0.5)
            doesContain = true;

        if (!doesContain)
            element = -1;
        else
            element = test1[r.nextInt(test1.length - 1)];

        String stringList = "";
        for (int i = 0; i < test1.length; i++)
            stringList += test1[i] + " ";
        System.out.println("The list is: " + stringList + "   Our element is: "
                + element);

        long startTime = System.nanoTime();

        boolean status = binarySearch(test1, element, 0, test1.length - 1);

        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        double seconds = (double) (duration / 1000000000.0);

        String output = "The expected result of this "
                + BinarySearch.class.getSimpleName() + " is: " + doesContain
                + "" + ", the result is: " + status
                + ". \r\nThis was computed in " + seconds + " seconds. \r\n";

        System.out.println(output);
    }

    // Returns true if array contains input element, false otherwise
    public static boolean binarySearch(int[] array, int element, int low,
            int high) {
        if (low > high) // This prevents a stack-overflow error
            return false;

        else {
            int mid = (int) Math.floor((low + high) / 2);

            if (element == array[mid])
                return true;

            // We know our element can only be on the left side
            else if (element < array[mid])
                return binarySearch(array, element, low, mid - 1);

            // Vice versa, the right side
            else
                return binarySearch(array, element, mid + 1, high);
        }
    }
}
