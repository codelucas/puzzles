package JavaInterview;

public class Karatsuba {
    /* Multiply two numbers in O(n^1.58) 
     * Implemented by Brian Ung 
     */
    
    static int mult(int value1, int value2, int n){        
        if (n!=1 && n%2==1) n+=1;
        if (n < 2) return value1&value2;   
    
        int shiftBy = n/2;
        
        int val1hi = value1 >> shiftBy;
        int val1lo = value1 & ((1 << shiftBy) - 1);
        System.out.println("The value of the low bits: " + val1lo);
        int val2hi = value2 >> shiftBy;
        int val2lo = value2 & ((1 << shiftBy) - 1);
        
        int x = mult(val1hi, val2hi, shiftBy);
        int y = mult(val1lo, val2lo, shiftBy);
        int shiftz = numBits(val1hi+val1lo, val2hi+val2lo);
        int z = mult(val1hi+val1lo, val2hi+val2lo, shiftz);
        
        return (x << n) + ((z-x-y) << shiftBy) + y;
    }
    
    private static int numBits(int val1, int val2){
        int max = val2;
        if(val1 > val2)
            max = val1;
        return Integer.SIZE - Integer.numberOfLeadingZeros(max);
    }
    
    public static void main(String args[]){
        System.out.println(mult(179, 186, 8));
    }
}