/**
 * Created by Xiang on 10/24/2016.
 */
import java.util.*;
public class minimumCover {
    public String minWindow(String source, String target) {
        // write your code
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        HashSet<Character> validchars = new HashSet<Character>();
        Queue<Integer> q = new LinkedList<>();
        char[] sourcearray = source.toCharArray();
        String res = "";
        for (int i = 0; i < target.length(); i ++){
            validchars.add(target.charAt(i));
        }
        for (int i = 0; i < source.length(); i ++){
            char cur = sourcearray[i];
            if (validchars.contains(cur)){
                if (!q.isEmpty() && sourcearray[q.peek()] == cur) q.poll();
                map.put(cur, i);
                System.out.println(q.add(i));
                if (map.size() == target.length()){
                    if (res.equals("") || res.length() > i - q.peek()){
                        res = source.substring(q.peek(), i + 1);
                        map.remove(sourcearray[q.poll()]);
                    }
                }
            }
        }
        return res;
    }

    int computeFactorial(int number) {
        int res = 1;
        while (number > 0){
            res *= number;
            number -= 1;
        }
        return res;
    }
    public static void main(String[] args){
        minimumCover m = new minimumCover();
        int v = m.computeFactorial(21);
        int k = 2 * Integer.MAX_VALUE + 1;
        System.out.println(v+","+k);
    }
}
