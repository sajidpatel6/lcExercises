package pers.ssp;
import java.util.ArrayList;
import java.util.List;

public class Exercise0007 {

    public static void main(final String[] args) {
        final Exercise0007 ex = new Exercise0007();
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