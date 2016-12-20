/**
 * Created by Xiang on 3/2/2016.
 */
import java.util.*;
class MedianFinder {
    private PriorityQueue<Integer> h1 = new PriorityQueue<Integer>();
    private PriorityQueue<Integer> h2 = new PriorityQueue<Integer>(new Comparator() {
        @Override
        public int compare(Object o1, Object o2) {
            int i1 = (int) o1;
            int i2 = (int) o2;
            return i1 >= i2 ? -1 : 1;
        }
    });

    // Adds a number into the data structure.
    public void addNum(int num) {
        if (h1.size() == h2.size()){
            if (h1.peek() > num) {
                h2.add(h1.poll());
            }
        }
    }

    // Returns the median of current data stream
    public double findMedian() {
        if (h1.size() > h2.size()) return h1.peek();
        else if (h1.size() < h2.size()) return h2.peek();
        else return (double)((h1.peek() + h2.peek()) / 2.0);
    }
    public static void main(String[] args){
        MedianFinder mf = new MedianFinder();
        Set<Integer> s = new HashSet<>();
        mf.addNum(1);
        mf.addNum(2);
        mf.addNum(3);
        mf.addNum(4);
    }
}
