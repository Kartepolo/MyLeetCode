/**
 * Created by Xiang on 11/12/2016.
 */
public class copybooks {
    public int copyBooks(int[] pages, int k) {
        // write your code here
        int n = pages.length;
        int[][] dp = new int[n+1][k+1];
        int[] presum = new int[n+1];
        for (int i = 1;i <= n; i ++){
            presum[i] = presum[i-1] + pages[i-1];
            dp[i][1] = presum[i];
            for (int j = 2; j <= k; j ++){
                dp[i][j] = Integer.MAX_VALUE;
                for (int p = 0; p < i; p ++){
                    int tmp = Math.max(dp[p][j-1], presum[i] - presum[p]);
                    dp[i][j] = Math.min(dp[i][j], tmp);
                }
            }
        }
        return dp[n][k];
    }
    public static void main(String[] args){
        copybooks cp = new copybooks();
        cp.copyBooks(new int[]{3,2,4}, 2);
    }
}
