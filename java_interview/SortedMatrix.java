package JavaInterview;


public class SortedMatrix {
    /*
     * Given an MxN matrix in which each row and each 
     * column is sorted in ascending order, write a method
     * to find an element.
     * 
     * A naive solution is to perform binary search row by
     * row until something has been found. Time: O(M*log(N))
     * 
     * We can also perform somewhat of an informed depth  first
     * traversal starting from one of the corners.
     * 
     */
    
    static int binarys(int[] row, int k, int a, int b) {
        while (a<=b) {
            int m = (a+b)/2;
            if (k < row[m]) b = m-1;
            else if (k > row[m]) a = m+1;
            else return m;
        }
        return -1;
    }
    
    static int[] naivematrixsearch(int[][] matrix, int k) {
        int columnlength = matrix[0].length;
        for (int i=0; i<columnlength; i++) {
            int[] row = matrix[i];
            int result = binarys(row, k, 0, row.length-1);
            if (result == -1)
                continue;
            else if (matrix[i][result] == k)
                return new int[] {i,result}; // row, column
        }
        return new int[] {-1, -1};
    }
    
    static int[] matrixsearch(int[][] matrix, int k, int ycoord, int xcoord) {
        if (xcoord < 0 || ycoord >= matrix.length) {
            return new int[] {-1, -1};
        }
        /* Start at upper right corner and logically progress */
        int topright = matrix[ycoord][xcoord];
        // eliminate right most column
        if (k < topright) {
            return matrixsearch(matrix, k, ycoord, xcoord-1);
        } else if (k > topright) { // must be in rightmost column, move up
            return matrixsearch(matrix, k, ycoord+1, xcoord);
        } else {
            return new int[] {ycoord, xcoord};
        }
    }
    
    static int[] matrixsearch(int[][] matrix, int k) {
        int ycoord = 0; // top row
        int xcoord = matrix[0].length-1; // last elem of top row
        return matrixsearch(matrix, k, ycoord, xcoord);
    }
    
    public static void main(String[] args) {
        // 15 20 40  85
        // 20 35 80  95
        // 30 35 95  105
        // 40 80 100 120
        int[][] matrix = new int[][] {
                new int[] {15, 20, 40,  85},
                new int[] {20, 35, 80,  95},
                new int[] {30, 35, 95,  105},
                new int[] {40, 80, 100, 120},
        };
        int lookingfor = 20;
        // int[] coords = naivematrixsearch(matrix, lookingfor);
        // System.out.println("x: " + coords[1] + " y: " + coords[0]);
        int[] coords = matrixsearch(matrix, lookingfor);
        System.out.println("x: " + coords[1] + " y: " + coords[0]);
    }
}
