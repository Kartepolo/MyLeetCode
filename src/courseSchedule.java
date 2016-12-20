/**
 * Created by Xiang on 10/2/2016.
 * BFS : Check if all nodes can be visited via a level-by-level peeling.
 * DFS : Check if starting from any unvisited nodes, you can find a cycle in the graph
 * Union Find : Same idea as above for detecting cycles.
 */
import java.util.*;
public class courseSchedule {

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Queue<Integer> q = new LinkedList<Integer>();
        List<Integer> res = new ArrayList<>();
        Map<Integer, Set<Integer>> Graph = new HashMap<Integer, Set<Integer>>();
        int visited_nodes = 0;
        int[] in_edges = new int[numCourses];
        for (int i = 0; i < numCourses; i ++) Graph.put(i, new HashSet<Integer>());
        for (int i = 0; i < prerequisites.length; i++){
            Set<Integer> current = Graph.get(prerequisites[i][1]);
            if (!current.contains(prerequisites[i][0])){
                in_edges[prerequisites[i][0]] += 1;
                current.add(prerequisites[i][0]);
            }
        }
        // Adding the initial nodes
        for (int i =0; i < numCourses; i++){
            if (in_edges[i] == 0) {
                q.add(i);
                res.add(i);
                in_edges[i] = -1;
            }
        }
        while (q.size() > 0){
            int level = q.size();
            while (level > 0){
                int cur = q.poll();
                for (Integer i : Graph.get(cur)) {
                    if (in_edges[i] > 0) in_edges[i] -= 1;
                    if (in_edges[i] == 0){
                        q.add(i);
                        res.add(i);
                        in_edges[i] = -1;
                    }
                }
                level -= 1;
            }
        }
        int[] resarr = new int[numCourses];
        if (res.size() == numCourses){
            for(int i = 0;i < numCourses;i++) resarr[i] = res.get(i);
        }
        return resarr;
    }

}
