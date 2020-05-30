package sword;

import java.util.ArrayList;

/**
 * @Author: lei
 * @Data: 2020.3.31 14:56
 * @Description: 包含min函数的栈
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））。
 * 注意：保证测试中不会当栈为空的时候，对栈调用pop()或者min()或者top()方法。
 */
public class test20 {
	
	ArrayList<Integer> list;  //作为自定义stack的物理结构
	int min;                  //存放当前的最大数据
	
	//初始化函数
	public test20(){
		this.list = new ArrayList<>();
		this.min = Integer.MAX_VALUE;
	}
	
	//往栈中压入元素时，如果要更新min（或者当前这个数等于min），那么还要把之前的min压进去
	public void push(int x){
		if(x <= min){
			list.add(min);
			list.add(x);
			min = x;
		}
		else
			list.add(x);
	}
	
	//出栈的时候，如果出去的是min，那么就要再出一个，更新min。
	public int pop(){
		int temp = list.get(list.size()-1);
		list.remove(list.size()-1);
		if(temp == min){
			min = list.get(list.size()-1);
			list.remove(list.size()-1);
		}
		return temp;
	}
	
	public int top(){
		return list.get(list.size() - 1);
	}
	
	public int min(){
		return min;
	}
	
	public static void main(String[] args) {
		test20 myStack = new test20();
		myStack.push(1);
		myStack.push(2);
		myStack.push(3);
		System.out.println(myStack.min());  // 1
		int out = myStack.pop();
		System.out.println("out = " + out); //out = 3
		System.out.println(myStack.min());  //1
		myStack.push(0);
		System.out.println(myStack.min());  //0
		myStack.push(3);
		System.out.println(myStack.top());  //3
	}
}//class end
