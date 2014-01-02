package JavaInterview;

public class StringNumAdd {
    /*
     * Given two strings representing integer numbers ("123" , "30") return a
     * string representing the sum of the two numbers ("153").
     * Assume that input numbers are all positive.
     *
     * This question is non trivial because Strings can be of arbitrary size.
     */
    
    static char tochar(byte b) {
        switch (b) {
            case 1: return '1';
            case 2: return '2';
            case 3: return '3';
            case 4: return '4';
            case 5: return '5';
            case 6: return '6';
            case 7: return '7';
            case 8: return '8';
            case 9: return '9';
            case 0: return '0';
            default: return '0';
        }
    }
    
    static String addstr(String lrg, String sml) {
        // Instead of storing data in an int or long
        // which will easily overflow, we can construct
        // a custom number with an array of bytes for space savings.
        
        byte[] n1 = new byte[lrg.length()];  
        byte[] n2 = new byte[sml.length()]; 
         
        for (int i=0; i<lrg.length(); i++) {
            char c = lrg.charAt(i);
            byte in = (byte) Character.getNumericValue(c);
            n1[i] = in;
        } 
        for (int i=0; i<sml.length(); i++) {
            char c = sml.charAt(i);
            byte in = (byte) Character.getNumericValue(c);
            n2[i] = in;
        } 
        int mx = Math.max(n1.length, n2.length);
        byte[] n3 = new byte[mx+1];
        int r1=n1.length-1, r2=n2.length-1, r3=n3.length-1;
        byte carry=0;
        
        while (r3 >= 0) {
            byte sum = carry;
            
            if (r1 >= 0) {
                sum += n1[r1--];
            } if (r2 >= 0) {
                sum += n2[r2--];
            }
            carry = (byte) (sum / 10);
            n3[r3--] = (byte) (sum % 10);
        }
        
        char[] cc = new char[n3.length];
        for (int b=0; b<n3.length; b++) {
            cc[b] = tochar(n3[b]);
        }
        
        String ret = new String(cc);
        if (ret.charAt(0) == '0')
            ret = ret.substring(1);
        return ret;
    }
    
    static String helper(String s1, String s2) {
        if (s1.length() >= s2.length())
            return addstr(s1, s2);
        return addstr(s2, s1);
    }
    
    public static void main(String[] args) {
        String s1 = "123";
        String s2 = "30";
        System.out.println(addstr(s1, s2));
    }
}
