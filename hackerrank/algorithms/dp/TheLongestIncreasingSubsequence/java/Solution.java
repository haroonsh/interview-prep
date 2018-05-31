import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {
    
    // Complete the longestIncreasingSubsequence function below.
    static int longestIncreasingSubsequence(int[] arr) {
        // O(n log n) solution
        if (arr.length == 0) {
            return 0;
        }
        int[] dp = new int[arr.length];
        dp[0] = arr[0];
        int count = 0;
        for (int i = 1; i < arr.length; i++) {
            int low = 0;
            int high = count;
            while (low < high) {
                // binary search to find smallest "l" s.t. A[i] <= dp[l]
                int middle = (low + high)/2;
                if (dp[middle] < arr[i]) {
                    low = middle + 1;
                } else {
                    high = middle;
                }
            }
            if (dp[high] < arr[i]) {
                // top never changed, adding arr[i] results in new LIS
                // of length "count + 1"
                count += 1;
                dp[count] = arr[i];
            } else if (dp[high] > arr[i]) {
                // replace dp[high] with arr[i]
                dp[high] = arr[i];
            }
        }
        return count + 1;
    }
    
    
    static int longestIncreasingSubsequence2(int[] arr) {
        // O(n^2) solution
        if (arr.length == 0) {
            return 0;
        }
        int longest = 1;
        int indexOfLongest = 0;
        List<List<Integer>> dp = new ArrayList<>();
        dp.add(new ArrayList<>(Arrays.asList(arr[0])));
        for (int i = 1; i < arr.length; i++) {
            List<Integer> currLongest = new ArrayList<>(Arrays.asList(arr[i]));
            int best = 1;
            int indexOfBestJ = -1;
            for (int j = 0; j < i; j++) {
                List<Integer> lisEndingAtJ = dp.get(j);
                if (arr[j] < arr[i] &&
                   best < lisEndingAtJ.size() + 1) {
                    // longer LIS ending at i found
                    best = lisEndingAtJ.size() + 1;
                    indexOfBestJ = j; 
                }
            }
            if (indexOfBestJ >= 0) {
                List<Integer> lisEndingAtJ = dp.get(indexOfBestJ);
                currLongest = new ArrayList<>(lisEndingAtJ);
                currLongest.add(arr[i]);
            }
            dp.add(currLongest);
            if (longest < currLongest.size()) {
                longest = currLongest.size();
                indexOfLongest = i;
            }
        }
        return longest;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            int arrItem = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            arr[i] = arrItem;
        }

        int result = longestIncreasingSubsequence(arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
