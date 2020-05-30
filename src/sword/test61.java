package sword;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: lei
 * @Data: 2020.4.5 11:25
 * @Description: 序列化二叉树
 * 请实现两个函数，分别用来序列化和反序列化二叉树
 * 二叉树的序列化是指：把一棵二叉树按照某种遍历方式的结果以某种格式保存为字符串，
 * 从而使得内存中建立起来的二叉树可以持久保存。
 * 序列化可以基于先序、中序、后序、层序的二叉树遍历方式来进行修改，序列化的结果是一个字符串，
 * 序列化时通过某种符号表示空节点（#），以 ！ 表示一个结点值的结束（value!）。
 *
 * 二叉树的反序列化是指：根据某种遍历顺序得到的序列化字符串结果str，重构二叉树。
 */
public class test61 {
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		TreeNode r2 = new TreeNode(20);
		TreeNode r3 = new TreeNode(+30);root.left = r2;root.right = r3;
		TreeNode r4 = new TreeNode(-40);
		r2.left = r4;
		//TreeNode r5 = new TreeNode(50);r2.left = r4;r2.right = r5;
		TreeNode r6 = new TreeNode(60);
		TreeNode r7 = new TreeNode(70);r3.left = r6;r3.right = r7;
		System.out.println(Serialize(root));
		TreeNode r = Deserialize(Serialize(root));
	}
	
	//按照层序遍历进行序列化: 1!20!30!40!#60!70!
	public static String Serialize(TreeNode root) {
		if(root == null) return null;
		StringBuilder sb = new StringBuilder();
		Queue<TreeNode> queue = new LinkedList<>();queue.add(root);
		int sum = 1; //代表queue中有多少非null节点
		while(!queue.isEmpty()){
			TreeNode node = queue.poll();
			if(node == null) sb.append('#');
			else{
				sb.append(node.val).append("!"); sum--;
				if(node.left != null) sum++;
				if(node.right != null) sum++;
				queue.add(node.left);   //碰到null，也要加到queue中。
				queue.add(node.right);
			}
			if(sum == 0) break;  //而当后面都不在有非null后，就不用再加到sb中了
		}
		return sb.toString();
	}
	
	//根据字符串：1!20!30!-40!#60!70!，恢复出原来的那棵树
	public static TreeNode Deserialize(String data) {
		if(data == null || data.length() == 0) return null;
		//首先，将data字符串中的数据都转换成一个个的TreeNode节点放到队列中去
		Queue<TreeNode> queue = new LinkedList<>();
		for(int i = 0;i < data.length();){
			if(data.charAt(i) == '#'){
				queue.add(null);i++;
			}
			else{
				int sum = 0, flag = 1;  //flag代表正负性
				if(data.charAt(i) == '-') {
					flag = -1; i++;
				}
				else if(data.charAt(i) == '+') i++;
				sum += (data.charAt(i) - '0');i++;
				while(data.charAt(i) != '!'){
					sum *= 10; sum += (data.charAt(i) - '0');i++;
				}
				queue.add(new TreeNode(sum * flag));
				i++;
			}
		}
		
		//第二步，将queue中的节点恢复成树
		TreeNode root = queue.poll();
		Queue<TreeNode> pre = new LinkedList<>(); //使用队列存放上一层的节点，也就是需要初始化左右节点的那些节点
		pre.add(root);
		while(!queue.isEmpty()){
			ArrayList<TreeNode> list = new ArrayList<>();  //存放非null的父节点的下一层的所有节点
			while(!pre.isEmpty()){
				TreeNode parent = pre.poll();
				if(parent != null){
					parent.left = queue.poll();
					list.add(parent.left);
					parent.right = queue.poll();
					list.add(parent.right);
				}
			}
			pre.addAll(list);
		}
		return root;
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
