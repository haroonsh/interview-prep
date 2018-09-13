import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws IOException {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String line = null;
        int size = Integer.parseInt(br.readLine());
        String[] matrix = new String[size];
        int count = 0;
        while ((line = br.readLine()) != null) {
            matrix[count] = line;
            count++;
        }
        
        int[] coor = findStart(matrix, size);
        boolean[][] visited = new boolean[size][size];
        List<String> solution = new ArrayList<>();
        visited[coor[0]][coor[1]] = true;
        
        solution = bfs(matrix, coor[0], coor[1], visited);
        for (String dir : solution) {
            System.out.println(dir);
        }
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
    
    public static boolean dfs(String[] matrix, int currX, int currY, List<String> path, boolean[][] visited) {
        if (matrix[currX].charAt(currY) == 'p') {
            // found
            return true;
        }
        
        // check up
        if (currX > 0 && ! visited[currX-1][currY]) {
            path.add("Up");
            visited[currX-1][currY] = true;
            if (dfs(matrix, currX - 1, currY, path, visited)) {
                return true;
            }
            visited[currX-1][currY] = false;
            path.remove(path.size() - 1);
        }
        
        // check right
        if (currY < matrix.length - 1 && ! visited[currX][currY+1]) {
            path.add("Right");
            visited[currX][currY+1] = true;
            if (dfs(matrix, currX, currY + 1, path, visited)) {
                return true;
            }
            visited[currX][currY+1] = false;
            path.remove(path.size() - 1);
        }
        
        // check down
        if (currX < matrix.length - 1 && ! visited[currX+1][currY]) {
            path.add("Down");
            visited[currX+1][currY] = true;
            if (dfs(matrix, currX+1, currY, path, visited)) {
                return true;
            }
            visited[currX+1][currY] = false;
            path.remove(path.size() - 1);
        }
        
        // check left
        if (currY > 0 && ! visited[currX][currY-1]) {
            path.add("Left");
            visited[currX][currY-1] = true;
            if (dfs(matrix, currX, currY-1, path, visited)) {
                return true;
            }
            visited[currX][currY-1] = false;
            path.remove(path.size() - 1);
        }
        
        return false;
    }
    
    /*
     * returns int[2] ([x,y]), s.t. (x,y) is the location of "m"
     */
    public static int[] findStart(String[] matrix, int size) {
        int[] sol = new int[2];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (matrix[i].charAt(j) == 'm') {
                    sol[0] = i;
                    sol[1] = j;
                    return sol;
                }
            }
        }
        return sol;
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
