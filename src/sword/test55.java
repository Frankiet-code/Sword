package sword;

/**
 * @Author: lei
 * @Data: 2020.4.2 15:16
 * @Description: 链表中环的入口节点
 */
public class test55 {
	public static void main(String[] args) {
		ListNode head1 = new ListNode(0);
		ListNode r1 = new ListNode(1);
		ListNode r2 = new ListNode(2);
		ListNode r3 = new ListNode(3);
		ListNode r4 = new ListNode(4);
		ListNode r5 = new ListNode(5);
		ListNode r6 = new ListNode(6);
		head1.next = r1;r1.next = r2;r2.next = r3;r3.next = r4;r4.next = r5;r5.next = r6;r6.next = r3;
		System.out.println(EntryNodeOfLoop(head1).val);    // 3
	}
	
	//打印链表
	public static void print(ListNode p, String name){
		System.out.print(name + ": ");
		while(p != null){
			System.out.print(p.val);
			if(p.next != null)
				System.out.print(" -> ");
			p = p.next;
		}
		System.out.println();
	}
	
	//思路，观察快慢指针的运行速度，推导出公式
	public static ListNode EntryNodeOfLoop(ListNode pHead){
		if(pHead == null || pHead.next == null) return null;
		ListNode fast = pHead, slow = pHead;
		while(fast != null && slow != null) {
			fast = fast.next.next;
			slow = slow.next;
			if(fast == slow)  //只能在这里面判断，不可以写到while后面，因为fast和slow刚开始的时候就是相等的
				break;
		}
		if(fast == null) return null;
		fast = pHead;
		while(fast != slow){
			fast = fast.next;
			slow = slow.next;
		}
		return fast;
	}
	
	static class ListNode{
		int val;
		ListNode next;
		
		public ListNode(int x){
			this.val = x;
			this.next = null;
		}
	}
}//class end
