package sword;

/**
 * @Author: lei
 * @Data: 2020.4.2 15:35
 * @Description: 删除链表中的重复节点
 * 在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。
 * 例如，链表1->2->3->3->4->4->5 处理后为 1->2->5
 */
public class test56 {
	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		ListNode r1 = new ListNode(1);
		ListNode r2 = new ListNode(2);
		ListNode r3 = new ListNode(3);
		ListNode r4 = new ListNode(3);
		ListNode r5 = new ListNode(4);
		ListNode r6 = new ListNode(5);
		ListNode r7 = new ListNode(5);
		head.next = r1;r1.next = r2;r2.next = r3;r3.next = r4;
		r4.next = r5;r5.next = r6;r6.next = r7;
		print(head, "oldHead");
		print(deleteDuplication(head), "newHead");
	}
	
	//思路：记录每次遍历到的那个节点，是否和之后的节点相同
	public static ListNode deleteDuplication(ListNode pHead){
		if(pHead == null || pHead.next == null) return pHead;
		ListNode dummy = new ListNode(-1);
		dummy.next = pHead;
		ListNode pre = dummy, cur = pre.next;
		while(cur != null && cur.next != null){
			boolean remove = false;   //开始时没有重复，不需要删除
			while(cur.next != null && cur.val == cur.next.val){
				cur = cur.next;   //cur指向的是重复元素中的最后一个
				remove = true;
			}
			if(remove){
				cur = cur.next;
				pre.next = cur;
			}
			else{
				pre = pre.next;   //越过不重复的元素，内部的指针关系依旧在
				cur = cur.next;
			}
		}
		return dummy.next;
	}
	
	//首先做一下链表的复制（不创建虚拟节点外的任何的节点）
	//其实写这个的目的，就是方便后来加入remove的判断和它为真的时候的情况
	public static ListNode copy(ListNode head){
		if(head == null || head.next == null) return head;
		ListNode dummy = new ListNode(-1);
		dummy.next = head;
		ListNode pre = dummy, cur = pre.next;
		while(cur != null && cur.next != null){
			boolean remove = false;
			if(!remove){
				pre = pre.next;  //直接越过不重复的元素
				cur = cur.next;  //cur也是直接后移
			}
		}
		return dummy.next;
	}
	
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
	
	static class ListNode{
		int val;
		ListNode next;
		
		public ListNode(int x){
			this.val = x;
			this.next = null;
		}
	}
}//class end
