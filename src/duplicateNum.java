/**
 * Created by Xiang on 11/1/2016.
 */
public class duplicateNum {
    public int[] duplicate(int[] nums){
        int[] res = new int[nums.length + 1];
        if(nums == null || nums.length == 0) return new int[0];
        if (nums.length == 1) return new int[]{nums[0], nums[0]};
        int i = 0;
        int j = 0;
        while ( i < nums.length - 1){
            res[j] = nums[i];
            j++;
            if (nums[i] > nums[i+1]) {
                res[j] = nums[i];
                j++;
                i++;
                while (j < res.length) {
                    res[j] = nums[i];
                    j++;
                    i++;
                }
                return res;
            }
        }
        res[j] = nums[i];
        res[j+1] = nums[i];
        return res;
    }
    public static void main(String[] args){
        duplicateNum d = new duplicateNum();
        d.duplicate(new int[]{7,6,2,4,5,3,4});
    }
}
