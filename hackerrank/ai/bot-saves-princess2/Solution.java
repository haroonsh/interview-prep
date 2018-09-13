import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

static void nextMove(int n, int r, int c, String [] grid){
    boolean[][] visited = new boolean[n][n];
    List<String> result = bfs(grid, r, c, visited);
    if (result.size() > 0) {
        System.out.println(result.get(0));   
    }
}
    

public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n,r,c;
        n = in.nextInt();
        r = in.nextInt();
        c = in.nextInt();
        in.useDelimiter("\n");
        String grid[] = new String[n];


        for(int i = 0; i < n; i++) {
            grid[i] = in.next();
        }

    nextMove(n,r,c,grid);

    }
    
    public static List<String> bfs(String[] matrix, int startX, int startY, boolean[][] visited) {
        List<String> solution = new ArrayList<>();
        visited[startX][startY] = true;
        Queue<BFSNode> queue = new LinkedList<>();
        BFSNode start = new BFSNode(startX, startY, solution, "");
        queue.add(start);
        while (! queue.isEmpty()) {
            BFSNode currNode = queue.remove();
            int x = currNode.currX;
            int y = currNode.currY;
            // System.out.println("Currently at (" + x + "," + y + ")");
            if (matrix[x].charAt(y) == 'p') {
                // found
                return currNode.path;
            }
            // check up
            if (x > 0 && !visited[x-1][y]) {
                visited[x-1][y] = true;
                List<String> newPath = new ArrayList<>(currNode.path);
                BFSNode upNode = new BFSNode(x-1, y, newPath, "UP");
                queue.add(upNode);
            }
            
            // check right
            if (y < matrix.length-1 && !visited[x][y+1]) {
                visited[x][y+1] = true;
                List<String> newPath = new ArrayList<>(currNode.path);
                BFSNode rightNode = new BFSNode(x, y+1, newPath, "RIGHT");
                queue.add(rightNode);
            }
            
            // check down
            if (x < matrix.length-1 && !visited[x+1][y]) {
                visited[x+1][y] = true;
                List<String> newPath = new ArrayList<>(currNode.path);
                BFSNode downNode = new BFSNode(x+1, y, newPath, "DOWN");
                queue.add(downNode);
            }
            
            // check left
            if (y > 0 && !visited[x][y-1]) {
                visited[x][y-1] = true;
                List<String> newPath = new ArrayList<>(currNode.path);
                BFSNode leftNode = new BFSNode(x, y-1, newPath, "LEFT");
                queue.add(leftNode);
            }
        }
        return solution;
    }
}

class BFSNode {
        int currX;
        int currY;
        List<String> path;
        
        public BFSNode(int x, int y, List<String> pathSoFar, String direction) {
            this.path = pathSoFar;
            if (direction.length() > 0) {
                this.path.add(direction);
            }
            this.currX = x;
            this.currY = y;
        }
    }


