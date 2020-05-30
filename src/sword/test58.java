package sword;

/**
 * @Author: lei
 * @Data: 2020.4.3 14:04
 * @Description: 对称的二叉树
 * 请实现一个函数，用来判断一颗二叉树是不是对称的。
 * 注意，如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。
 */
public class test58 {
	public static void main(String[] args) {
		System.out.println("There is no test cases...");
	}
	
	//思路：判断根节点是不是为空，是就返回true，不然就比较左右孩子
	public static boolean isSymmetrical(TreeNode pRoot){
		if(pRoot == null) return true;
		return isSame(pRoot.left, pRoot.right);
	}
	
	public static boolean isSame(TreeNode root1, TreeNode root2){
		if(root1 == null && root2 == null) return true;
		if(root1 == null) return false;
		if(root2 == null) return false;
		if(root1.val != root2.val) return false;
		return isSame(root1.left, root2.right) && isSame(root1.right, root2.left);
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
