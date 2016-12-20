/**
 * Created by Xiang on 3/1/2016.
 */
import java.util.*;
public class Long_valid {
        public static int longestValidParentheses(String s) {
            if (s.length() < 2) return 0;
            int[] validlength = new int[s.length()];
            Stack<Integer> stack = new Stack<Integer>();
            if (s.charAt(s.length()-1) == ')') stack.push(s.length() - 1);
            for (int j=s.length()-2;j >=0; j--){
                if (s.charAt(j) == '('){
                    if (stack.size() > 0) {
                        int pos = stack.pop();
                        validlength[j] = validlength[j + 1] + 2 + validlength[Math.min(pos + 1, s.length() - 1)];
                    }else{
                        validlength[j] = 0;
                    }
                }else{
                    stack.push(j);
                    validlength[j] = 0;
                }
            }
            int max = 0;
            for (int i =0; i < s.length(); i ++){
                if (validlength[i] > max) max = validlength[i];
            }
            return max;
        }
    public static void main(String[] args){
        String s = "((())(())";
        System.out.println(longestValidParentheses(s));
    }
}
