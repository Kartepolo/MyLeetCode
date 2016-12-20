/**
 * Created by Xiang on 11/11/2016.
 */
import java.util.*;
public class expressionTree {
    public class ExpressionTreeNode {
             public String symbol;
             public ExpressionTreeNode left, right;
             public ExpressionTreeNode(String symbol) {
                     this.symbol = symbol;
                     this.left = this.right = null;
                 }
         }
    Stack<String> operand;
    Stack<ExpressionTreeNode> nodes;
    public ExpressionTreeNode build(String[] expression) {
        // write your code here
        if (expression == null || expression.length == 0) return null;
        operand  = new Stack<String>();;
        nodes = new Stack<>();
        HashMap<String, Integer> m = new HashMap<String, Integer>();
        m.put("*", 1);
        m.put("/", 1);
        m.put("+", 0);
        m.put("-", 0);
        m.put(")", 2);
        m.put("(", 2);
        for (int i =0; i < expression.length; i++){
            String cur = expression[i];
            if (!m.containsKey(cur)){
                nodes.push(new ExpressionTreeNode(cur));
            }else{
                if (cur.equals("(") || cur.equals(")")){
                    if (cur.equals("(")) operand.push(cur);
                    else{
                        while (!operand.empty() && operand.peek() != "(") create();
                        if (!operand.empty()) operand.pop();
                    }
                }else{
                    if (!operand.empty() && !operand.peek().equals("(") && m.get(cur) < m.get(operand.peek())) create();
                    operand.push(cur);
                }
            }
        }
        while (!operand.empty()){
            create();
        }

        return nodes.pop();
    }
    private void create(){
        String tmp = operand.pop();
        ExpressionTreeNode n2 = nodes.pop();
        ExpressionTreeNode n1 = nodes.pop();
        ExpressionTreeNode newnode = new ExpressionTreeNode(tmp);
        newnode.left = n1;
        newnode.right = n2;
        nodes.push(newnode);
    }
    public static void main(String[] args){
        expressionTree c = new expressionTree();
        c.build(new String[]{"2","*","6","-","(","23","+","7",")","/","(","1","+","2",")"});
    }
}
