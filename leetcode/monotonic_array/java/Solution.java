class Solution {
    public boolean isMonotonic(int[] A) {
        boolean increasing = true;
        boolean decreasing = true;
        
        // base case
        if (A.length == 0) {
            return true;
        }
        
        int prev = A[0];
        for (int i = 0; i < A.length; i++) {
            increasing = increasing && (prev <= A[i]);
            decreasing = decreasing && (prev >= A[i]);
            prev = A[i];
        }
        return increasing || decreasing;
    }
}
