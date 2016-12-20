

/**
 * Created by Xiang on 2/27/2016.
 */
public class Subarray {
    public static int maxSubArray(int[] nums) {
        if (nums.length == 0) return Integer.MIN_VALUE;
        return maxhelper(nums, 0, nums.length - 1)[2];

    }
    private static int[] maxhelper(int[] nums, int start, int end){
        if (start == end) return new int[]{start,end,nums[start]};
        if (start == end -1) {
            int tmp = 0;
            if (nums[start] > 0) {start = start; tmp = tmp + nums[start];}
            else start = end;
            if (nums[end] > 0) {end = end; tmp = tmp + nums[end];}
            else end = start;
            return new int[]{start,end, tmp};
        }
        int mid = start + (end - start) / 2;
        int sub1 = 0, sub2 = 0;
        int[] left = maxhelper(nums, start, mid - 1);
        int[] right = maxhelper(nums, mid + 1 , end);
        if (nums[mid] > 0) {
            for (int i=left[1] + 1; i <= mid; i ++){
                sub1 = sub1 + nums[i];
            }
            for (int i = mid; i < right[0]; i ++){
                sub2 = sub2 + nums[i];
            }
            if (sub1 > 0){
                if (left[2] > 0) {left[2] = left[2] + sub1;left[1] = mid;}
                else left = new int[]{mid,mid,nums[mid]};
            }
            if (sub2 > 0){
                if (right[2] > 0) {right[2] = right[2] + sub2;right[0] = mid;}
                else right = new int[] {mid,mid, nums[mid]};
            }
            if (sub1 > 0 && sub2 > 0) return new int[]{left[0], right[1], left[2]+right[2] - nums[mid]};
        }
        return left[2] > right[2]? left : right;
    }
    public static void main(String[] args){
        int[] array = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArray(array));
    }
}
