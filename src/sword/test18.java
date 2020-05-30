package sword;

/**
 * @Author: lei
 * @Data: 2020.3.31 14:20
 * @Description: 二叉树的镜像
 * 操作给定的二叉树，将其变换为源二叉树的镜像。
 * 二叉树的镜像定义：源二叉树
 *     	    8
 *     	   /  \
 *     	  6   10
 *     	 / \  / \
 *     	5  7 9 11
 *     	镜像二叉树
 *     	    8
 *     	   /  \
 *     	  10   6
 *     	 / \  / \
 *     	11 9 7  5
 */
public class test18 {
	public static void main(String[] args) {
		System.out.println("There is no test cases...");
	}
	
	//分左右子树是否为空来讨论
	public static void Mirror(TreeNode root) {
		if(root == null || (root.left == null && root.right == null)) return;
		if(root.left == null){
			root.left = root.right;root.right = null;Mirror(root.left);
		}
		else if(root.right == null){
			root.right = root.left;root.left = null;Mirror(root.right);
		}
		else{
			TreeNode temp = root.left;root.left = root.right;root.right = temp;Mirror(root.left);Mirror(root.right);
		}
	}
	
	//上述方法的简写
	public static void Mirror_2(TreeNode root) {
		if(root == null || (root.left == null && root.right == null)) return;
		TreeNode temp = root.left;
		root.left = root.right;
		root.right = temp;
		Mirror(root.left);
		Mirror(root.right);
	}
	
	static class TreeNode{
		int val;
		TreeNode left;
		TreeNode right;
		
		public TreeNode(){
		}
		
		public TreeNode(int x){
			this.val = x;
			this.left = null;
			this.right = null;
		}
	}
}//class end
