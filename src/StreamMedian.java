/**
 * Created by Xiang on 10/23/2016.
 */
import java.util.*;
public class StreamMedian {
    public int[] medianII(int[] nums) {
        // write your code here
        List<Integer> res = new ArrayList<Integer>();
        PriorityQueue<Integer> minheap = new PriorityQueue<>();
        PriorityQueue<Integer> maxheap = new PriorityQueue<>();
        if (nums.length == 0) return null;
        res.add(nums[0]);
        maxheap.add(-nums[0]);
        for (int i = 1; i < nums.length; i++){
            if (nums[i] > -maxheap.peek()){
                minheap.add(nums[i]);
            }else{
                maxheap.add(-nums[i]);
            }
            if (maxheap.size() > minheap.size() + 1){
                minheap.add(-maxheap.poll());
            }
            if (maxheap.size() < minheap.size()){
                maxheap.add(-minheap.poll());
            }
            res.add(-maxheap.peek());
        }
        int[] b = new int[res.size()];
        for (int i = 0; i < res.size(); i++){
            b[i] = res.get(i);
        }
        return b;
    }
    public static void main(String[] args){
        int[] a = {4,5,1,3,2,6,0};
        StreamMedian s = new StreamMedian();
        s.medianII(a);
    }
}
