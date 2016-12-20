/**
 * Created by Xiang on 11/4/2016.
 */

import java.util.*;
public class scramble {
    int[][][] flag;
    boolean[][][] dp;
    public boolean isScramble(String s1, String s2) {
        // Write your code here
        if (s1 == null || s2 == null || s1.length() !=s2.length()) return false;
        flag = new int[s1.length()][s2.length()][s2.length() + 1];
        dp = new boolean[s1.length()][s2.length()][s2.length() + 1];
        helper(s1, s2, 0,0, s1.length());
        return dp[0][0][s1.length()];

    }
    public boolean helper(String s1, String s2, int i, int j, int k){
        if (flag[i][j][k] == 1) return dp[i][j][k];
        flag[i][j][k] = 1;
        if (k == 1){
            dp[i][j][1] = s1.charAt(i) == s2.charAt(j);
            return dp[i][j][1];
        }
        for (int pos = 1; pos < k; pos ++){
/*        String s1left = s1.substring(0, pos);
        String s1right = s1.substring(pos, s1.length());
        String s2left = s2.substring(0, pos);
        String s2right = s2.substring(pos, s2.length());
        String s2counter_r = s2.substring(0,s2.length() - pos);
        String s2counter_l = s2.substring(s2.length() - pos, s2.length());*/
            boolean sameside_l = this.helper(s1, s2, i, j, pos);
            boolean sameside_r = this.helper(s1, s2, i + pos, j + pos, k - pos);
            boolean switch_l = this.helper(s1, s2, i, j + k - pos, pos);
            boolean switch_r = this.helper(s1, s2, i + pos, j, k - pos);
            dp[i][j][k] = dp[i][j][k] || (sameside_l && sameside_r) || (switch_l && switch_r);
            if (dp[i][j][k]) break;
        }
        return dp[i][j][k];
    }
    public static void main(String[] args){
        scramble s = new scramble();
        System.out.println(s.isScramble("grtae","rgeat"));
    }
}
