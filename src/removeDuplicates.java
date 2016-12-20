import java.util.List;

/**
 * Created by Xiang on 8/3/2016.
 */
public class removeDuplicates {
    public int removeDuplicates(int[] nums) {
        int loc = 0;
        int i = 0;
        int skipped = 0;
        boolean seen;
        while(i < nums.length && loc < nums.length){
            while(loc < nums.length){
                seen = false;
                nums[i] = nums[loc];
                loc ++;
                if (nums[loc] != nums[i]) break;
                else{
                    if (seen) skipped +=1;
                    else seen = true;
                }
                loc ++;
            }
            if (loc - i - skipped >= 2){
                nums[i+1] = nums[i];
                i+=1;
            }
            i +=1;
        }
        return i + 1;
    }
    public static void main(String[] args){
        removeDuplicates c = new removeDuplicates();
        int[] nums = {1,1,1,1,2,2,3,4,4};
        int i = c.removeDuplicates(nums);
        System.out.println(i);
    }
}
