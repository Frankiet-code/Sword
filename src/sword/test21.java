package sword;

import java.util.Stack;

/**
 * @Author: lei
 * @Data: 2020.3.31 15:07
 * @Description: 栈的压入、弹出序列
 * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。
 * 假设压入栈的所有数字均不相等。例如序列1,2,3,4,5是某栈的压入顺序，序列4,5,3,2,1是该压栈序列对应的一个弹出序列，
 * 但4,3,5,1,2就不可能是该压栈序列的弹出序列。（注意：这两个序列的长度是相等的）
 */
public class test21 {
	public static void main(String[] args) {
		int[] nums1 = {1,2,3,4,5}, nums2 = {4,5,3,2,1}, nums3 = {4,3,5,1,2};
		System.out.println(IsPopOrder(nums1, nums2));
	}
	
	//思路，模拟一下，如果最后为空，那么就正确了
	public static boolean IsPopOrder(int [] pushA, int [] popA) {
		if(pushA == null && popA == null) return true;
		if(pushA == null || popA == null || pushA.length != popA.length) return false;
		Stack<Integer> stack = new Stack<>();
		for(int i = 0, j = 0;i < pushA.length;){
			if(stack.isEmpty()) stack.push(pushA[i++]);  //压入第一个元素
			while(i < pushA.length && (!stack.isEmpty()) && stack.peek() != popA[j]){
				stack.push(pushA[i++]);  //不匹配的时候，不停压栈
			}
			while(!stack.isEmpty() && stack.peek() == popA[j]){
				stack.pop();j++;         //匹配的时候，不停出栈
				if(stack.isEmpty() && j == pushA.length)
					return true;         //出栈的过程中会出现唯一成功的地方
			}
		}
		return false;
	}
}//class end
