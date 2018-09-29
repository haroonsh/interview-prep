class Solution {
    
    public String orderlyQueue(String S, int K) {
        StringBuilder sb = new StringBuilder();
        if (K == 1) {
            // only rotations of S possible
            String best = S;
            for (int i = 0; i < S.length(); i++) {
                StringBuilder temp = new StringBuilder();
                temp.append(S.substring(i));
                temp.append(S.substring(0, i));
                String tempStr = temp.toString();
                if (best.compareTo(tempStr) > 0)  {
                    System.out.print(i);
                    System.out.println(tempStr);
                    best = tempStr;
                }
            }
            sb.append(best);
        } else {
            TreeMap<Character, Integer> treeMap = new TreeMap<>();
            for (int i = 0; i < S.length(); i++) {
                char c = S.charAt(i);
                if (! treeMap.containsKey(c)) {
                    treeMap.put(c, 0);
                }
                treeMap.put(c, treeMap.get(c) + 1);
            }
            
            for (Character c : treeMap.keySet()) {
                int repeats = treeMap.get(c);
                for (int i = 0; i < repeats; i++) {
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }
    
    public String orderlyQueueBFS(String S, int K) {
        Queue<String> queue = new LinkedList<>();
        Set<String> set = new HashSet<>();
        String best = S;
        queue.add(S);
        set.add(S);
        while (! queue.isEmpty()) {
            String curr = queue.remove();
            for (int i = 0; i < curr.length() && i < K; i++) {
                StringBuilder sb = new StringBuilder();
                sb.append(curr.substring(0,i));
                sb.append(curr.substring(i+1));
                sb.append(curr.charAt(i));
                String newNode = sb.toString();
                if (! set.contains(newNode)) {
                    set.add(newNode);
                    queue.add(newNode);
                    if (best.compareTo(newNode) > 0) {
                        best = newNode;
                    }
                }
            }
        }
        return best;
    }
}
