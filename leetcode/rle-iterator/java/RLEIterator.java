// next O(n)
class RLEIterator {
    
    long numSeen = 0;
    int index = 0;
    long totalCount = 0;
    int[] arr;

    public RLEIterator(int[] A) {
        arr = A;
        if (A.length > 0) {
            totalCount = A[0];
        }
    }
    
    public int next(int n) {
        if (index >= arr.length) return -1;
        numSeen += n;
        while (numSeen > totalCount && index < arr.length) {
            index += 2;
            if (index < arr.length) totalCount += arr[index];
        }
        if (index + 1 < arr.length) return arr[index + 1];
        return -1;
    }
}

/**
 * Your RLEIterator object will be instantiated and called as such:
 * RLEIterator obj = new RLEIterator(A);
 * int param_1 = obj.next(n);
 */
