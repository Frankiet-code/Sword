package sword;

import java.util.Stack;

/**
 * @Author: lei
 * @Data: 2020.4.6 13:51
 * @Description: 变式题：判断按照a的顺序入栈的时候，否能够按照b的顺序出栈
 */
public class test5_cg {
	public static void main(String[] args) {
		int [] a = {1,2,3,4,5};
		int [] b = {4,5,3,2,1};
		System.out.println(IsPopOrder(a, b));  //true
	}
	
	//借助辅助的栈，跟随出栈的方式来模拟压栈
	public static boolean IsPopOrder(int [] push, int [] pop){
		if(push.length != pop.length) return false; //两个数组的长度不一致，肯定非法
		Stack<Integer> stack = new Stack<>();
		int count = 0;  //记录已经出栈的元素个数,也是在指向pop数组中的元素
		for (int value : push) {
			stack.push(value);   //压入元素
			while (!stack.isEmpty() && stack.peek() == pop[count]) {
				stack.pop();
				count++; //比较栈顶元素是否是第一个出栈的，是的话，就出栈，然后count++
			}
		}
		return count == pop.length;
	}
}//class end
