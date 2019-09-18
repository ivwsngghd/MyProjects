package _唯品会面试;

public class __543_二叉树的直径 {
    private int max = 0;    //用于记录最大的直径；

    public int diameterOfBinaryTree(TreeNode root){
        deep(root);
        return max;
    }

    private int deep(TreeNode root){
        if (root == null) return 0;

        int leftDeep = deep(root.left);
        int rightDeep = deep(root.right);

        max = Math.max(leftDeep+rightDeep,max);     //记录最大直径
        return Math.max(leftDeep,rightDeep)+1;        //返回更深的子树的深度；
    }

}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}