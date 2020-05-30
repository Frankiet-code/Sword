package sword;

import java.util.ArrayList;

/**
 * @Author: lei
 * @Data: 2020.3.31 15:56
 * @Description: 二叉树中和为某一值的路径
 * 输入一颗二叉树的根节点和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。
 * 路径定义为: 从树的 根结点 开始往下一直到 叶结点 所经过的结点形成一条路径。
 * (注意: 在返回值的list中，数组长度大的数组靠前)
 */
public class test24 {
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		TreeNode l1 = new TreeNode(3);
		TreeNode r1 = new TreeNode(4);
		TreeNode l2 = new TreeNode(3);
		TreeNode r2 = new TreeNode(2);
		TreeNode l3 = new TreeNode(0);
		TreeNode r3 = new TreeNode(1);
		TreeNode k = new TreeNode(1);
		root.left = l1;root.right = r1;
		l1.left = l2;l1.right = r2;r1.left = l3;r1.right = r3;
		l3.left = k;
		System.out.println(FindPath(root, 6));
	}
	
	static ArrayList<ArrayList<Integer>> res = new ArrayList<>();  //存放的返回结果
	static ArrayList<Integer> path = new ArrayList<>();   //存放当前遍历到的那个路径
	
	public static ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
		if(root == null) return res;
		helper(root, target, 0);
		sort(res);  //按照列表长度大小排序
		return res;
	}
	
	//冒泡排序
	private static void sort(ArrayList<ArrayList<Integer>> res) {
		for(int i = 0;i < res.size()-1;i++){
			for(int j = i+1;j < res.size()-1;j++){
				if(res.get(i).size() < res.get(j).size()){
					ArrayList<Integer> temp = res.get(j);
					res.set(j, res.get(i));
					res.set(i, temp);
				}
			}
		}
	}
	
	//sum代表之前走过的路径中所有节点的val的和，思路，使用sum来记录之前走过的路径上的val的和，如果到达了叶子节点，就看能不能达到target
	public static void helper(TreeNode node, int target, int sum){
		path.add(node.val);
		sum += node.val;
		//因为必须是到叶子节点才可以结束
		if(node.left == null && node.right == null && sum == target){
			ArrayList<Integer> list = new ArrayList<>(); //这里必须要新建一个list，再把path中的数据转移进去
			for(Integer val : path)  //因为如果是res.add(path)的话，实际只是将path的引用传了进去，
				list.add(val);       //path后来发生变化,那res中的数据就会跟着变化
			res.add(list);
		}
		if(node.left != null)
			helper(node.left, target, sum);
		if(node.right != null)
			helper(node.right, target, sum);
		path.remove(path.size() - 1); //删除最后一个节点，因为它是叶子节点，避免给影响到其他的路径中找
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
