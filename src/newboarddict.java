/**
 * Created by Xiang on 10/21/2016.
 */
import java.util.*;
public class newboarddict {
    private class TrieNode{
        char cur;
        Map<Character, TrieNode> map;
        boolean isword;

        TrieNode(){
            this.map = new HashMap<Character , TrieNode>();
            this.isword = false;
        }
    }

    public TrieNode addword(char c, TrieNode root){
        if (root.isword) root.isword = false;
        if (!root.map.containsKey(c)) root.map.put(c, new TrieNode());
        return root.map.get(c);
    }
    public boolean searchword(String word, TrieNode root){
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i ++){
            if (cur.isword || !cur.map.containsKey(word.charAt(i))) return false;
            cur = cur.map.get(word.charAt(i));
        }
        return true;
    }


    public ArrayList<String> wordSearchII(char[][] board, ArrayList<String> words) {
        // write your code here
        int m = board.length;
        Set<String> res = new HashSet<String>();
        if (m <= 0) new ArrayList<String>(res);
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};
        Queue<Integer> q = new LinkedList<Integer>();
        int idx, idy,newx, newy;
        q.add(0);
        TrieNode treeroot = new TrieNode();
        TrieNode next;
        for (int i = 0; i < m; i ++){
            for (int j = 0; j < n; j ++){
                next = treeroot;
                while (q.size() > 0){
                    int cur = q.poll();
                    idx = cur / m;
                    idy = cur % m;
                    if (!visited[idx][idy]){
                        next = addword(board[idx][idy], next);
                        for (int k = 0; j < dx.length; j++){
                            newx = idx + dx[k];
                            newy = idy + dy[k];
                            if (newx >= 0 && newx <m && newy >=0 && newy < n){
                                q.add(newx * n + newy);
                            }
                        }
                    }
                }
                next.isword = true;
            }
        }
        for (String w : words){
            if (searchword(w, treeroot)) res.add(w);
        }
        return new ArrayList<String>(res);
    }
}
