package pers.ssp.P0100;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class P0090SubsetsII {

//    Given an integer array nums that may contain duplicates, return all possible subsets (the power set).
//
//            The solution set must not contain duplicate subsets. Return the solution in any order.
//
//
//
//            Example 1:
//
//            Input: nums = [1,2,2]
//            Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
//            Example 2:
//
//            Input: nums = [0]
//            Output: [[],[0]]
//
//
//            Constraints:
//
//            1 <= nums.length <= 10
//            -10 <= nums[i] <= 10

    public static void main(final String[] args) {
        final P0090SubsetsII ex = new P0090SubsetsII();
        final int[] nums = { 4, 1, 0 }; // [[],[0],[0,1],[0,1,4],[0,4],[1],[1,4],[4]]

//        final List<List<Integer>> result = ex.subsetsWithDup(nums);
        final List<List<Integer>> result = ex.subsetsWithDup(nums);
        System.out.println(result);
    }

    private void subsetsHelper(final List<List<Integer>> list, final List<Integer> resultList, final int[] nums,
            final int start, final Set<String> dupChecker) {

        final List<Integer> dupList = new ArrayList<>(resultList);
        Collections.sort(dupList);
        final StringBuilder strB = new StringBuilder();
        for (final int intA : dupList) {
            strB.append(intA);
        }

        if (!dupChecker.contains(strB.toString())) {
            list.add(new ArrayList<>(dupList));
            dupChecker.add(strB.toString());
        }

        for (int i = start; i < nums.length; i++) {
            resultList.add(nums[i]);
            subsetsHelper(list, resultList, nums, i + 1, dupChecker);
            resultList.remove(resultList.size() - 1);
        }
    }

    public List<List<Integer>> subsetsWithDup(final int[] nums) {
        final List<List<Integer>> list = new ArrayList<>();
        final Set<String> dupChecker = new HashSet<>();
        subsetsHelper(list, new ArrayList<>(), nums, 0, dupChecker);
        return list;
    }

}
