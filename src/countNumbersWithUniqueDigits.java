/**
 * Created by Xiang on 7/17/2016.
 */
public class countNumbersWithUniqueDigits {
    private boolean switched = false;
    public int countNumbersWithUniqueDigits(int n) {
        if (n > 10) return countNumbersWithUniqueDigits(10);
        boolean[] used = new boolean[10];
        int res = 0;
        return helper(n,0, used);
    }
    private int helper(int n,int digitloc, boolean[] used){
        int result = 0;
        for (int i = 0; i <=9; i++){
            if (!used[i]) {
                if (digitloc == n -1){
                    result += 1;
                }else{
                    used[i] = true;
                    result += helper(n, digitloc + 1, used);
                    used[i] = false;
                }
            }
            }
        // add counts with the first digit as zero
        return result;
    }

    public static void main(String[] args){
        countNumbersWithUniqueDigits c = new countNumbersWithUniqueDigits();
        int i = c.countNumbersWithUniqueDigits(5);
        System.out.println(i);
    }
}
