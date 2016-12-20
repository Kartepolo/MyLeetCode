/**
 * Created by Xiang on 7/17/2016.
 */
import java.util.*;
public class partition {
    public List<List<String>> partition(String s) {
        if (s == null) return new ArrayList<List<String>>();
        else return helper(s, 0, s.length() - 1);
    }
    private List<List<String>> helper(String s, int startpos, int endpos){
        List<List<String>> res = new ArrayList<List<String>>();
        if (startpos > endpos) return res;
        if (isvalid(s, startpos, endpos)){
            List<String> tmp = new ArrayList<String>();
            tmp.add(s.substring(startpos, endpos + 1));
            res.add(tmp);
            if (startpos == endpos) return res;
        }
        for (int i = startpos; i < endpos; i ++){
            List<List<String>> nextpartition = helper(s, i + 1, endpos);
            List<List<String>> prevpartition = helper(s, startpos, i);
            for (List<String> pl : prevpartition){
                for (List<String> l : nextpartition){
                    pl.addAll(l);
                    res.add(pl);
                }
            }
        }
        return res;
    }
    private boolean isvalid(String s, int start, int end){
        boolean res = true;
        int mid = (start + end) / 2;
        for (int i = start; i <= mid; i++){
            if (s.charAt(i) != s.charAt(end- i + start)) return false;
        }
        return res;
    }
    public static void main(String[] args){
        partition c = new partition();
        List<List<String>> i = c.partition("aab");
        System.out.println(i);
    }
}
