package JavaInterview;

public class DangerousIslands {
    /*
     * Here is a grid of n*n where each cell represent an Island or and some of
     * these are very dangerous so u have to avoid these during path selections.
     * You can move up, down, left, right. You are given your starting position,
     * positions of dangerous Islands and position some specific Islands. Your
     * task is to deliver a message to all the specific Islands in minimum
     * number of moves to all specific Islands (NOTE-there are also chances that
     * no moves are possible to cover all specific Island ,in such case you have
     * to tell "NOT POSSIBLE TO DELIVER ALL", otherwise output minimum moves).
     * 
     * 
     * 
     * This is similar to the Travelling Salesman problem, which is NP-hard.
     * 
     * If the number S of specific islands is quite small, say <= 20, we can
     * perform S+1 Breadth First Searches to find all pairs of distances between
     * the S islands and the initial position. Then, we can check all
     * permutations of the S islands and calculate the cost of the possible
     * paths "Initial pos -> Island_1 -> .. Island_S" using the distances
     * calculated above. We can use dynamic programming to find the best path.
     * The DP state can be the current specific island and a bitmask with the
     * covered islands so far. The running time will be O(S^2 * 2^S + S * N^2).
     * Note that 2^20 is about 1 million which allows this solution to be fast.
     * 
     * If there are more specific islands, we can try A*. I don't know much
     * about heuristics but we should try to discuss them with the interviewer.
     * Like using the number of the islands covered so far and the current
     * cost.
     * 
     */
    
    public static void main(String[] args) {

    }
}
