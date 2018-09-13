class Solution {
    public int shortestPathLength(int[][] graph) {
        int numNodes = graph.length;
        // distances[i][j] = 'i' represents the nodes already visited, j represents current node
        int[][] distances = new int[1 << numNodes][numNodes];
        // complete graph has n^2 edges
        int maxDist = numNodes * numNodes;
        for (int[] row: distances) {
            Arrays.fill(row, maxDist);
        }
        Queue<BFSNode> queue = new LinkedList<>();
        
        // add start states
        for (int i = 0; i < numNodes; i++) {
            BFSNode node = new BFSNode(1 << i, i);
            distances[node.visited][node.curr] = 0;
            queue.offer(node);
        }
        
        // BFS
        while (! queue.isEmpty()) {
            BFSNode node = queue.poll();
            int currDist = distances[node.visited][node.curr];
            
            if (node.visited == (1 << numNodes) - 1) {
                // visited all nodes
                return currDist;
            }
            
            for (int i = 0; i < graph[node.curr].length; i++) {
                int neighbor = graph[node.curr][i];
                int newVisited = node.visited | (1 << neighbor);
                if (currDist + 1 < distances[newVisited][neighbor]) {
                    distances[newVisited][neighbor] = currDist + 1;
                    BFSNode newNode = new BFSNode(newVisited, neighbor);
                    queue.offer(newNode);
                }
            }
        }
        
        return -1;
    }
    
    class BFSNode {
        public int visited;
        public int curr;
        
        public BFSNode(int v, int c) {
            this.visited = v;
            this.curr = c;
        }
    }
    
}
