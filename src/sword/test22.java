package sword;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: lei
 * @Data: 2020.3.31 15:35
 * @Description: 从上往下打印出二叉树的每个节点，同层节点从左至右打印。
 */
public class test22 {
	public static void main(String[] args) {
		TreeNode root = new TreeNode(0);
		TreeNode l1 = new TreeNode(1);
		TreeNode r1 = new TreeNode(2);
		TreeNode l2 = new TreeNode(3);
		TreeNode r2 = new TreeNode(4);
		root.left = l1;root.right = r1;
		r1.left = l2;r1.right = r2;
		System.out.println(PrintFromTopToBottom(root)); //[0, 1, 2, 3, 4]
	}
	
	//层序遍历的非递归用队列，先序遍历的非递归用栈
	public static ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
		ArrayList<Integer> list = new ArrayList<>();
		if(root == null) return list;
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		while(!queue.isEmpty()){
			ArrayList<TreeNode> node_list = new ArrayList<>();
			while(!queue.isEmpty())
				node_list.add(queue.poll());
			for(TreeNode node : node_list) {
				list.add(node.val);
				if(node.left != null)
					queue.add(node.left);  //null就不需要添加进去了
				if(node.right != null)
					queue.add(node.right);
			}
		}
		return list;
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
