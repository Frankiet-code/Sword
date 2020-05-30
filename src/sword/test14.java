package sword;

/**
 * @Author: lei
 * @Data: 2020.3.31 12:27
 * @Description: 链表中倒数第k个节点
 */
public class test14 {
	public static void main(String[] args) {
		ListNode head = new ListNode(0);
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		head.next = node1; node1.next = node2; node2.next = node3;
		System.out.println(FindKthToTail_2(head, 2).val); //2
	}
	
	//第一种方式，使用快慢指针，fast先走k-1步，slow再开始走
	public static ListNode FindKthToTail(ListNode head, int k) {
		if(k < 1) return null;
		ListNode fast = head, slow = head;
		while(k > 0){
			if(fast == null)
				return null;
			fast = fast.next;
			k --;
		}
		while(fast != null){
			fast = fast.next;
			slow = slow.next;
		}
		return slow;
	}
	
	//第二种方式，先计算出链表的长度len，然后找第len-k+1个节点
	public static ListNode FindKthToTail_2(ListNode head, int k) {
		if(k < 1) return null;
		int len = 0;
		ListNode p = head;
		while(p != null) {
			p = p.next;
			len ++;
		}
		if(len < k) return null;
		p = head;
		for(int i = len-k;i > 0;i--){
			p = p.next;
		}
		return p;
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
	public static void print(test15.ListNode node){
		while(node != null){
			System.out.print(node.val + " ");
			node = node.next;
		}
	}
}//class end
