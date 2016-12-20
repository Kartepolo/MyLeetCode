/**
 * Created by Xiang on 2/25/2016.
 */
import javax.print.attribute.IntegerSyntax;
import java.util.*;
import java.util.stream.Collector;
import java.lang.Math;
import static java.lang.Math.toIntExact;
public class MinimumWindow {
    public static String minWindow(String s, String t) {
        int[] t_c = new int[128];
        int count = t.length();
        if(s.length()<count)return "";
        for(int i = 0; i< count; i++){t_c[t.charAt(i)]++;}  // Constructing arrays
        int start = 0, end = 0;
        String res = "";
        boolean isStart = false;//whether move start pointer or not
        while(end<s.length()){
            if(!isStart){
                if(t_c[s.charAt(end)] > 0)count--;
                t_c[s.charAt(end)]--;
            }
            if(count == 0) {
                if(res==""||end-start+1<res.length())
                    res = s.substring(start,end+1);
                if(res.length()==t.length())return res;
            }
            if(t_c[s.charAt(start)] < 0 && count == 0){
                t_c[s.charAt(start)]++;
                if(t_c[s.charAt(start)]>0)count++;
                start++;
                isStart = true;
            }
            else{
                isStart = false;
                end++;
            }
        }
        return res;
    }

    private static int[] findmin(Hashtable<Character, List> table, HashMap<Character, Integer> counter, int pos, int low, int high){
        int[] result = new int[2];
        int pos1, pos2;
        List<Character> keys = new ArrayList<Character>(counter.keySet());
        if (pos == keys.size()){
            result[0] = low;
            result[1] = high;
            return result;
        }
        int min = Integer.MAX_VALUE;
        Character key = keys.get(pos);
        List<Integer> cur = table.get(key);
        int count = counter.get(key);
        pos1 = low;
        pos2 = high;
        for (int i=0; i + count < cur.size(); i ++){
            if (cur.get(i + count) < high&& cur.get(i) > low) {
                return findmin(table, counter, pos + 1, low, high);
            }
            int news = Math.max(cur.get(i + count), high) - Math.min(cur.get(i), low);
            if (news < min){
                min = news;
                pos1 = Math.min(cur.get(i), low);
                pos2 = Math.max(cur.get(i + count), high);
            }
        }
        return findmin(table, counter, pos + 1, pos1, pos2);
    }


    public static void main(String[] args){
        int n = 7;
        long m = -12;
        System.out.println(fractionToDecimal(n,toIntExact(m)));
    }
    public static String fractionToDecimal(int numerator, int denominator) {
        long num = (long) numerator;
        long den = (long) denominator;
        boolean flag = false;
        if (num * den < 0) {
            num = Math.abs(num);
            den = Math.abs(den);
            flag = true;
        }
        StringBuilder result = new StringBuilder("");
        HashMap<Long, Integer> occ = new HashMap<Long,Integer>();
        if (den == 0) return 0+"";
        long f = num / den;
        long left = num % den;
        result.append(f+"");
        if (left == 0) {
            if (flag){
                result.insert(0,"-");
            }
            return result.toString();
        }
        occ.put(left, 0);
        boolean boom = true;
        StringBuilder decimal = new StringBuilder("");
        int count = 1;
        int traces = 0;
        while (left * 10 % den != 0 && boom){
            f = 10 * left / den;
            left = 10 * left % den;
            decimal.append(f);
            if (occ.containsKey(left)) boom = false;
            else occ.put(left, count);
            count = count + 1;
        }
        if (boom == false){
            decimal.insert(occ.get(left)+traces, "(");
            decimal.append(')');
        }else{
            decimal.append(left * 10 / denominator);
        }
        result.append("."+decimal);
        if (flag){
            result.insert(0,"-");
        }
        return result.toString();
    }
}
