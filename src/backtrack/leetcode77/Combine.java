package backtrack.leetcode77;


import sun.rmi.server.InactiveGroupException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        int[] nums = new int[n];
        for(int i=0; i<n; i++){
           nums[i] = i+1;
        }
        // System.out.println(Arrays.toString(nums));
        boolean[] selected = new boolean[n];
        for(int i=0; i<n; i++){
            selected[i] = false;
        }
        backtrack(res, path, nums, selected, 0, k);
        return res;
    }

    public void backtrack(List<List<Integer>> res, List<Integer> path, int[] nums, boolean[] selected, int start, int k){
        if(path.size() == k){
            List<Integer> pathCopy = new ArrayList<>(path);
            res.add(pathCopy);
            // System.out.println(res);
        }
        for(int i=start; i<nums.length; i++){
            path.add(nums[i]);

            backtrack(res, path, nums, selected, i+1, k);

            path.remove(path.size()-1);
        }


    }
}


public class Combine {
    public static void main(String[] args) {
        // 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合
        Solution solution = new Solution();
        System.out.println(solution.combine(4, 2));

    }
}
