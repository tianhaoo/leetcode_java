package backtrack.leetcode79;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class exist {
    public static void main(String[] args) {
        Solution solution = new Solution();
        char[][] board = {
            {'A','B','C','E'},
            {'S','F','C','S'},
            {'A','D','E','E'}
        };
        String word="SEE";

//        char[][] board = {
//                {'a'}
//        };
//        String word="a";
        System.out.println(solution.exist(board, word));
    }
}


class Solution {
    public boolean exist(char[][] board, String word) {

        int m = board.length;
        int n = board[0].length;
        boolean[] exist = {false};
        int[][] directions = {
                {0, 1},
                {1, 0},
                {-1, 0},
                {0, -1},
        };
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                System.out.println(i);
                System.out.println(j);
                List<Character> path = new ArrayList<>();
                if (!exist[0]){
                    boolean [][] selected = new boolean[m][n];
                    for(int k=0; k<m; k++){
                        for(int v=0; v<n; v++){
                            selected[k][v] = false;
                        }
                    }
                    backtrack(path, board, word, i, j, selected, directions, exist);
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

    public void backtrack(List<Character> path, char[][] board, String word, int i, int j, boolean[][] selected, int[][] directions, boolean[] exist){
        if(exist[0] == true){
            return;
        }
//        if(!selected[i][j]){
//            path.add(board[i][j]);
//            selected[i][j] = true;
//        }


        Character[] pathCharacterArray = path.toArray(new Character[path.size()]);
        char[] pathCharArray = new char[pathCharacterArray.length];
        for(int k=0; k<pathCharacterArray.length; k++){
            pathCharArray[k] = pathCharacterArray[k];
        }
        String pathString = String.valueOf(pathCharArray);
        System.out.println(pathString);

        if(pathString.equals(word)){
            exist[0] = true;
            // System.out.println(exist[0]);
            return;
        }

        if(pathString.length() >= word.length()){
            return;
        }

        if(pathString.length() >=1 && pathString.charAt(pathString.length()-1) != board[i][j]){
            return;
        }


        char c = board[i][j];
        path.add(c);
        selected[i][j] = true;

        for (int[] direction : directions) {
            int m = i+direction[0];
            int n = j+direction[1];

            if (isValid(board, m, n) && !selected[m][n]) {
                backtrack(path, board, word, m, n, selected, directions, exist);
            }
        }

        path.remove(path.size() - 1);
        selected[i][j] = false;
    }
}