package backtrack.leetcode39;

import java.util.ArrayList;
import java.util.List;

public class combinationSum {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] candidates = new int[]{2, 3, 6, 7};
        int target = 7;
        List<List<Integer>> res = solution.combinationSum(candidates, target);

        System.out.println(res);

    }
}

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();

        backtrack(res, path, 0, candidates, target);

        return res;

    }

    // 类似求所有的子集
    public void backtrack(List<List<Integer>> res, List<Integer> path, int start, int[] candidates, int target){
        // System.out.print("#");
        // System.out.println(path);
        int sum = 0;
        for(int x : path){
            sum += x;
        }
        if(sum>target){
            return;
        }
        // System.out.println(sum);
        if(sum == target){
            // System.out.println(path);
            List<Integer> pathCopy = new ArrayList<>(path);
            res.add(pathCopy);
        }


        for(int i=start; i<candidates.length; i++){
            path.add(candidates[i]);

            backtrack(res, path, i, candidates, target);

            path.remove(path.size()-1);
        }
    }
}


