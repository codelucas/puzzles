package JavaInterview;

public class DVR {
    /*
     * 
     * I need a function that takes the name of the movie to look up and the
     * width of the letter grid, and computes the key presses that will enter
     * that string on the DVR grid. The output should be a string, with "u",
     * "d", "l", "r", and "!" corresponding to up, down, left, right, and
     * select.
     * 
     * For example, with a grid of width 5, a b c d e f g h i j k l m n o p q r
     * s t u v w x y z the movie "up" would be "dddd!u!".
     */

    static char[] alphabet = get_alphabet();
    static String UP = "u";
    static String DOWN = "d";
    static String LEFT = "l";
    static String RIGHT = "r";
    static String SELECT = "!";

    static char[] get_alphabet() {
        /* Generates the english alphabet in a char array. */
        char cur = 'a';
        char[] alphabet = new char[26];
        for (int i = 0; i < 26; i++) {
            alphabet[i] = cur;
            cur++;
        }
        return alphabet;
    }

    static int[] getcoords(char letter, int width) {
        /*
         * Given a character, return it's position in the alphabet matrix
         */
        int pos = -1;
        for (int i = 0; i < alphabet.length; i++) {
            if (letter == alphabet[i]) {
                pos = i;
                break;
            }
        }
        int rownum = (int) Math.floor(pos / width);
        int colnum = pos % width;
        int[] ret = { rownum, colnum };
        // System.out.println(rownum + " " + colnum);
        return ret;
    }

    static String getsequence(String name, int width) {

        /*
         * We must take ceiling because sometimes it's not a perfect square, we
         * need the extra row.
         * 
         * I thought the two below vars would be needed, was wrong! int height =
         * (int) Math.ceil(alphabet.length / width); char[][] matrix = new
         * char[width][height];
         */

        StringBuilder seq = new StringBuilder();
        int[] curcoord = { 0, 0 }; // y, x

        for (int i = 0; i < name.length(); i++) {
            char curnchar = name.charAt(i);
            int[] nextcoord = getcoords(curnchar, width);
            int deltay = nextcoord[0] - curcoord[0];
            int deltax = nextcoord[1] - curcoord[1];

            boolean goup = true;
            boolean goright = true;
            if (deltax < 0)
                goright = false;
            if (deltay > 0) // tricky, this one is flipped
                goup = false;

            deltax = Math.abs(deltax);
            deltay = Math.abs(deltay);

            for (int j = 0; j < deltay; j++) {
                if (goup)
                    seq.append(UP);
                else
                    seq.append(DOWN);
            }
            for (int k = 0; k < deltax; k++) {
                if (goright)
                    seq.append(RIGHT);
                else
                    seq.append(LEFT);
            }
            seq.append(SELECT);
            curcoord[0] = nextcoord[0];
            curcoord[1] = nextcoord[1];
        }
        return seq.toString();
    }

    public static String getPath(int w, char[] str) {
        /*
         * Some other guys code on careercup. God this version is so much better
         * than mine :\
         */
        int i = 0;
        StringBuilder sb = new StringBuilder();
        int curR = 0;
        int curC = 0;
        while (i < str.length) {
            int destR = (str[i] - 'a') / w;
            int destC = (str[i] - 'a') % w;

            while (curC > destC) {
                sb.append('l');
                curC--;
            }

            while (curR > destR) {
                sb.append('u');
                curR--;
            }

            while (curC < destC) {
                sb.append('r');
                curC++;
            }

            while (curR < destR) {
                sb.append('d');
                curR++;
            }

            sb.append('!');
            i++;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        // for (char c : alphabet)
        // System.out.println("char " + c);
        System.out.println(getsequence("sex", 5));
    }

}
