/*
  Time: O(n log n), Space: O(n)
  Algorithm:
    1) Single pass through array in order to build sorted map containing: (number, instances of number in array)
    2) Starting at the lowest key in map, try to find 'W' consecutive keys.  Decrement count in map by 1 each time a key is visited.  If count reaches 0, remove from map.  If found, continue, otherwise return false.
    3) Repeat step 2 until the 'n' numbers are counted for.
*/
class Solution {
    public boolean isNStraightHand(int[] hand, int W) {
        if ((hand.length % W != 0)) {
            // impossible to have 'W' groups of 'W'
            return false;
        }
        
        TreeMap<Integer, Integer> intCount = new TreeMap<>();
        
        for (int i = 0; i < hand.length; i++) {
            if (! intCount.containsKey(hand[i])) {
                intCount.put(hand[i], 1);
            } else {
                intCount.put(hand[i], intCount.get(hand[i]) + 1);
            }
        }
        
        boolean result = true;
        int groups = 0;
        while (groups < hand.length) {
            result = result && verifyGroup(intCount, W);
            if (! result) {
                return false;
            }
            groups+=W;
        }
        
        return result;
    }
    
    public boolean verifyGroup(TreeMap<Integer, Integer> intCount, int W) {
        if (intCount.size() == 0) {
            return false;
        }
        int curr = intCount.firstKey();
        int count = 0;
        while (count < W) {
            // System.out.println(curr);
            if (intCount.get(curr) == 1) {
                intCount.remove(curr);
            } else {
                intCount.put(curr, intCount.get(curr) - 1);
            }
            if ((! intCount.containsKey(curr + 1)) && count != W - 1) {
                return false;
            }
            curr+=1;
            count++;
        }
        return true;
    }
}
