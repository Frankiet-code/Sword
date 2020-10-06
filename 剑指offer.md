[TOC]

# 《剑指offer》试题总结

## 1.二维数组中的查找

>在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。AC

```java
//思路1：挨个比较，时间复杂度O(n*n)
public boolean Find(int target, int [][] array) {
    int rows = array.length, cols = array[0].length;
    for(int i = 0;i < rows;i++){
        for(int j = 0;j < cols;j++){
            if(array[i][j] == target){
                return true;
            }
        }
    }
    return false;
}

//思路2：从左下角或者右上角出发，然后判断当前的数和target的大小关系，时间复杂度O(rows+cols)
public boolean Find(int target, int [][] array) {
    int rows = array.length, cols = array[0].length;
    int i = 0, j = cols - 1;  //这里从右上角出发
    while(i < rows && j >= 0){
        if(array[i][j] == target){
            return true;
        }
        else if(array[i][j] < target){
            i++;   //当前的数太小了，就去下一行找target
        }
        else{
            j--;  //当前的数太大了，就去前一列中找target
        }
    }
    return false;
}

```

## 2.替换空格

>请实现一个函数，将一个字符串中的每个空格替换成“%20”。例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。 AC

```java
//思路：挨个判断，用一个StringBuilder对象来做记录
public String replaceSpace(StringBuffer str) {
    StringBuilder sb = new StringBuilder();
    for(int i = 0;i < str.length();i++){
        if(str.charAt(i) == ' '){
            sb.append("%20");
        }
        else{
            sb.append(str.charAt(i));
        }
    }
    return sb.toString();
}
```

## 3.从尾到头打印链表

>输入一个链表，按链表从尾到头的顺序返回一个ArrayList。 AC 

```java
//思路：利用add(index, val)函数，指定index为0，每次都添加到链表的头部
public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
    ArrayList<Integer> res = new ArrayList<>();
    ListNode node = listNode;
    while(node != null){
        res.add(0, node.val);
        node = node.next;
    }
    return res;
}
```

## 4.重建二叉树

>输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。 0.8AC

```java
//思路：前序遍历是根-左-右，中序遍历是左-根-右，根据前序遍历确定好根节点的val，然后
public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
    if(pre == null || pre.length == 0){
        return null;
    }
    return helper(pre, 0, pre.length-1, in, 0, in.length-1);
}

public TreeNode helper(int[] pre, int p_start, int p_end, int[] in, int i_start, int i_end){
    if(p_start > p_end || i_start > i_end){
        return null;
    }
    TreeNode root = new TreeNode(pre[p_start]);
    int i = i_start;
    while(i <= i_end){
        if(in[i] == root.val){
            break;
        }
        else{
            i++;  //如果不是，记得去查找下一个
        }
    }
    //左子树的节点个数：i-i_start
    //右子树的节点个数：i_end-i
    root.left  = helper(pre, p_start+1, p_start+i-i_start, in, i_start, i-1);
    root.right = helper(pre, p_start+i-i_start+1, p_end, in, i+1, i_end);
    return root;
}
```

## 5.用两个栈实现队列

>用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。

```java
import java.util.Stack;

//思路：实际存放数据的数据结构是栈1，而栈2只是用来辅助的。每次都新push进来的元素放到栈1的栈底，然后弹出栈顶。
public class Solution {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();
    
    public void push(int node) {
        while(!stack1.isEmpty()){
            stack2.push(stack1.pop());
        }
        stack1.push(node);
        while(!stack2.isEmpty()){
            stack1.push(stack2.pop());
        }
    }
    
    public int pop() {
        if(stack1.isEmpty()){
            return -1;
        }
        else{
            return stack1.pop();
        }
    }
}
```

## 6.选择数组的最小数字

>把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
>输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。
>例如数组[3,4,5,1,2]为[1,2,3,4,5]的一个旋转，该数组的最小值为1。
>NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。 0.4AC

```java
import java.util.ArrayList;

public class Solution {
    public int minNumberInRotateArray(int [] array) {
        if(array == null || array.length == 0) return 0;
        //使用二分法来做
        int left = 0, right = array.length-1;
        while(left < right){
            if(array[left] < array[right]){
                return array[left];   //[left, right]是非递减的序列，那么array[left]肯定是最小值了
            }
            int mid = left + ((right - left) >> 1);
            if(array[left] < array[mid]){
                left = mid;   //[left, mid]是非递减的序列，那么最小值只可能在[mid*, right]之间，因此要改变left为mid
            }
            else if(array[mid] < array[right]){
                right = mid;   //[mid, right]是非递减的序列，那么最小值只可能在[left, mid*]之前，因此要改变right为mid
            }
            else{
                left++;  //出现相等的情况的话，left就前移一位
            }
        }
        return array[left];
    }
}
```

## 7.斐波那契数列

>大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项（从0开始，第0项为0，第1项是1）。
>
>0 0 1 1 2 3 5 ...  AC

```java
//思路1：递归的写法
public int Fibonacci(int n) {
    if(n == 0) return 0;
    if(n == 1) return 1;
    return Fibonacci(n-1) + Fibonacci(n-2);
}

//思路2：非递归的写法
public int Fibonacci(int n) {
    if(n == 0) return 0;
    int first = 0, second = 1;
    while(n > 1){
        int temp = second;
        second += first;
        first = temp;
        n--;
    }
    return second;
}
```

## 8.跳台阶

>一只青蛙一次可以跳上1级台阶，也可以跳上2级。
>
>求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。

```java
//思路：递归式子：f(n) = f(n-1) + f(n-2)。
//代表的意思：最后一次可以跳1阶，也可以跳2阶，如果跳了1阶，那么之前的跳法是f(n-1)种；如果跳了2阶，那么之前的跳法是f(n-2)种。
//可以采用非递归的写法：
public int JumpFloor(int target) {
    if(target == 0) return 0;
    int first = 0, second = 1;
    while(target >= 1){
        int temp = second;
        second += first;
        first = temp;
        target --;
    }
    return second;
}
```

## 9.变态跳台阶

>一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。
>
>求该青蛙跳上一个n级的台阶总共有多少种跳法。

```java
//思路：仿效上一题的做法：f(n) = f(n-1) + f(n-2) + f(n-3) + ... + f(1)
//将n变成n-1：f(n-1) = f(n-2) + f(n-3) + ... + f(1)
//做差：f(n) - f(n-1) = f(n-1)   ->    f(n) = 2 * f(n-1)
public int JumpFloorII(int target) {
    if(target == 0) return 0;
    if(target == 1) return 1;
    int res = 1;
    while(target > 1){
        res *= 2;
        target --;
    }
    return res;
}
```

## 10.矩阵覆盖

>我们可以用2x1的小矩形横着或者竖着去覆盖更大的矩形。
>
>请问用n个2x1的小矩形无重叠地覆盖一个2xn的大矩形，总共有多少种方法？
>
>重点：每个矩阵可以横着放，也可以竖着放。

```java
//思路：递归式子：f(n) = f(n-1) + f(n-2)。
//代表：最后一个矩阵如竖着放，那么之前的放法是f(n-1)种；如果横着放，那么倒数第二块也一定得横着放，那么之前的放法是f(n-2)种。
//可以采用非递归的写法：
public int RectCover(int target) {
    if(target == 0) return 0;
    if(target == 1) return 1;
    int f1 = 1, f2 = 2;
    while(target != 2){
        int temp = f2;
        f2 = f1 + f2;
        f1 = temp;
        target -- ;
    }
    return f2;
}
```

## 11.二进制中1的个数

>输入一个整数，输出该数32位二进制表示中1的个数。其中负数用补码表示。

```java
//思路：将n与(n-1)求并，没求一次并，count加1.知道n为0为止。
//例如：5: 101。5&4 = 101 & 100 = 100；100 & 011 = 0.做了两次并，count为2；
public int NumberOf1(int n) {
    int count = 0;
    while(n != 0){
        n &= (n-1);
        count++;
    }
    return count;
}
```

## 12.数值的整数次方

>给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
>
>保证base和exponent不同时为0

```java
public class Solution {
	public double Power(double base, int exponent) {
		if(base == 0) return 0;
		if(exponent == 0) return 1;
		double res = base;
		int flag = 1;
		if(exponent < 0){
			exponent *= -1;
			flag = -1;
		}
		while(exponent > 1){
			res = res * res;
			exponent /= 2;
			if(exponent % 2 == 1) res *= base;
		}
		return (flag == 1) ? res : 1 / res;
	}
}
```

## 13.调整数组使奇数位于偶数前

>输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，所有的偶数位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。

```java
public class Solution {
    
    //思路1：使用冒泡排序，就能保持相对位置不变。其实也就是冒泡排序的稳定性
    public void reOrderArray1(int [] array) {
        if(array == null || array.length <= 1) return;
        for(int i = 0;i < array.length-1;i++){
            for(int j = 0;j < array.length-i-1;j++){
                if(array[j] % 2 == 0 && array[j+1] %2 == 1){
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
    }
    
    //思路2：我们知道插入排序也是稳定性的，所有可以借鉴其思路。
    //每次都将后面的那个奇数放到前面去，然后之间的那些数都后移
    public void reOrderArray2(int [] array) {
        if(array == null || array.length <= 1) return;
        int odd_index = -1;//摆放位置正确的最后一个奇数的位置，最开始的时候默认没有一个奇数是放在正确位置的，所以初始值是-1
        for(int i = 0;i < array.length;i++){
            if(array[i] % 2 == 1){
                int temp = i, temp_val = array[i];  //记录下array[i]的值
                //将前面的偶数，也就是[odd_index+1,i-1]内的数，都后移一位
                while(temp > odd_index + 1){
                    array[temp] = array[temp-1];
                    temp --;
                }
                array[temp] = temp_val;   //将奇数放到正确的位置上去
                odd_index++;   //多了一个摆放位置正确的奇数，odd_index 加1
            }
        }
    }
}

```

## 14.链表中倒数第k个节点

>输入一个链表，输出该链表中倒数第k个结点。

```java
public class Solution {
    //思路1：快慢指针，快指针先走k个节点
    public ListNode FindKthToTail(ListNode head, int k) {
        ListNode fast = head, slow = head;
        while(k != 0){
            if(fast == null){
                return null;  //如果fast为null，说明节点数小于k，就返回null
            }
            fast = fast.next;
            k--;
        }
        while(fast != null){
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
}
```

## 15.反转链表

>输入一个链表，反转链表后，输出新链表的表头。

```java
public class Solution {
    
    //思路1：非递归方法 + 虚拟头节点 + 头插法
    public ListNode ReverseList1(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode dummy = new ListNode(-1), p = head;
        while(p != null){
            ListNode temp = dummy.next;
            dummy.next = p;
            p = p.next;
            dummy.next.next = temp;
        }
        return dummy.next;
    }
    
    //思路2：递归方法
    public ListNode ReverseList2(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode tmp = head.next;  //未来的尾部倒数第二个结点
        ListNode newHead = ReverseList(head.next);
        head.next = null;  //加这一行避免成环
        tmp.next = head;
        return newHead;
    }
}
```

## 16.合并两个排序的链表

>输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。

```java
public class Solution {
    //思路：不使用额外的内存空间。先获取两个链表中较小的那个作为合并后链表的头结点。
    public ListNode Merge(ListNode list1,ListNode list2) {
        if(list1 == null) return list2;
        if(list2 == null) return list1;
        ListNode head, p1 = list1, p2 = list2;
        if(p1.val < p2.val){
            head = p1;
            p1 = p1.next;
        }
        else{
            head = p2;
            p2 = p2.next;
        }
        ListNode p = head;
        while(p1 != null && p2 != null){
            if(p1.val > p2.val){
                p.next = p2;
                p2 = p2.next;
            }
            else{
                p.next = p1;
                p1 = p1.next;
            }
            p = p.next;
        }
        if(p1 != null){
            p.next = p1;
        }
        if(p2 != null){
            p.next = p2;
        }
        return head;
    }
}
```

## 17.数的子结构

>输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）

```java
public class Solution {
    //思路：如果当前root1和root2的val是相同的，就继续比较两个树是否相同
    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        if(root1 == null || root2 == null) return false;
        if(root1.val == root2.val && helper(root1, root2)){
            return true;
        }
        else{
            return HasSubtree(root1.left, root2) || HasSubtree(root1.right, root2);
        }
    }
    
    public boolean helper(TreeNode root, TreeNode node){
        if(node == null) return true;  //注意：这里不需要判断root是不是为null
        if(root == null || root.val != node.val){
            return false;
        }
        return helper(root.right, node.right) && helper(root.left, node.left);
    }
}
```

## 18.二叉树的镜像

>操作给定的二叉树，将其变换为源二叉树的镜像。
>
>```
>源二叉树： 
>    	    8
>    	   /  \
>    	  6   10
>    	 / \  / \
>    	5  7 9   11
>
>镜像二叉树：
>    	    8
>    	   /  \
>    	  10   6
>    	 / \  / \
>    	11  9 7  5
>```

```java
public class Solution {
    //思路：先交换左右子节点，然后对左右子树 进行 递归镜像处理
    public void Mirror(TreeNode root) {
        if(root == null) return;
        TreeNode tmp = root.right;
        root.right = root.left;
        root.left = tmp;
        //下面递归
        Mirror(root.left);
        Mirror(root.right);
    }
}
```

## 19.顺时针打印矩阵

>输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。例如，如果输入如下4 X 4矩阵： 
>
>1  2   3  4
>
>5  6   7  8 
>
>9  10  11 12 
>
>13 14  15 16 
>
>则依次打印出数字 1,2,3,4,8,12,16,15,14,13,9,5,  6,7,11,10.

```java
import java.util.ArrayList;

public class Solution {
    
    ArrayList<Integer> res = new ArrayList<>();
    
    public ArrayList<Integer> printMatrix(int [][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return res;
        }
        helper(matrix, 0, matrix.length-1, 0, matrix[0].length-1);
        return res;
    }
    
    //思路：约定两个点(i_start, j_start) 和 (i_end, j_end)，在这个范围内的点才是有效的。
    public void helper(int[][] mat, int i_start, int i_end, int j_start, int j_end){
        if(i_start > i_end || j_start > j_end) return ;
        if(i_start == i_end){
            while(j_start <= j_end){
                res.add(mat[i_start][j_start]);
                j_start++;
            }
            return;
        }
        if(j_start == j_end){
            while(i_start <= i_end){
                res.add(mat[i_start][j_start]);
                i_start++;
            }
            return;
        }
        int i = i_start, j = j_start;
        while(j <= j_end){
            res.add(mat[i][j]);
            j++;
        }
        i++;j--;
        while(i <= i_end){
            res.add(mat[i][j]);
            i++;
        }
        j--;i--;
        while(j >= j_start){
            res.add(mat[i][j]);
            j--;
        }
        j++;i--;
        while(i > i_start){
            res.add(mat[i][j]);
            i--;
        }
        helper(mat, i_start+1, i_end-1, j_start+1, j_end-1);
    }
}
```

## 20.包含min函数的栈

>定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））。

```java
import java.util.Stack;

public class Solution {
    
    Stack<Integer> stack = new Stack<>();  //存放数据
    int min = Integer.MAX_VALUE;
    
    public void push(int node) {
        if(node <= min){
            stack.push(min);   //查看是不是需要修改min的值。这里的条件需要是node <= min
            min = node;
        }
        stack.push(node);  //再把node压栈
    }
    
    public void pop() {
        if(stack.pop() == min){
            min = stack.pop();
        }
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int min() {
        return min;
    }
}
```

## 21.栈的压入、弹出序列

>输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。假设压入栈的所有数字均不相等。例如序列1,2,3,4,5是某栈的压入顺序，序列4,5,3,2,1是该压栈序列对应的一个弹出序列，但4,3,5,1,2就不可能是该压栈序列的弹出序列。（注意：这两个序列的长度是相等的）

```java
import java.util.*;

public class Solution {
    //思路：分别用两个下标来指代两个数组，然后用一个栈来模拟压栈、出栈的过程。
    public boolean IsPopOrder(int [] pushA, int [] popA) {
        if(pushA == null || popA == null || pushA.length != popA.length){
            return false;
        }
        Stack<Integer> stack = new Stack<>();
        int i = 0, j = 0;  //分别指代两个数组
        while(i < pushA.length && j < popA.length){
            if(!stack.isEmpty() && stack.peek() == popA[j]){
                stack.pop();
                j++;
            }
            else{
                stack.push(pushA[i]);
                i++;
            }
        }
        while(!stack.isEmpty() && stack.peek() == popA[j]){
            stack.pop();
            j++;
        }
        return stack.isEmpty();
    }
}
```

## 22.从上往下打印二叉树

>从上往下打印出二叉树的每个节点，同层节点从左至右打印。

```java
import java.util.*;

public class Solution {
    //每层节点从左到右打印，其实就是层次遍历了
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            List<TreeNode> nodes = new ArrayList<>();  //使用list来顺序存放当前层的所有节点
            while(!queue.isEmpty()){
                nodes.add(queue.poll());
            }
            for(TreeNode node: nodes){
                res.add(node.val);
                if(node.left != null){
                    queue.add(node.left);
                }
                if(node.right != null){
                    queue.add(node.right);
                }
            }
        }
        return res;
    }
}
```

## 23.二叉搜索树的后续遍历序列

>输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。如果是则返回true,否则返回false。假设输入的数组的任意两个数字都互不相同。

```java
public class Solution {
    //思路：二叉树后续遍历是左-右-根，且根节点的值比左子树的节点都大，但比右子树的节点都小
    //所以，根据上面这个规律，就能每次将数组一分为二。
    public boolean VerifySquenceOfBST(int [] sequence) {
        if(sequence == null || sequence.length == 0) return false;
        if(sequence.length == 1) return true;
        return helper(sequence, 0, sequence.length-1);
    }
    
    public boolean helper(int[] nums, int left, int right){
        if(left >= right){
            return true;
        }
        int root_val = nums[right];
        int i = left;
        while(nums[i] < root_val){
            i++;
        }
        int left_tree_end = i-1;
        while(i < right){
            if(nums[i] < root_val){
                return false;   //又出现了小于root_val的值，说明出错了
            }
            else{
                i++;
            }
        }
        return helper(nums, left_tree_end+1, right-1) 
            && helper(nums, left, left_tree_end);
    }
}
```

## 24.二叉树中和为某一值的路径

>输入一颗二叉树的根节点和一个整数，按字典序打印出二叉树中结点值的和为输入整数的所有路径。路径定义为从树的**根结点**开始往下一直到**叶结点**所经过的结点形成一条路径。

```java
import java.util.*;

public class Solution {
    
    ArrayList<ArrayList<Integer>> res = new ArrayList<>();
    ArrayList<Integer> cur = new ArrayList<>();
    
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        if(root == null) return res;
        dfs(root, target, 0);
        return res;
    }
    
    //sum0代表之前走过的路径中所有节点的val的和
    public void dfs(TreeNode root, int sum, int sum0){
        if(root == null){
            return;
        }
        cur.add(root.val);
        sum0 += root.val;
        if(root.left == null && root.right == null && sum == sum0){  //因为必须是到叶子节点才可以结束
            ArrayList<Integer> list = new ArrayList<>(cur);
            if(!res.contains(list)){
                res.add(list);   //去重
            }
        }
        dfs(root.left, sum, sum0);
        dfs(root.right, sum, sum0);
        cur.remove(cur.size()-1);   //删除最后一个节点，因为它是叶子节点，避免给影响到其他的路径中找
    }
}
```

## 25：复杂链表的复制

>输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，另一个特殊指针random指向一个随机节点），请对此链表进行深拷贝，并返回拷贝后的头结点。
>
>（注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）0.5AC

```java
/*
public class RandomListNode {
    int label;
    RandomListNode next = null;
    RandomListNode random = null;

    RandomListNode(int label) {
        this.label = label;
    }
}
*/
import java.util.HashMap;

public class Solution {
    //思路1：使用一个hashmap存放下旧节点和新节点之间的对应关系
    public RandomListNode Clone1(RandomListNode pHead){
        HashMap<RandomListNode, RandomListNode> map = new HashMap<>();
        RandomListNode p = pHead, dummy = new RandomListNode(-1);
        RandomListNode q = dummy;
        while(p != null){
            q.next = new RandomListNode(p.label);
            map.put(p, q.next);
            p = p.next;
            q = q.next;
        }
        p = pHead;
        q = dummy.next;
        while(p != null){
            q.random = map.get(p.random);
            p = p.next;
            q = q.next;
        }
        return dummy.next;
    }
    
    //思路2：在思路1中hashmap数据结构其实使用了额外的空间，如果不使用hashmap的话，可以按照下面的步骤来
    //1、深拷贝出一条链表，只不过将新节点放在旧节点的后面，如：1->2->3 变成 1->(1)->2->(2)->3->(3)
    //2、修改新节点中的random域，指向的节点是它对应的旧节点的random域指向的节点的后面一个节点。
    //3、从新旧链表的合体中拆出新的链表。
    public RandomListNode Clone2(RandomListNode pHead){
        //第一步，深拷贝每一个旧节点，并将每个新节点放在旧节点的后面
        if(pHead == null){
            return null;
        }
        RandomListNode p = pHead;
        while(p != null){
            RandomListNode temp = p.next;
            p.next = new RandomListNode(p.label);
            p.next.next = temp;
            p = temp;
        }
        p = pHead;
        //第二步，更新新节点的random域
        while(p != null){
            p.next.random = (p.random == null) ? null : p.random.next;
            p = p.next.next;
        }
        p = pHead;
        //第三步，将新节点拆出来
        RandomListNode root = p.next;
        RandomListNode q = root;
        while(p != null){
            p.next = q.next;
            q.next = (p.next == null) ? null : p.next.next;
            p = p.next;
            q = q.next;
        }
        return root;
    }
}
```

## 26.二叉搜索树与双向链表

>输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。要求不能创建任何新的结点，只能调整树中结点指针的指向。

```java
import java.util.*;

public class Solution {
    //思路1：采用中序遍历的方式，获取所有的节点，然后将这些节点组成一个双向链表
    List<TreeNode> nodes = new ArrayList<>();
    
    public TreeNode Convert(TreeNode pRootOfTree) {
        if(pRootOfTree == null) return null;
        InOrder(pRootOfTree);  //中序遍历
        TreeNode root = nodes.get(0);
        TreeNode pre = root;  //记录前一个节点
        for(int i = 1;i < nodes.size();i++){
            TreeNode node = nodes.get(i);  //获取当前节点
            pre.right = node;
            node.left = pre;
            pre = node;
        }
        return root;
    }
    
    public void InOrder(TreeNode root){
        if(root == null) return;
        InOrder(root.left);
        nodes.add(root);
        InOrder(root.right);
    }
}
```

```java
public class Solution {
    //思路2：画一个如下所示的二叉搜索树，然后转换成双向链表。
    /*
             4
            / \
           2   6
          /\   /\
         1  3 5  7     转换成为 1 2 3 4 5 6 7
    */
    
    TreeNode head;
    
    public TreeNode Convert(TreeNode pRootOfTree) {
        if(pRootOfTree == null) return null;
        Convert(pRootOfTree.right);   //右子树成为一个双向链表
        if(head == null){
            head = pRootOfTree; //如果head没有初始化，就初始化成当前节点。head是右子树的头节点
        }
        else{
            head.left = pRootOfTree;
            pRootOfTree.right = head;
            head = pRootOfTree;  //head前移
        }
        Convert(pRootOfTree.left);   //左子树成为一个双向链表
        return head;
    }
}
```

## [27].字符串的排列

>输入一个字符串,按字典序打印出该字符串中字符的所有排列。例如输入字符串abc,则按字典序打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。

```java
//abc的全排列：abc acb    bac bca    cab cba
//插空规则，当有两个ab的时候，有ab ba两种，就出现了 *ab a*b ab*  *ba  b*a ba*六个空位置可以放c
import java.util.ArrayList;

public class Solution {
    public ArrayList<String> Permutation(String str) {
		ArrayList<String> res = new ArrayList<>();
		if(str == null || str.length() == 0) return res;
		if(str.length() == 1){
			res.add(str);
			return res;
		}
		String sub = str.substring(0, str.length()-1);
		return helper(str.charAt(str.length()-1), Permutation(sub));  //将c放到ab的全排列中去
	}
	
	//例如c 放到ab的全排列ab ba中：*ab a*b ab* *ba b*a ba*这一共六个位置的
	private static ArrayList<String> helper(char c, ArrayList<String> per) {
		ArrayList<String> list = new ArrayList<>();
		for(String str : per){
			//将c插入到str中去，同时要去重
			for(int i = 0;i <= str.length();i++){
				StringBuilder sb = new StringBuilder(str);
				sb.insert(i, c);
				if(!list.contains(sb.toString()))
					list.add(sb.toString());
			}
		}
		//按照字典的顺序进行排序,简单的双层比较
		for(int i = 0;i < list.size();i++){
			for(int j = i+1;j < list.size();j++){
				if(list.get(i).compareTo(list.get(j)) > 0){
					String temp = list.get(i);
					list.set(i, list.get(j));
					list.set(j, temp);
				}
			}
		}
		return list;
	}
}
```

## 28.数组中出现超过一半的数

>数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
>
>例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。
>
>由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0。

```java
import java.util.*;

public class Solution {
    //思路1：使用hashmap存放每个数字出现的次数，然后找到出现次数大于数组长度一半的那个
    public int MoreThanHalfNum_Solution1(int [] array) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int num: array){
            if(map.containsKey(num)){
                map.put(num, map.get(num)+1);
            }
            else{
                map.put(num, 1);
            }
        }
        for(int key: map.keySet()){
            if(map.get(key) > array.length/2){
                return key;
            }
        }
        return 0;
    }
    
    //思路2：使用cur记录当前的数，并且用count记cur当前的次数。
    //如果新出现的数与cur不同，count减去1；
    //没当count为0的时候，将cur变为新出现的数
    public int MoreThanHalfNum_Solution2(int [] array) {
        if(array == null || array.length == 0) return 0;
        int cur = array[0], count = 1;
        for(int i = 1;i < array.length;i++){
            if(count == 0){
                //更新为新的数
                cur = array[i];
                count = 1;
            }
            else{
                if(cur != array[i]) count--;
                else count++;
            }
        }
        //还要判断当前这个cur是不是满足条件的
        int cnt = 0;
        for(int num: array){
            if(cur == num){
                cnt++;
            }
        }
        return (cnt > array.length/2) ?  cur:0;  //只有长度大于一半的数，才能返回cur
    }
}
```

## 29.最小的K个数

>输入n个整数，找出其中最小的K个数。
>
>例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4。

```java
import java.util.*;

public class Solution {
    //思路1：使用堆排序
    public ArrayList<Integer> GetLeastNumbers_Solution1(int [] input, int k) {
        ArrayList<Integer> res = new ArrayList<>();
        if(input == null || input.length == 0 || input.length < k) {
            return res;  //这里一定要加判断条件：input.length < k，不然过不了
        }
        Queue<Integer> minHeap = new PriorityQueue<>();  //最小堆
        //最大堆是：Queue<Integer> maxHeap = new PriorityQueue<>((o1, o2)->(o2-o1));
        for(int num: input){
            minHeap.offer(num);
        }
        for(int i = 0;i < k;i++){
            res.add(minHeap.poll());
        }
        return res;
    }
    
    //思路2：使用冒泡排序，只进行k趟，选出k个最小的元素就可以了
    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> res = new ArrayList<>();
        if(input == null || input.length == 0 || input.length < k) return res;
        for(int i = 0;i < k;i++){
            for(int j = 0;j < input.length-i-1;j++){
                if(input[j] < input[j+1]){
                    int temp = input[j];
                    input[j] = input[j+1];
                    input[j+1] = temp;
                }
            }
            res.add(input[input.length-i-1]);  //res.add(刚刚挑出来的最小元素)
        }
        return res;
    }
}
```

## 30.连续子数组的最大和

>HZ偶尔会拿些专业问题来忽悠那些非计算机专业的同学。今天测试组开完会后,他又发话了:在古老的一维模式识别中,常常需要计算连续子向量的最大和,当向量全为正数的时候,问题很好解决。
>
>但是,如果向量中包含负数,是否应该包含某个负数,并期望旁边的正数会弥补它呢？例如:{6,-3,-2,7,-15,1,2,2},连续子向量的最大和为8(从第0个开始,到第3个为止)。
>
>给一个数组，返回它的最大连续子序列的和。

```java
public class Solution {
    //思路：使用动态规划的方式，采用dp数组,dp[i]记录的是数组中前i个元素能够获得的最大和
    public int FindGreatestSumOfSubArray(int[] array) {
        if(array == null || array.length == 0) return 0;
        int[] dp = new int[array.length]; dp[0] = array[0];
        int max = dp[0];
        for(int i = 1;i < array.length;i++){
            if(dp[i-1] < 0){
                dp[i] = array[i];  //之前的最大和小于0，不如重新开始
            }
            else{
                dp[i] = array[i]+dp[i-1]; //之前的最大和大于0，可以在dp[i-1]的基础上继续加
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
```

## 31.整数中1出现的次数

>求出1~13的整数中1出现的次数,并算出100~1300的整数中1出现的次数？为此他特别数了一下1~13中包含1的数字有1、10、11、12、13因此共出现6次,但是对于后面问题他就没辙了。ACMer希望你们帮帮他,并把问题更加普遍化,可以很快的求出任意非负整数区间中1出现的次数（从1 到 n 中1出现的次数）。

```java
public class Solution {
    //思路：挨个统计1~n中每个数的1的个数
    public int NumberOf1Between1AndN_Solution(int n) {
        int sum = 0;
        for(int i = 1;i <= n;i++){
            sum += count(i);
        }
        return sum;
    }
    
    //统计整数num中有多少个1
    public int count(int num){
        int count = 0;
        while(num != 0){
            if(num % 10 == 1){
                count++;
            }
            num /= 10;
        }
        return count;
    }
}
```

## 32.把数组排成最小的数

>输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323。

```java
import java.util.ArrayList;

public class Solution {
    //思路：重新定义排序：A和B组成起来，如果AB > BA，那么就将B换到A前面去。将数组排好序之后，再把数组中的数都合起来
    public String PrintMinNumber(int [] numbers) {
        if(numbers == null || numbers.length == 0) return "";
        //使用冒泡排序的思路来做。
        for(int i = 0;i < numbers.length;i++){
            for(int j = 0;j < numbers.length-i-1;j++){
                if(compare(numbers[j], numbers[j+1]) > 0){
                    int temp = numbers[j];
                    numbers[j] = numbers[j+1];
                    numbers[j+1] = temp;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int num: numbers){
            sb.append(num);
        }
        return sb.toString();
    }
    
    //如果ab > ba，那么返回1,；否则返回-1。比如4 3 > 3 4，就返回1
    public int compare(int a, int b){
        return (merge(a, b) > merge(b, a)) ? 1 : -1;
    }
    
    //将a和b合并起来，比如3,4 返回整数 34
    public int merge(int a, int b){
        int sum = a, temp = b;
        while(temp != 0){
            temp /= 10;
            sum *= 10;
        }
        return sum+b;
    }
}
```

## 33.丑数

>把只包含质因子2、3和5的数称作丑数（Ugly Number）。例如6、8都是丑数，但14不是，因为它包含质因子7。 
>
>习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。

```java
public class Solution {
    //思路：确定好第一个丑数之后，然后通过乘2、乘3或者乘5得到下一个丑数。
    //需要用3个指针来记录可以通过哪个数做上述乘法，得到下一个丑数。且相同的数要记得去除。
    public int GetUglyNumber_Solution(int index) {
        if(index < 1) return 0;  //这一行不加也要报错
        int[] dp = new int[index];  //求出前index个丑数
        dp[0] = 1;  //定好第一个丑数值
        int p2 = 0;  //表示dp[0]*2可以得到下一个丑数，下面的类似
        int p3 = 0;
        int p5 = 0;
        for(int i = 1;i < index;i++){
            dp[i] = Math.min(dp[p2]*2, Math.min(dp[p3]*3, dp[p5]*5));
            if(dp[p2]*2 == dp[i]) p2 ++;
            if(dp[p3]*3 == dp[i]) p3 ++;
            if(dp[p5]*5 == dp[i]) p5 ++;
        }
        return dp[index-1];
    }
}
```

## 34.第一个只出现一次的字符

>在一个字符串(0<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,并返回它的位置, 如果没有则返回 -1（需要区分大小写）.（从0开始计数）

```java
import java.util.*;

public class Solution {
    //思路：使用map统计下每个字符出现的次数。然后从头遍历字符串，找到第一个出现次数为1的字符。
    public int FirstNotRepeatingChar(String str) {
        if(str == null || str.length() == 0) return -1;
        HashMap<Character, Integer> map = new HashMap<>();
        for(int i = 0;i < str.length();i++){
            char c = str.charAt(i);
            if(map.containsKey(c)){
                map.put(c, map.get(c)+1);
            }
            else{
                map.put(c, 1);
            }
        }
        for(int i = 0 ;i < str.length();i++){
            if(map.get(str.charAt(i)) == 1){
                return i;
            }
        }
        return -1;
    }
}
```

## 35.数组中的逆序对

>在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组,求出这个数组中的逆序对的总数P。并将P对1000000007取模的结果输出。 即输出P%1000000007
>
>```
>输入：1,2,3,4,5,6,7,0
>```
>
>```
>输出：7
>```

```java
public class Solution {
    //思路：在归并排序的过程中，找到逆序对的数量
    long res = 0;
    long mod = 1000000007;
    public int InversePairs(int [] array) {
        if(array == null || array.length <= 1) return 0;
        mergeSort(array, 0, array.length-1);
        return (int)(res % mod);
    }
    
    public void mergeSort(int[] nums, int left, int right){
        if(nums == null || nums.length <= 1 || left >= right) return;
        int mid = left + ((right-left) >> 1);
        mergeSort(nums, left, mid);
        mergeSort(nums, mid+1, right);
        merge(nums, left, mid, right);
    }
    
    //将排好序了的nums中的[left, mid] 和 [mid+1, right]进行合并
    public void merge(int[] nums, int left, int mid, int right){
        int[] temp = new int[right-left+1];   //先开辟空间，来需要合并的数据
        int index = 0, l = left, r = mid+1;
        while(l <= mid && r <= right){
            if(nums[l] <= nums[r]){
                temp[index++] = nums[l++];
            }
            else{
                temp[index++] = nums[r++];
                res += mod-l+1; //1 3 6 | 0 5 由于3>0，那么3和6都是大于0的，所以加的左边的数3及其后面数的数量
            }
        }
        //后面剩余的数，其实都不会影响了。
        while(l <= mid){
            temp[index++] = nums[l++];
        }
        while(r <= right){
            temp[index++] = nums[r++];
        }
        for(int i = 0;i < temp.length;i++){
            nums[left+i] = temp[i];   //将temp中的数据搬迁到原数组中去
        }
    }
}
```

## 36.两个链表的第一个公共节点

>输入两个链表，找出它们的第一个公共结点。

```java
public class Solution {
    //思路：假设pHead1和pHead2的开头点分别是A和B，公共结点是O，末尾点时C
    //那么两个分别从A和B出发的指针，都走长度为AO+OC+BO的长度，就会在O点相遇
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        if(pHead1 == null || pHead2 == null) return null;
        ListNode p1 = pHead1, p2 = pHead2;
        while(p1 != p2){
            p1 = p1.next;
            p2 = p2.next;
            if(p1 == p2) break;  //出现相等的，就赶紧break出去
            if(p1 == null) p1 = pHead2;
            if(p2 == null) p2 = pHead1;
        }
        return p1;
    }
}
```

## 37.数字在升序数组中出现次数

>统计一个数字在升序数组中出现的次数。

```java
public class Solution {
    //思路：既然是排好序的数组，那么肯定就优先使用二分查找了
    public int GetNumberOfK(int [] array , int k) {
        if(array == null || array.length == 0) return 0;
        if(array[0] > k || array[array.length-1] < k) return 0;
        int left = 0, right = array.length-1, res = 0;
        while(left <= right){
            int mid = left + ((right - left) >> 1);
            if(array[mid] == k){
                res++;
                int i = mid-1, j = mid+1;
                while(i >= 0 && array[i] == k){
                    i--;
                    res++;
                }
                while(j < array.length && array[j] == k){
                    j++;
                    res++;
                }
                return res;
            }
            else if(array[mid] < k){
                left = mid+1;
            }
            else{
                right = mid-1;
            }
        }
        return res;
    }
}
```

## 38.二叉树的深度

>输入一棵二叉树，求该树的深度。从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路径，最长路径的长度为树的深度。

```java
public class Solution {
    public int TreeDepth(TreeNode root) {
        if(root == null) return 0;
        int right = TreeDepth(root.right);  //右子树深度
        int left = TreeDepth(root.left);  //左子树深度
        return Math.max(right, left)+1;
    }
}
```

## 39.平衡二叉树

>输入一棵二叉树，判断该二叉树是否是平衡二叉树。
>
>在这里，我们只需要考虑其平衡性，不需要考虑其是不是排序二叉树

```java

public class Solution {
    //思路：平衡二叉树的特点，一是需要是排序二叉树，左子树上的节点值 < root < 右子树的节点值
    //二是左右子树的高度差的绝对值小于1。
    //本题中不考虑条件1，所以只用判断深度差就可以了。
    public boolean IsBalanced_Solution(TreeNode root) {
        if(root == null) return true;
        int rightDepth = TreeDepth(root.right);
        int leftDepth = TreeDepth(root.left);
        if(Math.abs(leftDepth-rightDepth) > 1){
            return false;
        }
        else{
            return IsBalanced_Solution(root.left) && IsBalanced_Solution(root.right);
        }
    }
    
    public int TreeDepth(TreeNode root) {
        if(root == null) return 0;
        int right = TreeDepth(root.right);  //右子树深度
        int left = TreeDepth(root.left);  //左子树深度
        return Math.max(right, left)+1;
    }
}
```

## 40.数组中只出现了一次的数字

>先做一个基础题：一个整型数组里除了一个数字之外，其他的数字都出现了两次。找出这个只出现一次的数字。

```java
//思路：相同的两个数字做异或，得到0，所以将所有的数做一次异或就可以得到了。
public int find(int[] nums){
    if(nums == null || nums.length == 0) return -1;
    int res = 0;
    for(int num: nums){
        res ^= num;  //依次做异或操作
    }
    return res;
}
```

>再说当前这个题目：一个整型数组里除了两个数字之外，其他的数字都出现了两次。请写程序找出这两个只出现一次的数字。

```java
//num1,num2分别为长度为1的数组。传出参数
//将num1[0],num2[0]设置为返回结果
public class Solution {
    //方式1：使用hashset，出现重复的数字，就从set中移除掉
    public void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
        HashSet<Integer> set = new HashSet<>();
        for(int i = 0;i < array.length;i++){
            if(set.contains(array[i])){
                set.remove(array[i]);
            }
            else{
                set.add(array[i]);
            }
        }
        int index = 0;
        int[] nums = new int[2];
        for(int key: set){
            nums[index++] = key;
        }
        num1[0] = nums[0];
        num2[0] = nums[1];
    }
    
    //方式2：两个相同的数做异或后，得到0；
    //如果两个只出现了1次的数字做异或，得到了一个数值，其最高位，可以拿来将原先的数字分成两波
	//每一波数做完异或，能够得到一个只出现了一次的数
    public void FindNumsAppearOnce2(int [] array,int num1[] , int num2[]) {
        int sum = 0;
        for(int num: array){
            sum ^= num;
        }
        //将sum的最低位找出来
        int count = 1;  //比如，如果sum是 100100  那么count就是100
        while((count & sum) == 0){   //记得这里一定要打括号
            count = count << 1;
        }
        //下面根据与count的取和，将array中的数分成两拨。然后每拨数做异或，得到一个单独的数
        for(int num: array){
            if((num & count) == 0){  //记得这里一定要打括号
                num1[0] ^= num;
            }
            else{
                num2[0] ^= num;
            }
        }
    }
}
```

## 41.和为S的连续正数序列

>小明很喜欢数学,有一天他在做数学作业时,要求计算出9~16的和,他马上就写出了正确答案是100。但是他并不满足于此,他在想究竟有多少种连续的正数序列的和为100(至少包括两个数)。没多久,他就得到另一组连续正数和为100的序列:18,19,20,21,22。现在把问题交给你,你能不能也很快的找出所有和为S的连续正数序列? Good Luck!
>
>```
>输出所有和为S的连续正数序列。序列内按照从小至大的顺序，序列间按照开始数字从小到大的顺序
>```

```java
import java.util.ArrayList;
public class Solution {
    public ArrayList<ArrayList<Integer> > FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer> > res = new ArrayList<>();
        if(sum <= 2) return res;
        ArrayList<Integer> cur = new ArrayList<>();
        cur.add(1);cur.add(2);int sum0 = 3;
        int m = 1, k = 2;  //[m, k]内的数字都在cur中
        while(k < sum){
            if(sum0 == sum){
                ArrayList<Integer> list = new ArrayList<>(cur);
                res.add(list);
                cur.remove(0);  //添加到res中之后，需要删除链表中的第一个元素，防止后续重复出现
                sum0 -= m;
                m++;
            }
            else if(sum0 < sum){
                k++;   //添加进新的元素
                cur.add(k);
                sum0 += k;
            }
            else{
                cur.remove(0);
                sum0 -= m;
                m++;
            }
        }
        return res;
    }
}
```

## 42.和为S的两个数字

>输入一个递增排序的数组和一个数字S，在数组中查找两个数，使得他们的和正好是S，如果有多对数字的和等于S，输出两个数的乘积最小的。

```java
import java.util.ArrayList;
public class Solution {
    public ArrayList<Integer> FindNumbersWithSum(int [] array,int sum) {
        ArrayList<Integer> res = new ArrayList<>();
        if( array == null || array.length == 0 || array[0] > sum){
            return res;
        }
        int[] nums = new int[2];
        int left = 0, right = array.length-1, mult = Integer.MAX_VALUE;  //乘积先放最大值
        while(left < right){
            if(array[left] + array[right] == sum){
                if(array[left]*array[right] < mult){
                    mult = array[left]*array[right];
                    nums[0] = array[left];
                    nums[1] = array[right];
                }
                left++;
                right--;
            }
            else if(array[left] + array[right] < sum){
                left++;
            }
            else{
                right--;
            }
        }
        if(nums[0] == 0 && nums[1] == 0){
            return res;
        }
        else{
            res.add(nums[0]);
            res.add(nums[1]);
        }
        return res;
    }
}
```

## 43.左旋转字符串

>汇编语言中有一种移位指令叫做循环左移（ROL），现在有个简单的任务，就是用字符串模拟这个指令的运算结果。对于一个给定的字符序列S，请你把其循环左移K位后的序列输出。
>
>例如，字符序列S=”abcXYZdef”,要求输出循环左移3位后的结果，即“XYZdefabc”。

```java
public class Solution {
    //充分使用substring函数，[start, end)
    public String LeftRotateString(String str,int n) {
        if(str == null || str.length() <= 1 || n % str.length() == 0){
            return str;
        }
        n %= str.length();
        return str.substring(n, str.length()) + str.subSequence(0, n);
    }
}
```

## 44.翻转单词顺序列

>牛客最近来了一个新员工Fish，每天早晨总是会拿着一本英文杂志，写些句子在本子上。同事Cat对Fish写的内容颇感兴趣，有一天他向Fish借来翻看，但却读不懂它的意思。例如，“student. a am I”。后来才意识到，这家伙原来把句子单词的顺序翻转了，正确的句子应该是“I am a student.”。Cat对一一的翻转这些单词顺序可不在行，你能帮助他么？

```java
public class Solution {
    public String ReverseSentence(String str) {
        if(str == null || str.trim().length() <= 1){   //记得去除掉多余的空格
            return str;
        }
        StringBuilder sb = new StringBuilder();
        String[] strings = str.split(" ");  //以空格为界限，进行单词的划分
        for(int i = strings.length-1;i>0;i--){
            sb.append(strings[i]).append(" ");
        }
        sb.append(strings[0]);
        return sb.toString();
    }
}
```

## 45.扑克牌顺子

>LL今天心情特别好,因为他去买了一副扑克牌,发现里面居然有2个大王,2个小王(一副牌原本是54张^_^)...他随机从中抽出了5张牌,想测测自己的手气,看看能不能抽到顺子,如果抽到的话,他决定去买体育彩票,嘿嘿！！“红心A,黑桃3,小王,大王,方片5”,“Oh My God!”不是顺子.....LL不高兴了,他想了想,决定大\小 王可以看成任何数字,并且A看作1,J为11,Q为12,K为13。上面的5张牌就可以变成“1,2,3,4,5”(大小王分别看作2和4),“So Lucky!”。LL决定去买体育彩票啦。 现在,要求你使用这幅牌模拟上面的过程,然后告诉我们LL的运气如何， 如果牌能组成顺子就输出true，否则就输出false。为了方便起见,你可以认为大小王是0。

```java
import java.util.*;

public class Solution {
    public boolean isContinuous(int [] numbers) {
        if(numbers == null || numbers.length != 5){
            return false;
        }
        Arrays.sort(numbers);  //先排个顺序
        int count = 0;  //统计王的个数
        for(int num: numbers){
            if(num == 0){
                count++;
            }
        }
        //这里的i从count开始，代表跳过数组中前面的count个0
        for(int i = count;i < 4;i++){
            if(numbers[i] == numbers[i+1] || numbers[i]+1+count < numbers[i+1]){
                return false;  //如果出现相同的数字，或者王不够用的时候，就不是顺子
            }
            else{
                count -= numbers[i+1]-numbers[i]-1;  //扣除掉需要使用的顺子
            }
        }
        return true;
    }
}
```

## 46.孩子们的游戏（约瑟夫I）

>每年六一儿童节,牛客都会准备一些小礼物去看望孤儿院的小朋友,今年亦是如此。HF作为牛客的资深元老,自然也准备了一些小游戏。其中,有个游戏是这样的:首先,让小朋友们围成一个大圈。然后,他随机指定一个数m,让编号为0的小朋友开始报数。每次喊到m-1的那个小朋友要出列唱首歌,然后可以在礼品箱中任意的挑选礼物,并且不再回到圈中,从他的下一个小朋友开始,继续0...m-1报数....这样下去....直到剩下最后一个小朋友,可以不用表演,并且拿到牛客名贵的“名侦探柯南”典藏版(名额有限哦!!^_^)。请你试着想下,哪个小朋友会得到这份礼品呢？(注：小朋友的编号是从0到n-1)
>
>如果没有小朋友，请返回-1

```java
import java.util.*;

public class Solution {
    //思路：将n个小朋友的编号放到list中，然后采用模拟的办法，剔除掉n-1个小朋友，留下的最后一个小朋友就对了。
    public int LastRemaining_Solution(int n, int m) {
        if(n < 1){
            return -1;  //没有小朋友的时候返回-1
        }
        //用list存放n个编号0~n-1的小朋友
        List<Integer> list = new ArrayList<>();
        for(int i = 0;i < n;i++){
            list.add(i);
        }
        //从list中剔除n-1个小朋友
        int index = 0, out;  //index是起始位置，out是出去的小朋友的位置
        while(list.size() > 1){
            out = (index + m - 1) % list.size();  //对当前链表中的长度取模
            index = out;  //下次开始的小朋友的位置
            list.remove(out);
        }
        return list.get(0);
    }
}
```

## 47.求1~n的和

>求1+2+3+...+n。
>
>要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。

```java
public class Solution {
    //思路：使用布尔表达式。短路原则，如果前面已经是false了，就不会执行后面的。
    public int Sum_Solution(int n) {
        int sum = n;
        boolean res = (n > 1) && ((sum += Sum_Solution(n-1)) > 0);//只有在n>1情况下，才会执行后面的sum...
        return sum;
    }
}
```

## 48.不用加减乘除做加法

>写一个函数，求两个整数之和，要求在函数体内不得使用+、-、*、/四则运算符号。

```java
public class Solution {
    //思路：做异或操作可以得到一个值，比如001 ^ 010 = 011实际就是做了加法；
    //只不过碰到同时为1的位的时候，需要额外判断
    public int Add(int num1, int num2) {
        int sum = num1 ^ num2;
        int carry = num1 & num2;
        while(carry != 0){
            carry = carry << 1;
            int temp = sum;  //保存下当前的sum值，方便求 并
            sum = sum ^ carry;
            carry = carry & temp;
        }
        return sum;
    }
}
```

## 49.把字符串转换成整数

>将一个字符串转换成一个整数，要求不能使用字符串转换整数的库函数。 
>
>数值为0或者字符串不是一个合法的数值则返回0
>
>```
>输入描述: 输入一个字符串,包括数字字母符号,可以为空
>```
>
>```
>输出描述: 如果是合法的数值表达则返回该数字，否则返回0
>```
>
>```
>输入:
>+2147483647
>1a33
>```
>
>```
>输出:
>2147483647
>0
>```

```java
public class Solution {
    //思路：先跳过字符串前面的空格；然后检查正负号；最后判断后面的数字；当然如果出现了其他字符，就返回0了。
    public int StrToInt(String str) {
        if(str == null || str.length() == 0) return 0;
        int sum = 0, flag = 1, i = 0;
        while(str.charAt(i) == ' '){
            i++;
        }
        if(str.charAt(i) == '+'){
            i++;
        }
        else if(str.charAt(i) == '-'){
            i++;
            flag *= -1;
        }
        while(i < str.length()){
            char c = str.charAt(i);
            if(c <= '9' && c >= '0'){
                sum *= 10;
                sum += (c-'0');
            }
            else{
                return 0;
            }
            i++;
        }
        return sum*flag;
    }
}
```

## 50.数组中重复的数字

>在一个长度为n的数组里的所有数字都在0到n-1的范围内。 数组中某些数字是重复的，但不知道有几个数字是重复的。也不知道每个数字重复几次。请找出数组中任意一个重复的数字。 
>
>例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，那么对应的输出是第一个重复的数字2。

```java
import java.util.*;

public class Solution {
    //思路：用set存放出现过的数字，如果发现了出现过的数字，就返回了
    public boolean duplicate(int[] numbers, int length, int [] duplication) {
        if(numbers == null || numbers.length == 0) return false;
        Set<Integer> set = new HashSet<>();
        for(int num: numbers){
            if(set.contains(num)){
                duplication[0] = num;
                return true;
            }
            else{
                set.add(num);
            }
        }
        return false;
    }
}
```

## 51.构建乘积数组

>给定一个数组A[0,1,...,n-1],请构建一个数组B[0,1,...,n-1],其中B中的元素B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。不能使用除法。（注意：规定B[0] = A[1] * A[2] * ... * A[n-1]，B[n-1] = A[0] * A[1] * ... * A[n-2];）
>
>对于A长度为1的情况，B无意义，故而无法构建，因此该情况不会存在。

```java
import java.util.ArrayList;

public class Solution {
    //思路：仅仅使用乘法，res[i] = 1。然后，将res[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]分成两部分：
    //res[i] *= A[0]*A[1]*...*A[i-1]
    //res[i] *= A[i+1]*...*A[n-1]
    public int[] multiply(int[] A) {
        if(A == null || A.length <= 1) return new int[0];
        int[] res = new int[A.length];
        for(int i = 0;i < A.length;i++){
            res[i] = 1;
        }
        int mul = A[0];
        //首先，从左边开始乘起，使得res[i] = A[0]*A[1]*...*A[i-1];
        for(int i = 1;i < A.length;i++){
            res[i] *= mul;
            mul *= A[i];
        }
        //之后，从右边开始乘起，使得res[i] *= A[i+1]*A[i+1]*...*[A.length-1];
        mul = A[A.length-1];
        for(int i = A.length -2;i >= 0;i--){
            res[i] *= mul;
            mul *= A[i];
        }
        return res;
    }
}
```

## 52.正则表达式匹配

>请实现一个函数用来匹配包括'.'和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（包含0次）。 在本题中，匹配是指字符串的所有字符匹配整个模式。
>
>例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但是与"aa.a"和"ab*a"均不匹配

```java
public class Solution {
    public boolean match(char[] str, char[] pattern) {
		if((str == null && pattern == null)) return true;
		if(str == null || str.length == 0) return helper1(pattern, 0);
		if(pattern == null || pattern.length == 0) return false;
		return helper2(str, 0, pattern, 0);
	}
	
	//看pat[index]及其后面是不是都是a*b*c*这样的
	private boolean helper1(char[] pat, int index) {
		while(index+1 < pat.length && pat[index+1] == '*')
			index += 2;
		return index == pat.length;
	}
	
	//判断str[index1]与pat[index2]后面是否匹配
	private boolean helper2(char[] str, int index1, char[] pat, int index2) {
		boolean res1 = index1 == str.length; //str结束了
		boolean res2 = index2 == pat.length; //pat结束了
		boolean res3 = helper1(pat, index2); //pat末尾是b*类似的
		if(res1 && (res2 || res3))
			return true;
		if(res1 || res2){
			if(res2)
				return false;
			else
				return res3;
		}
		//当都没有到达末尾的时候，情况1：pat后面是a*这样的结构
		//如果第一个字符可以匹配，那么可能匹配0次或n次
		//不可以匹配，那么就只能匹配0次了
		if(index2+1 < pat.length && pat[index2+1] == '*'){
			if(helper3(str, index1, pat, index2))
				return helper2(str, index1, pat, index2+2) || helper2(str, index1+1, pat, index2);
			else
				return helper2(str, index1, pat, index2+2);
		}
		//情况2：没有a*这样的结构，只能一个个匹配了
		if(helper3(str, index1, pat, index2))
			return helper2(str, index1+1, pat, index2+1);
		else
			return false;
	}
	
	//匹配 str[index1] 与 pat[index2]
	private boolean helper3(char[] str, int index1, char[] pat, int index2) {
		return str[index1] == pat[index2] || pat[index2] == '.';
	}
}
```

## 53.表示数值的字符串

>请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。例如，字符串"+100","5e2","-123","3.1416"和"-1E-16"都表示数值。 但是"12e","1a3.14","1.2.3","+-5"和"12e+4.3"都不是。

```java
//正则表达式的使用

import java.util.regex.Pattern;
 
public class Solution {
    public static boolean isNumeric(char[] str) {
		boolean point = false, exp = false; //标志有小数点，有指数部分
		for(int i = 0;i < str.length;i++){
			if(str[i] == '+' || str[i] =='-'){
				if(i + 1 == str.length || !(str[i+1] >= '0' && str[i+1] <= '9' || str[i+1] == '.'))
					return false;  //如果str只有一个正负号，或者正负号后面既不是数字，也不是. 代表不是合法的
				if(!(i == 0 || str[i-1] == 'e' || str[i-1] == 'E'))
					return false;  //正负号只能出现在开头或者e/E的后面
			}else if(str[i] == '.'){
				if(point || exp || !(i+1 < str.length && str[i+1] >= '0' && str[i+1] <= '9'))
					return false;  //不允许出现两个. 且 .的前面不能有e，小数点后只能是数字
				point = true;
			}else if(str[i] == 'e' || str[i] == 'E'){
				if(exp || i+1 == str.length || !(str[i+1] >= '0' && str[i+1] <= '9' || str[i+1] == '-' || str[i+1] == '+'))
					return false;  //e的前面不能有e，但可以有. 且e/E的后面只能是数字或者正负号
				exp = true;
			}else if(str[i] < '0' || str[i] > '9')
				return false;
		}
		return true;
    }
}
```

## 54.字符流中首个不重复的字符

>请实现一个函数用来找出字符流中第一个只出现一次的字符。
>
>例如，当从字符流中只读出前两个字符"go"时，第一个只出现一次的字符是"g"。当从该字符流中读出前六个字符“google"时，第一个只出现一次的字符是"l"。如果当前字符流没有存在出现一次的字符，返回#字符。

```java
import java.util.*;

public class Solution {
    
    Queue<Character> queue = new LinkedList<>();  //按照插入的顺序存放那些出现过的字符，但保证其中无重复字符
    int[] count = new int[128];
    
    //Insert one char from stringstream
    public void Insert(char ch){
        if(count[ch] == 0){
            queue.add(ch);  //新出现的字符入队
        }
        count[ch]++;
    }
    
    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce(){
        while(!queue.isEmpty()){
            if(count[queue.peek()] == 1){
                return queue.peek();
            }
            else{
                queue.poll();
            }
        }
        return '#';
    }
}
```

## 55.链表中环的入口节点

>给一个链表，若其中包含环，请找出该链表的环的入口结点，否则，输出null。

```java
public class Solution {
	//思路：设置快慢指针，快指针一次可以走两格，慢指针只能走一格，那么只要是有环的链表，肯定是可以相遇的。
    //之后，将fast指针移到开头，并且速度减半；slow指针不动；那么两个继续走，就会在环的入口处相遇了。
    //原理：假设链表开始点为O，环的入口为点A，快慢指针第一次相遇点为B。
    //设O->A段长m，A->B段（顺时针圆弧）长k，B->A段长n。同时假设快指针在相遇前多走了x圈。
    //根据两个指针使用的时间相同，可以得到关系式：[m+x(n+k)+k] / 2 = m+k
    //可以得到：m+k = x(n+k)   ->   m+k = (x-1)(n+k)+n+k   ->   m = (x-1)(n+k)+n
    //那么我们从O点出发，按照速度1前进，到达A，需要耗时：m
    //从B点继续出发，按照速度1前进，经过x-1圈后，到达A点，耗时(x-1)(n+k)+n
    //根据推出来的式子，两者最终会在A点相遇。
    public ListNode EntryNodeOfLoop(ListNode pHead){
        if(pHead == null || pHead.next == null) return null;
        ListNode fast = pHead, slow = pHead;
        while(fast != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(fast == slow) break;
        }
        if(fast == null || fast.next == null) return null;  //如果没有出现环，就直接返回null了
        fast = pHead;
        while(fast != slow){
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
}
```

## 56.删除链表中重复的节点

>**原题**：在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。 
>
>例如，链表1->2->3->3->4->4->5 处理后为 1->2->5。

```java
public class Solution {
    //思路：这种题目别想别的，画好图，看着图写代码。根据是否出现重复数字，分两种情况来进行讨论。
    public ListNode deleteDuplication(ListNode pHead)
    {
        if(pHead == null || pHead.next == null) return pHead;
        ListNode dummy = new ListNode(-1);  //使用虚拟头结点的方式
        ListNode q = dummy, p = pHead, pre;
        while(p != null){
            if(p.next != null && p.val == p.next.val){
                int val = p.val;
                while(p != null && p.val == val){
                    p = p.next;    //越过所有的空闲节点
                }
            }
            else{
                q.next = p;
                p = p.next;
                q = q.next;
                q.next = null;  //暂时与后面的节点脱钩
            }
        }
        return dummy.next;
    }
}
```

>**变式题**：给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
>
>例如，链表1->2->3->3->4->4->5 处理后为 1->2->3->4->5。

```java
class Solution {
    //一样分情况讨论，与上面不同的是，可以留一个节点了。
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode p = head, pre;  //p指向当前节点
        while(p != null){
            if(p.next != null && p.val == p.next.val){
                pre = p;  //记录下这个节点
                while(p.next != null && p.val == p.next.val){
                    p = p.next;
                }
                p = p.next;    //后移到后面的第一个不同值的节点
                pre.next = p;
            }
            else{
                //当前节点与后面节点的值不同的时候，可以直接跳过
                p = p.next;
            }
        }
        return head;
    }
}
```

## [57].二叉树的下一个节点

>给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。

```java
public class Solution {
    public TreeLinkNode GetNext(TreeLinkNode node){
        if(node == null) return null;
        //当node有右子树的时候，就去找右子树中最左边的那个节点
        if(node.right != null){
            TreeLinkNode p = node.right;
            while(p.left != null){
                p = p.left;
            }
            return p;
        }
        //当node没有右子树的时候，看父节点;
        //如果没有父节点了，就可以直接返回null了;
        //如果有父节点的话，就看这个节点是不是它的父节点的左孩子，是的话，就返回其父节点;然后再去找父节点的父节点，看看是不是其父节点的左孩子
        while(node.next != null){
            if(node.next.left == node)
                return node.next;
            node = node.next;
        }
        return null;
    }
}
```

## 58.对称二叉树

>请实现一个函数，用来判断一棵二叉树是不是对称的。
>
>注意，如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。

```java
public class Solution {
    //思路：如果根节点的左节点值和右节点值相同，且都是这样的，就对称了
    boolean isSymmetrical(TreeNode pRoot){
        if(pRoot == null){
            return true;
        }
        return helper(pRoot.left, pRoot.right);
    }
    
    boolean helper(TreeNode node1, TreeNode node2){
        if(node1 == null && node2 == null) return true;
        if(node1 == null || node2 == null || node1.val != node2.val) return false;
        return helper(node1.left, node2.right) && helper(node1.right, node2.left);
    }
}
```

## 59.按之字形顺序打印二叉树

>请实现一个函数按照之字形打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右至左的顺序打印，第三行按照从左到右的顺序打印，其他行以此类推。

```java
import java.util.*;

public class Solution {
    //思路：其实是层次遍历的一种变形，需要借助栈，每次先压进去的节点，在其子节点的访问顺序上，是后访问的。
    //同时，还需要一个flag来标识，是先压入左节点，还是有压入右节点。
    public ArrayList<ArrayList<Integer> > Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer> > res = new ArrayList<>();
        if(pRoot == null){
            return res;
        }
        Stack<TreeNode> stack = new Stack<>();
        boolean flag = true;   //true代表先压入左节点。因为根节点的左节点需要后访问，所以是先入栈的。
        stack.push(pRoot);
        while(!stack.isEmpty()){
            ArrayList<Integer> res0 = new ArrayList<>();
            ArrayList<TreeNode> nodes = new ArrayList<>();
            while(!stack.isEmpty()){
                nodes.add(stack.pop());
            }
            for(TreeNode node: nodes){
                res0.add(node.val);
                if(flag){
                    if(node.left != null) stack.push(node.left);
                    if(node.right != null) stack.push(node.right);
                }
                else{
                    if(node.right != null) stack.push(node.right);
                    if(node.left != null) stack.push(node.left);
                }
            }
            res.add(res0);
            flag = !flag;
        }
        return res;
    }
}
```

## 60.把二叉树打印成多行

>从上到下按层打印二叉树，同一层结点从左至右输出。每一层输出一行。

```java
import java.util.*;

public class Solution {
    //思路：其实就是用队列来实现一个层次遍历了
    ArrayList<ArrayList<Integer> > Print(TreeNode pRoot) {
    	ArrayList<ArrayList<Integer> > res = new ArrayList<>();
        if(pRoot == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(pRoot);
        while(!queue.isEmpty()){
            ArrayList<TreeNode> nodes = new ArrayList<>();
            ArrayList<Integer> res0 = new ArrayList<>();
            while(!queue.isEmpty()){
                nodes.add(queue.poll());
            }
            for(TreeNode node: nodes){
                res0.add(node.val);
                if(node.left != null) queue.add(node.left);
                if(node.right != null) queue.add(node.right);
            }
            res.add(res0);
        }
        return res;
    }
}
```

## [61].序列化二叉树

>请实现两个函数，分别用来序列化和反序列化二叉树
>
>二叉树的序列化是指：把一棵二叉树按照某种遍历方式的结果以某种格式保存为字符串，从而使得内存中建立起来的二叉树可以持久保存。序列化可以基于先序、中序、后序、层序的二叉树遍历方式来进行修改，序列化的结果是一个字符串，序列化时通过 某种符号表示空节点（#），以 ！ 表示一个结点值的结束（value!）。
>
>二叉树的反序列化是指：根据某种遍历顺序得到的序列化字符串结果str，重构二叉树。
>
>例如，我们可以把一个只有根节点为1的二叉树序列化为"1,"，然后通过自己的函数来解析回这个二叉树

```java
import java.util.*;

public class Solution {
//按照层序遍历进行序列化: 1!20!30!40!#60!70!
	String Serialize(TreeNode root) {
		if(root == null) return null;
		Queue<TreeNode> queue = new LinkedList<>();
		StringBuilder sb = new StringBuilder();
		queue.add(root);
		int sum = 1;  //统计队列中非null节点的个数
		while(!queue.isEmpty()){
			List<TreeNode> nodes = new ArrayList<>();
			while(!queue.isEmpty()){
				nodes.add(queue.poll());
			}
			for(TreeNode node : nodes){
				if(node != null){
					sb.append(node.val).append('!');
					sum --;
					queue.add(node.left);
					queue.add(node.right);
					if(node.left != null) sum ++;
					if(node.right != null) sum ++;
				}
				else
					sb.append('#');
			}
			if(sum == 0) break;  //当后续的队列中都是null，也就是空节点的时候，就结束。
		}
		return sb.toString();
	}
	
	//根据字符串：1!20!30!-40!#60!70!，恢复出原来的那棵树
    TreeNode Deserialize(String str) {
		if(str == null || str.length() == 0 || str.charAt(0) == '#') return null;
		//首先，将str中的数值转换成为一个TreeNode的数组
		Queue<TreeNode> queue = new LinkedList<>();
		for(int i = 0;i < str.length();i++){
			if(str.charAt(i) == '#') queue.add(null);
			else{
				int val = 0;
				int flag = 1;
				if(str.charAt(i) == '-'){
					i++;
					flag = -1;
				}
				else if(str.charAt(i) == '+'){
					i++;
				}
				while(i < str.length() && str.charAt(i) != '!'){
					val *= 10;
					val += (int)(str.charAt(i) - '0');
					i++;
				}
				TreeNode node = new TreeNode(val * flag);
				queue.add(node);
			}
		}
		TreeNode root = queue.poll();
		List<TreeNode> pre = new ArrayList<>();  //存放父节点，这里肯定非null
		pre.add(root);
		while(!queue.isEmpty()){
			List<TreeNode> cur = new ArrayList<>();
			for(TreeNode parent : pre){
				parent.left = queue.poll();
				parent.right = queue.poll();
				if(parent.left != null)
					cur.add(parent.left);
				if(parent.right != null)
					cur.add(parent.right);
			}
			pre = cur;
		}
		return root;
	}
}
```

## 62.二叉搜索树的第k个节点

>给定一棵二叉搜索树，请找出其中的第k小的结点。
>
>例如，（5，3，7，2，4，6，8） 中，按结点数值大小顺序第三小结点的值为4。

```java
import java.util.*;

public class Solution {
    //对于二叉搜索树来说，按照中序遍历得到的是排好序了的数组，我们要找第k小的，其实就是找这个数组中的第k个元素.
    
    List<TreeNode> nodes = new ArrayList<>();
    
    TreeNode KthNode(TreeNode pRoot, int k){
        if(k <= 0 || pRoot == null) return null;  //防止空树和k小于等0
        InOrder(pRoot, k);
        if(k > nodes.size()) return null; //防止k过大了
        return nodes.get(k-1);
    }
    
    void InOrder(TreeNode root, int k){
        if(nodes.size() == k) return;  //当前k个数获取之后，就停止遍历
        if(root.left != null)
	        InOrder(root.left, k);
        nodes.add(root);
        if(root.right != null)
 	       InOrder(root.right, k);
    }
}
```

## 63.数据流中的中位数

>如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。我们使用Insert()方法读取数据流，使用GetMedian()方法获取当前读取数据的中位数。

```java
import java.util.*;

public class Solution {

    ArrayList<Integer> list = new ArrayList<>();  //存放数据用的，需要保持顺序的列表
    
    public void Insert(Integer num) {
        //插入后，依旧要保持list中的数据是升序存放到的
        int i = 0;
        while(i < list.size() && list.get(i) < num){
            i++;
        }
        list.add(i, num);
    }

    public Double GetMedian() {
        int size = list.size();  //当前列表中的数据个数
        if(size % 2 == 1){
            return 1.0 * list.get(size / 2);
        }
        else{
            return 0.5 * (list.get(size/2-1) + list.get(size/2));
        }
    }
}
```

## 64.滑动窗口的最大值

>给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值。
>
>例如，如果输入数组{2,3,4,2,6,2,5,1}及滑动窗口的大小3，那么一共存在6个滑动窗口，他们的最大值分别为{4,4,6,6,6,5}； 针对数组{2,3,4,2,6,2,5,1}的滑动窗口有以下6个： {[2,3,4],2,6,2,5,1}， {2,[3,4,2],6,2,5,1}， {2,3,[4,2,6],2,5,1}， {2,3,4,[2,6,2],5,1}， {2,3,4,2,[6,2,5],1}， {2,3,4,2,6,[2,5,1]}。
>
>窗口大于数组长度的时候，返回空

```java
import java.util.*;

public class Solution {
    //使用双端队列存放数据的下标，保证对首元素永远是当前窗口中的最大值的下标
    //每次有新的元素来的时候，如果队列前面的元素比它小，就从后到前面，依次把前面的元素删除掉，
    public ArrayList<Integer> maxInWindows(int [] num, int size){
        ArrayList<Integer> res = new ArrayList<>();
        if(num == null || num.length == 0 || size <= 0) return res;
        Deque<Integer> queue = new LinkedList<>();
        for(int i = 0;i < num.length;i++){
            while(!queue.isEmpty() && num[queue.peekLast()] < num[i]){
                queue.removeLast();
            }
            queue.add(i);  //放入当前的元素的下标
            if(i >= size-1){
                //当num[size-1]入队后，就已经形成一个完成的滑动窗口了。
                res.add(num[queue.peekFirst()]);
            }
            if(i - queue.peekFirst() == size-1){
                //当队列中的队首下标过期的时候，需要将它移出去
                queue.removeFirst();
            }
        }
        return res;
    }
}
```

## 65.矩阵中的路径

>请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一个格子开始，每一步可以在矩阵中向左，向右，向上，向下移动一个格子。如果一条路径经过了矩阵中的某一个格子，则该路径不能再进入该格子。 
>
>例如 a b c e
>
>​     s f c s
>
>​     a d e e
>
>矩阵中包含一条字符串"bcced"的路径，但是矩阵中不包含"abcb"路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入该格子。

```java
public class Solution {
    public boolean hasPath(char[] matrix, int rows, int cols, char[] str){
        //首先，需要将一维的字符串数组，转换成为二维的字符数组
        char[][] mat = new char[rows][cols];
        for(int i = 0;i < rows;i++){
            for(int j = 0;j < cols;j++){
                mat[i][j] = matrix[i*cols + j];
            }
        }
        boolean[][] visit = new boolean[rows][cols];
        for(int i = 0;i < rows;i++){
            for(int j = 0;j < cols;j++){
                if(dfs(mat, i, j, visit, str, 0)){
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean dfs(char[][] mat, int i, int j, boolean[][] visit, char[] str, int k){
        if(k == str.length) return true;  //遍历完了的时候，就返true
        if(i < 0 || i >= mat.length || j < 0 || j >= mat[0].length || visit[i][j] || mat[i][j] != str[k]){
            return false;
        }
        visit[i][j] =  true;
        boolean res = dfs(mat, i-1, j, visit, str, k+1) ||
                dfs(mat, i+1, j, visit, str, k+1) ||
                dfs(mat, i, j-1, visit, str, k+1) ||
                dfs(mat, i, j+1, visit, str, k+1);
        if(res == true){
            return res;
        }
        visit[i][j] = false;  //回溯，不影响其他遍历
        return false;
    }
}
```

## 66.机器人的运动范围

>地上有一个m行和n列的方格。
>
>一个机器人从坐标0,0的格子开始移动，每一次只能向左，右，上，下四个方向移动一格，但是不能进入行坐标和列坐标的数位之和大于k的格子。 
>
>例如，当k为18时，机器人能够进入方格（35,37），因为3+5+3+7 = 18。但是，它不能进入方格（35,38），因为3+5+3+8 = 19。请问该机器人能够达到多少个格子？

```java
public class Solution {
    //依旧是采用dfs的做法
    
    int res = 0;
    
    public int movingCount(int threshold, int rows, int cols){
        if(threshold < 0) return res;
        boolean[][] visit = new boolean[rows][cols];
        dfs(0, 0, visit, threshold, rows, cols);  //注意这个题目只能从（0,0）出发
        return res;
    }
    
    public void dfs(int i, int j, boolean[][] visit, int threshold, int rows, int cols){
        if(i < 0 || i >= rows || j < 0 || j >= cols || visit[i][j]) return;
        if(get(i, j) > threshold) return;
        visit[i][j] = true;
        res++;
        dfs(i-1, j, visit, threshold, rows, cols);  //再去找四周其他的点
        dfs(i+1, j, visit, threshold, rows, cols);
        dfs(i, j-1, visit, threshold, rows, cols);
        dfs(i, j+1, visit, threshold, rows, cols);
    }
    
    //得到两个数的数位之和
    public int get(int i, int j){
        int sum = 0;
        while(i != 0){
            sum += i % 10;
            i /= 10;
        }
        while(j != 0){
            sum += j % 10;
            j /= 10;
        }
        return sum;
    }
}
```

## 67.剪绳子

>给你一根长度为n的绳子，请把绳子剪成整数长的m段（m、n都是整数，n>1并且m>1，m<=n），每段绳子的长度记为k[1],...,k[m]。请问k[1]x...xk[m]可能的最大乘积是多少？
>
>例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。

```java
//首先，可以写出target<2 或者等于2,3,4时候的情况。
//然后，如果我们知道dp[i]的话，也就是知道了i被划分开，最大得到的乘积是dp[i]
//那么，当我们需要求dp[n]的时候，就只需要找到dp[i]*dp[n-i]中的最大值就可以了
public class Solution {
    public int cutRope(int target) {
        if(target < 2) return 0;
        if(target == 2) return 1;
        if(target == 3) return 2;
        int[] dp = new int[target+1];
        dp[1] = 1;   //这里实际不是说1被拆开后，能乘积得到1
        dp[2] = 2;
        dp[3] = 3;
        for(int i = 4;i <= target;i++){
            int max = Integer.MIN_VALUE;
            for(int j = 1;j <= i/2;j++){
                max = Math.max(max, dp[j] * dp[i-j]); //将长度为j的绳子划分为j和i-j两段
            }
            dp[i] = max;
        }
        return dp[target];
    }
}
```

