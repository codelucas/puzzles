package JavaInterview;

public class RelatedStrings {
    /*
     * Given an array of strings, find the string which is made up of maximum
     * number of other strings contained in the same array.
     * 
     * e.g. [ “rat”, ”cat”, “abc”, “xyz”, “abcxyz”, “ratcatabc”, “xyzcatratabc”
     * ] Answer: “xyzcatratabc” “abcxyz” contains 2 other strings, “ratcatabc”
     * contains 3 other strings, “xyzcatratabc” contains 4 other strings"
     * 
     * Implemented via Rabin-Karp, borrowed from princeton.edu ^ NEVERMIND, that
     * algorithm has too much overhead.
     */

    static boolean contains(String str, String sub) {
        if (str == null || sub == null)
            return false;

        if (sub.length() > str.length())
            return false;

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == sub.charAt(0)) {
                int j;
                for (j = 1; j < sub.length() && (i + j) < str.length(); j++) {
                    if (str.charAt(i + j) != sub.charAt(j))
                        return false;
                }
                if (j == sub.length())
                    return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {

        String[] list = { "rat", "cat", "abc", "xyz", "abcxyz", "ratcatabc",
                "xyzcatratabc" };

        // Bookeeping each string's number of matches
        // We are assuming the list is unique.
        int[] scores = new int[list.length];
        int maxscore = Integer.MIN_VALUE;
        String maxstr = null;

        for (int i = 0; i < list.length; i++) {
            for (int j = 0; j < list.length; j++) {

                if (i == j)
                    continue; // same index

                String str = list[i];
                String sub = list[j];

                // substring.len must be <= string.len
                if (sub.length() > str.length())
                    continue;

                if (contains(str, sub)) {
                    System.out.println(str + " contains " + sub);
                    scores[i] += 1;
                    if (scores[i] > maxscore) {
                        maxstr = str;
                        maxscore = scores[i];
                    }
                }
            }
        }

        System.out.println(maxstr);
    }

}

// Nevermind, not using it. Too much overhead.
/*
 * class RabinKarp { private String pat; // the pattern // needed only for Las
 * Vegas private long patHash; // pattern hash value private int M; // pattern
 * length private long Q; // a large prime, small enough to avoid long overflow
 * private int R; // radix private long RM; // R^(M-1) % Q
 * 
 * public RabinKarp(int R, char[] pattern) { throw new
 * UnsupportedOperationException("Operation not supported yet"); }
 * 
 * public RabinKarp(String pat) { this.pat = pat; // save pattern (needed only
 * for Las Vegas) R = 256; M = pat.length(); Q = longRandomPrime();
 * 
 * // precompute R^(M-1) % Q for use in removing leading digit RM = 1; for (int
 * i = 1; i <= M - 1; i++) RM = (R * RM) % Q; patHash = hash(pat, M); }
 * 
 * // Compute hash for key[0..M-1]. private long hash(String key, int M) { long
 * h = 0; for (int j = 0; j < M; j++) h = (R * h + key.charAt(j)) % Q; return h;
 * }
 * 
 * // Las Vegas version: does pat[] match txt[i..i-M+1] ? private boolean
 * check(String txt, int i) { for (int j = 0; j < M; j++) if (pat.charAt(j) !=
 * txt.charAt(i + j)) return false; return true; }
 * 
 * // check for exact match public int search(String txt) { int N =
 * txt.length(); if (N < M) return N; long txtHash = hash(txt, M);
 * 
 * // check for match at offset 0 if ((patHash == txtHash) && check(txt, 0))
 * return 0;
 * 
 * // check for hash match; if hash match, check for exact match for (int i = M;
 * i < N; i++) { // Remove leading digit, add trailing digit, check for match.
 * txtHash = (txtHash + Q - RM * txt.charAt(i - M) % Q) % Q; txtHash = (txtHash
 * * R + txt.charAt(i)) % Q;
 * 
 * // match int offset = i - M + 1; if ((patHash == txtHash) && check(txt,
 * offset)) return offset; }
 * 
 * // no match return N; }
 * 
 * // a random 31-bit prime private static long longRandomPrime() { BigInteger
 * prime = BigInteger.probablePrime(31, new Random()); return prime.longValue();
 * }
 * 
 * public static void main(String[] args) { String pat = args[0]; String txt =
 * args[1]; char[] pattern = pat.toCharArray(); char[] text = txt.toCharArray();
 * 
 * RabinKarp searcher = new RabinKarp(pat); int offset = searcher.search(txt);
 * 
 * // print results System.out.println("text:    " + txt);
 * 
 * // from brute force search method 1 System.out.print("pattern: "); for (int i
 * = 0; i < offset; i++) System.out.print(" "); System.out.println(pat); }
 * 
 * }
 */