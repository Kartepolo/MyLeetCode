/**
 * Created by Xiang on 11/1/2016.
 */
import java.util.*;
public class pathparser {
    public int longestpath(String s){
        String[] lines = s.split("/n");
        int maxlength = 0;
        int len = 0;
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < lines.length; i++){
            String cur = lines[i];
            int spaceNum = 0;
            for (int j = 0;j < cur.length(); j++){
                if (cur.charAt(j) == ' ') spaceNum ++;
                else break;
            }
            String filename = cur.substring(spaceNum);
            if (stack.size() == spaceNum){
                if (cur.contains(".jpeg")||cur.contains(".png")||cur.contains(".gif")){
                    stack.push(filename);
                    len += filename.length() + 1;
                    maxlength = Math.max(maxlength, len);
                    if (len == maxlength){
                        for(String ss: stack){
                            System.out.print("\\"+ss);
                        }
                        System.out.println();
                    }
                }else{
                    len += filename.length() + 1;
                    stack.push(filename);
                }
                i++;
            }else if (spaceNum < stack.size()){
                while (spaceNum < stack.size()){
                    String tmp = stack.pop();
                    len -= tmp.length() + 1;
                }
            }
        }
        return maxlength;
    }
}
