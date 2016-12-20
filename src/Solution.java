import java.util.ArrayList;
import java.util.List;

/**
 * Created by Xiang on 9/30/2016.
 */

class TreeNode {
    	int val;
    	TreeNode left;
    	TreeNode right;
    TreeNode(int x) { val = x; }
}
public class Solution {
    private List<TreeNode> list = new ArrayList<>();
    private TreeNode head = new TreeNode(0);

    public List<TreeNode> generateTrees(int n) {
        help(0,n,n,this.head);
        return this.list;
    }

    void help(int start, int end, int count, TreeNode parent){
        count--;
        for(int i=start;i<end;i++){
            parent.val = i+1;
            System.out.println(i+1+":"+count+"--"+list.size());
            if(i!=start){
                parent.left = new TreeNode(0);
                help(start,i,count,parent.left);
            }
            if(i+1!=end){
                parent.right = new TreeNode(0);
                help(i+1,end,count,parent.right);
            }
            if(count==0) {
                list.add(clone(this.head));
            }
            System.out.println(i+1+":"+count+"---"+list.size());
        }
    }

    TreeNode clone(TreeNode head){
        if(head == null) return null;
        TreeNode node = new TreeNode(head.val);
        node.left = clone(head.left);
        node.right = clone(head.right);
        return node;
    }

    public static void main(String[] arg){
        Solution a = new Solution();
        a.generateTrees(2);
    }
}

