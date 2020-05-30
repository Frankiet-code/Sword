package sword;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: lei
 * @Data: 2020.4.5 11:17
 * @Description: 把二叉树打印成多行
 * 从上到下按层打印二叉树，同一层结点从左至右输出。每一层输出一行。
 */
public class test60 {
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		TreeNode r2 = new TreeNode(2);
		TreeNode r3 = new TreeNode(3);root.left = r2;root.right = r3;
		TreeNode r4 = new TreeNode(4);
		TreeNode r5 = new TreeNode(5);r2.left = r4;r2.right = r5;
		TreeNode r6 = new TreeNode(6);
		TreeNode r7 = new TreeNode(7);r3.left = r6;r3.right = r7;
		ArrayList<ArrayList<Integer>> res = Print(root);
		System.out.println(res);
	}
	
	//其实就是二叉树的层序遍历
	public static ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<>();
		if(pRoot == null) return res;
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(pRoot);
		while(!queue.isEmpty()) {
			ArrayList<TreeNode> nodes = new ArrayList<>();
			ArrayList<Integer> list = new ArrayList<>();
			while(!queue.isEmpty())	nodes.add(queue.poll());
			for(TreeNode node : nodes){
				list.add(node.val);
				if(node.left != null) queue.add(node.left);
				if(node.right != null) queue.add(node.right);
			}
			res.add(list);
		}
		return res;
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
