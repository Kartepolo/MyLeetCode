/**
 * Created by Xiang on 9/30/2016.
 */
import java.util.*;
public class minHeight {

    public class Node implements Comparable<Node>{
        int val;
        Set<Integer> neighbors;

        Node(int val, Set<Integer> n){
            this.val = val;
            this.neighbors = n;
        }

        public int compareTo(Node n){
            if (this.neighbors.size() < n.neighbors.size()) return -1;
            else if (this.neighbors.size() == n.neighbors.size()) return 0;
            else return 1;
        }
    }

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> res = new ArrayList<Integer>();
        if (n <= 0) return res;
        List<Set<Integer>> m = new ArrayList<Set<Integer>>();
        for (int i = 0; i < n; i++) m.add(new HashSet<Integer>());
        for (int i = 0; i < edges.length; i ++){
            int n1 = edges[i][0];
            int n2 = edges[i][1];
            m.get(n1).add(n2);
            m.get(n2).add(n1);
        }
        int count = n;
        while (count > 2){
            count -= res.size();
            res.clear();
            for (int i = 0; i < n; i++){
                if (m.get(i).size() == 1){
                    res.add(i);
                }
            }
            for(Integer i : res){
                Set<Integer> curneighbors = m.get(i);
                for (Integer j : curneighbors) {
                    m.get(j).remove(i);
                    m.get(i).remove(j);
                }
            }
        }
        return res;
    }
    public static void main(String[] args){
        minHeight S = new minHeight();
        int[][] edges = {{0,1},{1,2},{1,3}};
        S.findMinHeightTrees(4,edges);
    }
}
