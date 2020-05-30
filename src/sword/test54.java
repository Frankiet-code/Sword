package sword;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: lei
 * @Data: 2020.4.2 15:05
 * @Description: 字符流中第一个不重复的字符
 * 请实现一个函数用来找出字符流中第一个只出现一次的字符。
 * 例如，当从字符流中只读出前两个字符"go"时，第一个只出现一次的字符是"g"。
 * 当从该字符流中读出前六个字符“google"时，第一个只出现一次的字符是"l"。
 * 如果没有的话，就返回 #
 */
public class test54 {
	public static void main(String[] args) {
		Insert('q');
		Insert('w');
		Insert('e');
		Insert('t');
		Insert('r');
		Insert('o');
		Insert('q');
		Insert('w');
		System.out.println(FirstAppearingOnce());  //e
	}
	
	static int [] count = new int[128];  //记录128个字符出现的次数
	
	static Queue<Character> queue = new LinkedList<>();  //队列，存放之前没有出现过的新加入的元素
	
	//Insert one char from stringStream
	public static void Insert(char ch) {
		if(count[ch] == 0){
			queue.add(ch);count[ch] ++;
		}
		else
			count[ch] ++;
	}
	
	//return the first appearance once char in current stringStream
	public static char FirstAppearingOnce() {
		while(!queue.isEmpty()){
			char c = queue.peek();
			if(count[c] == 1)
				return c;
			else
				queue.poll();
		}
		return '#';
	}
}//class end
