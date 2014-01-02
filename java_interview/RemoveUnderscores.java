package JavaInterview;

public class RemoveUnderscores {
    /*
     * Write a function that transforms a string such that sequences of
     * consecutive underscores become a single underscore. E.g.\
     * (_hello___world__ => _hello_world_) 
     * */
    
    static String r_underscores(String s) {
        /* O(n) memory and time. */
        char[] c = new char[s.length()];
        int counter = 0;
        while (counter < s.length()) {
            if (s.charAt(counter) == '_') {
                c[counter] = '_';
                counter++;
                while (counter<s.length() && s.charAt(counter)=='_') {
                    counter++;
                }
            } else {
                c[counter] = s.charAt(counter);
                counter++;
            }
        }
        return new String(c);
    }
    
    public static void main(String[] args) {
        String s = "_hello___world__";
        System.out.println(r_underscores(s));
    }
}
