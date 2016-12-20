/**
 * Created by Xiang on 11/10/2016.
 */
import java.util.*;
public class countsmall {
    public class SegmentTreeNode {
        int start;
        int end;
        int max;
        int count;
        SegmentTreeNode left;
        SegmentTreeNode right;
        SegmentTreeNode(int start, int end, int max, int count){
            this.start = start;
            this.end = end;
            this.max = max;
            this.count = count;
        }
    }

    public class QueryTree{
        SegmentTreeNode root;
        QueryTree(int[] A){
            if (A == null || A.length == 0) root = null;
            else root = this.build(A, 0, A.length - 1);
        }
        SegmentTreeNode build(int[] A, int start, int end){
            if (start == end) return new SegmentTreeNode(start, end, A[start], 1);
            int mid = (start + end) / 2;
            SegmentTreeNode left = build(A, start, mid);
            SegmentTreeNode right = build(A, mid + 1, end);
            SegmentTreeNode cur = new SegmentTreeNode(start, end, Math.max(left.max, right.max), left.count + right.count);
            cur.left = left;
            cur.right = right;
            return cur;
        }
        int query(SegmentTreeNode cur, int val, int startidx){
            if (cur == null || cur.start >= startidx) return 0;
            if (cur.end < startidx && cur.max < val) return cur.count;
            return query(cur.left, val, startidx) + query(cur.right, val, startidx);
        }
    }
    public ArrayList<Integer> countOfSmallerNumberII(int[] A) {
        // write your code here
        QueryTree qt = new QueryTree(A);
        ArrayList<Integer> res = new ArrayList<Integer>();
        for (int i = 1; i < A.length; i ++){
            if (A[i] > A[i-1]) res.add(res.get(i - 1) + 1);
            else if (A[i] == A[i-1]) res.add(res.get(i-1));
            else res.add(qt.query(qt.root, A[i], i));
        }
        return res;
    }
    public static void main(String[] args){
        countsmall c = new countsmall();
        c.countOfSmallerNumberII(new int[]{1,2,7,8,5});
    }
}
