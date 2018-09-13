class Solution {
    public int peakIndexInMountainArray(int[] A) {
        int i = 1;
        while (i < A.length && A[i-1] < A[i]) {
            i++;
        }
        return i-1;
    }
}
