/**
 * Created by Xiang on 11/16/2016.
 */
import java.util.*;
public class ZigZagIterator2 {

    private class Pair{
        int val;
        int pos;
        Pair(int v, int p){
            this.val = v;
            this.pos = p;
        }
    }
    public class ZigzagIterator2 {
        /**
         * @param vecs a list of 1d vectors
         */
        List<Iterator<Integer>> Iterators;
        int cnt;
        Stack<Pair> history;
        Set<Integer> popped;

        public ZigzagIterator2(ArrayList<ArrayList<Integer>> vecs) {
            // initialize your data structure here.
            Iterators = new ArrayList<Iterator<Integer>>();
            for (ArrayList<Integer> vec : vecs){
                if (vec.size() > 0) Iterators.add(vec.iterator());
            }
            cnt = 0;
            popped = new HashSet<Integer>();
        }
        public void rewind(){
            Pair p = history.pop();
            if (popped.contains(p.pos)){
                List<Integer> newlist = new ArrayList<Integer>();
                newlist.add(p.val);
                Iterators.add(p.pos, newlist.iterator());
                popped.remove(p.pos);
            }
        }
        public int next() {
            // Write your code here
            while (popped.contains(cnt)){
                cnt = cnt++ % Iterators.size();
            }
            int elem = Iterators.get(cnt).next();
            history.push(new Pair(elem, cnt));
            if (Iterators.get(cnt).hasNext()){
                cnt = (cnt + 1) % Iterators.size();
            }else{
                popped.add(cnt);
                if (Iterators.size() > popped.size()){
                    cnt = cnt % Iterators.size();
                }
            }
            return elem;
        }

        public boolean hasNext() {
            // Write your code here
            return popped.size() == Iterators.size();
        }
    }
}
