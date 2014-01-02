package JavaInterview;

public class BitInsertion {
    /*
     * You are given two 32-bit numbers, N and M and
     * 2 bit positions, i and j. Write a method to 
     * insert M into N such that M starts at j and ends
     * at bit i. You can assume bits j-i have enough
     * room to fit all of M.
     * 
     * Ex:
     * N=100 0000 0000, M=10011, i=2, j=6
     * Outout=100 0100 1100
     * 
     * Disclaimer: For the soln I had to flat out read out
     * of the answer key because of lack of bit manipulation exp.
     */
    
    static int bitinsert(int N, int M, int i, int j) {
        /* We need to create bit mask to clear j-i of N */       
        int ones = ~0; 
        int leftmask = ones << (j+1); // +1 because i,j are 0 based
        int rightmask = (1 << i) - 1; // push 1 over i spots (1 based) then
                                      // minus 1 to invert all i+1 bits (0 based again)
        int mask = leftmask | rightmask;
        System.out.println("mask: "+Integer.toBinaryString(mask));
        System.out.println("N: "+Integer.toBinaryString(N));
        System.out.println("M: "+Integer.toBinaryString(M));
        /* Mask N with our value with bitwise AND */
        N = N & mask;
        /* Shift M over by i to ready it */
        M = (M << i);
        /* Merge the two */
        N = N | M;
        System.out.println("OUTPUT: "+Integer.toBinaryString(N));
        return N;
    }
    
    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(bitinsert(1<<10, 19, 2, 6)));
        // System.out.println(Integer.toBinaryString((1<<10)));
    }
}
