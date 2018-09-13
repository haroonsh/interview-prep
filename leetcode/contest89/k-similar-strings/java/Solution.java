class Solution {
    public int kSimilarity(String A, String B) {
        // O(n^2) neighbors, swap any two indices
        Queue<State> queue = new LinkedList<>();
        Set<String> visitedStates = new HashSet<>();
        State startState = new State(A, 0);
        visitedStates.add(A);
        // perform BFS starting from State(A, 0)
        queue.add(startState);
        while (! queue.isEmpty()) {
            State currState = queue.remove();
            if (currState.str.compareTo(B) == 0) {
                // reached goal state
                return currState.dist;
            }
            int length = currState.str.length();
            for (int i = 0; i < length; i++) {
                for (int j = i+1; j < length; j++) {
                    StringBuilder sb = new StringBuilder();
                    if (i > 0) {
                        // str[0:i]
                        sb.append(currState.str.substring(0,i));
                    }
                    // s[j]
                    sb.append(currState.str.charAt(j));
                    if (j > i+1) {
                        // str[i+1:j]
                        sb.append(currState.str.substring(i+1, j));
                    }
                    // s[i]
                    sb.append(currState.str.charAt(i));
                    if (j < length - 1) {
                        // str[j+1:]
                        sb.append(currState.str.substring(j+1));
                    }
                    State newState = new State(sb.toString(), currState.dist + 1);
                    if (! visitedStates.contains(newState.str)) {
                        queue.add(newState);
                        visitedStates.add(newState.str);
                    }
                }
            }
        }
        // if A and B are anagrams, 
        // this statement should never be reachable
        return -1;
    }
}

class State {
    String str;
    int dist;
    
    public State(String s, int d) {
        this.str = s;
        this.dist = d;
    }
}
