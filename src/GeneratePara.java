/**
 * Created by Xiang on 5/14/2016.
 */
import java.util.*;
public class GeneratePara {
        int lsize = 0;
        int counter = 0;
        List<String> result = new ArrayList<String>();
        public List<String> generateParenthesis(int n) {
            char[] sub = new char[n * 2];
            DFSGenerate(0, n, sub);
            return result;
        }
        private void DFSGenerate(int l, int n, char[] sub){
            if (l == 2 * n) {
                result.add(new String(sub));
                return;
            }else{
                if (lsize > 0) {
                    sub[l] = ')';
                    lsize --;
                    DFSGenerate(l+1, n, sub);
                    // sub[l] = '$';
                    lsize++;
                }
                if (counter < n){
                    sub[l] = '(';
                    lsize ++;
                    DFSGenerate(l+1,n, sub);
                    // sub[l] = '';
                    lsize --;
                    counter ++;
                }
            }
        }
    public static void main(String[] args){
        GeneratePara p = new GeneratePara();
        p.generateParenthesis(2);
    }
}
