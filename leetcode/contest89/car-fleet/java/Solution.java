class Solution {
    public int carFleet(int target, int[] position, int[] speed) {
        int n = position.length;
        if (n == 0) {
            return 0;
        }
        boolean[] isFinished = new boolean[n];
        boolean allComplete = false;
        int numFleets = 0;
        int[] startOrder = new int[n];
        while (!allComplete) {
            allComplete = true;
            Map<Integer, List<Integer>> positionToIndices = new HashMap<>();
            Set<Integer> finishedPos = new HashSet<>();
            for (int i = 0; i < n; i++) {
                if (! isFinished[i]) {
                    position[i] += speed[i];
                    if (! positionToIndices.containsKey(position[i])) {
                        positionToIndices.put(position[i], new ArrayList<Integer>());
                        
                    }
                    positionToIndices.get(position[i]).add(i);
                }
                // update speeds if two or more cars reached same pos
                for (Integer pos : positionToIndices.keySet()) {
                    List<Integer> indices = positionToIndices.get(pos);
                    if (indices.size() > 1) {
                        // more than one car in fleet
                        // update speed to each car in fleet to min speed
                        int minSpeed = Integer.MAX_VALUE;
                        for (int j = 0; j < indices.size(); j++) {
                            if (speed[indices.get(j)] < minSpeed) {
                                minSpeed = speed[indices.get(j)];
                            }
                        }
                        for (int j = 0; j < indices.size(); j++) {
                            speed[indices.get(j)] = minSpeed;
                        }
                    }
                }
                
                if (! isFinished[i]) {
                    if (position[i] >= target) {
                        // car has reached
                        isFinished[i] = true;
                        finishedPos.add(position[i]);
                    }
                }
                allComplete = allComplete && isFinished[i];
            }
            numFleets += finishedPos.size();
        }
        return numFleets;
    }
}
