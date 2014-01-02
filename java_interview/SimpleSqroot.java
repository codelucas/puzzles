package JavaInterview;

public class SimpleSqroot {
    /* Calculate the sq root of a number without
     * using any form of a sq root function. */
    
    static double sqrt(int n) {
        double err = 0.000001;
        /* We can implement binary search
         * we know 0 <= sqrt(n) <= n 
         * The time complexity is*/
        double lo = 0;
        double hi = n;
        while (hi-lo > err) {
            double mid = (lo+hi)/2.0;
            if ((mid*mid)>n) {
                hi = mid;
            } else if ((mid*mid)<n) {
                lo = mid;
            } else {
                return mid;
            }
        }
        return (lo+hi)/2;
    }
    
    public static void main(String[] args) {
        System.out.println(sqrt(2));
        System.out.println(Math.sqrt(2));
    }
}
