class Solution {
    public int numPermsDISequence(String S) {
        int N = S.length();
        int MOD = 1000000007;
        
        // dp[i][j] = # of ways to place P_i with relative rank j
        int[][] dp = new int[N + 1][N + 1];
        for (int i = 0; i <= N; i++) {
            dp[0][i] = 1;
        }
        
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= i; j++) {
                if (S.charAt(i-1) == 'D') {
                    // S[i-1] == 'D'
                    for (int k = j; k < i; k++) {
                        dp[i][j] += dp[i-1][k];
                        dp[i][j] = dp[i][j] % MOD;
                    }
                } else {
                    // S[i-1] == 'I'
                    for (int k = 0; k < j; k++) {
                        dp[i][j] += dp[i-1][k];
                        dp[i][j] = dp[i][j] % MOD;
                    }
                }
            }
        }
        
        int ans = 0;
        for (int x : dp[N]) {
            ans += x;
            ans = ans % MOD;
        }
        
        return ans;
    }
}
