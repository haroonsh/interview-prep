class Solution {
    public int[] loudAndRich(int[][] richer, int[] quiet) {
        // topological sort of graph where each node represents a person
        // and a directed edge from person i to j represents
        // richness of i > richness of j
        // we also choose the minimum quiet value stored in each of
        // node's "parents" to be minimum quiet value of the node
        
        int graphSize = quiet.length;
        
        DirectedGraph digraph = new DirectedGraph(graphSize);
        
        for (int i = 0; i < quiet.length; i++) {
            // every element in quiet may not appear in "richer"
            digraph.addNode(i);
        }
        
        for (int i = 0; i < richer.length; i++) {
            int start = richer[i][0];
            int end = richer[i][1];
            digraph.addEdge(start, end);
        }
        
        // init default "GraphNode.bestQuietPair"
        for (int i = 0; i < quiet.length; i++) {
            QuietPair qp = new QuietPair(i, quiet[i]);
            digraph.nodes[i].setBestQuietPair(qp);
        }
        
        // perform topological sort (BFS style)
        Queue<BFSNode> activeQueue = new LinkedList<>();
        Map<Integer, BFSNode> blockedQueue = new HashMap<>();
        
        
        for (int i = 0; i < graphSize; i++) {
            BFSNode bfsNode = new BFSNode(digraph.nodes[i]);
            if (bfsNode.inDegreeCount == 0) {
                activeQueue.add(bfsNode);
            } else {
                blockedQueue.put(i, bfsNode);
            }
        }
        int[] solution = new int[graphSize];
        while (! activeQueue.isEmpty()) {
            BFSNode node = activeQueue.remove();
            int currNodeID = node.graphNode.id;
            solution[currNodeID] = node.graphNode.bestQuietPair.id;
            
            List<Integer> removeList = new ArrayList<>();
            for (Integer key : blockedQueue.keySet()) {
                BFSNode bfsNode = blockedQueue.get(key);
                if (bfsNode.graphNode.parents.contains(currNodeID)) {
                    bfsNode.inDegreeCount--;
                    if (bfsNode.inDegreeCount == 0) {
                        // add to active queue, update bestQuietPair
                        QuietPair best = bfsNode.graphNode.bestQuietPair;
                        for (Integer parentID : bfsNode.graphNode.parents) {
                            GraphNode parent = digraph.nodes[parentID];
                            if (best.compareTo(parent.bestQuietPair) == 1) {
                                // new best found
                                best = parent.bestQuietPair;
                            }
                        }
                        bfsNode.graphNode.setBestQuietPair(best);
                        removeList.add(key);
                    }
                }
            }
            for (Integer key : removeList) {
                BFSNode removedNode = blockedQueue.remove(key);
                activeQueue.add(removedNode);
            }
        }
        return solution;
    }
    
    class BFSNode {
        GraphNode graphNode;
        int inDegreeCount;
        
        public BFSNode(GraphNode gn) {
            this.graphNode = gn;
            this.inDegreeCount = gn.parents.size();
        }
    }
    
    class DirectedGraph {
        GraphNode[] nodes;
        
        public DirectedGraph(int size) {
            this.nodes = new GraphNode[size];
            for (int i = 0; i < size; i++) {
                this.nodes[i] = null;
            }
        }
        
        public void addNode(int identifier) {
            if (this.nodes[identifier] == null) {
                // node not present
                this.nodes[identifier] = new GraphNode(identifier);
            }
        }
        
        public void addEdge(int start, int end) {
            if (this.nodes[start] == null) {
                this.addNode(start);
            }
            if (this.nodes[end] == null) {
                this.addNode(end);
            }
            this.nodes[start].addChild(this.nodes[end]);
            this.nodes[end].addParent(this.nodes[start]);
        }
    }
    
    class GraphNode {
        int id;
        QuietPair bestQuietPair;
        Set<Integer> parents;
        Set<Integer> children;
        
        public GraphNode(int identifier) {
            this.id = identifier;
            this.parents = new HashSet<>();
            this.children = new HashSet<>();
        }
        
        public void setBestQuietPair(QuietPair best) {
            this.bestQuietPair = best;
        }
        
        public void addParent(GraphNode parent) {
            this.parents.add(parent.id);
        }
        
        public void addChild(GraphNode child) {
            this.children.add(child.id);
        }
    }
    
    class QuietPair implements Comparable<QuietPair> {
        int id;
        int quietLevel;
        
        public QuietPair(int identifier, int val) {
            this.id = identifier;
            this.quietLevel = val;
        }
        
        public int compareTo(QuietPair other) {
            Integer i1 = new Integer(this.quietLevel);
            Integer i2 = new Integer(other.quietLevel);
            return i1.compareTo(i2);
        }
    }
}
