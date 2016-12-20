/**
 * Created by Xiang on 8/6/2016.
 */
public class rangeBitwiseAnd {
    public static int rangeBitwiseAnd(int m, int n) {
        int l = 0;
        int p = 0;
        while (n >> l > 0){
            l ++;
        }
        int left = m;
        while (left >= (1 << (l- p -1)) && left > 0){
            p = p + 1;
            left = left - (1 << (l - p));
        }
        int result = (1 << p) - 1;
        return l - p;

    }
    public static void main(String[] args){
        int i = rangeBitwiseAnd(6,7);
        System.out.println(i);
    }

    public int maxProfit(int[] prices) {
        int[][] result = new int[prices.length][4];
        //Empty, bought,  firstT, second, nochance
        result[0][0] = - prices[0];
        result[0][1] = 0;
        result[0][2] = 0;
        result[0][3] = 0;
        for (int i =1; i < prices.length ; i ++){
            result[i][0] = Math.max(- prices[i], result[i-1][0]);
            result[i][1] = Math.max(result[i-1][0] + prices[i], result[i-1][1]);
            result[i][2] = Math.max(result[i-1][1] - prices[i], result[i-1][2]);
            result[i][3] = Math.max(result[i-1][2] + prices[i], result[i-1][3]);
        }
        return 0;
    }
}
