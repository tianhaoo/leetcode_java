package backtrack.leetcode17;


import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> letterCombinations(String digits) {
        if(digits.equals("")){
            return new ArrayList<>();
        }

        String[] map = new String[] {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        char[] digitsArray = digits.toCharArray();
        List<String> res = new ArrayList<>();

        backtrack(res, "", digitsArray, 0, map);

        return res;

    }

    public void backtrack(List<String> res, String path, char[] digitsArray, int position, String[] map){
        if(path.length() == digitsArray.length){
            // System.out.println(path);
            res.add(path);
        }
        String choices = "";
        if(position < digitsArray.length){
            choices = map[digitsArray[position] - '0'];
        }

        for(int i=0; i<choices.length(); i++){
            path += choices.charAt(i);

            backtrack(res, path, digitsArray, position+1, map);

            path = path.substring(0, path.length()-1);
        }

    }


}



public class letterCombinations {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.letterCombinations(""));
    }
}
