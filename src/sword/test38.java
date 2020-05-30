package sword;

/**
 * @Author: lei
 * @Data: 2020.4.1 15:07
 * @Description: 二叉树的深度
 * 输入一棵二叉树，求该树的深度。
 * 从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路径，最长路径的长度为树的深度
 */
public class test38 {
	public static void main(String[] args) {
		System.out.println("There is no cases...");
	}
	
	//思路：max(左子树深度，右子树深度）+ 1
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
