package sword;

/**
 * @Author: lei
 * @Data: 2020.3.31 13:20
 * @Description: 反转链表
 */
public class test15 {
	public static void main(String[] args) {
		ListNode head = new ListNode(0);
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		head.next = node1; node1.next = node2; node2.next = node3;
		ListNode res = ReverseList(head);
		print(res);  // 3 2 1 0
	}
	
	//使用虚拟节点+头插法来做
	public static ListNode ReverseList(ListNode head) {
		ListNode dummy = new ListNode(-1);
		ListNode p = head;
		while(p != null){
			ListNode temp = dummy.next;  //记录dummy.next
			dummy.next = p;  //头插入新节点
			p = p.next;
			dummy.next.next = temp;  //连接上之前的节点
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
