package pers.ssp.P0200;

import java.util.ArrayList;
import java.util.List;

public class P0113PathSumII {
//    Given the root of a binary tree and an integer targetSum, return all root-to-leaf paths where the sum of the node values in the path equals targetSum. Each path should be returned as a list of the node values, not node references.
//
//            A root-to-leaf path is a path starting from the root and ending at any leaf node. A leaf is a node with no children.
//
//
//
//            Example 1:
//
//
//            Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
//            Output: [[5,4,11,2],[5,8,4,5]]
//            Explanation: There are two paths whose sum equals targetSum:
//            5 + 4 + 11 + 2 = 22
//            5 + 8 + 4 + 5 = 22
//            Example 2:
//
//
//            Input: root = [1,2,3], targetSum = 5
//            Output: []
//            Example 3:
//
//            Input: root = [1,2], targetSum = 0
//            Output: []
//
//
//            Constraints:
//
//            The number of nodes in the tree is in the range [0, 5000].
//            -1000 <= Node.val <= 1000
//            -1000 <= targetSum <= 1000
    public static void main(final String[] args) {
        final P0113PathSumII ex = new P0113PathSumII();
        final TreeNode leaf1 = new TreeNode(1);
        final TreeNode leaf2 = new TreeNode(2);
        final TreeNode root = new TreeNode(3, leaf1, leaf2);
        final int targetSum = 4;
        final List<List<Integer>> result = ex.pathSum(root, targetSum);
        System.out.println(result);
    }

    private void addPath(final TreeNode root, final int targetSum, final List<List<Integer>> result, final int sum,
            final List<Integer> currPath) {
        currPath.add(root.val);
        if ((root.left == null) && (root.right == null)) {
            if ((sum + root.val) == targetSum) {
                final List<Integer> list2Add = new ArrayList<>();
                for (final Integer node : currPath) {
                    list2Add.add(node);
                }
                result.add(list2Add);
            }
        } else {
            if (root.left != null) {
                addPath(root.left, targetSum, result, sum + root.val, currPath);
            }
            if (root.right != null) {
                addPath(root.right, targetSum, result, sum + root.val, currPath);
            }
        }
        currPath.remove(currPath.size() - 1);
    }

    public List<List<Integer>> pathSum(final TreeNode root, final int targetSum) {
        final List<List<Integer>> result = new ArrayList<>();
        final int sum = 0;
        final List<Integer> currPath = new ArrayList<>();

        if (root != null) {
            addPath(root, targetSum, result, sum, currPath);
        }

        return result;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(final int val) {
        this.val = val;
    }

    TreeNode(final int val, final TreeNode left, final TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}