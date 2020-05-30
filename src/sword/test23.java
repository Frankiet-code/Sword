package sword;

/**
 * @Author: lei
 * @Data: 2020.3.31 15:46
 * @Description: 二叉树的后序遍历
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。
 * 如果是则输出Yes,否则输出No。
 * 假设输入的数组的任意两个数字都互不相同。
 */
public class test23 {
	public static void main(String[] args) {
		int [] nums1 = {2,5,4,7,10,8,6};
		int [] nums2 = {2,5,4,7,10,8,9};
		int [] nums3 = {7,4,6,5};
		System.out.println(VerifySequenceOfBST(nums1)); //true
		System.out.println(VerifySequenceOfBST(nums2)); //false
		System.out.println(VerifySequenceOfBST(nums3)); //false
	}
	
	//BST的后序遍历的特点
	public static boolean VerifySequenceOfBST(int [] sequence) {
		if(sequence == null || sequence.length == 0)
			return false;  //空树也返回false
		return helper(sequence, 0, sequence.length-1);
	}
	
	//思路：根据末尾的节点（根节点）的值，将前面的数据划分成为两个部分。
	//验证1：两个部分中，我们找到第一个都比root点小的数组，然后检查另外一部分是否都比root大
	//验证2：这两个部分又是否分别都是BST
	private static boolean helper(int[] nums, int start, int end) {
		if(start >= end)
			return true;
		int root = nums[end], i = start;
		//首先，从头开始找，比root小的那部分，是后面要验证的左子树 [start, i-1]
		while(i < end){
			if(nums[i] > root)
				break;
			i++;
		}
		//然后再看后半部分是不是都比root要大
		for(int j = i;j < end;j++){
			if(nums[j] < root)
				return false;
		}
		return helper(nums, start, i-1) && helper(nums, i, end-1);
	}
}//class end
