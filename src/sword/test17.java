package sword;

/**
 * @Author: lei
 * @Data: 2020.3.31 13:48
 * @Description: 树的子结构
 * 输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
 */
public class test17 {
	public static void main(String[] args) {
		System.out.println("There is no test cases...");
	}
	
	//先将root2为null排除掉，再去检查root2在不在root1中.一定要注意递归的使用
	public boolean HasSubtree(TreeNode root1,TreeNode root2) {
		if(root1 == null || root2 == null)  //空树不是任意一个树的子结构
			return false;
		return containsTree(root1, root2) || HasSubtree(root1.left, root2) || HasSubtree(root1.right, root2);
	}
	
	public static boolean containsTree(TreeNode root1, TreeNode root2){
		if(root2 == null) return true;  //只要root2为空了，就可以返回true
		if(root1 == null) return false; //root2不为空，而root1为空的话，就只能返回false了
		if(root1.val == root2.val)
			return containsTree(root1.left, root2.left) && containsTree(root1.right, root2.right); //左右子树也要匹配
		else
			return false;
	}
	
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		
		TreeNode() { }
		
		TreeNode(int value) {
			super();
			this.val = value;
			this.left = null;
			this.right = null;
		}
		
		TreeNode(int value, TreeNode left, TreeNode right) {
			super();
			this.val = value;
			this.left = left;
			this.right = right;
		}
	}
}//class end
