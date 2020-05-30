package sword;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @Author: lei
 * @Data: 2020.4.5 10:26
 * @Description: 按之字形打印二叉树
 * 请实现一个函数按照之字形打印二叉树，
 * 即第一行按照从左到右的顺序打印，第二层按照从右至左的顺序打印，
 * 第三行按照从左到右的顺序打印，其他行以此类推。
 */
public class test59 {
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
	
	//采用栈的方式,并且用change变量改变先压左子树还是先压右子树这个顺序。
	//          1
	//       2      3
	//    4   5   6   7
	//首先，栈里面是1;全部出栈，list={1}，然后依次将2 3压栈
	//之后，3出栈，2出栈，list={3,2}。依次将 7 6 5 4 压栈
	//最后，4 5 6 7依次出栈，list={4,5,6,7}
	public static ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<>();
		if(pRoot == null) return res;
		Stack<TreeNode> stack = new Stack<>();
		stack.add(pRoot);
		boolean change = false;
		while(!stack.isEmpty()){
			ArrayList<TreeNode> nodes = new ArrayList<>();
			ArrayList<Integer> list = new ArrayList<>();
			while (!stack.isEmpty())
				nodes.add(stack.pop());   //全部出栈
			for(TreeNode node : nodes){
				list.add(node.val);
				if(change){
					if(node.right != null) stack.push(node.right);
					if(node.left != null) stack.push(node.left);
				}
				else{
					if(node.left != null) stack.push(node.left);
					if(node.right != null) stack.push(node.right);
				}
			}
			res.add(list);
			change = !change;  //改变顺序
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
