package pers.ssp.P0900;

import java.util.ArrayList;
import java.util.List;

public class P0827MakingALargeIsland_02 {

//    You are given an n x n binary matrix grid. You are allowed to change at most one 0 to be 1.
//
//    Return the size of the largest island in grid after applying this operation.
//
//    An island is a 4-directionally connected group of 1s.
//
//
//
//    Example 1:
//
//    Input: grid = [[1,0],[0,1]]
//    Output: 3
//    Explanation: Change one 0 to 1 and connect two 1s, then we get an island with area = 3.
//    Example 2:
//
//    Input: grid = [[1,1],[1,0]]
//    Output: 4
//    Explanation: Change the 0 to 1 and make the island bigger, only one island with area = 4.
//    Example 3:
//
//    Input: grid = [[1,1],[1,1]]
//    Output: 4
//    Explanation: Can't change any 0 to 1, only one island with area = 4.
//
//
//    Constraints:
//
//    n == grid.length
//    n == grid[i].length
//    1 <= n <= 500
//    grid[i][j] is either 0 or 1.

    class Element {
        int row;
        int column;
        int index;
        int val;

        boolean checked = false;
        int island = -1;

        public Element(final int row2, final int column2, final int val2, final int index2) {
            row = row2;
            column = column2;
            index = index2;
            val = val2;
        }
    }

    public static void main(final String[] args) {
        final P0827MakingALargeIsland_02 ex = new P0827MakingALargeIsland_02();

//        final int[][] grid = { { 1, 1, 1 }, { 0, 0, 0 }, { 1, 1, 1 } };

        final int[][] grid = new int[1000][1000];
        boolean b = false;
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                if (b) {
                    grid[i][j] = 1;
                } else {
                    grid[i][j] = 0;
                }
                b = !b;
            }
        }
        final int result = ex.largestIsland(grid);
        System.out.println(result);
    }

    public void checkElement(final List<Element> elemsToCheck, final List<List<Element>> elemsRef,
            final Element currElem, final int row, final int column) {
        if ((row >= 0) && (column >= 0) && (row < elemsRef.size()) && (column < elemsRef.get(row).size())) {
            final Element elem = elemsRef.get(row).get(column);
            if (elem.checked) {
                //
            } else {
                elem.checked = true;
                if ((elem.island == -1) && (elem.val > 0)) {
                    elem.island = currElem.island;
                    checkNeighbors(elemsToCheck, elemsRef, elem);
                }
            }
        }
    }

    private void checkLeft(final List<Element> elemsToCheck, final List<List<Element>> elemsRef,
            final Element currElem) {
        final int row = currElem.row;
        final int column = currElem.column - 1;

        checkElement(elemsToCheck, elemsRef, currElem, row, column);
    }

    private void checkNeighbors(final List<Element> elemsToCheck, final List<List<Element>> elemsRef,
            final Element currElem) {
        checkLeft(elemsToCheck, elemsRef, currElem);
        checkRight(elemsToCheck, elemsRef, currElem);
        checkNorth(elemsToCheck, elemsRef, currElem);
        checkSouth(elemsToCheck, elemsRef, currElem);
    }

    private void checkNorth(final List<Element> elemsToCheck, final List<List<Element>> elemsRef,
            final Element currElem) {
        final int row = currElem.row - 1;
        final int column = currElem.column;

        checkElement(elemsToCheck, elemsRef, currElem, row, column);
    }

    private void checkRight(final List<Element> elemsToCheck, final List<List<Element>> elemsRef,
            final Element currElem) {
        final int row = currElem.row;
        final int column = currElem.column + 1;

        checkElement(elemsToCheck, elemsRef, currElem, row, column);
    }

    private void checkSouth(final List<Element> elemsToCheck, final List<List<Element>> elemsRef,
            final Element currElem) {
        final int row = currElem.row + 1;
        final int column = currElem.column;

        checkElement(elemsToCheck, elemsRef, currElem, row, column);
    }

    private ArrayList<Integer> findLargestIsland(final List<List<Element>> elemsRef, final List<Element> elemsToCheck) {
        int lastIslandId = -1;

        for (int itemIndex = elemsToCheck.size() - 1; itemIndex >= 0; itemIndex--) {
            final Element currElem = elemsToCheck.get(itemIndex);
            if (!currElem.checked) {
                currElem.checked = true;
                if ((currElem.island == -1) && (currElem.val > 0)) {
                    currElem.island = ++lastIslandId;
                    checkNeighbors(elemsToCheck, elemsRef, currElem);
                }
            }
        }

        final ArrayList<Integer> islandSizes = new ArrayList<>();
        for (int itemIndex = elemsToCheck.size() - 1; itemIndex >= 0; itemIndex--) {
            final Element currElem = elemsToCheck.get(itemIndex);
            if (currElem.island > -1) {
                if (islandSizes.size() > currElem.island) {
                    Integer a = islandSizes.get(currElem.island);
                    islandSizes.set(currElem.island, a == null ? Integer.valueOf(1) : ++a);
                } else {
                    for (int k = islandSizes.size() == 0 ? 0 : islandSizes.size(); k <= currElem.island; k++) {
                        islandSizes.add(currElem.island, Integer.valueOf(0));
                    }
                    islandSizes.set(currElem.island, Integer.valueOf(1));
                }
            }
        }
        return islandSizes;

//        int max = Integer.MIN_VALUE;
//
//        for (final int currSize : islandSizes) {
//            if (max < currSize) {
//                max = currSize;
//            }
//        }
//        if (islandSizes.size() == 0) {
//            return 0;
//        }
//
//        return max;
    }

    public int largestIsland(final int[][] grid) {

        final List<Element> elemsToCheck = new ArrayList<>();
        final List<List<Element>> elemsRef = new ArrayList<>();
        for (int row = 0; row < grid.length; row++) {
            final List<Element> elemsSubRef = new ArrayList<>();
            elemsRef.add(elemsSubRef);
            for (int column = 0; column < grid[row].length; column++) {
                final Element elem = new Element(row, column, grid[row][column], elemsToCheck.size());
                elemsToCheck.add(elem);
                elemsSubRef.add(elem);
            }
        }

        final ArrayList<Integer> islandSizes = findLargestIsland(elemsRef, elemsToCheck);

        final int realMax = 0;
        if (islandSizes.size() == 1) {
            return islandSizes.get(0) + 1;
        }

        // TODO now we need to check the perimeter of the islands for bridging

//        int max = Integer.MIN_VALUE;
//
//        for (final int currSize : islandSizes) {
//            if (max < currSize) {
//                max = currSize;
//            }
//        }
//        realMax = max;
//
//        int checkedCount = 0;
//        while ((checkedCount < elemsToCheck.size())) {
//            final Element elem = elemsToCheck.get(checkedCount);
//            for (final Element resetElem : elemsToCheck) {
//                resetElem.checked = false;
//                resetElem.island = -1;
//            }
//            if (elem.val == 0) {
//                elem.val = 1;
//                islandSizes = findLargestIsland(elemsRef, elemsToCheck);
//                int newMax = 0;
//                if (islandSizes.size() == 1) {
//                    return islandSizes.get(0) + 1;
//                }
//                max = Integer.MIN_VALUE;
//
//                for (final int currSize : islandSizes) {
//                    if (max < currSize) {
//                        max = currSize;
//                    }
//                }
//                newMax = max;
//
//                if (realMax < newMax) {
//                    realMax = newMax;
//                }
//                elem.val = 0;
//            }
//            checkedCount++;
//        }
        return realMax;
    }

}