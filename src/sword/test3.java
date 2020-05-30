package sword;

import java.util.ArrayList;

/**
 * @Author: lei
 * @Data: 2020.3.30 21:25
 * @Description: 从尾到头打印链表
 * 输入一个链表，按链表从尾到头的顺序返回一个ArrayList。
 */
public class test3 {
	public static void main(String[] args) {
		ListNode head = new ListNode(0);
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		head.next = node1; node1.next = node2; node2.next = node3;
		ArrayList<Integer> res = printListFromTailToHead(head);
		System.out.println(res);  //[3, 2, 1, 0]
	}
	
	//使用 list.add(0, val) 直接插在最前面
	public static ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
		ArrayList<Integer> list = new ArrayList<>();
		ListNode p = listNode;
		while(p != null) {
			list.add(0, p.val);
			p = p.next;
		}
		return list;
	}
	
	public static class ListNode {
        int val;
        ListNode next;

       ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }
}//class end
