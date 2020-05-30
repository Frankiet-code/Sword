package sword;

/**
 * @Author: lei
 * @Data: 2020.4.2 17:04
 * @Description: 二叉树的下一个结点
 * 给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。
 * 注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。
 */
public class test57 {
	public static void main(String[] args) {
		System.out.println("There is no test cases...");
	}
	
	//思路：分情况讨论
	//首先：如果这个节点有右子树，那么就是右子树中的最左边的那个孩子；如果是根节点，且只有左子树，那么就是null
	//之后：如果这个节点是叶子节点，且是它父亲节点的右孩子，那么下个节点就是它的爷爷节点；
	//最后：如果这个节点是叶子节点，且是它父亲节点的左孩子，那么下个节点就是父亲节点。
	public static TreeLinkNode GetNext(TreeLinkNode pNode){
		if(pNode == null) return null;
		//第一种情况：如果有右子树，那么就找右子树中的最左节点
		if(pNode.right != null){
			pNode = pNode.right;
			while(pNode.left != null) pNode = pNode.left;
			return pNode;
		}
		//如果没有右子树，那么找第一个当前节点是父节点左孩子的节点
		while(pNode.next != null){
			if(pNode.next.left == pNode) return pNode.next;  //当前这个点就是它父亲节点的左孩子
			pNode = pNode.next;  //往上继续找。至少pNode是它父亲节点的左孩子
		}
		return null;
	}
	
	static class TreeLinkNode {
		int val;
		TreeLinkNode left = null;
		TreeLinkNode right = null;
		TreeLinkNode next = null;  //指向父亲节点
		
		TreeLinkNode(int val) {
			this.val = val;
		}
	}
}//class end
