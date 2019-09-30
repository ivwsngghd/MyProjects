package leetcode;

public class _104__maxDepth {
    public int maxDepth(TreeNode root) {
        if(root == null) return 0;
        return Math.max(maxDepth(root.left)+1,maxDepth(root.right)+1);

    }

    public static void main(String[] args) {

    }
}

class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
}
