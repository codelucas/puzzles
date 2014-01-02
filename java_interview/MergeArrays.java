package JavaInterview;

public class MergeArrays {
    /*
     * You have two sorted arrays of integers, A and B. A has a buffer at the
     * end at least the size of B. Write a method to merge B into A in sorted
     * order.
     * 
     * This looks like the "merge" portion of mergesort but with a twist. Let's
     * try not to allocate memory for a new list.
     * 
     * I will use -1 to delimit the "Buffer element"
     */

    static int[] merge(int[] A, int[] B) {
        /*
         * Our strategy: In order to keep this linear and to not allocate new
         * memory for a list, we will find the index where the buffer begins in
         * A. Then we will perform a backwards merge from mergesort because we
         * don't want to keep shifting entire lists of elements towards the
         * buffer.
         */

        int buffercap = 0;

        for (int i = 0; i < A.length; i++) {
            if (A[i] == -1) {
                buffercap = i;
                break;
            }
        }

        int acnt = buffercap - 1;
        int bcnt = B.length - 1;
        int cnt = A.length - 1;

        while (bcnt >= 0 && acnt >= 0) {

            if (A[acnt] > B[bcnt]) {
                A[cnt--] = A[acnt];
                A[acnt] = -1;
                acnt--;
            } else {
                A[cnt--] = B[bcnt--];
            }
        }

        while (bcnt >= 0) {
            A[cnt--] = B[bcnt--];
        }

        // This portion is necessary because we need to
        // account for incase the buffer is very large in size
        while (acnt >= 0) {
            A[cnt--] = A[acnt];
            A[acnt] = -1;
            acnt--;
        }

        return A;
    }

    public static void main(String[] args) {

        int[] A = { 1, 3, 5, 7, 9, 11, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
        int[] B = { 2, 4, 6, 8, 12 };

        int[] C = merge(A, B);
        for (int x : C) {
            System.out.println(x);
        }
    }
}
