package pers.ssp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Exercise0006 {

    public static void main(final String[] args) {
        final Exercise0006 ex = new Exercise0006();
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
