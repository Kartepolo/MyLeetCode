/**
 * Created by Xiang on 11/12/2016.
 */
import java.util.*;
public class sortcolor {
    public void sortColors(int[] nums) {
        // write your code here
        int pos1 = 0;
        int pos2 = nums.length - 1;
        int i = 0;
        while (i < pos2){
            if (nums[i] == 0){
                swap(nums, pos1, i);
                pos1 ++;
            }
            if (nums[i] == 2){
                swap(nums, pos2, i);
                pos2--;
            }
            if (nums[i] == 1) i++;
        }
    }
    public boolean validTree(int n, int[][] edges) {
        // Write your code here
        if (n <= 1) return true;
        if (edges == null || edges.length == 0) return false;
        Set<Integer> visit = new HashSet<Integer>();
        List<List<Integer>> m = new ArrayList<List<Integer>>();

        for (int i = 0; i < n; i++){
            m.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < edges.length; i ++){
            m.get(edges[i][0]).add(edges[i][1]);
            m.get(edges[i][1]).add(edges[i][0]);
        }
        if (!dfs(visit, m, 0, -1)) return false;
        //last step. Check if
        return visit.size() == n;

    }
    private boolean dfs(Set<Integer> visit, List<List<Integer>> graph, int cur, int prev){
        if (visit.contains(cur)) return false;
        visit.add(cur);
        for (Integer neigh : graph.get(cur)){
            if (!neigh.equals(prev) && !dfs(visit, graph, neigh, cur)) return false;
        }
        return true;
    }
    private void swap(int[] nums, int idx1, int idx2){
        int tmp = nums[idx1];
        nums[idx1] = nums[idx2];
        nums[idx2] = tmp;
    }
    public static void main(String[] args){
        sortcolor sc = new sortcolor();
        sc.sortColors(new int[]{2,0,0,1,2,0,2});
        sc.validTree(5, new int[][]{{0,1},{0,2},{0,3},{1,4}});
    }
}
