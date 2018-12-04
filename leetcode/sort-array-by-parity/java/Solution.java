class Solution {
    public int[] sortArrayByParity(int[] A) {
        int[] sol = new int[A.length];
        int evenCount = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] % 2 == 0) {
                evenCount++;
            }
        }
        int evenIndex = 0;
        int oddIndex = evenCount;
        for (int i = 0; i < A.length; i++) {
            if (A[i] % 2 == 0) {
                sol[evenIndex] = A[i];
                evenIndex++;
            } else {
                sol[oddIndex] = A[i];
                oddIndex++;
            }
        }
        return sol;
    }
}
