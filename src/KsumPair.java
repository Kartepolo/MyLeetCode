/**
 * Created by Xiang on 9/27/2016.
 */
import java.util.*;
public class KsumPair {

        private class Node implements Comparable<Node>{

            private int val;
            private int idx1;
            private int idx2;

            Node(int val, int idx1, int idx2){
                this.val = val;
                this.idx1 = idx1;
                this.idx2 = idx2;
            }

            @Override
            public int compareTo(Node n) {
                if (this.val < n.val) return -1;
                else if (this.val == n.val) return 0;
                else return 1;
            }
        }
        public int kthSmallestSum(int[] A, int[] B, int k) {
            // Write your code here
            int l1 = A.length;
            int l2 = B.length;
            if (l1 == 0 || l2 == 0) return 0;
            int res;
            PriorityQueue<Node> q = new PriorityQueue<Node>();
            for (int i =0; i < l1; i++) q.add(new Node(A[i] + B[0], i, 0));
            int i = 1;
            while (i < k && q.size() > 0) {
                Node cur = q.poll();
                System.out.println(cur.val);
                if (cur.idx2 < l2 - 1){
                    Node n = new Node(A[cur.idx1] + B[cur.idx2 + 1], cur.idx1, cur.idx2 + 1);
//                    if (q.size() == 0 || q.peek().compareTo(n) >= 0) {
                    q.add(n);
//                    }
                }
                i += 1;
            }
            if (q.size() > 0) return q.peek().val;
            else return 0;
        }
    public static void main(String[] args) {
        KsumPair k = new KsumPair();
        int[] A = {1,7,11};
        int[] B = {2,4,6};
        System.out.println(k.kthSmallestSum(A, B, 8));
    }
}
