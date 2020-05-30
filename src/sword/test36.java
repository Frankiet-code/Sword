package sword;

/**
 * @Author: lei
 * @Data: 2020.4.1 14:49
 * @Description: 两个链表的公共节点
 * 输入两个链表，找出它们的第一个公共结点。
 * （注意因为传入数据是链表，所以错误测试数据的提示是用其他方式显示的，保证传入数据是正确的）
 */
public class test36 {
	public static void main(String[] args) {
		System.out.println("There is no cases...");
	}
	
	//思路，使用两个指针，从两个的链表头结点分别出发，当一个到达了末尾之后，再换到对方的开头，最后它们会相聚到一块
	public static ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
		if(pHead1 == null || pHead2 == null) return null;
		ListNode p1 = pHead1, p2 = pHead2;
		while(p1 != p2){
			p1 = p1.next;p2 = p2.next;
			if(p1 != p2){ //这里必须要判断一次p1和p2是否相等。如果两个相等，且都是null，那么就无限循环了
				if(p1 == null) p1 = pHead2;
				if(p2 == null) p2 = pHead1;
			}
		}
		return p1;
	}
	
	static class ListNode {
		int val;
		ListNode next = null;
		
		ListNode(int val) {
			this.val = val;
		}
	}
}//class end
