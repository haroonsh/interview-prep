class Solution {
    public String[] uncommonFromSentences(String A, String B) {
        Map<String, Integer> map = new HashMap<>();
        Set<String> best = new HashSet<>();
        String[] wordsA = A.split(" ");
        String[] wordsB = B.split(" ");
        helper(wordsA, map, best);
        helper(wordsB, map, best);
        return best.toArray(new String[0]);
    }
    
    public void helper(String[] words, Map<String, Integer> map, Set<String> best) {
        for (int i = 0 ; i < words.length; i++) {
            String word = words[i];
            if (! map.containsKey(word)) {
                map.put(word, 1);
                best.add(word);
            } else {
                map.put(word, map.get(word) + 1);
                if (best.contains(word)) {
                    // word should no longer be in best,
                    // but we still keep track of it in map
                    best.remove(word);
                }
            }
        }
    }
}
