/**
 * Created by Xiang on 11/4/2016.
 */
public class lis2d {
    boolean[][] flag;
    public int longestIncreasingContinuousSubsequenceII(int[][] A) {
        // Write your code here
        if (A == null || A.length == 0) return 0;
        int[][] dp = new int[A.length][A[0].length];
        flag = new boolean[A.length][A[0].length];
        int maxl = 0;
        for (int i =0; i < A.length; i ++){
            for (int j = 0; j < A[0].length; j ++){
                maxl = Math.max(this.search(i, j, A, dp), maxl);
            }
        }
        return maxl;
    }
    public int search(int x, int y, int[][] A, int[][] dp){
        if (flag[x][y]) return dp[x][y];
        int m = A.length;
        int n = A[0].length;
        int[] dx = {-1,0,1,0};
        int[] dy = {0,-1,0,1};
        dp[x][y] = 1;
        flag[x][y] = true;
        for (int i =0; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >=0 && nx < m && ny >=0 && ny < n){
                if (A[nx][ny] <= A[x][y]) dp[x][y] = Math.max(dp[x][y], 1 + this.search(nx, ny, A, dp));
            }
        }
        return dp[x][y];
    }

}
