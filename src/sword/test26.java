package sword;

/**
 * @Author: lei
 * @Data: 2020.3.31 20:40
 * @Description: 二叉搜索树与双向链表
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。
 * 要求不能创建任何新的结点，只能调整树中结点指针的指向。
 */
public class test26 {
	public static void main(String[] args) {
		System.out.println("There is no test cases...");
	}

	static TreeNode head;
	
	public static TreeNode Convert(TreeNode pRootOfTree) {
		if(pRootOfTree == null) return null;
		Convert(pRootOfTree.right);
		if(head == null)
			head = pRootOfTree;
		else {
			pRootOfTree.right = head;
			head.left = pRootOfTree;
			head = pRootOfTree;
		}
		Convert(pRootOfTree.left);
		return head;
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
