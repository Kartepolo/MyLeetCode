/**
 * Created by Xiang on 10/21/2016.
 */
import java.util.*;
public class boarddict {
    private class TrieNode{
        String s;
        Map<Character, TrieNode> map;
        boolean isword;

        TrieNode(){
            this.map = new HashMap<Character, TrieNode>();
            this.isword = false;
        }
    }

    public void addword(String word, TrieNode root){
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i ++){
            char curchar = word.charAt(i);
            if (!cur.map.containsKey(curchar)) cur.map.put(curchar, new TrieNode());
            cur = cur.map.get(curchar);
        }
        cur.s = word;
        cur.isword = true;
    }

    public void dfs(Set<String> res,char[][] board, int x, int y, TrieNode cur) {
        if (cur.isword) res.add(cur.s);
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};
        int m = board.length;
        int n = board[0].length;
        int newx, newy;
        if (cur.map.containsKey(board[x][y])) {
            char now = board[x][y];
            board[x][y] = 0;
            for (int i = 0; i < dx.length; i++) {
                newx = dx[i] + x;
                newy = dy[i] + y;
                if (newx >= 0 && newx < m && newy >= 0 && newy < n) {
                    dfs(res, board, newx, newy, cur.map.get(now));
                }
            }
            board[x][y] = now;
        }
    }

    public ArrayList<String> wordSearchII(char[][] board, ArrayList<String> words) {
        // write your code here
        int m = board.length;
        Set<String> res = new HashSet<String>();
        if (m <= 0) new ArrayList<String>(res);
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];

        TrieNode treeroot = new TrieNode();
        for (String w : words){
            addword(w, treeroot);
        }

        for (int i = 0; i < m; i ++){
            for (int j = 0; j < n; j ++){
                    dfs(res, board, i, j, treeroot);
            }
        }
        return new ArrayList<String>(res);
    }

    public static void main(String[] args){
        char[][] b = {"b".toCharArray(),"a".toCharArray(),"b".toCharArray(),"b".toCharArray(),"a".toCharArray()};
        ArrayList<String> words = new ArrayList<String>();
        words.add("babbab");
        boarddict bd = new boarddict();
        bd.wordSearchII(b, words);
    }

}
