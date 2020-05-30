package sword;

/**
 * @Author: lei
 * @Data: 2020.3.30 21:31
 * @Description: 重建二叉树
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
 * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * 例如输入前序遍历序列{1, 2,4,7,  3,5,6,8}
 * 和中序遍历序列{4,7,2, 1 ,5,3,8,6}，则重建二叉树并返回。
 */
public class test4 {
	public static void main(String[] args) {
		System.out.println("There is no test cases...");
	}
	
	//前序：根节点->左子树->右子树；中序：左子树->根节点->右子树；后序：左子树->右子树->根节点；
	//那个根据前序遍历第一个是根节点；然后找到这个根节点在中序遍历中的位置，该位置左边是它的左子树，右边是它的右子树
	//之后，对左子树和右子树照样递归的做
	public static TreeNode reConstructBinaryTree(int [] pre,int [] in) {
		if(pre == null || pre.length == 0 || in == null || in.length == 0 || pre.length != in.length) return null;
		return helper(pre, 0, pre.length - 1, in, 0, in.length - 1);
	}
	
	public static TreeNode helper(int [] pre, int p_start, int p_end, int [] in, int i_start, int i_end){
		if(p_start > p_end || i_start > i_end) return null;  //结束标志
		TreeNode root = new TreeNode(pre[p_start]);
		int count = 0;  //记录左子树一共有多少个元素
		for(int i = i_start; i <= i_end;i++){
			if(in[i] != pre[p_start]) count++;
			else break;
		}
		root.left =  helper(pre, p_start + 1, p_start + count, in, i_start, i_start+count-1);
		root.right = helper(pre, p_start+count+1,  p_end, in,i_start+count+1, i_end);
		return root;
	}
	
	//树结点的类
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		public TreeNode(int x) {
			this.val = x;
			this.left = null;
			this.right = null;
		}
	}
}//class end
