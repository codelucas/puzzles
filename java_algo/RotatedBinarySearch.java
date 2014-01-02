package JavaAlgo;

import java.util.Random;

// Similar to the binary tree example, but this one accounts for
// input lists that are rotated, like eg 4 5 6 7 8 1 2 3 
// The whole trick to this is that one side is always sorted no matter what
// by the nature of lists, so we can partition the list out, regular
// binary search the sorted one, and keep partitioning the unsorted one
// until we find the element
public class RotatedBinarySearch {
    public static void generateTests() {
        Random r = new Random();
        // int listSize = r.nextInt(10) + 5;
        int[] test1 = new int[7];

        test1[0] = 3;
        test1[1] = 4;
        test1[2] = 5;
        test1[3] = -5;
        test1[4] = -4;
        test1[5] = -3;
        test1[6] = -2;

        boolean doesContain = false;
        int element;

        if (Math.random() < 0.5)
            doesContain = true;

        if (!doesContain)
            element = -123432532;
        else
            element = test1[r.nextInt(test1.length - 1)];

        String stringList = "";
        for (int i = 0; i < test1.length; i++)
            stringList += test1[i] + " ";
        System.out.println("The list is: " + stringList + "   Our element is: "
                + element);

        long startTime = System.nanoTime();

        boolean status = rotatedBinarySearch(test1, element, 0,
                test1.length - 1);

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
    public static boolean rotatedBinarySearch(int[] array, int element,
            int low, int high) {
        int mid = (low + high) / 2;

        if (array[mid] == element)
            return true;

        else {
            // Bottom sorted
            if (array[low] <= array[mid]) {
                // It's in the top half
                if (element >= array[low] && element < array[mid])
                    return binarySearch(array, element, low, mid);
                else
                    return rotatedBinarySearch(array, element, mid + 1, high);
            }
            // Top sorted
            else if (array[mid + 1] <= array[high]) {
                // It's in the top half
                if (element >= array[mid + 1] && element <= array[high])
                    return binarySearch(array, element, mid + 1, high);
                else
                    return rotatedBinarySearch(array, element, low, mid);
            }
        }
        return false;
    }/*
      * public static boolean rotatedBinarySearch(int[] array, int element, int
      * low, int high) { int mid = (int) Math.floor((low + (high-low) / 2));
      * 
      * if(low > high) return false;
      * 
      * if(array[low] <= array[high]) return binarySearch(array, element, low,
      * high);
      * 
      * else { //the other part is unsorted. return (rotatedBinarySearch(array,
      * element, low, mid) || rotatedBinarySearch(array, element, mid + 1,
      * high)); } }
      */

    public static boolean binarySearch(int[] array, int element, int low,
            int high) {
        if (low > high) // This prevents a stack-overflow error
            return false;

        else {
            int mid = (int) Math.floor((low + (high - low) / 2));

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
