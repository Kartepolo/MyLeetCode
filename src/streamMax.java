/**
 * Created by Xiang on 10/23/2016.
 */
import java.util.*;
public class streamMax {
    public ArrayList<Integer> maxSlidingWindow(int[] nums, int k) {
        // write your code here
        Deque<Integer> d = new LinkedList<>();
        ArrayList<Integer> res = new ArrayList<Integer>();
        if (k == 0) return res;
        int prev;
        int i = 0;
        while (i < nums.length){
            if (i >= k) d.pop();
            int j = d.size();
            while(j > 0){
                prev = d.pop();
                if(prev < nums[i]){
                    d.add(nums[i]);
                }else{
                    d.push(prev);
                    break;
                }
                j --;
            }
            d.add(nums[i]);
            if (i + 1 >= k){
                int curmax = d.pop();
                d.push(curmax);
                res.add(curmax);
            }
            i += 1;
        }
        return res;
    }
    public static void main(String[] args){
        int[] nums = {1577,330,1775,206,296,356,219,999,790,1435,1218,1046,745,650,1199,1290,442,1767,1098,521,854,1718,528,1011,1862,1352,797,1453,779,1891,341,1255,1892,98,978,1173,452,366,1397,576,1256,334,233,1309,575,48,1308,1524,1776,1514,541,1027,43,1073,1136,83,1376,104,864,1578,57,1778,695,664,1475,1025,341,248,687,848,1673,820,1435,1622,1330,1767,1189,828,1556,41,28,1283,462,373,1012,122,619,1129,689,1610,616,452,1369,1018,1784,528,683,346,1817,812,1905,1625,1704,130,636,1731,1450,1045,1352,809,429,991,1285,81,1383,1406,1786,1661,1059,729,1651,108,1608,114,484,471,962,1482,1153,1238};
        streamMax s = new streamMax();
        s.maxSlidingWindow(nums, 8);

    }

}
