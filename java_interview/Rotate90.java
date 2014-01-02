import java.util.Random;

// Rotate a NxN matrix by 90 degrees to the right

public class Rotate90 {
    static void rotate_arr(int[][] arr) {
        // Iterate from zero to floor(N/2) because we
        // close the gap by 2 rows & cols each time. 
        // One critical thing to note is our inner loop
        // starts where our outer left off and ends at 
        // the difference between n and our outer index.
        int n = arr.length-1;
        for (int i=0; i < arr.length/2; i++) {
            for (int j=i; j < (n-i); j++) {
                int tmp = arr[i][j];
                arr[i][j] = arr[n-j][i];
                arr[n-j][i] = arr[n-i][n-j];
                arr[n-i][n-j] = arr[j][n-i];
                arr[j][n-i] = tmp;
            }    
        } 
    }

    public static void main(String[] args) {
        int[][] m = new int[3][3];
        Random r = new Random();
        for (int i=0; i<m.length; i++) {
            for (int j=0; j<m.length; j++) {
                m[i][j] = r.nextInt(1000);
                System.out.print(Integer.toString(m[i][j])+" ");
            }
            System.out.println("");
        }    
        System.out.println("");
        rotate_arr(m);
        for (int i=0; i<m.length; i++) {
            for (int j=0; j<m[0].length; j++) {
                System.out.print(Integer.toString(m[i][j])+" ");
            }
            System.out.println("");
        }
    }
}
