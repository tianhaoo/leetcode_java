package backtrack.leetcode79;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class exist {
    public static void main(String[] args) {
        Solution solution = new Solution();

//        char[][] board = {
//            {'A','B','C','E'},
//            {'S','F','C','S'},
//            {'A','D','E','E'}
//        };
//        String word="ABCCED";

//        char[][] board = {
//                {'a'},
//                {'a'}
//        };
//        String word="b";

        char[][] board = {
                {'a'}
        };
        String word="a";
        System.out.println(solution.exist(board, word));
    }
}


class Solution {
    public boolean exist(char[][] board, String word) {

        int m = board.length;
        int n = board[0].length;
        if(m==1 && n==1){
            return String.valueOf(board[m-1][n-1]).equals(word);
        }

        boolean[] exist = {false};
        int[][] directions = {
                {0, 1},
                {1, 0},
                {-1, 0},
                {0, -1},
        };
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                // System.out.println(i + ", " + j + " " + board[i][j]);
                List<Character> path = new ArrayList<>();
                if (!exist[0]){
                    boolean [][] visited = new boolean[m][n];
                    for(int k=0; k<m; k++){
                        for(int v=0; v<n; v++){
                            visited[k][v] = false;
                        }
                    }
                    backtrack(path, board, word, i, j, 0, visited, directions, exist);
                }
            }
        }
        return exist[0];

    }

    public boolean isValid(char[][] board, int m, int n){
        if(m < 0 || n < 0){
            return false;
        }
        if(m >= board.length){
            return false;
        }
        if(n >= board[0].length){
            return false;
        }
        return true;
    }

    public void backtrack(List<Character> path, char[][] board, String word, int startX, int startY, int pos, boolean[][] visited, int[][] directions, boolean[] exist){
        if(exist[0] == true){
            return;
        }
        // System.out.println(startX + "# " + startY);

        if(board[startX][startY] != word.charAt(pos)){
            exist[0] = false;
            return;
        } else if(pos == word.length()-1){
            exist[0] = true;
            return;
        }


        visited[startX][startY] = true;

        for (int[] direction : directions) {
            int m = startX + direction[0];
            int n = startY + direction[1];

            if (isValid(board, m, n) && !visited[m][n]) {
                backtrack(path, board, word, m, n, pos+1, visited, directions, exist);
            }
        }

        visited[startX][startY] = false;
    }
}
