package JavaInterview;

public class NumberWords {
    /* Count the number of words in a big string. */

    static boolean isword(char c) {
        char[] newlines = {' ', '\r', '\t', '\n'};
        for (char ic : newlines) {
            if (ic == c) {
                return true;
            }
        }
        return false;
    }
    
    static int customcount(String s) {
        int cnt = 0;
        boolean inword = false;
        for (int i=0; i<s.length(); i++) {
            char cur = s.charAt(i);
            if (!isword(cur)) {
                inword = true;
            } else {
                if (inword) {
                    cnt++;
                    inword = false;
                }
            }
        }
        // don't forget that if we are in a word still and there
        // is no last whitepsace to let us incre, we need to incre.
        if (inword) {
            cnt++;
        }
        return cnt;
    }
    
    public static void main(String[] args) {
        String article = "Sixty-four percent of Americans view the Republican Party unfavorably, and 56 " +
        		"percent view the tea party unfavorably. Both are all-time highs in CNN's polling, although " +
        		"in each case, it's only a 2-point increase from a survey taken Sept. 27 through 29."+

                "Prominent Republicans have also taken a hit. House Speaker John Boehner's (Ohio) unfavorable " +
                "rating is at 55 percent, up from 48 percent just before the shutdown, while Senate Minority Leader" + 
                " Mitch McConnell's (Ky.) unfavorables rose to 42 percent from 39 percent before the shutdown. Members of their "+
                "own party are also increasingly rating them negatively, according to polling. Sen. Ted Cruz (Texas), one of " +
                "the most vocal supporters of the shutdown, saw his unfavorable rating climb 6 points to 42 percent.";
     
        System.out.println("Our length " + customcount(article));
        System.out.println("Official length " + article.split("[ \r\n\t]").length);
    }
}
