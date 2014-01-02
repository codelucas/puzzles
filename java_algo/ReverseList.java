package JavaAlgo;

// Reverses a string, literally thats all this does: More functionality later.
public class ReverseList {
    public static void generateTests() {
        String t = "Lucas attempts to write code to reverse a string!";
        System.out.println("The unreversed string is: " + t);

        long startTime = System.nanoTime();

        // String m = reverseString(t); Uncomment to use the iterative method,
        // instead of recursive
        String m = reverseStringRecursive(t);

        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        double seconds = (double) (duration / 1000000000.0);

        String output = "The sorted string is " + m
                + "\r\nThis was computed in " + seconds + " seconds. \r\n";
        System.out.println(output);
    }

    // Non-recursive strategy
    public static String reverseString(String raw) {
        char[] indexedStrings = new char[raw.length()];
        for (int i = raw.length() - 1, j = 0; i >= 0; i--, j++)
            indexedStrings[j] = raw.charAt(i);

        return new String(indexedStrings);
    }

    // Recursive strategy
    public static String reverseStringRecursive(String raw) {
        if (raw.length() < 2)
            return raw;

        String first = raw.substring(0, 1);
        String last = raw.substring(raw.length() - 1, raw.length());
        String middle = raw.substring(1, raw.length() - 1);
        return last + reverseStringRecursive(middle) + first;
    }
}
