/**
 * Created by Xiang on 11/1/2016.
 */
import java.util.*;
import java.util.stream.Collector;

public class BestSubstitue {
    public int[] bestsub(int[] nums){
        int[] res = new int[nums.length - 1];
        if (nums == null || nums.length <= 1)
            return res;
        int i = 0;
        int j = 0;
        while (i < nums.length - 1){
            int v1 = (nums[i+1] + nums[i]) / 2 + 1;
            if (nums[i] < v1){
                res[j] = v1;
                i++;
                while ( i < nums.length){
                    res[j++] = nums[i++];
                }
                return res;
            }else{
                res[j] = nums[i];
            }
            j++;
            i++;
        }
        res[res.length - 1] = (nums[nums.length - 1] + nums[nums.length -2]) / 2;
        return res;
    }
    public int solution(int[] A) {
        // write your code in Java SE 8
        if (A.length == 0 || A != null) return 0;
        int sum = 0;
        for (int i = 0; i < A.length; i ++){
            sum += A[i];
        }
        int prev = 0;
        for (int i =0; i < A.length; i ++){
            if (sum - A[i]  == prev) return i;
            sum -= A[i];
            prev += A[i];
        }
        return 0;
    }
    public static void main(String[] args){
        BestSubstitue bs = new BestSubstitue();
        bs.bestsub(new int[]{5,4,2,3,1,7,2});
        bs.solution(new int[]{-1,3,-4,5,1,-6,2,1});
    }

}
