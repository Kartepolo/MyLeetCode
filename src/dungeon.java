import java.util.HashMap;
import java.util.Map;
import java.util.*;
/**
 * Created by Xiang on 9/25/2016.
 */
public class dungeon {
    public int calculateMinimumHP(int[][] dungeon) {
        int start = 1;
        int end = 10;
        while (!cansave(end, dungeon)) end *= 2;
        while (start < end){
            int mid = (start + end) / 2;
            if (cansave(mid, dungeon)) end = mid;
            else start = mid + 1;
        }
        return start;
    }

    private boolean cansave(int inithealth, int[][] dungeon){
        int m = dungeon.length;
        int n = dungeon[0].length;
        int[][] remain = new int[m][n];
        remain[0][0] = inithealth + dungeon[0][0];
        for (int i = 1; i < m; i++){
            if (remain[i-1][0] <= 0) remain[i][0] = 0;
            else remain[i][0] = remain[i-1][0] + dungeon[i][0];
        }
        for (int j = 1; j < n; j++){
            if (remain[0][j-1] <= 0) remain[0][j] = 0;
            remain[0][j] = remain[0][j-1] + dungeon[0][j];
        }
        for (int i = 1; i < m; i ++){
            for (int j = 1; j < n; j ++){
                int c1 = 0;
                int c2 = 0;
                if (remain[i-1][j] > 0) c1 = remain[i-1][j] + dungeon[i][j];
                if (remain[i][j-1] > 0) c2 = remain[i][j-1] + dungeon[i][j];
                remain[i][j] = Math.max(c1, c2);
            }
        }
        return remain[m-1][n-1] > 0;
    }
    public static void main(String[] args) {
        dungeon d = new dungeon();
        int[][] dd = {{-3,5}};
        System.out.println(d.cansave(1,dd));
        System.out.println(d.cansave(10,dd));
        System.out.println(d.cansave(3,dd));
        d.calculateMinimumHP(dd);
    }
}
