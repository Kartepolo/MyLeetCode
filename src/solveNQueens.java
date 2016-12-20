/**
 * Created by Xiang on 7/30/2016.
 */
import java.util.*;
public class solveNQueens {
    private int[] Xaxis;
    private int[] Yaxis;
    private boolean[] solution;
    private List<List<String>> result;
    public List<List<String>> solveNQueens(int n) {
        this.result = new ArrayList<List<String>>();
        this.Xaxis = new int[n];
        this.Yaxis = new int[n];
        for (int i=0; i < n; i ++){
            this.Xaxis[i] = n;
            this.Yaxis[i] = n;
        }
        this.solution = new boolean[n * n];
        helper(n, 0);
        return this.result;
    }

    private void helper(int n, int cur){
        if (cur == n){
            //Write the result
            List<String> tmp = new ArrayList<String>();
            StringBuilder sb = new StringBuilder(n);
            for (int i=0; i < n*n; i ++){
                if (this.solution[i]) sb.append("Q");
                else sb.append(".");
                if (i % n == n - 1){
                    tmp.add(sb.toString());
                    sb.setLength(0);
                }
            }
            this.result.add(tmp);
        }else{
            for (int j = 0; j <n; j ++){
                if (isvalid(cur, j, n)){
                    this.Xaxis[cur] = j;
                    this.Yaxis[j] = cur;
                    this.solution[cur * n + j] = true;
                    helper(n, cur + 1);
                    this.solution[cur * n + j] = false;
                    this.Xaxis[cur] = n;
                    this.Yaxis[j] = n;
                }
            }
        }
        return;
    }
    private boolean isvalid(int xloc, int yloc, int n){
        if (this.Xaxis[xloc] <n || this.Yaxis[yloc] < n) return false;
        for (int i =0; i < n; i++){
            if (this.Xaxis[i] != n && Math.abs(xloc - i) == Math.abs(yloc - this.Xaxis[i])) return false;
        }
        return true;
    }
}
