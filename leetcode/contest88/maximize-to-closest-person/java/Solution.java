class Solution {
    public int maxDistToClosest(int[] seats) {
        int i = 0;
        int best = 0;
        while (i < seats.length) {
            while (i < seats.length && seats[i] == 1) {
                i++;
            }
            int j = i;
            while (i < seats.length && seats[i] == 0) {
                i++;
            }
            if(j == 0 || i == seats.length){
                best = Math.max(best, i - j);
            }else{
                best = Math.max(best, (i - j + 1) / 2) ;
            }
        }
        return best;
    }
}
