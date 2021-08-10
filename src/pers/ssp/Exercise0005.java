package pers.ssp;

import java.util.ArrayList;
import java.util.List;

public class Exercise0005 {

    private static boolean checkIfNoPreReqs(final int course, final int[][] prerequisites) {

        boolean result = true;
        for (final int[] prerequisite : prerequisites) {
            if (prerequisite[0] == course) {
                result = false;
                break;
            }
        }
        return result;

    }

    public static void main(final String[] args) {
        final Exercise0005 ex = new Exercise0005();
        final int numCourses = 3;// 4;
//                [[0,1],[3,1],[1,3],[3,2]];
        final int[][] prerequisites = { { 0, 1 }, { 0, 2 }, { 1, 2 } };// { { 0, 1 }, { 3, 1 }, { 1, 3 }, { 3, 2 } };

        final boolean result = ex.canFinish(numCourses, prerequisites);
        System.out.println(result);
    }

    public boolean canFinish(final int numCourses, final int[][] prerequisites) {
        boolean result = true;
        boolean possible = true;
        final List<Boolean> okMap = new ArrayList<>(numCourses);
        final List<Integer> checkedMap = new ArrayList<>(numCourses);

        for (int i = 0; i < numCourses; i++) {
            possible = checkIfNoPreReqs(i, prerequisites);
            okMap.add(i, possible);
        }

        for (int i = 0; i < numCourses; i++) {
            possible = checkPreReqs(i, prerequisites, okMap, checkedMap);
            okMap.set(i, possible);
            if (!possible) {
                result = false;
                break;
            }
            checkedMap.clear();
        }
        return result;
    }

    private boolean checkPreReqs(final int course, final int[][] prerequisites, final List<Boolean> okMap,
            final List<Integer> checkedMap) {

        if (okMap.get(course)) {
            return true;
        }

        // Check 2 - if there is a prerequisite, make sure we can do that as well
        boolean result = true;
        final List<Integer> moreChecks = new ArrayList<>();

        for (final int[] prerequisite : prerequisites) {
            if (prerequisite[0] == course) {
                if (!checkedMap.contains(prerequisite[0])) {
                    checkedMap.add(prerequisite[0]);
                } else {
                    result = false;
                    break;
                }

                moreChecks.add(prerequisite[1]);

            }
        }

        for (final int morecheck : moreChecks) {
            result = checkPreReqs(morecheck, prerequisites, okMap, checkedMap);
            if (result == false) {
                break;
            }
        }

        return result;
    }
}
