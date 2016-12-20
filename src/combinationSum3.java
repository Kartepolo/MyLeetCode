/**
 * Created by Xiang on 7/17/2016.
 */
import java.util.*;
public class combinationSum3 {
    public List<List<Integer>> combinationSum3(int k, int n) {
        if (n < k*(k+1) /2 || n > (19 - k) *k / 2) return null;
        else return helper(k, n, 0);
    }
    private List<List<Integer>> helper(int k, int n, int prevmin){
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (2 * n >= k *(2* prevmin + k) && 2 * n <= (19 - k) *k  && 9 - prevmin >= k ){
            if (k == 1){
                List<Integer> pp = new ArrayList<Integer>();
                pp.add(n);
                res.add(pp);
            }else{
                for (int i=prevmin + 1; i <= 9; i ++){
                    List<List<Integer>> tmp = helper(k-1, n - i, i);
                    for (List<Integer> l : tmp){
                        l.add(i);
                        res.add(l);
                    }
                }
            }
        }
        return res;
    }





    public static void main(String[] args){
        combinationSum3 c = new combinationSum3();
        List<List<Integer>> i = c.combinationSum3(3, 7);
        System.out.println(i);
    }
}
