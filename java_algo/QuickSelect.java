package JavaAlgo;

public class QuickSelect {
   /*
    * Find the kth smallest element in a list.
    * O(n) time complexity, O(1) space.
    */
   static String arrstr(int[] arr) {
       String s = "[";
       for (int i : arr) {
           s += " "+i;
       }
       return s+" ]";
   }
   
   static void swap(int[] arr, int x, int y) {
       int tmp = arr[x];
       arr[x] = arr[y];
       arr[y] = tmp;
   }
   
   static int partition(int[] arr, int a, int b) {
       int piv = arr[a];
       int start = a+1;
       int end = b;
       while (start <= end) {
           while (arr[start] < piv) {
               start++;
           }
           while(arr[end] > piv) {
               end--;
           }
           if (start <= end) {
               swap(arr, start, end);
               start++;
               end--;
           }
       }
       swap(arr, a, end); // or start-1
       return end;        // or start-1
   }
   
   static int qselect(int[] arr, int k, int start, int end) {
       if (end < start) {
           return -1;
       }
       int piv = partition(arr, start, end);
       if (k < piv) {
           return qselect(arr, k, start, piv-1);
       } else if (k > piv) {
           return qselect(arr, k, piv+1, end);
       } else {
           return arr[piv];
       }
   }
   
   public static void main(String[] args) {
       // 2, 3, 4, 5, 7, 8, 9, 12
       // ans: 2 and 12
       int[] arr = {7, 5, 2, 3, 12, 9, 4, 8};
       int k = 3; // ans=5
       System.out.println(qselect(arr, k, 0, arr.length-1));
   }
}
