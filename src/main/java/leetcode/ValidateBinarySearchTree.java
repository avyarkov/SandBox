package leetcode;

// LeetCode 98
public class ValidateBinarySearchTree {
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

    public static boolean isValidBST(TreeNode root) {
        return dfs(root).isValid;
    }

    public record SubtreeData(boolean isValid, int min, int max) {}

    public static SubtreeData dfs(TreeNode root) {
        boolean isValid = true;
        int min = root.val, max = root.val;
        if (root.left != null) {
            var leftRes = dfs(root.left);
            //noinspection ConstantValue
            isValid &= leftRes.isValid;
            isValid &= leftRes.max < root.val;
            min = leftRes.min;
        }
        if (root.right != null) {
            var rightRes = dfs(root.right);
            isValid &= rightRes.isValid;
            isValid &= rightRes.min > root.val;
            max = rightRes.max;
        }
        return new SubtreeData(isValid, min, max);
    }

    public static void main(String[] args) {
        var root = new TreeNode(2, new TreeNode(1), new TreeNode(3));
        System.out.println(isValidBST(root));
    }
}
