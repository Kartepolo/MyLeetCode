/**
 * Created by Xiang on 10/1/2016.
 */
import java.util.*;
public class numofislands {
    private int m, n;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, -1, 0, 1};
    public int numIslands(char[][] grid) {
        m = grid.length;
        n = grid[0].length;
        int idx, idy;
        int count = 0;
        for (int i = 0; i < m; i ++){
            for (int j = 0; j < n; j ++){
                if (grid[i][j] != '1') continue;
                count++;
                bfs(i, j, grid);
            }
        }
        return count;
    }

    public void bfs(int idx, int idy, char[][] grid){
        Queue<Integer> q = new LinkedList<Integer>();
        q.add(idx * n + idy);
        while (q.size() > 0){
            int cur = q.poll();
            idx = cur / n;
            idy = cur % n;
            grid[idx][idy] = '2';
            for (int i=0; i < dx.length; i ++){
                    int newx = dx[i] + idx;
                    int newy = dy[i] + idy;
                    if (newx < 0 || newx >= m || newy < 0 || newy >= n) continue;
                    if (grid[newx][newy] == '1') {
                        q.add(newx * n + newy);
                        grid[newx][newy] = 2;
                    }
                }
        }
    }
    public static void main(String[] args){
        numofislands S = new numofislands();
        String[] abc = {"11111011111111101011","01111111111110111110","10111001101111111111","11110111111111111111","10011111111111111111","10111111011101110111","01111111111101101111","11111111111101111011","11111111110111111111","11111111111111111111","01111111011111111111","11111111111111111111","11111111111111111111","11111011111110111111","10111110111011110111","11111111111101111110","11111111111110111100","11111111111111111111","11111111111111111111","11111111111111111111"};
        char[][] grid = new char[abc.length][abc[0].length()];
        for (int i =0; i < abc.length; i++){
            grid[i] = abc[i].toCharArray();
        }
        S.numIslands(grid);
    }
}
