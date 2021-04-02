package backtrack.leetcode78;

import java.util.ArrayList;
import java.util.List;

import sun.jvm.hotspot.runtime.aarch64.AARCH64CurrentFrameGuess;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> route = new ArrayList<>();
        backtrack(res, route, nums, 0);
        return res;
    }

    public void backtrack(List<List<Integer>> res, List<Integer> route, int[] nums, int start){
        List<Integer> routeCopy = new ArrayList<>(route);
        res.add(routeCopy);

        // 常用方法，
        for(int i=start; i<nums.length; i++){  // 所有可能的选择
            // 做选择
            route.add(nums[i]);

            backtrack(res, route, nums, i+1); // 这里传i+1

            // 撤销选择
            route.remove(route.size()-1);
        }
    }
}


public class Subsets {
    public static void main(String[] args) {
        // 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
        Solution solution = new Solution();
        int[] nums = new int[]{1, 2 ,3};
        System.out.println(solution.subsets(nums));
    }
}
