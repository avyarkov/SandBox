package leetcode;

import java.util.ArrayList;
import java.util.List;

// LeetCode 102
public class PreorderTraversalLevelOrderOfBinaryTree {
    // Definition for a binary tree node.
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {this.val = val;}
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return List.of();
        }
        List<List<Integer>> res = new ArrayList<>();
        preorderTraversal(root, res, 0);
        return res;
    }

    private void preorderTraversal(TreeNode root, List<List<Integer>> list, int level) {
        if (level == list.size()) {
            list.add(new ArrayList<>());
        }
        list.get(level).add(root.val);
        if (root.left != null) {
            preorderTraversal(root.left, list, level + 1);
        }
        if (root.right != null) {
            preorderTraversal(root.right, list, level + 1);
        }
    }

    public static void main(String[] args) {
        var instance = new PreorderTraversalLevelOrderOfBinaryTree();
        var res = instance.levelOrder(new TreeNode(3, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7))));
        System.out.println(res);
    }
}
