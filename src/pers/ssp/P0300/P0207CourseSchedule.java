package pers.ssp.P0300;

import java.util.ArrayList;
import java.util.List;

public class P0207CourseSchedule {

//    There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
//
//            For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
//            Return true if you can finish all courses. Otherwise, return false.
//
//
//
//            Example 1:
//
//            Input: numCourses = 2, prerequisites = [[1,0]]
//            Output: true
//            Explanation: There are a total of 2 courses to take.
//            To take course 1 you should have finished course 0. So it is possible.
//            Example 2:
//
//            Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
//            Output: false
//            Explanation: There are a total of 2 courses to take.
//            To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.
//
//
//            Constraints:
//
//            1 <= numCourses <= 2000
//            0 <= prerequisites.length <= 5000
//            prerequisites[i].length == 2
//            0 <= ai, bi < numCourses
//            All the pairs prerequisites[i] are unique.
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
        final P0207CourseSchedule ex = new P0207CourseSchedule();
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
