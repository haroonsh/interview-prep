class Solution {
    public String shiftingLetters(String S, int[] shifts) {
        if (shifts.length == 0) {
            // edge case, shifts is empty
            return S;
        }
        long[] sums = new long[shifts.length];
        sums[shifts.length - 1] = shifts[shifts.length - 1];
        for (int i = shifts.length - 2; i >= 0; i--) {
            sums[i] = sums[i+1] + shifts[i];
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < S.length(); i++) {
            Character c = S.charAt(i);
            int ascii = (int) c;
            long base = ascii - 97;
            base += (sums[i]);
            base = base % 26;
            base += 97;
            char newc = (char) base;
            sb.append(newc);
        }
        return sb.toString();
    }
}
