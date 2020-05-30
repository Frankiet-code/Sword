package sword;

import java.util.ArrayList;

/**
 * @Author: lei
 * @Data: 2020.4.5 12:43
 * @Description: 二叉搜索树的第k个结点
 * 给定一棵二叉搜索树，请找出其中的第k小的结点。
 * 例如， （5，3，7，2，4，6，8）    中，按结点数值大小顺序第三小结点的值为4。
 */
public class test62 {
	public static void main(String[] args) {
		TreeNode root = new TreeNode(5);
		TreeNode r2 = new TreeNode(3);
		TreeNode r3 = new TreeNode(7);root.left = r2;root.right = r3;
		TreeNode r4 = new TreeNode(2);
		TreeNode r5 = new TreeNode(4);r2.left = r4;r2.right = r5;
		TreeNode r6 = new TreeNode(6);
		TreeNode r7 = new TreeNode(8);r3.left = r6;r3.right = r7;
		System.out.println(KthNode(root, 2).val); //3
	}
	
	//使用中序遍历，然后找第k个节点
	public static TreeNode KthNode(TreeNode pRoot, int k){
		if(pRoot == null || k <= 0) return null;
		inOrder(pRoot, list);
		if(k > list.size())
			return null;
		return list.get(k-1);
	}
	
	static ArrayList<TreeNode> list = new ArrayList<>();
	
	public static void inOrder(TreeNode root, ArrayList<TreeNode> list){
		if(root == null) return;
		inOrder(root.left, list);
		list.add(root);
		inOrder(root.right, list);
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
