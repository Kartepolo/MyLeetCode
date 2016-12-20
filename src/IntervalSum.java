/**
 * Created by Xiang on 11/6/2016.
 */
import java.util.*;
public class IntervalSum {
    public class SegmentTreeNode {
        public int start, end, sum;
        public SegmentTreeNode left, right;
        public SegmentTreeNode(int start, int end, int sum) {
            this.start = start;
            this.end = end;
            this.sum = sum;
            this.left = this.right = null;
        }
    }
    private SegmentTreeNode root;

    /**
     * @param A: An integer array
     */
    public IntervalSum(int[] A) {
        // write your code here
        if (A == null || A.length == 0) root = null;
        root = this.build(A, 0, A.length - 1);
    }

    private SegmentTreeNode build(int[] A, int start, int end){
        SegmentTreeNode cur = new SegmentTreeNode(start, end,A[start]);
        if (start < end){
            int mid = (start + end) / 2;
            cur.left = build(A, start, mid);
            cur.right = build(A, mid + 1, end);
            cur.sum = cur.left.sum + cur.right.sum;
        }
        return cur;
    }

    private int queryhelper(SegmentTreeNode root, int start, int end) {
        // write your code here
        if (root == null) return 0;
        if ((start <= root.start && end >= root.end)) return root.sum;
        int mid = (root.start + root.end) / 2;
        if (end <= mid) return queryhelper(root.left, start, end);
        if (start >= mid + 1) return queryhelper(root.right, start, end);
        return queryhelper(root.left, start, mid) + queryhelper(root.right, mid + 1, end);
    }

    /**
     * @param start, end: Indices
     * @return: The sum from start to end
     */
    public long query(int start, int end) {
        // write your code here
        return this.queryhelper(root, start, end);
    }

    /**
     * @param index, value: modify A[index] to value.
     */
    public void modify(int index, int value) {
        // write your code here
        if (root == null || index < root.start || index > root.end) return;
        Stack<SegmentTreeNode> s = new Stack<SegmentTreeNode>();
        SegmentTreeNode tmp = root;
        while (tmp != null && tmp.start < tmp.end){
            s.push(tmp);
            int mid = (tmp.start + tmp.end) / 2;
            if (index > mid) tmp = tmp.right;
            else tmp = tmp.left;
        }
        //Recalculate
        int oldval = tmp.sum;
        tmp.sum = value;
        while (s.size() > 0){
            tmp = s.pop();
            tmp.sum = tmp.sum - oldval + value;
        }
    }
    public static void main(String[] args){
        IntervalSum is = new IntervalSum(new int[]{1,2,7,8,5});
        is.modify(0,4);
    }
}
