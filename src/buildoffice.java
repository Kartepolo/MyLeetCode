/**
 * Created by Xiang on 11/7/2016.
 */
import java.util.*;
public class buildoffice {
    public class Node{
        int x;
        int y;
        Node (int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    public int shortestDistance(int[][] grid) {
        // Write your code here
        if (grid == null || grid.length == 0) return -1;
        int mintotal = Integer.MAX_VALUE;
        List<Node> arr = new ArrayList<Node>();
        for (int i = 0; i < grid.length; i ++){
            for (int j = 0; j < grid[0].length; j ++){
                if (grid[i][j] == 1){
                    arr.add(new Node(i, j));
                }
            }
        }
        int[][] mindist = new int[grid.length][grid[0].length];
        for (Node house : arr){
            int[][] curdist = this.finddist(grid, house);
            for (int i = 0; i < curdist.length; i ++){
                for (int j =0; j < curdist[0].length; j ++){
                    if (grid[i][j] == 0 && curdist[i][j] != 0) mindist[i][j] += curdist[i][j];
                    else mindist[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        for (int i = 0; i < mindist.length; i ++){
            for (int j = 0;j < mindist[0].length; j ++){
                mintotal = Math.min(mintotal, mindist[i][j]);
            }
        }
        return mintotal < Integer.MAX_VALUE?mintotal : -1;
    }
    public int[][] finddist(int[][] grid, Node n){
        Queue<Node> q= new LinkedList<Node>();
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int[][] res = new int[grid.length][grid[0].length];
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};
        int newx, newy;
        q.offer(n);
        visited[n.x][n.y] = true;
        int step = 0;
        while (q.size() > 0){
            int presize = q.size();
            step++;
            while (presize > 0){
                Node cur = q.poll();
                for (int i = 0; i < dx.length; i++){
                    newx = cur.x + dx[i];
                    newy = cur.y + dy[i];
                    if (newx >= 0 && newx < grid.length && newy >= 0 && newy < grid[0].length){
                        if (!visited[newx][newy]){
                            if (grid[newx][newy] == 0){
                                res[newx][newy] = step;
                                q.add(new Node(newx, newy));
                            }
                            visited[newx][newy] = true;
                        }
                    }
                }
                presize --;
            }
        }
        return res;
    }
    public static void main(String[] args){
        buildoffice B = new buildoffice();
        B.shortestDistance(new int[][]{{0,1,0,0,},{1,0,2,1},{0,1,0,0}});
    }
}
