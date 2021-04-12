package backtrack.leetcode51;

import java.security.spec.RSAOtherPrimeInfo;
import java.util.ArrayList;
import java.util.List;

public class solveNQueens {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solveNQueens(4));

    }

}


class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        List<String> board = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++){
            sb.append(".");
        }
        for (int i=0; i<n; i++){
            board.add(sb.toString());
        }
        backtrack(res, board, n, 0);

        return res;
    }



    public boolean isValid(List<String> board, int n, int r, int c){
        // 是否有同一行的
        String boardR = board.get(r);
        if(boardR.contains("Q")) return false;

        // 是否有同一列的
        StringBuilder sbBoardC = new StringBuilder();
        for(int i=0; i<n; i++){
            sbBoardC.append(board.get(i).charAt(c));
        }
        if(sbBoardC.toString().contains("Q")) return false;

        int tempR;
        int tempC;
        StringBuilder sb;
        // 右下
        tempR = r;
        tempC = c;
        sb = new StringBuilder();
        while(tempR < n && tempC < n){
            sb.append(board.get(tempR).charAt(tempC));
            tempR ++;
            tempC ++;
        }
        if(sb.toString().contains("Q")) return false;

        // 左上
        tempR = r;
        tempC = c;
        sb = new StringBuilder();
        while(tempR >= 0 && tempC >= 0){
            sb.append(board.get(tempR).charAt(tempC));
            tempR --;
            tempC --;
        }
        if(sb.toString().contains("Q")) return false;

        // 右上
        tempR = r;
        tempC = c;
        sb = new StringBuilder();
        while(tempR >= 0 && tempC < n){
            sb.append(board.get(tempR).charAt(tempC));
            tempR --;
            tempC ++;
        }
        if(sb.toString().contains("Q")) return false;

        // 左下
        tempR = r;
        tempC = c;
        sb = new StringBuilder();
        while(tempR < n && tempC >= 0){
            sb.append(board.get(tempR).charAt(tempC));
            tempR ++;
            tempC --;
        }
        if(sb.toString().contains("Q")) return false;

        return true;
    }

    public void backtrack(List<List<String>> res, List<String> board, int n, int r){
        // System.out.println(r);
        if(r >= n){
            // System.out.println(board);
            List<String> boardCopy = new ArrayList<>(board);
            res.add(boardCopy);
            return;
        }
        // 确定行r之后可以选择任意一列
        for(int c=0; c<n; c++){
            // System.out.print("#");
            // System.out.println(c);
            if(isValid(board, n, r, c)){
                // 在(r, c)位置放置一个棋子
                char[] boardR = board.get(r).toCharArray();
                boardR[c] = 'Q';
                board.set(r, String.valueOf(boardR));
                // System.out.print("%");
                // System.out.println(r);
                backtrack(res, board, n, r+1);

                // 在(r, c)位置取消放置
                boardR = board.get(r).toCharArray();
                boardR[c] = '.';
                board.set(r, String.valueOf(boardR));
            }
        }
    }
}
