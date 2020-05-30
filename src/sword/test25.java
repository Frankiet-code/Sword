package sword;

import java.util.HashMap;

/**
 * @Author: lei
 * @Data: 2020.3.31 17:00
 * @Description: 复杂链表的复制
 * 输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，另一个特殊指针指向任意一个节点），
 * 返回结果为复制后复杂链表的head。（注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）
 */
public class test25 {
	public static void main(String[] args) {
		System.out.println("There is no cases...");
	}
	
	//思路：先完整地复制节点值和next域，并且建立新旧节点之间的一一映射关系
	//之后，再遍历旧链表，然后将random域的信息对应到新链表中去，
	public static RandomListNode Clone(RandomListNode pHead){
		if(pHead == null) return null;
		HashMap<RandomListNode, RandomListNode> map = new HashMap<>(); //旧节点，新节点
		RandomListNode p = pHead;
		RandomListNode newHead = new RandomListNode(p.label), q = newHead;
		map.put(p, q);
		while(p.next != null){
			q.next = new RandomListNode(p.next.label);
			p = p.next;
			q = q.next;
			map.put(p, q);
		}
		p = pHead;
		while(p != null){
			map.get(p).random = map.get(p.random);
			p = p.next;
		}
		return newHead;
	}
	
	static class RandomListNode {
		int label;
		RandomListNode next = null;
		RandomListNode random = null;
		
		RandomListNode(int label) {
			this.label = label;
		}
	}
}//class end
