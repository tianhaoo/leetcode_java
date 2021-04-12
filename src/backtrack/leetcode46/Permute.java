package backtrack.leetcode46;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> permute(int[] nums) {
        boolean[] selected = new boolean[nums.length];
        for(int i=0; i<nums.length; i++){
            selected[i] = false;
        }
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> routes = new ArrayList<>();
        backtrack(res, routes, nums, selected);
        return res;
    }

    public static void backtrack(List<List<Integer>> res, List<Integer> route, int[] choices, boolean[] selected){
        if(route.size() == choices.length){
            // System.out.println(routes);
            List<Integer> routeCopy = new ArrayList<>(route);
            res.add(routeCopy);
        }
        // 不能重复选择元素
        for(int i=0; i<choices.length; i++){ // 所有可能的选择
            if (! selected[i]){
                int currentChoice = choices[i];
                // 做选择
                selected[i] = true;
                route.add(currentChoice);
                // 递归调用
                backtrack(res, route, choices, selected);
                // 撤销选择
                selected[i] = false;
                route.remove(route.size()-1);
            }
        }
    }
}


public class Permute {
    // 给定一个 没有重复 数字的序列，返回其所有可能的全排列
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[]{1, 2 ,3};
        System.out.println(solution.permute(nums));
    }
}

