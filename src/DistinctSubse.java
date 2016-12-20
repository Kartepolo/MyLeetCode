/**
 * Created by Xiang on 3/20/2016.
 */
import java.util.*;
public class DistinctSubse {
        private class Sub{
            int pos;
            int count;
            Sub(int pos, int count){
                this.pos = pos;
                this.count = count;
            }
        }
        HashMap<Character, List<Sub>> map = new HashMap<Character, List<Sub>>();
        public  int numDistinct(String s, String t) {
            HashSet<Character> c = new HashSet<Character>();
            for (int i =0; i < t.length(); i ++){
                c.add(t.charAt(i));
            }
            for (int j = 0; j < s. length(); j ++){
                Character tmp = s.charAt(j);
                if (c.contains(tmp)){
                    if (!map.containsKey(tmp)) map.put(tmp, new ArrayList<Sub>());
                    map.get(tmp).add(new Sub(j,1));
                }
            }
            //Construction of info is finished
            for (int i=t.length() - 1; i > 0; i--){
                subhelper(t.charAt(i), t.charAt(i - 1));
            }
            List<Sub> f = map.get(t.charAt(0));
            int total = 0;
            for (int i =0; i <f.size(); i++ ){
                total = total + f.get(i).count;
            }
            return total;
        }
        private void subhelper(Character c, Character p){
            List<Sub> cur = map.get(c);
            List<Sub> prev = map.get(p);
            int sum = 0;
            for (int i=0 ; i < cur.size(); i++){
                sum = sum + cur.get(i).count;
            }
            int j = 0;
            for (int i = 0; i < prev.size(); i ++){
                while (j < cur.size()){
                    if (cur.get(j).pos > prev.get(i).pos) {j ++;sum = sum - cur.get(j).count;}
                }
                if (j < cur.size()) prev.get(i).count = sum;
                else prev.get(i).count = 0;
            }
        }
    public static void main(String[] args){
        String s = "rabbbit";
        String t = "rabbit";
        DistinctSubse d = new DistinctSubse();
        d.numDistinct(s,t);
    }



}
