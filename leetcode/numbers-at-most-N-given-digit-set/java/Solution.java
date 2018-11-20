class Solution {
    public int atMostNGivenDigitSet(String[] D, int N) {
        String str = String.valueOf(N);
        int k = str.length();
        int[] dp = new int[k + 1];
        dp[k] = 1;
        // dp[i] is the amount of numbers with (k - i) digits that are less than or equal to the last (k-i) digits of N
        
        for (int i = k-1; i >= 0; i--) {
            int curr = str.charAt(i) - '0';
            for (String d : D) {
                int digit = Integer.valueOf(d);
                if (digit < curr) {
                    dp[i] += Math.pow(D.length, k-i-1);
                } else if (digit == curr) {
                    dp[i] += dp[i+1];
                }
            }
        }
        
        for (int i = 1; i < k; i++) {
            dp[0] += Math.pow(D.length, i);
        }
        
        return dp[0];
    }

}
