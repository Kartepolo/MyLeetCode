/**
 * Created by Xiang on 11/3/2016.
 */
public class longestpalindrome {
    public String longestPalindrome(String s) {
        // Write your code here
        if (s == null || s.length() <= 1) return s;
        char[] tmp = new char[s.length() * 2 + 1];
        for (int i = 0; i < tmp.length; i ++){
            if (i % 2 == 0) tmp[i] = '#';
            else tmp[i] = s.charAt(i / 2);
        }
        int id = 0;
        int mx = 0;
        int[] P = new int[tmp.length];
        for (int i = 0; i < P.length; i ++){
            // We can utlize the previous results only if current pos is less than the preivous boundary
            P[i] = mx > i ? Math.min(mx - i, P[2 * id - i]) : 1;
            while (P[i] + i < tmp.length && i - P[i] >= 0){
                if (tmp[i + P[i]] == tmp[i - P[i]]) P[i]++;
                else break;
            }
            //Update the new boundary. Anything between would be included
            if (P[i] + i > mx){
                id = i;
                mx = id + P[i];
            }
            if (P[i] + i >= P.length) break;
        }
        StringBuilder sb = new StringBuilder();
        int startpos = 2 * id - mx + 1;
        for (int i = startpos; i < mx; i ++){
            System.out.println(tmp[i]);
            if (tmp[i] != '#') sb.append(tmp[i]);
        }
        return sb.toString();
    }
    public static void main(String[] args){
        longestpalindrome s = new longestpalindrome();
        s.longestPalindrome("abcdzdcab");
    }
}
