package JavaInterview;


public class PrefixSuffix {
    /*
     * Given two String, s1 and s2. to find the longest 
     * substring which is prefix of s1, as well as 
     * suffix of s2.
     */
    
    static String presuf(String s1, String s2) {
        for (int i=0; i<s2.length(); i++) {
            boolean pass = true;
            int width = s2.length()-i-1;
            for (int j=0; (j<s1.length()) && (width>0); j++) {
                if (s1.charAt(j) != s2.charAt(i)) {
                    pass = false;
                    continue;
                }
                width--;
            }
            if (pass && s1.charAt(0) == s2.charAt(i)) 
                return s2.substring(i);   
        }
        return "";
    }
    
    public static void main(String[] args) {
        System.out.println(presuf("lucas", "alanlu"));
    }
}
