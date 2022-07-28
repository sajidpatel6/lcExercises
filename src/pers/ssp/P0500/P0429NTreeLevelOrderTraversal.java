package pers.ssp.P0500;

import java.util.ArrayList;
import java.util.List;

public class P0429NTreeLevelOrderTraversal {

//    Given an n-ary tree, return the level order traversal of its nodes' values.
//
//            Nary-Tree input serialization is represented in their level order traversal, each group of children is separated by the null value (See examples).
//
//
//
//            Example 1:
//
//
//
//            Input: root = [1,null,3,2,4,null,5,6]
//            Output: [[1],[3,2,4],[5,6]]
//            Example 2:
//
//
//
//            Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
//            Output: [[1],[2,3,4,5],[6,7,8,9,10],[11,12,13],[14]]
//
//
//            Constraints:
//
//            The height of the n-ary tree is less than or equal to 1000
//            The total number of nodes is between [0, 104]

    public static void main(final String[] args) {
        final P0429NTreeLevelOrderTraversal ex = new P0429NTreeLevelOrderTraversal();

        final Node child2 = new Node(2);
        final Node child6 = new Node(6);
        final Node child14 = new Node(14);
        final List<Node> children11 = new ArrayList<>();
        children11.add(child14);
        final Node child11 = new Node(11, children11);
        final List<Node> children7 = new ArrayList<>();
        children7.add(child11);
        final Node child7 = new Node(7, children7);

        final List<Node> children3 = new ArrayList<>();
        children3.add(child6);
        children3.add(child7);
        final Node child3 = new Node(3, children3);

        final Node child12 = new Node(12);
        final List<Node> children8 = new ArrayList<>();
        children8.add(child12);
        final Node child8 = new Node(8, children8);

        final List<Node> children4 = new ArrayList<>();
        children4.add(child8);
        final Node child4 = new Node(4, children4);

        final Node child13 = new Node(13);
        final List<Node> children9 = new ArrayList<>();
        children9.add(child13);
        final Node child9 = new Node(9, children9);

        final Node child10 = new Node(10);

        final List<Node> children5 = new ArrayList<>();
        children5.add(child9);
        children5.add(child10);
        final Node child5 = new Node(5, children5);

        final List<Node> children1 = new ArrayList<>();
        children1.add(child2);
        children1.add(child3);
        children1.add(child4);
        children1.add(child5);
        final Node root = new Node(1, children1);
        final List<List<Integer>> result = ex.levelOrder(root);
        System.out.println(result);
    }

    public List<List<Integer>> levelOrder(final Node root) {
        final List<List<Integer>> levels = new ArrayList<>();
        final int levelInt = 0;

        if (root != null) {
            traverse(null, root, levels, levelInt);
        }

        return levels;
    }

    private void traverse(final Node parentNode, final Node currNode, final List<List<Integer>> levels,
            final int levelInt) {

        if (parentNode == null) {
            final List<Integer> level = new ArrayList<>();
            level.add(Integer.valueOf(currNode.val));
            levels.add(level);
        }
        final List<Integer> level = new ArrayList<>();
        if (currNode.children != null) {
            for (final Node child : currNode.children) {
                level.add(Integer.valueOf(child.val));
            }
            final int newLevelInt = levelInt + 1;
            if ((level.size() > 0)) {
                if (levels.size() > (newLevelInt)) {
                    final List<Integer> levelOld = levels.get(newLevelInt);
                    levelOld.addAll(level);
                } else {
                    levels.add(level);
                }
            }

            for (final Node child : currNode.children) {
                traverse(currNode, child, levels, newLevelInt);
            }
        }
    }
}

class Node {
    public int val;
    public List<Node> children;

    public Node() {
    }

    public Node(final int _val) {
        val = _val;
    }

    public Node(final int _val, final List<Node> _children) {
        val = _val;
        children = _children;
    }
}
