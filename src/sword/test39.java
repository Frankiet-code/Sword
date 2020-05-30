package sword;

/**
 * @Author: lei
 * @Data: 2020.4.1 15:09
 * @Description: 平衡二叉树
 * 输入一棵二叉树，判断该二叉树是否是平衡二叉树。
 */
public class test39 {
	public static void main(String[] args) {
		System.out.println("There is no cases...");
	}
	
	//思路：平衡二叉树的特点：左右子树的深度之差小于等于1.同时根节点的值大于左子树中的值，小于右子树中的值
	public static boolean IsBalanced_Solution(TreeNode root) {
		if(root == null) return true;
		int depth_abs = Math.abs(TreeDepth(root.left) - TreeDepth(root.right)); //两个子树的高度差
		if(depth_abs > 1) return false;
		boolean left_is_b = (root.left == null) || IsBalanced_Solution(root.left);
		boolean right_is_b = (root.right == null) || IsBalanced_Solution(root.right);
		return left_is_b && right_is_b;
	}
	
	public static int TreeDepth(TreeNode root) {
		if(root == null) return 0;
		int left = TreeDepth(root.left);
		int right = TreeDepth(root.right);
		return Math.max(left, right) + 1;
	}
	
	static class TreeNode {
		int val = 0;
		TreeNode left = null;
		TreeNode right = null;
		
		public TreeNode(int val) {
			this.val = val;
		}
	}
}//class end
