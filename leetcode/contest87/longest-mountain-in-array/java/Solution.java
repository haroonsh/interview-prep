class Solution {
    public int longestMountain(int[] A) {
        /*
        M[i] = {
                    0 if i = 0, len(A) - 1
                    0 if !(A[i-1] < A[i] && A[i] > A[i+1])
                    1 + |lessThanChain(i-1)| + |greaterThanChain(i+1)|
                }
        */
        if (A.length == 0) {
            return 0;
        }
        int[] leftDecreasingChain = new int[A.length];
        leftDecreasingChain[0] = 0;
        for (int i = 1; i < A.length; i++) {
            if (A[i-1] < A[i]) {
                leftDecreasingChain[i] = leftDecreasingChain[i-1] + 1;
            } else {
                leftDecreasingChain[i] = 0;
            }
        }
        
        int[] rightDecreasingChain = new int[A.length];
        rightDecreasingChain[A.length - 1] = 0;
        for (int i = A.length - 2; i >= 0; i--) {
            if (A[i] > A[i+1]) {
                // chain extended
                rightDecreasingChain[i] = rightDecreasingChain[i+1] + 1;
            } else {
                rightDecreasingChain[i] = 0;
            }
        }
        
        // try to find mountain peak
        int best = 0;
        for (int i = 1; i < A.length - 1; i++) {
            if (leftDecreasingChain[i] > 0 && rightDecreasingChain[i] > 0) {
                // a mountain peak found
                if (best < leftDecreasingChain[i] + rightDecreasingChain[i] + 1) {
                    // new "best" mountain peak found
                    best = leftDecreasingChain[i] + rightDecreasingChain[i] + 1;
                }
            }
        }
        
        return best;
    }
    
    public void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.print("\n");
    }
    
    public void printAll(int[] A, int[] leftDecreasingChain, int[] rightDecreasingChain) {
        System.out.println("A");
        printArr(A);
        
        System.out.println("leftDecreasingChain");
        printArr(leftDecreasingChain);
        
        System.out.println("rightDecreasingChain");
        printArr(rightDecreasingChain);
    }
}
