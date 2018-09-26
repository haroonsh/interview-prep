class Solution {
    
    public int subarrayBitwiseORs(int[] A) {
        Set<Integer> ans = new HashSet<>();
        Set<Integer> curr = new HashSet<>();
        curr.add(0);
        for (int element : A) {
            Set<Integer> currNext = new HashSet<>();
            for (int val : curr) {
                currNext.add(val | element);
            }
            currNext.add(element);
            curr = currNext;
            ans.addAll(curr);
        }
        return ans.size();
    }
    
    public int subarrayBitwiseORsQudratic(int[] A) {
        int N = A.length;
        Set<Integer> set = new HashSet<>();
        if (N == 0) {
            // no elements
            return 0;
        }
        
        int[][] matrix = new int[N][N];
        // matrix[i][j] = A[i] | A[i+1] | ... | A[j]
        
        // instantiate diagonal
        for (int i = 0; i < A.length; i++) {
            matrix[i][i] = A[i];
            if (! set.contains(matrix[i][i])) {
                set.add(matrix[i][i]);
            }
        }
        
        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                matrix[i][j] = matrix[i][j-1] | A[j];
                if (! set.contains(matrix[i][j])) {
                    set.add(matrix[i][j]);
                }
            }
        }
        return set.size();
    }
}
