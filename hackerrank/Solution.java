import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class Solution {

    /*
     * Complete the coinOnTheTable function below.
     */
    static int coinOnTheTable(int m, int k, String[] board) {
        /*
         * Write your code here.
         */
        
        if (board.length == 0 || board[0].length() == 0) {
            return 0;
        }
        
        int[][] dp = new int[board.length][board[0].length()];
        for (int i = 0; i < board.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        dp[0][0] = 0;
        int[] sol = findLocOfStar(board, board.length, m);
        int curr_row = sol[0];
        int curr_col = sol[1];
        
        int result = coinOnTheTableHelper(board, dp, curr_row, curr_col, k);
        if (result == Integer.MAX_VALUE) {
            return -1;
        }
        return result;
        
    }
    
    static int coinOnTheTableHelper(String[] board, int[][] dp, 
                                     int i, int j, int k) {
        // base cases
        if (i == 0 && j == 0) {
            // origin
            return 0;
        }
        
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length()) {
            return Integer.MAX_VALUE;
        }
        
        if (k == 0) {
            // no more turns left
            dp[i][j] = Integer.MAX_VALUE;
            return dp[i][j];
        }

        if (dp[i][j] >= 0) {
            // already computed return
            return dp[i][j];
        }
        
        // else if previous position was...
        
        dp[i][j] = Integer.MAX_VALUE;
        
        // UP
        if (i > 0) {
            int penalty = 0;
            coinOnTheTableHelper(board, dp, i-1, j, k-1);
            if (board[i-1].charAt(j) != 'D') {
                // penalty!
                if (dp[i-1][j] != Integer.MAX_VALUE) {
                    penalty = 1;
                }
            }
            if (dp[i-1][j] == -1) {
                System.out.println("YikesUP!");
            }
            dp[i][j] = Math.min(dp[i][j], dp[i-1][j] + penalty);
        }
        
        // LEFT
        if (j > 0) {
            int penalty = 0;
            coinOnTheTableHelper(board, dp, i, j-1, k-1);
            if ( board[i].charAt(j-1) != 'R') {
                // penalty!
                if (dp[i][j-1] != Integer.MAX_VALUE) {
                    penalty = 1;
                }
            }
            if (dp[i][j-1] == -1) {
                System.out.println("YikesLEFT!");
            }
            dp[i][j] = Math.min(dp[i][j], dp[i][j-1] + penalty);
        }
        
        // DOWN
        if (i < board.length - 1) {
            int penalty = 0;
            coinOnTheTableHelper(board, dp, i+1, j, k-1);
            if (board[i+1].charAt(j) != 'U') {
                // penalty!
                if (dp[i+1][j] != Integer.MAX_VALUE) {
                    penalty = 1;
                }
            }
            if (dp[i+1][j] == -1) {
                System.out.println("YikesDOWN!");
            }
            dp[i][j] = Math.min(dp[i][j], dp[i+1][j] + penalty);
        }
        
        // RIGHT
        if (j < board[0].length() - 1) {
            int penalty = 0;
            coinOnTheTableHelper(board, dp, i, j+1, k-1);
            if (board[i].charAt(j+1) != 'L') {
                // penalty!
                if (dp[i][j+1] != Integer.MAX_VALUE) {
                    penalty = 1;
                }
            }
            if (dp[i][j+1] == -1) {
                System.out.println("YikesRIGHT!");
            }
            dp[i][j] = Math.min(dp[i][j], dp[i][j+1] + penalty);
        }
        
        return dp[i][j];
    }
    
    static int[] findLocOfStar(String[] board, int n, int m) {
        int[] sol = new int[2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i].charAt(j) == '*') {
                    // found!
                    sol[0] = i;
                    sol[1] = j;
                }
            }
        }
        return sol;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nmk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nmk[0].trim());

        int m = Integer.parseInt(nmk[1].trim());

        int k = Integer.parseInt(nmk[2].trim());

        String[] board = new String[n];

        for (int boardItr = 0; boardItr < n; boardItr++) {
            String boardItem = scanner.nextLine();
            board[boardItr] = boardItem;
        }

        int result = coinOnTheTable(m, k, board);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();
    }
}
