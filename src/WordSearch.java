/**
 * Created by Xiang on 5/14/2016.
 */
public class WordSearch {
    private int width;
    private int height;
    private String w;
    private boolean[][] Visited;
    public boolean exist(char[][] board, String word) {
        width = board.length;
        height = board[0].length;
        w = word;
        Visited = new boolean[width][height];
        boolean r = false;
        for (int i = 0; i < width; i ++){
            for (int j = 0; j < height; j ++){
                r = helper(board, 0, i, j);
                if (r) return true;
            }
        }
        return r;
    }
    private boolean helper(char[][] board, int next, int xpos, int ypos){
        boolean result = false;
        if (next == this.w.length()) return true;
        for (int i =-1; i<=1; i ++){
            for (int j = -1; j <=1; j++){
                if (isvalid(xpos +i, ypos + j)){
                    if (board[xpos + i][ypos + j] == w.charAt(next) && !Visited[xpos + i][ypos + j]){
                        Visited[xpos + i][ypos + j] = true;
                        result = helper(board,next + 1, xpos + i,ypos + j);
                        if (!result) Visited[xpos + i][ypos + j] = false;
                        else return true;
                    }
                }
            }
        }
        return result;
    }
    private boolean isvalid(int xpos, int ypos){
        return xpos >= 0 && xpos < this.width && ypos >=0 && ypos < this.height;
    }

    public static void main(String[] args){
        int[] arr = new int[9];
        char[][] board = {"ABCE".toCharArray(),"SFCS".toCharArray(),"ADEE".toCharArray()};
        WordSearch w = new WordSearch();
        w.exist(board,"ABCCED");
    }
}
