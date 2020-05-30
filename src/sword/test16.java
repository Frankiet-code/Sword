package sword;

/**
 * @Author: lei
 * @Data: 2020.3.31 13:28
 * @Description: 合并两个排序（单调递增）的链表
 */
public class test16 {
	public static void main(String[] args) {
		ListNode list1 = new ListNode(1);
		ListNode p1 = new ListNode(4);
		ListNode p2 = new ListNode(6);
		ListNode p3 = new ListNode(9);
		ListNode p4 = new ListNode(10);
		list1.next = p1;p1.next = p2;p2.next = p3;p3.next=p4;
		
		ListNode list2 = new ListNode(-1);
		ListNode q1 = new ListNode(3);
		ListNode q2 = new ListNode(20);
		ListNode q3 = new ListNode(328);
		list2.next = q1;q1.next=q2;q2.next=q3;
		
		ListNode res = Merge(list2, list1 );
		print(res); // -1 -> 1 -> 3 -> 4 -> 6 -> 9 -> 10 -> 20 -> 328
	}
	
	//只要有一个不为null就可以继续添加元素
	public static ListNode Merge(ListNode list1, ListNode list2) {
		ListNode p1 = list1, p2 = list2;
		ListNode dummy = new ListNode(-1), p = dummy;
		while(p1 != null || p2 != null){
			if(p1 == null){
//				p.next = new ListNode(p2.val); 新建节点，保留了原来的链表
				p.next = p2;   //使用原来的节点
				p2 = p2.next;
			}
			else if(p2 == null) {
//				p.next = new ListNode(p1.val);
				p.next = p1;
				p1 = p1.next;
			}
			else{
				if(p1.val < p2.val){
//					p.next = new ListNode(p1.val);
					p.next = p1;
					p1 = p1.next;
				}
				else {
//					p.next = new ListNode(p2.val);
					p.next = p2;
					p2 = p2.next;
				}
			}
			p = p.next;
		}
		return dummy.next;
	}
	
	public static class ListNode {
		int val;
		ListNode next;
		
		ListNode(int val) {
			this.val = val;
			this.next = null;
		}
	}
	
	//print the list
	public static void print(ListNode node){
		while(node != null){
			System.out.print(node.val);
			if(node.next != null)
				System.out.print(" -> ");
			node = node.next;
		}
	}
}//class end
