/**
 * Created by Xiang on 11/6/2016.
 */
import java.util.*;
public class subarraymin {
    class Presum implements Comparable<Presum>{
        int index;
        int val;
        Presum(int idx, int val){
            this.index = idx;
            this.val = val;
        }
        public int compareTo(Presum p){
            if (this.val < p.val) return -1;
            else if (this.val == p.val) return 0;
            else return 1;
        }
    }
    public int[] subarraySumClosest(int[] nums) {
        // write your code here
        if (nums == null || nums.length == 0) return new int[]{0,0};
        int[] res = new int[2];
        Presum[] presum = new Presum[nums.length + 1];
        int minval = Integer.MAX_VALUE;
        presum[0] = new Presum(0, 0);
        for (int i = 1; i <= nums.length; i ++){
            presum[i] = new Presum(i, presum[i-1].val + nums[i-1]);
        }
        Arrays.sort(presum);
        for (int i = 1; i <= nums.length; i ++){
            int diff = Math.abs(presum[i].val - presum[i-1].val);
            if (diff < minval){
                minval = Math.abs(presum[i].val - presum[i-1].val);
                res[0] = Math.min(presum[i].index, presum[i-1].index);
                res[1] = Math.max(presum[i].index, presum[i-1].index) - 1;
            }
        }
        return res;
    }
    public static void main(String[] args){
        subarraymin s = new subarraymin();
        s.subarraySumClosest(new int[]{-3,1,1,-3,5});
    }
}
