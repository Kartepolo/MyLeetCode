import java.util.*;


/**
 * Created by Xiang on 9/30/2016.
 */

public class minimumDepth {


    public int minDepth(TreeNode root) {
        int res = 0;
        int level;
        if (root == null) return res;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (q.size() > 0) {
            level = q.size();
            int i = 0;
            res += 1;
            while (i < level) {
                TreeNode cur = q.poll();
                if (cur.left == null && cur.right == null){
                    return res;
                }
                if (cur.left != null){
                    q.add(cur.left);
                }
                if (cur.right != null){
                    q.add(cur.right);
                }
                i += 1;
            }
        }
        return res;

    }
    public static void main(String[] args){
        minimumDepth S = new minimumDepth();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        S.minDepth(root);
    }
}
