package sword;

import java.util.Stack;

/**
 * @Author: lei
 * @Data: 2020.3.30 22:11
 * @Description: 用两个栈来实现队列
 * 用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
 */
public class test5 {
	
	Stack<Integer> stack1 = new Stack<>();
	Stack<Integer> stack2 = new Stack<>();
	
	public test5(){	}
	
	//将一个栈里的元素都弹出去，然后再压x，之后再将之前出去的元素压回来
	//从而保证，每次最新进来的元素都在栈底，最后才可以出去。
	public void push(int x){
		while(!stack1.isEmpty()){
			stack2.push(stack1.pop());
		}
		stack1.push(x);
		while(!stack2.isEmpty()){
			stack1.push(stack2.pop());
		}
	}
	
	public int pop(){
		if(stack1.isEmpty())
			return -1;
		else
			return stack1.pop();
	}
	
	public int peek(){
		return stack1.peek();
	}
	
	public static void main(String[] args) {
		test5 queue = new test5();
		queue.push(1);
		queue.push(2);
		queue.push(3);
		queue.push(4);
		System.out.println(queue.pop()); //1
		System.out.println(queue.pop()); //2
		queue.push(5);
		System.out.println(queue.pop()); //3
	}
}//class end
