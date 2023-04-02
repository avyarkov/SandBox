package leetcode;

import java.util.ArrayList;

// LeetCode 99
public class InorderTraversalBinarySearchTree {
    // Definition for a binary tree node.
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void recoverTree(TreeNode root) {
        ArrayList<Integer> values = new ArrayList<>();
        ArrayList<TreeNode> nodes = new ArrayList<>();
        inorderTraversal(root, values, nodes);
        int l = 0;
        while (values.get(l) < values.get(l + 1)) {
            l++;
        }
        int r = values.size() - 1;
        while (values.get(r) > values.get(r - 1)) {
            r--;
        }
        int tmp = nodes.get(l).val;
        nodes.get(l).val = nodes.get(r).val;
        nodes.get(r).val = tmp;
    }

    public static void inorderTraversal(TreeNode root, ArrayList<Integer> values, ArrayList<TreeNode> nodes) {
        if (root.left != null) {
            inorderTraversal(root.left, values, nodes);
        }
        values.add(root.val);
        nodes.add(root);
        if (root.right != null) {
            inorderTraversal(root.right, values, nodes);
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2, new TreeNode(3), new TreeNode(1));
        System.out.println(root.left.val + " " + root.right.val);
        recoverTree(root);
        System.out.println(root.left.val + " " + root.right.val);
    }
}
