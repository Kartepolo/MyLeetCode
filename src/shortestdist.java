/**
 * Created by Xiang on 9/24/2016.
 */

import java.io.*;
import java.util.*;
public class shortestdist {

    public static int[] getdist(HashMap<Integer,List<Integer>> graph, int start, int total){
        int[] res = new int[total];
        Arrays.fill(res, -1);
        res[start-1] = 0;
        boolean[] visited = new boolean[total];
        visited[start - 1] = true;
        PriorityQueue<Integer> q = new PriorityQueue<Integer>();
        q.add(start);
        int step = 1;
        while (q.size() > 0){
            int cur = q.poll();
            for (Integer l : graph.get(cur)) {
                if (!visited[l-1]){
                    res[l-1] = step * 6;
                    q.add(l);
                    visited[l-1] = true;
                }
            }
            step++;
        }
        return res;
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        shortestdist s = new shortestdist();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            int Queries = Integer.parseInt(br.readLine());
            for (int j = 0; j < Queries; j++) {
                HashMap<Integer, List<Integer>> graph = new HashMap<Integer, List<Integer>>();
                String[] config = br.readLine().split(" ");
                for (int i = 0; i < Integer.parseInt(config[1]); i++) {
                    String[] tmp = br.readLine().split(" ");
                    int tmp0 = Integer.parseInt(tmp[0]);
                    int tmp1 = Integer.parseInt(tmp[1]);
                    if (!graph.containsKey(tmp0)) graph.put(tmp0, new LinkedList<Integer>());
                    if (!graph.containsKey(tmp1)) graph.put(tmp1, new LinkedList<Integer>());
                    graph.get(tmp0).add(tmp1);
                    graph.get(tmp1).add(tmp0);
                }
                int start = Integer.parseInt(br.readLine());
                int[] res = getdist(graph, start, Integer.parseInt(config[0]));
                boolean flag = true;
                for (int i = 0; i < res.length; i++) {
                    if (i != start - 1){
                        if (flag) {
                            System.out.print(res[i]);
                            flag = false;
                        } else {
                            System.out.print(" " + res[i]);
                        }
                    }
                }
                System.out.println("");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
