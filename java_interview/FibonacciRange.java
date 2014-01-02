package JavaInterview;

public class FibonacciRange {
    /*
     * Fibonacci Numbers: A number is said to be Fibonacci number if it follows
     * the fibonacci property. (Ex: 112, 1123, etc). But additionally, it need
     * not necessarily start with 1, as with the normal fibonacci series. 
     * 
     * So, in this new definition, 112(1,1,2) is a fibonacci number and so is
     * 121224(12,12,24), and so is 252550(25,25,50). 
     * 
     * So, given any two numbers as input, print out all the 
     * Fibonacci Numbers within that range..
     */
    
    static int numdigits(int num) {
        String s = Integer.toString(num);
        return s.length();
    }
    
    static int appendnum(int base, int num) {
        return (base * ((int) Math.pow(10, numdigits(num)))) + num;
    }
    
    static void buildfib(int start, int finish) {
        /* Construct the fib numbers in range. We are loosely 
         * allowing fib numbs so 1235 is allowed because 2+3=5 */
        for (int i=1; true; i++) {
            int n = appendnum(appendnum(i, i), i+i);  // 112
            if (n > finish) {
                return;
            }
            for (int i0=i; true; i0++) {
                int third = i0;    // 1 (digit right behind forfront)
                int second = i0+i; // 2 (forfront digit)
                n = appendnum(appendnum(i, i0), i+i0); // 112, 123, ...
                if (n > finish) {
                    break;
                }
                // if is needed in loop incase starting bound is high
                while (n <= finish) {
                    if (n >= start) { 
                        // System.out.println("second: " + 
                        //    second + " third: " + third);
                        System.out.println(n); 
                    }
                    int tmp = second+third; // 3
                    third = second; // 2
                    second = tmp; // 3
                    
                    n = appendnum(n, tmp); // 1123;
                }
            }
        }
    }
    
    /* The below code is wrong as it misses a few edge cases.
    static boolean isfib(int num) {
        * This solution is naive. We iterate the radix sizes
         * for the fibonacci numbers. A radix=1 means we
         * are checking if 1+1=2 in 112, a radix=2 means
         * we are checking if 12+12=13 in 121213. *
        String numstr = Integer.toString(num);
        
        if (numstr.length() < 3)
            return false;
        
        for (int radix=1; radix<=(numstr.length()/3); radix++) {
            
            String third = numstr.substring(0, 0+radix);
            String second = numstr.substring(0+(radix), radix*2);
            String current = "";
            boolean passed = true;
            
            for (int i=(radix*2); (i+radix-1) < numstr.length(); i++) { 
                String chunk = numstr.substring(i, i+radix);
                current = chunk;
                System.out.println("cur, second, third, radix "+
                        current+" "+second+" "+third+" "+radix);
                if ((Integer.parseInt(third) + Integer.parseInt(second)) 
                        != Integer.parseInt(current)) {
                    passed = false;
                    break;
                }
                third = second;
                second = current;
            }
            if (passed) {
                return true;
            }
        }
        return false;
    } */
    
    public static void main(String[] args) {
        // System.out.println(isfib(112));
        buildfib(1, 1000000);
    }
}
