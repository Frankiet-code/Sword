请使用crtl+f，自己搜索题号或者题目名称，就不加目录了。加油！  :blush:

-------

| 作者 | 雷品源 |
| :---: |  :----: |
| 进度 |  75 |
| 时间 |  20201029  |

-------
# 一、算法题

## 1.两数之和

>给定一个整数数组**nums**和一个目标值**target**，请你在该数组中找出和为目标值的那**两个**整数，并返回他们的数组下标。
>
>你可以假设每种输入只会对应一个答案。但数组中同一个元素不能使用两遍。
>
>例子：给定 nums = [2, 7, 11, 15], target = 9
>
>因为 nums[0] + nums[1] = 2 + 7 = 9
>所以返回 [0, 1]

```java
import java.util.HashMap;

class Solution {
    //思路1：使用hashmap存放（数字－其下标）
    public int[] twoSum1(int[] nums, int target) {
        if(nums == null || nums.length <= 1) return new int[0];
        int[] res = new int[2];
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0;i < nums.length;i++){
            if(map.containsKey(target-nums[i])){
                res[0] = map.get(target-nums[i]);
                res[1] = i;
                return res;
            }
            else{
                map.put(nums[i], i);
            }
        }
        return res;
    }
}
```

## 2.两数之和

>给出两个**非空**的链表用来表示两个非负的整数。其中，它们各自的位数是按照**逆序**的方式存储的，并且它们的每个节点只能存储**一位**数字。如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
>
>您可以假设除了数字**0**之外，这两个数都不会以**0**开头。
>
>示例：输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
>输出：7 -> 0 -> 8
>原因：342 + 465 = 807

```java
class Solution {
    //思路：注意这里的两个数本身就是逆序存放的，并且最后的结果也是逆序存放，所以从开始往后面加就可以了
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        ListNode dummy = new ListNode(-1);  //虚拟头结点
        ListNode p1 = l1, p2 = l2, p = dummy;
        int carry = 0, sum = 0;  //carry表示进位
        while(p1 != null || p2 != null || carry != 0){
            sum = carry;
            carry = 0;
            if(p1 != null){
                sum += p1.val; p1 = p1.next;
            }
            if(p2 != null){
                sum += p2.val; p2 = p2.next;
            }
            if(sum > 9){
                carry = 1; sum -= 10;  //更新carry和sum的值
            }
            p.next = new ListNode(sum);
            p = p.next;
        }
        return dummy.next;
    }
}
```

## 3.无重复字符的最长子串

>给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
>
>示例 1:
>
>输入: "abcabcbb"
>输出: 3 
>解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。

```java
class Solution {
    //思路：滑动窗口机制，使用set存放窗口[left, right]中的字符；
    //如果有相同的字符，就移动左指针，并在set中删除左指针指向的那个字符
    public int lengthOfLongestSubstring(String s) {
        if(s == null || s.length() <= 0) return 0;
        int res = 0;
        Set<Character> set = new HashSet<>();
        int left = 0, right = 0;
        while(right < s.length()){
            char c = s.charAt(right);
            if(set.contains(c)){
                set.remove(s.charAt(left));   //移除掉窗口left对应的字符
                left++;
            }
            else{
                set.add(c);  //窗口是[left, right]
            	res = Math.max(res, right-left+1);
            	right++; 
            }
        }
        return res;
    }
    
    //思路2：同样是使用滑动窗口，只不过这里使用hashmap存放 字符-其下标
    public int lengthOfLongestSubstring2(String s) {
        if(s == null || s.length() == 0) return 0;
        HashMap<Character, Integer> map = new HashMap<>();
        int left = 0, right = 0, res = 0;
        while(right < s.length()){
            char c = s.charAt(right);
            if(map.containsKey(c)){
                //如果碰到了重复的字符，就调整窗口左侧的位置，到这个重复的字符之前出现的下标处的后一个位置
                left = Math.max(left, map.get(c)+1);  //但是这里一定要保证left不会后退
            }
            map.put(c, right);
            res = Math.max(res, right-left+1);  //窗口中存放的值是[left, right]
            right++;
        }
        return res;
    }
    
    //思路3：暴力解，判断字符串s的所有子串是否是无重复的，返回无重复的子串中最长的那个的长度
    public static int lengthOfLongestSubstring3(String s) {
		if(s == null || s.length() == 0)
			return 0;
		int res = 0;
		for(int i = 0;i < s.length();i++){
			for(int j = i + 1;j <= s.length();j++){
				String sub = s.substring(i, j);
				if(isUnique(sub))
					res = Math.max(res, sub.length());
			}
		}
		return res;
	} 
	
	//使用hashSet来存放之前已经遍历过的字符 时间复杂度O(N)
	public static boolean isUnique(String str){
		Set<Character> set = new HashSet<>();
		for(int i = 0;i < str.length();i++){
			if(set.contains(str.charAt(i)))
				return false;
			else
				set.add(str.charAt(i));
		}
		return true;
	}
}
```

## 5.最长回文子串

>给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
>
>示例 1：输入: "babad"  输出: "bab"   注意: "aba" 也是一个有效答案。
>示例 2：输入: "cbbd"   输出: "bb"

```java
class Solution {
    //采用中心扩散法来做
    public String longestPalindrome(String s) {
        if(s == null || s.length() <= 1) return s;
        int res = 0, start = 0, end = 0;
        for(int i = 0;i < s.length();i++){
            int len1 = lengthOfSingleCore(s, i);
            int len2 = lengthOfDoubleCore(s, i, i+1);
            if(Math.max(len1, len2) > res){
                res = Math.max(len1, len2);
                if(len1 >= len2){
                    //单核的回文更加长
                    start = i - len1/2;
                    end = i + len1/2;
                }
                else{
                    //双核的回文更加长
                    start = i - (len2-1)/2;
                    end = i + len2/2;
                }
            }
        }
        return s.substring(start, end+1);
    }
    
    public int lengthOfSingleCore(String s, int i){
        int l = i, r = i;
        while(l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)){
            l--; r++;
        }
        return (r-l-1);
    }
    
    public int lengthOfDoubleCore(String s, int l, int r){
        while(l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)){
            l--; r++;
        }
        return (r-l-1);
    }
    
}

同时，可以将上述中的单核和双核两种方式，合二为一，用一个函数来做，代码见下面：

class Solution {
    //思路：采用中心扩散法，从一个中心（可以是一个字符，也可以是两个相同的字符）出发，然后向两侧扩散，得到能够达到的最长回文。因此，需要遍历整个字符串，每个字符都可以成为中心。
    public String longestPalindrome(String s) {
        if(s == null || s.length() == 0) return "";
        int start = 0, end = 0, res = 0;
        for(int i = 0;i < s.length();i++){
            int len1 = getLength(s, i, i);  //中心为1个字符的时候
            int len2 = getLength(s, i, i+1);  //中心可能是2个字符的时候
            if(Math.max(len1,len2) > res){
                //出现了更大的回文长度
                res = Math.max(len1, len2);
                start = i - (res-1)/2;
                end = i + res/2;
            }
        }
        return s.substring(start, end+1);
    }
    
    //从s中的i和j位置出发，往两侧走，得到的回文串的长度。
    //注意：这里的i和j位置上的字符是否相同，也是需要判断的
    public int getLength(String s, int i, int j){
        while(i >= 0 && j <= s.length()-1 && s.charAt(i) == s.charAt(j)){
            i--;j++;
        }
        return j-i-1;//得到的回文串是(i, j)，因此长度是j-i-1。如果j=i+1/i，那么至少长度为0
    }
}
```



## 14.最长公共前缀

>编写一个函数来查找字符串数组中的最长公共前缀。
>
>如果不存在公共前缀，返回空字符串 ""。
>
>示例 1:
>
>输入: ["flower","flow","flight"]
>输出: "fl"
>示例 2:
>
>输入: ["dog","racecar","car"]
>输出: ""
>解释: 输入不存在公共前缀。
>说明:
>
>所有输入只包含小写字母 a-z 。
>

```java
class Solution {
    //思路：选中第一个字符串，然后看它的字符在后面其他字符串中是否都依次出现。
    public String longestCommonPrefix(String[] strs) {
        if(strs == null || strs.length == 0) return "";
        StringBuilder res = new StringBuilder();
        for(int i = 0;i < strs[0].length();i++){
            char c = strs[0].charAt(i);
            for(int j = 1;j < strs.length;j++){
                if(i >= strs[j].length() || strs[j].charAt(i) != c){
                    return res.toString();   //碰到字符串结尾或者不同的字符，就直接返回了。
                }
            }
            res.append(c);
        }
        return res.toString();
    }
}
```

## 15.三数之和

>给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
>
>注意：答案中不可以包含重复的三元组。
>
>示例：给定数组 nums = [-1, 0, 1, 2, -1, -4]，
>
>满足要求的三元组集合为：[  [-1, 0, 1],  [-1, -1, 2] ]

```java
class Solution {
    //思路：需要先排序，然后从前面开始，选定好有个数之后，
    //剩下的数使用双指针的方法来求两数之和等于targ-nums[i]的值
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null || nums.length <= 2) return res;
        Arrays.sort(nums);
        int target = 0;  //这里将target设定为0，符合题意，也方便三数之和的变式题————四数之和
        for(int i = 0;i < nums.length-2 && nums[i] <= target;i++){
            if(i >= 1 && nums[i] == nums[i-1]) continue; //如果nums[i]与之前的数相同，需要跳过去
            int left = i+1, right = nums.length-1;
            while(left < right){
                if(nums[left]+nums[right]==target-nums[i]){
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    //如果后面的数相同，需要跳过
                    while(left<right&&nums[left]==nums[left+1]){
                        left++;
                    }
                    while(left<right&&nums[right]==nums[right-1]){
                        right--;
                    }
                    left++;right--;
                }
                else if(nums[left]+nums[right]<target-nums[i]){
                    left++;
                }
                else{
                    right--;
                }
            }
        }
        return res;
    }
}
```

## 16.最接近的三数之和

>给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
>
>```
>输入：nums = [-1,2,1,-4], target = 1
>输出：2
>解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
>```

```java
class Solution {
    //思路：依旧是采用和三数之和的思路，先进行排序，然后找到最接近target的值，发现差距变小之后，就更新res的值
    public int threeSumClosest(int[] nums, int target) {
        if(nums == null || nums.length <= 2) return -1;
        Arrays.sort(nums);
        int res = nums[0]+nums[1]+nums[2];  //默认值设为前三个数的和
        for(int i = 0;i < nums.length-2;i++){
            if(i > 0 && nums[i-1] == nums[i]) continue;
            int left = i+1, right = nums.length-1;
            while(left < right){
                int sum = nums[i] + nums[left] + nums[right];
                if(sum == target){
                    //此时的差距是最小的，可以直接返回了
                    return sum;
                }
                else if(sum < target){
                    left++;
                }
                else{
                    right--;
                }
                if(Math.abs(sum-target) < Math.abs(res-target)){
                    res = sum;
                }
            }
        }
        return res;
    }
}
```

## 17.电话号码的字母组合

>给定一个仅包含数字**2-9**的字符串，返回所有它能表示的字母组合。
>
>给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
>
>2:abc   3:def   4:ghi   5:jkl  6:mno  7:pqrs   8:tuv    9:wxyz
>
>示例：  输入："23"w
>        输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
>说明:  尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。

```java
class Solution {
    //思路：首先使用hashmap将数字和字母的对应关系存放起来，然后再根据digits的情况进行字符串的拼接
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if(digits == null || digits.length() == 0){
            return res;
        }
        HashMap<Integer, List<String>> map = new HashMap<>();
        char c = 'a';
        for(int i = 2;i <= 9;i++){
            List<String> list = new ArrayList<>();
            for(int j = 0;j < 3;j++){
                list.add(String.valueOf(c++));  //将字符串转换为字符串
            }
            if(i == 7 || i == 9){
                list.add(String.valueOf(c++));
            }
            map.put(i, list);
        }
        for(int i = 0;i < digits.length();i++){
            List<String> list0 = map.get((int)(digits.charAt(i) - '0'));
            if(res.size() == 0){
                for(String s : list0){
                    res.add(s);
                }
            }
            else{
                List<String> res_new = new ArrayList<>();
                for(String s1: list0){
                    for(String s0: res){
                        res_new.add(s0+s1);
                    }
                }
                res.clear();  //清空掉原先的列表中的字符串。
                res.addAll(res_new);  //加入新的字符串列表中的全部元素
            }
        }
        return res;
    }
}
```

## 18.四数之和

>给定一个包含**n**个整数的数组**nums**和一个目标值**target**，判断**nums**中是否存在四个元素a,b,c和d，使得**a+b+c+d**的值与**target**相等？找出所有满足条件且不重复的四元组。
>
>注意：答案中不可以包含重复的四元组。
>
>示例：给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
>
>满足要求的四元组集合为：
>[ [-1,  0, 0, 1],
>  [-2, -1, 1, 2],
>  [-2,  0, 0, 2]]

```java
class Solution {
    //思路：利用之前的三数之和，先限定好第一个数之和，然后再剩下的数中间找到三数之和等于target-nums[i]的数
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> fourSum(int[] nums, int target) {
        if(nums == null || nums.length <= 3) return res;
        Arrays.sort(nums);
        for(int i = 0;i < nums.length-3;i++){
            if(i > 0 && nums[i-1] == nums[i]) continue;
            threeSum(nums, target-nums[i], i);
        }
        return res;
    }
    
    //这是改造好了的三数之和，其中的start代表从nums[start]开始求四数之和的。
    //思路：需要先排序，然后从前面开始，选定好有个数之后，
    //剩下的数使用双指针的方法来求两数之和等于targ-nums[i]的值
    public void threeSum(int[] nums, int target, int start) {
        for(int i = start+1;i < nums.length - 2;i++){
            if(i > start+1 && nums[i] == nums[i-1]) continue; //如果nums[i]与之前的数相同，需要跳过去
            int left = i+1, right = nums.length-1;
            while(left < right){
                if(nums[left]+nums[right]==target-nums[i]){
                    res.add(Arrays.asList(nums[start], nums[i], nums[left], nums[right]));
                    //如果后面的数相同，需要跳过
                    while(left<right&&nums[left]==nums[left+1]){
                        left++;
                    }
                    while(left<right&&nums[right]==nums[right-1]){
                        right--;
                    }
                    left++;right--;
                }
                else if(nums[left]+nums[right]<target-nums[i]){
                    left++;
                }
                else{
                    right--;
                }
            }
        }
    }
}
```

## 19.删除链表的倒数第N个节点

>给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
>
>示例：
>
>给定一个链表: 1->2->3->4->5, 和 n = 2.
>
>当删除了倒数第二个节点后，链表变为 1->2->3->5.
>说明：给定的 n 保证是有效的。
>
>进阶：你能尝试使用一趟扫描实现吗？
>

```java
class Solution {
    //思路1：首先计算出链表的总长度，然后计算出我们是删除链表的正序第几个节点
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null || n <= 0) return head;
        int len = 0;
        ListNode p = head;
        while(p != null){
            p = p.next;
            len += 1;
        }
        if(n > len) return head;  //如果只有3个节点，却要删倒数第4个，就直接返回了
        p = head;
        n = len - n;  //变成正数第几个节点
        ListNode pre = null;  //当前节点的前一个节点
        while(n != 0){
            pre = p;
            p = p.next;
            n -= 1;
        }
        if(pre == null){
            return head.next;  //如果pre没有初始化成正常节点，代表n为0，需要删除首个节点。
        }
        else{
            pre.next = p.next;  //如果初始化为正常节点了，就跳过当前节点。
        }
        return head;
    }
    
    //思路2：采用双指针的方式，快的指针先走k个节点，就可以只遍历一遍链表。
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null || n <= 0) return head;
        ListNode fast = head, slow = head, pre = head;
        while(n != 0){
            fast = fast.next;
            n -= 1;
        }
        if(fast == null){
            return head.next;   //如果fast已经为null了，代表删除的就是第一个节点。
        }
        while(fast != null){
            pre = slow;
            slow = slow.next;
            fast = fast.next;
        }
        pre.next = slow.next;
        return head;
    }
}
```

## 20.有效的括号

>给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
>
>有效字符串需满足：
>
>左括号必须用相同类型的右括号闭合。
>左括号必须以正确的顺序闭合。
>注意空字符串可被认为是有效字符串。
>
>示例 1:
>
>输入: "()"
>输出: true
>示例 2:
>
>输入: "()[]{}"
>输出: true
>示例 3:
>
>输入: "(]"
>输出: false
>示例 4:
>
>输入: "([)]"
>输出: false
>示例 5:
>
>输入: "{[]}"
>输出: true

```java
class Solution {
    //思路1：使用栈来做，遇到匹配的括号的时候，就弹出栈顶元素，最后判断栈是不是为空就行。
    public boolean isValid(String s) {
        if(s == null || s.length() == 0) return true;
        Stack<Character> stack = new Stack<>();
        for(int i = 0;i < s.length();i++){
            if(stack.isEmpty() || (!match(stack.peek(), s.charAt(i)))){
                stack.push(s.charAt(i)); //栈为空，或者不匹配的时候，继续压栈。
            }
            else{
                stack.pop();
            }
        }
        return stack.isEmpty();
    }
    
    public boolean match(char c1, char c2){
        if((c1 == '(' && c2 == ')')||(c1 == '[' && c2 == ']')||(c1 =='{'&&c2 =='}')){
            return true;
        }
        return false;
    }
    
    //思路2：同样使用栈来做，遇到匹配的括号的时候，就弹出栈顶元素，最后判断栈是不是为空就行。
    //与思路1的区别是，这里不再用match函数来进行匹配判定，而是用一个map来存放匹配信息。
    public boolean isValid(String s) {
        if(s == null || s.length() == 0) return true;
        HashMap<Character, Character> map = new HashMap<>();
        map.put(')', '(');  //用来寻找前面半部分括号
        map.put(']', '[');
        map.put('}', '{');
        Stack<Character> stack = new Stack<>();
        for(int i = 0;i < s.length();i++){
            char c = s.charAt(i);
            if(map.containsKey(c)){
                //先判断是不是右边部分，是的话，才可能进行匹配
                if(stack.isEmpty() || stack.peek() != map.get(c)){
                    return false;  //此时，这个右边括号无法匹配，会一直留着，直接返回false
                }
                else{
                    stack.pop();   
                }
            }
            else{
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }
    
    //思路3:循环使用java中的replace函数，将相连的{} / [] / ()，用""进行替换，最后看长度是不是0
    public boolean isValid(String s) {
        int len;
        do{
            len = s.length();
            s = s.replace("()", "").replace("[]", "").replace("{}", "");
        } while(len != s.length());
        return s.length() == 0;
    }
}
```

- **小知识点回顾**：栈是先进后出，是一种逻辑结构。在数据结构中，分物理结构和逻辑结构两种。像数组（又称顺序存储）、链表（又称链式存储）这些实实在在实现在物理上的东西，是物理结构；而栈、队列、树、堆、图等等数据结构，我们可以通过数组和链表这样的物理结构去实现，它们都称为逻辑结构。
- 这个时候，可能面试题就来了，让你**实现一个栈看看？**
- 等你用数组或者链表实现了简单的栈，面试官又会让你实现一些变种题，比如：155题的最小栈，或者延伸到其他数据结构上去，比如：232用栈实现队列。或者像225那样用队列来实现一个栈。

```java
//用数组实现一个栈，用来存放类型为Object的数据，提供初始化、入栈、出栈、判空等基本功能，支持自动扩容，并提供测试用例。
/**
 * @Author: lei
 * @Date: 2020/10/18 16:10
 * @Description: 使用数组实现一个堆栈
 */
public class MyStack<E> {
	
	private Object[] value;  //存放数据的
	private int capacity;  //当前容量，需要扩容的话，每次*2
	private int top = -1;  //栈顶
	
	public MyStack(){
		this.capacity = 10;  //默认容量为10
		this.value = new Object[capacity];
	}
	
	public MyStack(int capacity){
		this.capacity = capacity;
		this.value = new Object[capacity];
	}
	
	public void push(E e){
		if(top == capacity){
			this.capacity *= 2;
		}
		top++;
		value[top] = e;
	}
	
	public Object pop() throws Exception {
		if(top == -1){
			throw new Exception("the stack is empty.");
		}
		else{
			Object v = value[top];
			top--;
			return v;
		}
	}
	
	public Object peek() throws Exception {
		if(top == -1){
			throw new Exception("the stack is empty.");
		}
		else{
			return value[top];
		}
	}
	
	//判空函数
	public boolean isEmpty(){
		return top == -1;
	}
	
	public static void main(String[] args) throws Exception {
		MyStack myStack = new MyStack();
		myStack.push(1);
		myStack.push(1);
		myStack.push(2);
		myStack.push(3);
		System.out.println(myStack.peek());
		System.out.println(myStack.pop());
		System.out.println(myStack.pop());
		System.out.println(myStack.pop());
		System.out.println(myStack.peek());
	}
}//class end
```



## 21.合并两个有序链表

>将两个升序链表合并为一个新的**升序**链表并返回。
>
>新链表是通过拼接给定的两个链表的所有节点组成的。 
>
>```
>例如： 输入：1->2->4, 1->3->4   输出：1->1->2->3->4->4
>```

```java
class Solution {
    //思路：首先判断两个链表为不为null，然后比较两个链表的首个节点，选出最小的。
    //之后，再比较剩下的节点。
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		if(l1 == null) return l2;
		if(l2 == null) return l1;
		ListNode dummy = null, p1 = l1, p2 = l2;
		if(p1.val < p2.val){
			dummy = p1;
			p1 = p1.next;
		}
		else{
			dummy = p2;
			p2 = p2.next;
		}
		ListNode p = dummy;
		while(p1 != null && p2 != null){
			if(p1.val < p2.val){
				p.next = p1;
				p1 = p1.next;
			}
			else{
				p.next = p2;
				p2 = p2.next;
			}
			p = p.next;
		}
		if(p1 == null){
			p.next = p2;
		}
		if(p2 == null){
			p.next = p1;
		}
		return dummy;
    }
}
```

## 23.合并K个升序链表

>给你一个链表数组，每个链表都已经按升序排列。
>
>请你将所有链表合并到一个升序链表中，返回合并后的链表。
>
>例如：
>
>输入：lists = [[1,4,5],[1,3,4],[2,6]]
>输出：[1,1,2,3,4,4,5,6]
>解释：链表数组如下：
>[
>  1->4->5,
>  1->3->4,
>  2->6
>]
>将它们合并到一个有序链表中得到。
>1->1->2->3->4->4->5->6

```java
class Solution {
    //思路1：使用题21的合并两个升序链表的代码，对链表数组中的每个链表进行合并。
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length == 0) return null;
        if(lists.length == 1) return lists[0];
        ListNode res = null;
        for(ListNode head: lists){
            res = mergeTwoLists(res, head);
        }
        return res;
    }
    
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		if(l1 == null) return l2;
		if(l2 == null) return l1;
		ListNode dummy = null, p1 = l1, p2 = l2;
		if(p1.val < p2.val){
			dummy = p1;
			p1 = p1.next;
		}
		else{
			dummy = p2;
			p2 = p2.next;
		}
		ListNode p = dummy;
		while(p1 != null && p2 != null){
			if(p1.val < p2.val){
				p.next = p1;
				p1 = p1.next;
			}
			else{
				p.next = p2;
				p2 = p2.next;
			}
			p = p.next;
		}
		if(p1 == null){
			p.next = p2;
		}
		if(p2 == null){
			p.next = p1;
		}
		return dummy;
    }
    
    //思路2：使用堆排序的方式，改写一下优先队列中的比较函数，这里其实就没有利用 排序好的 这个条件
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length == 0) return null;
        if(lists.length == 1) return lists[0];
        //使用最小堆
        Queue<ListNode> minHeap = new PriorityQueue<>(new Comparator<ListNode>() {
			@Override
			public int compare(ListNode o1, ListNode o2) {
				return o1.val-o2.val;
			}
		});
        ListNode dummy = new ListNode(-1);
        ListNode q = dummy;
        for(ListNode head: lists){
            ListNode p = head;
            while(p != null){
                minHeap.offer(p);
                p = p.next;
            }
        }
        while(!minHeap.isEmpty()){
            q.next = minHeap.poll();
            q = q.next;
        }
        q.next = null;  //截断
        return dummy.next;
    }
}
```

## 45.跳跃游戏II

>给定一个非负整数数组，你最初位于数组的第一个位置。
>
>数组中的每个元素代表你在该位置可以跳跃的最大长度。
>
>你的目标是使用最少的跳跃次数到达数组的最后一个位置。
>
>示例:
>
>输入: [2,3,1,1,4]
>输出: 2
>解释: 跳到最后一个位置的最小跳跃数是 2。
>     从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
>说明: w假设你总是可以到达数组的最后一个位置。

```java
class Solution {
    //思路1：直接基于跳跃游戏I中那个思路来修改代码即可。
    //并且，因为这里需要使用最小的步数，所以应该每次都从前往后看，是否能在i很小的时候，就直接跳到lastPosition
    //代码没问题，但是经常出现提交超时的情况。
    public int jump(int[] nums) {
        if(nums == null || nums.length <= 1) return 0;
        int lastPosition = nums.length-1;
        int res = 0;
        while(lastPosition != 0){
            for(int i = 0;i < nums.length-1;i++){
                if(i + nums[i] >= lastPosition){
                    lastPosition = i;
                    res++;
                    break;
                }
            }
        }
        return res;
    }
    
    //于是换了一个思路：基于跳跃游戏I中的思路II来整的
    public int jump2(int[] nums) {
        if(nums.length == 1) return 0;
        int cur = 0, canReach = 0 + nums[0];  //当前位置，当前能到达的最远位置
        int res = 0;
        for(int i = 0;i < nums.length;i++){
            canReach = Math.max(canReach, i + nums[i]);
            if(canReach >= nums.length-1) {
                return res+1;  //已经到达了，就可以直接返回了
            }
            if(i == cur){  //这代表是从当前位置起跳的情况下。
                res++;
                cur = canReach;
            }
        }
        return res;
    }
}
```

## 53.最大子序列和

>给定一个整数数组**nums**，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
>
>示例: 输入: [-2,1,-3,4,-1,2,1,-5,4]   输出: 6
>解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。

```java
class Solution {
    //思路：动态规划的题目，建立dp数组，然后使用局部最优解来做。
    public int maxSubArray(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        dp[0] = nums[0];   //子序列中最少有一个元素
        int res = dp[0];
        for(int i = 1;i < nums.length;i++){
            if(dp[i-1] < 0){
                dp[i] = nums[i];  //之前的部分序列和小于0，舍弃掉
            }
            else{
                dp[i] = dp[i-1] + nums[i]; //可以借助之前的部分序列和，组成更大的局部序列和
            }
            res = Math.max(res, dp[i]);  //看当前的局部最优解，能不能成为全局的最优解
        }
        return res;
    }
}
```

## 54.螺旋矩阵

>给定一个包含**m x n**个元素的矩阵（m行, n列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
>
>示例 1: 输入:
>[ [ 1, 2, 3 ],
>  [ 4, 5, 6 ],
>  [ 7, 8, 9 ]]      输出: [1,2,3,6,9,8,7,4,5]
>示例 2:  输入:
>[ [1, 2, 3, 4],
>  [5, 6, 7, 8],
>  [9,10,11,12]]     输出: [1,2,3,4,8,12,11,10,9,5,6,7]

```java
class Solution {
    
    List<Integer> res = new ArrayList<>();
    
    //思路：限定好左上角的坐标和右下角的坐标，然后遍历4个边的数字。
    public List<Integer> spiralOrder(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return res;
        }
        helper(matrix, 0, matrix.length-1, 0, matrix[0].length-1);
        return res;
    }
    
    public void helper(int[][] mat, int i_start, int i_end, int j_start, int j_end){
        if(i_start > i_end || j_start > j_end) return ;
        if(i_start == i_end){  //代表此时只剩下一行了
            while(j_start <= j_end){
                res.add(mat[i_start][j_start++]);
            }
            return ;
        }
        if(j_start == j_end){  //代表此时只剩下一列了
            while(i_start <= i_end){
                res.add(mat[i_start++][j_start]);
            }
            return ;
        }
        int i = i_start, j = j_start;  //开始位置
        while(j <= j_end){
            res.add(mat[i][j++]);
        }
        j--;i++;
        while(i <= i_end){
            res.add(mat[i++][j]);
        }
        i--;j--;
        while(j >= j_start){
            res.add(mat[i][j--]);
        }
        j++;i--;
        while(i > i_start){
            res.add(mat[i--][j]);
        }
        helper(mat, i_start+1, i_end-1, j_start+1, j_end-1);  //再去遍历内层的数字
    }
}
```

## 55.跳跃游戏

>给定一个非负整数数组，你最初位于数组的第一个位置。
>
>数组中的每个元素代表你在该位置可以跳跃的最大长度。
>
>判断你是否能够到达最后一个位置。
>
>示例 1:
>
>输入: [2,3,1,1,4]
>输出: true
>解释: 我们可以先跳 1 步，从位置 0 到达 位置 1, 然后再从位置 1 跳 3 步到达最后一个位置。
>示例 2:
>
>输入: [3,2,1,0,4]
>输出: false
>解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
>
>注意：这个问题的升级版本是**45：跳跃游戏II**

```java
class Solution {
    //思路1：从最后一个位置开始起，检查前面是否有可以跳到那里，是的话，就更新最后一个位置
    public boolean canJump(int[] nums) {
        if(nums == null || nums.length <= 1) {
            return true;
        }
        int lastPosition = nums.length - 1;
        for(int i = nums.length - 2;i >= 0;i--){
            if(nums[i] + i >= lastPosition){
                lastPosition = i;   //如果可以跳到的话，就更新lastPosition的值。
            }
        }
        return lastPosition == 0;
    }
    
    //思路2：从头开始，判断每次能够达到的最大位置，如果最大位置已经到达了最后一个地方，就返回true
    public boolean canJump(int[] nums) {
        if(nums == null || nums.length <= 1) {
            return true;
        }
        int canReachPostion = 0;  //当前能到的最远位置是0
        //因为当前只能最远达到canReachPostion，所以，也只能利用到nums[canReachPostion]，还不能利用后面的，除非循环中更新了canReachPostion的值。
        for(int i = 0;i <= canReachPostion;i++){
            canReachPostion = Math.max(canReachPostion, i+nums[i]);
            if(canReachPostion >= nums.length-1){
                return true;
            }
        }
        return false;
    }
}
```

## 56.合并区间

>给出一个区间的集合，请合并所有重叠的区间。
>
>示例 1:
>
>输入: intervals = [[1,3],[2,6],[8,10],[15,18]]
>输出: [[1,6],[8,10],[15,18]]
>解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
>
>示例 2:
>
>输入: intervals = [[1,4],[4,5]]
>输出: [[1,5]]
>解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
>
>注意：输入类型已于2019年4月15日更改。 请重置默认代码定义以获取新方法签名。
>
>提示：**intervals\[i][0] <= intervals\[i][1]**
>

```java
class Solution {
    //思路：首先需要按照开始时间也就是intervals[0]的值进行一个排序，然后从前往后进行排序
    public int[][] merge(int[][] intervals) {
        if(intervals == null || intervals.length == 0) {
            return new int[0][0];
        }
        List<Integer> res = new ArrayList<>();
        Arrays.sort(intervals, (o1, o2)->(o1[0]-o2[0]));  //注意这里写的时候，是o1, o2,不是o1-o2
        int i = 0;
        while(i < intervals.length){
            int start = intervals[i][0];
            int end = intervals[i][1];
            while(i+1 < intervals.length && end >= intervals[i+1][0]){//看看后面的区间是否可以合并
                end = Math.max(end, intervals[i+1][1]);  //进行合并
                i++;
            }
            res.add(start);
            res.add(end);
            i++;
        }
        int[][] result = new int[res.size()/2][2];
        for(i = 0;i < res.size()/2;i++){
            result[i][0] = res.get(2*i);
            result[i][1] = res.get(2*i+1);
        }
        return result;
    }
}
```



## 59.螺旋矩阵II

>给定一个正整数 n，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。
>
>示例: 输入: 3
>输出:
>[ [ 1, 2, 3 ],
>  [ 8, 9, 4 ],
>  [ 7, 6, 5 ]]

```java
class Solution {
    //思路：沿用螺旋矩阵I的方法就可以了
    public int[][] generateMatrix(int n) {
        if(n <= 0) return new int[0][0];
        int[][] mat = new int[n][n];
        helper(mat, 0, n-1, 0, n-1, 1);
        return mat;
    }
    
    //k是当前需要填充的那个数
    public void helper(int[][] mat,int i_start,int i_end,int j_start,int j_end,int k){
        if(i_start > i_end || j_start > j_end) return;
        if(i_start == i_end){
            // 此时只有一行了
            while(j_start <= j_end){
                mat[i_start][j_start++] = k++;
            }
            return;
        }
        if(j_start == j_end){
            // 此时只有一列了
            while(i_start <= i_end){
                mat[i_start++][j_start] = k++;
            }
            return;
        }
        int i = i_start, j = j_start;
        while(j <= j_end){
            mat[i][j++] = k++;
        }
        j--;i++;
        while(i <= i_end){
            mat[i++][j] = k++;
        }
        i--;j--;
        while(j >= j_start){
            mat[i][j--] = k++;
        }
        j++;i--;
        while(i > i_start){
            mat[i--][j] = k++;
        }
        helper(mat, i_start+1, i_end-1, j_start+1, j_end-1, k);
    }
}
```





## 75.颜色分类

>给定一个包含红色、白色和蓝色，一共 n 个元素的数组，**原地对它们进行排序**，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
>
>此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
>
>注意: 不能使用代码库中的排序函数来解决这道题。
>
>**示例:**
>
>```
>输入: [2,0,2,1,1,0]
>输出: [0,0,1,1,2,2]
>
>进阶：
>	一个直观的解决方案是使用计数排序的两趟扫描算法。
>	首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。
>	你能想出一个仅使用常数空间的一趟扫描算法吗？
>```

```java
class Solution {
    //思路1：先扫描一遍统计好0,1,2分别出现的次数，然后再扫描一遍，按照各自的次数重写这个数组
    public void sortColors1(int[] nums) {
        if(nums == null || nums.length <= 1) return;
        int count0 = 0, count1 = 0, count2 = 0;
        for(int num: nums){
            if(num == 0){
                count0 ++;
            }
            else if(num == 1){
                count1 ++;
            }
            else{
                count2 ++;
            }
        }
        //下面重写这个数组
        for(int i = 0;i < nums.length;i++){
            if(count0 != 0){
                nums[i] = 0;
                count0--;
            }
            else{
                if(count1 != 0){
                    nums[i] = 1;
                    count1--;
                }
                else{
                    nums[i] = 2;
                }
            }
        }
    }
    
    //思路2:设置双指针，分别指向开头的0和末尾的2，如果遍历中途遇到0，就换到前面；遇到2就换到后面
    public void sortColors2(int[] nums) {
        if(nums == null || nums.length <= 1) return;
        int p = 0, p0 = 0, p2 = nums.length-1;
        while(p <= p2){  //注意这里一定得是小于等于，如果只是小于的话，用例[2 0 1]过不了
            if(nums[p] == 0){
                if(p == p0){
                    p0 ++;
                    p ++;
                }
                else{
                    swap(nums, p, p0);  //换到前面去
                    p0++;
                }
            }
            else if(nums[p] == 2){
                swap(nums, p, p2);  //换到后面去
                p2--;
            }
            else{
                p++;
            }
        }
    }
    
    public void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j]= temp;
    }
}
```

## 88.合并两个有序数组

>给你两个有序整数数组*nums1*和*nums2*，请你将*nums2*合并到*nums1*中*，*使*nums1* 成为一个有序数组。
>
>说明:  初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
>你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
>
>l例如：输入:
>nums1 = [1,2,3,0,0,0], m = 3
>nums2 = [2,5,6],       n = 3
>
>输出: [1,2,2,3,5,6]
>

```java
class Solution {
    //思路：从末尾开始放，存放两个数组中相对大的那个元素
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if(nums1 == null || nums2 == null) return;
        int i = m+n-1, i1 = m-1, i2 = n-1;
        while(i1 >= 0 && i2 >= 0){
            if(nums1[i1] > nums2[i2]){
                nums1[i] = nums1[i1];
                i1--;
            }
            else{
                nums1[i] = nums2[i2];
                i2--;
            }
            i--;
        }
        while(i2 >= 0){
            //如果数组2中还有元素没有搬过来，就都搬过来
            nums1[i--] = nums2[i2--];
        }
    }
}
```

## 101.对称二叉树

>给定一个二叉树，检查它是否是镜像对称的。
>
>例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
>
>    	1
>       / \
>      2   2
>     / \ / \
>    3  4 4  3
>
>但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
>
>    	1
>       / \
>      2   2
>       \   \
>        3   3

```java
class Solution {
    public boolean isSymmetric(TreeNode root) {
        if((root == null) || (root.left == null && root.right == null)){
            return true;
        }
        return helper(root.left, root.right);  //使用辅助函数，做对称的比较。
    }
    
    public boolean helper(TreeNode node1, TreeNode node2){
        if(node1 == null && node2 == null) return true;
        if(node1 == null || node2 == null || node1.val != node2.val) return false;
        return helper(node1.left, node2.right) && helper(node1.right, node2.left);
    }
}
```

## 112.路径总和

>给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
>
>说明: 叶子节点是指没有子节点的节点。
>
>示例: 
>给定如下二叉树，以及目标和 sum = 22，
>
>              5
>             / \
>            4   8
>           /   / \
>          11  13  4
>         /  \      \
>        7    2      1
>返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
>

```java
class Solution {
    //思路：如果当前点是叶子节点了，就判断一次当前值是不是为0了。如果遇到了null，就返回false；
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null) return false;
        sum -= root.val;
        if(root.left == null && root.right == null){
            if(sum == 0){
                return true;
            }
            else{
                return false;
            }
        }
        return hasPathSum(root.left, sum) || hasPathSum(root.right, sum);
    }
}
```

## 113.路径总和II

>给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
>
>说明: 叶子节点是指没有子节点的节点。
>
>示例:
>给定如下二叉树，以及目标和 sum = 22，
>
>              5
>             / \
>            4   8
>           /   / \
>          11  13  4
>         /  \    / \
>        7    2  5   1
>返回: [ [5,4,11,2],  [5,8,4,5] ]
>

```java
class Solution {
    //思路：使用一个list来保存当前遍历过的节点值
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> cur = new ArrayList<>();
    
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if(root == null) {
            return res;
        }
        dfs(root, sum, 0);
        return res;
    }
    
    public void dfs(TreeNode root, int sum, int sum0){
        if(root == null) return ;
        cur.add(root.val);
        sum0 += root.val;
        if(root.left == null && root.right == null && sum == sum0){  //当且仅当现在是叶子节点，且相等的时候，才加到res
            List<Integer> list = new ArrayList<>();
            list.addAll(cur);
            res.add(list);
        }
        dfs(root.left, sum, sum0);
        dfs(root.right, sum, sum0);
        cur.remove(cur.size()-1);
    }
}
```



## 121.买卖股票的最佳时机

>给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
>
>如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
>
>注意：你不能在买入股票前卖出股票。
>
>示例 1:
>
>输入: [7,1,5,3,6,4]
>输出: 5
>解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
>注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
>示例 2:
>
>输入: [7,6,4,3,1]
>输出: 0
>解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。

```java
class Solution {
    //思路：每次都记录下当前能够得到的最小价格，以及最大利润。
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length <= 1) {
            return 0;
        }
        int min_price = Integer.MAX_VALUE, max_profit = 0;
        for(int price : prices){
            min_price = Math.min(min_price, price);  //得到最小价格
            max_profit = Math.max(max_profit, price - min_price);  //得到最大利润（以上两个值的前后顺序是无关的）
        }
        return max_profit;
    }
}
```

## 129.求根到叶子结点的数字之和

>给定一个二叉树，它的每个结点都存放一个 0-9 的数字，每条从根到叶子节点的路径都代表一个数字。
>
>例如，从根到叶子节点路径 1->2->3 代表数字 123。
>
>计算从根到叶子节点生成的所有数字之和。
>
>说明: 叶子节点是指没有子节点的节点。
>
>示例 1:
>
>输入: [1,2,3]
>    1
>   / \
>  2   3
>输出: 25
>解释: 从根到叶子节点路径 1->2 代表数字 12. 从根到叶子节点路径 1->3 代表数字 13. 因此，数字总和 = 12 + 13 = 25.
>示例 2:
>
>输入: [4,9,0,5,1]
>    4
>   / \
>  9   0
> / \
>5   1
>输出: 1026
>解释:
>从根到叶子节点路径 4->9->5 代表数字 495.
>从根到叶子节点路径 4->9->1 代表数字 491.
>从根到叶子节点路径 4->0 代表数字 40.
>因此，数字总和 = 495 + 491 + 40 = 1026.

```java
class Solution {
    
    int res = 0;  //存放数字之和
    
    public int sumNumbers(TreeNode root) {
        if(root == null) return res;
        dfs(root, 0);  //0代表当前路径得到的值还是0
        return res;
    }
    
    public void dfs(TreeNode root, int cur){
        if(root == null) return ;
        cur *= 10;
        cur += root.val;
        if(root.left == null && root.right == null){
            res += cur;  //如果当前节点是叶子节点的话，就需要增加一次res的值。
        }
        else{
            dfs(root.left, cur);
            dfs(root.right, cur);
        }
    }
}
```



## 136.只出现一次的数字

>给定一个非空整数数组，除了**某个元素只出现一次**以外，其余**每个元素均出现两次**。找出那个只出现了一次的元素。
>
>说明：你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
>
>示例 1:
>
>输入: [2,2,1]
>输出: 1
>示例 2:
>
>输入: [4,1,2,1,2]
>输出: 4

```java
class Solution {
    //思路1:因为两个相同的元素异或得到0，任意数和0异或不变，
    //所以全部的数做异或，得到的就是那个单独的数
    public int singleNumber(int[] nums) {
        if(nums == null || nums.length == 0) return -1;
        int res = nums[0];
        for(int i = 1;i < nums.length;i++){
            res ^= nums[i];
        }
        return res;
    }
    
    //思路2：针对数字中的32位中的每一位进行检测，如果发现这个位出现1的次数是奇数
    //那么就说明，单独的那个数的这个位为1
    public int singleNumber(int[] nums) {
        if(nums == null || nums.length == 0) return -1;
        int res = 0;
        for(int i = 0;i < 32;i++){
            int count = 0;   //计数器
            int mask = 1 << i;
            for(int num: nums){
                if((mask & num) != 0){
                    count ++;
                }
            }
            if(count % 2 == 1) {
                res |= mask;  //将当前位的1赋值到res中
            }
        }
        return res;
    }
}

```

## 137.只出现一次的数字II

>给定一个**非空**整数数组，除了某个元素只出现一次以外，其余每个元素均出现了三次。
>
>找出那个只出现了一次的元素。
>
>**说明：**
>
>你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
>
>**示例 1:**
>
>```
>输入: [2,2,3,2]
>输出: 3
>```
>
>**示例 2:**
>
>```
>输入: [0,1,0,1,0,1,99]
>输出: 99
>```

```java
class Solution {
    //采用136题的思路二：对32位中的每一位进行讨论。相比较而言，只是把2换成了3
    public int singleNumber(int[] nums) {
        int res = 0;
        for(int i = 0;i < 32;i++){
            int mask = 1 << i;
            int count = 0;
            for(int num: nums){
                if((mask & num) != 0){
                    count ++;
                }
            }
            if(count % 3 == 1){
                res |= mask;
            }
        }
        return res;
    }
}
```



## 141.环形链表

>给定一个链表，判断链表中是否有环。
>
>如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
>
>如果链表中存在环，则返回 true 。 否则，返回 false 。
>
>**进阶：**你能用 *O(1)*（即，常量）内存解决此问题吗？

```java
public class Solution {
    //思路：使用快慢指针，如果中途相遇了，就说明有环
    public boolean hasCycle(ListNode head) {
        ListNode fast = head, slow = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) return true;
        }
        return false;
    }
}
```

## 142.环形链表

>给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
>
>为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
>

```java
public class Solution {
    //思路：首先还是需要判断是否存在环。然后将快指针移动到head，慢速走，最终会在环那里相遇
    public ListNode detectCycle(ListNode head) {
        if(head == null || head.next == null) return null;
        ListNode fast = head, slow = head;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow) break;
        }
        if(fast == null || fast.next == null) return null;
        fast = head;
        while(fast != slow){  //也有可能在刚开始的时候，就是相等的。
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }
}
```



## 143.重排链表

>给定一个单链表 *L*：*L*0→*L*1→…→*L**n*-1→*L*n ，
>将其重新排列后变为： *L*0→*L**n*→*L*1→*L**n*-1→*L*2→*L**n*-2→…
>
>你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
>
>**示例 1:**
>
>```
>给定链表 1->2->3->4, 重新排列为 1->4->2->3.
>```
>
>**示例 2:**
>
>```
>给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
>```

```java
class Solution {
    public void reorderList(ListNode head) {
        if(head == null || head.next == null) return ;
        //1. 使用快慢指针，找到中间点，将链表分成两部分
        ListNode fast = head, slow = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        //2. 后半部分翻转
        ListNode p1 = head;
        ListNode p2 = reverse(slow.next);
        slow.next = null;
        //3. 合并两个链表
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        while(p1 != null && p2 != null){
            p.next = p1;
            p1 = p1.next;
            p = p.next;
            p.next = p2;
            p2 = p2.next;
            p = p.next;
        }
        if(p1 != null){
            p.next = p1;
        }
    }
    
    public ListNode reverse(ListNode head){
        if(head == null || head.next == null) return head;
        ListNode p = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }
}
```

## 144.二叉树的前序遍历

>给定一个二叉树，返回它的**前序**遍历。
>
>示例: 输入: [1,null,2,3] 
>   1
>    \
>     2
>    /
>   3 
>
>输出: [1,2,3]
>进阶: 递归算法很简单，你可以通过迭代算法完成吗？

```java
//思路1：使用递归的方式做
class Solution {
    
    List<Integer> res = new ArrayList<>();
    
    public List<Integer> preorderTraversal(TreeNode root) {
        if(root == null) {
            return res;
        }
        preOrder(root);
        return res;
    }
    
    public void preOrder(TreeNode root){
        if(root == null) return;
        res.add(root.val);
        preOrder(root.left);
        preOrder(root.right);
    }
}

//思路2：使用栈辅助，采用迭代的方法做。先弹出根节点，并读取val，然后压入右节点，再压入左节点。
/*
   1
  /  \
 2    3
*/
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            res.add(node.val);
            if(node.right != null){
                stack.push(node.right);
            }
            if(node.left != null){
                stack.push(node.left);
            }
        }
        return res;
    }
}
```



## 148.排序链表

>在 *O*(*n*log*n*) 时间复杂度和常数级空间复杂度下，对链表进行排序。
>
>```
>示例： 输入: 4->2->1->3   输出: 1->2->3->4
>```

```java
class Solution {
    //思路：归并排序的时间复杂度就是nlogn，所以这里也可以实现一个归并排序来做。
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null) return head;
        //使用快慢指针找到链表的中间节点，将链表一分为二
        ListNode slow = head, fast = head, pre = head;
        while(fast != null && fast.next != null){
            pre = slow;   //用来记录slow的前一个节点，方便将链表的前一半截断
            slow = slow.next;
            fast = fast.next.next;
        }
        pre.next = null;  //截断两段的联系
        ListNode p1 = head, p2 = slow;   //现在就是这两个小链表了
        p1 = sortList(p1);  //两个链表分别排序
        p2 = sortList(p2);   
        ListNode p = merge(p1, p2);  //合并两个升序链表，可以直接用题21
        return p;
    }
    
    public ListNode merge(ListNode l1, ListNode l2) {
		if(l1 == null) return l2;
		if(l2 == null) return l1;
		ListNode dummy = null, p1 = l1, p2 = l2;
		if(p1.val < p2.val){
			dummy = p1;
			p1 = p1.next;
		}
		else{
			dummy = p2;
			p2 = p2.next;
		}
		ListNode p = dummy;
		while(p1 != null && p2 != null){
			if(p1.val < p2.val){
				p.next = p1;
				p1 = p1.next;
			}
			else{
				p.next = p2;
				p2 = p2.next;
			}
			p = p.next;
		}
		if(p1 == null){
			p.next = p2;
		}
		if(p2 == null){
			p.next = p1;
		}
		return dummy;
    }
}
```



## 151.翻转字符串里的单词

>给定一个字符串，逐个翻转字符串中的每个单词。
>
>说明：
>
>无空格字符构成一个 单词 。
>输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
>如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
>
>```
>示例1：输入："the sky is blue":   输出："blue is sky the"
>
>示例2：输入："  hello world!  "   输出："world! hello"
>解释：输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
>
>示例3： 输入："a good   example"  输出："example good a"
>解释：如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
>```

```java
class Solution {
    //思路：遍历字符串，找到每一个完整单词，然后使用Insert函数
    public String reverseWords(String s) {
        if(s == null || s.length() <= 1) return s;
		StringBuilder res = new StringBuilder();
		s = s.trim();
		int i = 0;
		StringBuilder word = new StringBuilder();
		boolean flag = false;  //代表当前word是否是实际的单词
		while(i < s.length()){
			char c = s.charAt(i);
			if(c == ' '){
				if(flag){
					res.insert(0," " + word);
					word.delete(0, word.length());
					flag = false;
				}
			}
			else{
				word.append(c);
				flag = true;
			}
			i++;
		}
		if(flag){
			res.insert(0," " + word);
		}
		return res.toString().trim();
    }
}
```

## 155.最小栈

>设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
>
>push(x) —— 将元素 x 推入栈中。
>pop() —— 删除栈顶的元素。
>top() —— 获取栈顶元素。
>getMin() —— 检索栈中的最小元素。
>
>
>示例:
>
>输入：
>["MinStack","push","push","push","getMin","pop","top","getMin"]
>[[],[-2],[0],[-3],[],[],[],[]]
>
>输出：
>[null,null,null,null,-3,null,0,-2]
>
>解释：
>MinStack minStack = new MinStack();
>minStack.push(-2);
>minStack.push(0);
>minStack.push(-3);
>minStack.getMin();   --> 返回 -3.
>minStack.pop();
>minStack.top();      --> 返回 0.
>minStack.getMin();   --> 返回 -2.

```java
class MinStack {

    Stack<Integer> stack = new Stack<>();
    int min = Integer.MAX_VALUE;
    
    /** initialize your data structure here. */
    public MinStack() {
        
    }
    
    //如果当前值小于等于min，需要先将min压栈，然后压入x，并更新min的值
    public void push(int x) {
        if(x <= min){
            stack.push(min);
            min = x;
        }
        stack.push(x);
    }
    
    //如果当前出栈是min，就需要再出栈一个元素，作为新的min
    public void pop() {
        if(stack.pop() == min){
            min = stack.pop();
        }
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return min;
    }
}
```



## 186.翻转字符串里的单词II

>给定一个字符串，逐个翻转字符串中的每个单词。
>
>示例：
>
>输入: ["t","h","e"," ","s","k","y"," ","i","s"," ","b","l","u","e"]
>输出: ["b","l","u","e"," ","i","s"," ","s","k","y"," ","t","h","e"]
>
>注意：
>
>  单词的定义是不包含空格的一系列字符
>  输入字符串中不会包含前置或尾随的空格
>  单词与单词之间永远是以单个空格隔开的

```java
class Solution{
    //思路：先将每个单词进行翻转，然后再将整个字符数组进行翻转
	public void reverseWords(char[] s){
		if(s == null || s.length <= 1) return;
		int start = 0, end = 0;
		while(end < s.length){
			if(s[end] == ' '){
				reverse(s, start, end-1);
				start = end+1;
			}
			end++;
		}
		if(start > 0)
			reverse(s, start, s.length-1);  //这里需要加一个判断，避免只有一个单词的字符数组被翻转两次
		reverse(s, 0, s.length-1);
	}
	
	//翻转字符数组，i是开始位置，k是结束位置
	private void reverse(char[] chars, int l, int r) {
		while(l < r){
			char temp = chars[l];
			chars[l] = chars[r];
			chars[r] = temp;
			l++;r--;
		}
	}
}
```





## 259.较小的三数之和

>给定一个长度为 n 的整数数组和一个目标值 target，寻找能够使条件 nums[i] + nums[j] + nums[k] < target 成立的三元组 i, j, k 个数（0 <= i < j < k < n）。
>
>输入: nums = [-2,0,1,3], target = 2 
>
>输出: 2  
>
>解释: 因为一共有**两个**三元组满足累加和小于 2:  [-2,0,1]  [-2,0,3]

```java
class Solution {
    //思路：依旧是三数之和的旧思路，只不过在确定组合数量的时候，需要考虑好组合数量的真实情况。
    public static int threeSumSmaller(int[] nums, int target){
        if(nums == null || nums.length <= 2) return 0;
        int res = 0;
        Arrays.sort(nums);
        for(int i = 0;i < nums.length-2 && nums[i] < target;i++){
            int left = i+1, right = nums.length-1;
            while(left < right){
                int sum = nums[i] + nums[left] + nums[right];
                if(sum < target){
                    res += right - left;  //这里不能单纯地+1,因为一旦right--了，实际上会减少right-left个可用的组合
                    right--;
                }
                else{
                    left++;
                }
            }
        }
        return res;
    }
}
```



## 206.反转链表

>反转一个单链表。
>
>示例:
>
>输入: 1->2->3->4->5->NULL
>输出: 5->4->3->2->1->NULL
>进阶:
>你可以迭代或递归地反转链表。你能否用两种方法解决这道题？

```java
class Solution {
    //思路1：使用虚拟节点+头插法来做
    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(-1);   //虚拟头结点
        ListNode p = head;
        while(p != null){
            ListNode temp = dummy.next;  //先保存好首个有效节点
            dummy.next = p;
            p = p.next;  //指针后移
            dummy.next.next = temp;  //将之前的首个有效节点，连接上去。
        }
        return dummy.next;
    }
    
    //思路2：使用递归的方法来做（画个图来做更好）
    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        ListNode p = head.next;  //当前链表的第二个节点，将是未来的倒数第二个节点
        ListNode newHead = reverseList(head.next);  //新的头结点
        p.next = head;
        head.next = null;
        return newHead;
    }
    
    //思路2换个写法
    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode newHead = reverseList(head.next);  //后面的部分翻转
        head.next.next = head;  //head.next已经成为了最后一个节点，现在链接上head，将head变成最后一个节点
        head.next = null;  //最后一个节点的next置为null
        return newHead;
    }
    
    //思路3：借助先进后出的栈。(该思路性能比较差，没有前面的两种那么巧妙)
    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        Stack<ListNode> stack = new Stack<>();
        ListNode p = head;
        while(p != null){
            stack.push(p);
            p = p.next;
        }
        ListNode newHead = stack.pop();
        p = newHead;  //指向新的头结点
        while(!stack.isEmpty()){
            p.next = stack.pop();
            p = p.next;
        }
        p.next = null;  //最后一个节点next置为null
        return newHead;
    }
}
```



## 225.用队列实现栈

>使用队列实现栈的下列操作：
>
>push(x) -- 元素 x 入栈
>pop() -- 移除栈顶元素
>top() -- 获取栈顶元素
>empty() -- 返回栈是否为空
>注意:
>
>你只能使用队列的基本操作-- 也就是 push to back, peek/pop from front, size, 和 is empty 这些操作是合法的。
>你所使用的语言也许不支持队列。 你可以使用 list 或者 deque（双端队列）来模拟一个队列 , 只要是标准的队列操作即可。
>你可以假设所有操作都是有效的（例如, 对一个空的栈不会调用 pop 或者 top 操作）。

```java
class MyStack {

    //思路和用队列实现栈是差不多的，这里用两个队列。在pop的时候，将队列中的元素都按照原来的顺序放到另一个队列中，然后将最后一个元素poll出去，从而实现将最早到来的元素先pop出去。
    Queue<Integer> queue1 = new LinkedList<>();
    Queue<Integer> queue2 = new LinkedList<>();
    
    /** Initialize your data structure here. */
    public MyStack() {
        
    }
    
    /** Push element x onto stack. */
    public void push(int x) {
        queue1.add(x);
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        if(queue1.size() == 0){
            return -1;
        }
        while(queue1.size() != 1){
            queue2.add(queue1.poll());
        }
        int value = queue1.poll();
        while(queue2.size() != 0){
            queue1.add(queue2.poll());
        }
        return value;
    }
    
    /** Get the top element. */
    public int top() {
       if(queue1.size() == 0){
            return -1;
        }
        int value = pop();
        queue1.add(value);
        return value;
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return (queue1.size() == 0);
    }
}
```



## 232.用栈实现队列

>使用栈实现队列的下列操作：
>
>- push(x) -- 将一个元素放入队列的尾部。
>- pop() -- 从队列首部移除元素。
>- peek() -- 返回队列首部的元素。
>- empty() -- 返回队列是否为空。
>
>**示例:**
>
>```
>MyQueue queue = new MyQueue();
>
>queue.push(1);
>queue.push(2);  
>queue.peek();  // 返回 1
>queue.pop();   // 返回 1
>queue.empty(); // 返回 false
>```

```java
class MyQueue {

    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();
    
    //思路：使用两个栈，来做顺序的交换
    /** Initialize your data structure here. */
    public MyQueue() {

    }
    
    /** Push element x to the back of queue. */
    public void push(int x) {
        //先将栈1的元素都压到栈2中
        while(!stack1.isEmpty()){
            stack2.push(stack1.pop());
        }
        stack2.push(x);
        while(!stack2.isEmpty()){
            stack1.push(stack2.pop());
        }
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        return stack1.pop();
    }
    
    /** Get the front element. */
    public int peek() {
        return stack1.peek();
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return stack1.isEmpty();
    }
}
```

## 233.数字1的个数【待补充】

>给定一个整数 n，计算所有小于等于 n 的非负整数中数字 1 出现的个数。
>
>**示例:**
>
>```
>输入: 13
>输出: 6 
>解释: 数字 1 出现在以下数字中: 1, 10, 11, 12, 13 。
>```

```java
/*
《编程之美》上这样说:
    设N = abcde ,其中abcde分别为十进制中各位上的数字。
    如果要计算百位上1出现的次数，它要受到3方面的影响：百位上的数字，百位以下（低位）的数字，百位以上（高位）的数字。
    如果百位上数字为0，百位上可能出现1的次数由更高位决定。比如：12013，则可以知道百位出现1的情况可能是：100~199，1100~1199,2100~2199，，...，11100~11199，一共1200个。可以看出是由更高位数字（12）决定，并且等于更高位数字（12）乘以 当前位数（100）。注意：高位数字不包括当前位
    如果百位上数字为1，百位上可能出现1的次数不仅受更高位影响还受低位影响。比如：12113，则可以知道百位受高位影响出现的情况是：100~199，1100~1199,2100~2199，，....，11100~11199，一共1200个。和上面情况一样，并且等于更高位数字（12）乘以 当前位数（100）。但同时它还受低位影响，百位出现1的情况是：12100~12113,一共14个，等于低位数字（13）+1。 注意：低位数字不包括当前数字
    如果百位上数字大于1（2~9），则百位上出现1的情况仅由更高位决定，比如12213，则百位出现1的情况是：100~199,1100~1199，2100~2199，...，11100~11199,12100~12199,一共有1300个，并且等于更高位数字+1（12+1）乘以当前位数（100）
*/
class Solution {
    public int countDigitOne(int n) {
        if (n < 1)
            return 0;
        int len = getLenOfNum(n);
        if (len == 1)
            return 1;
        int tmp = (int) Math.pow(10, len - 1);
        int first = n / tmp; // 获取n的最高位数字
        int firstOneNum = first == 1 ? n % tmp + 1 : tmp; // 获取n的最高位为1时有多少个数字
        int otherOneNUm = first * (len - 1) * (tmp / 10); // 在介于n % tmp到n之间的数字中，除了最高位为1，其余各个数字分别为1 的总数和
        return firstOneNum + otherOneNUm + countDigitOne(n % tmp);
    }
    private int getLenOfNum(int n) {
        int len = 0;
        while (n != 0) {
            len++;
            n /= 10;
        }
        return len;
    }
}
```



## 234.回文链表

>请判断一个链表是否为回文链表。
>
>示例 1:
>
>输入: 1->2
>输出: false
>示例 2:
>
>输入: 1->2->3->2->1
>输出: true
>进阶：
>你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？

```java
class Solution {
    //思路：这个题目算是很综合的一个题目，需要分下面的三个步骤来完成
    //1.使用快慢指针，找到中心点。注意：如果整个链表的个数是奇数的话，后半部分链表中是需要将第一个节点舍弃的，比如示例2.
    //2.将后半部分链表进行翻转；
    //3.比较两个部分的链表是不是完全一样的。
    public boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null){
            return true;
        }
        //使用快慢指针找到中间节点
        ListNode mid = findMid(head);
        //然后对后半部分的链表进行翻转
        ListNode p1 = head, p2 = reverse(mid);
        //最后比较p1和p2链表中的节点值是不是完全相同的
        while(p1 != null && p2 != null){
            if(p1.val != p2.val){
                return false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        return true;
    }
    
    //这里确保了head的节点个数至少是2.
    public ListNode findMid(ListNode head){
        ListNode fast = head, slow = head;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        if(fast == null){
            return slow;  //如果fast变为null，代表整个链表中的节点个数是偶数的。
        }
        else{
            return slow.next;  //此时链表中的节点个数是奇数的。后半部分的链表中的第一个节点需要舍弃。
        }
    }
    
    //使用递归进行链表翻转
    public ListNode reverse(ListNode head){
        if(head == null || head.next == null){
            return head;
        }
        ListNode newHead = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}
```



## 250.统计同值子树

>做当前这个题目之前，先做一个前序题，找子树中，是否存在两个子树，它们的所有节点的和是否是相同的。
>
>比如下面这个二叉树：
>
>```77
>             1
>        /    \
>     2      99
>   / \    /  \
>100  5  0    1
>```
>
>这种情况下，有两个子树，其内部节点的和为100，就返回true。

```java
class Solution {
    
    List<Integer> res = new ArrayList<>();
    
    public boolean isSymmetric(TreeNode root){
        if((root == null) || (root.left == null && root.right == null)){
            return false;  //节点个数小于等于1，返回false
        }
        dfs(root);   //遍历整个树，求出所有存在的子树和
        //下面判断res中是否存在相同的两个数
        //System.out.println(res);   //[100, 5, 107, 0, 1, 100, 208]
        Set<Integer> set = new HashSet<>();
        for(int num: res){
            if(set.contains(num)){
                return true;  //有相同的子树和
            }
            else{
                set.add(num);
            }
        }
        return false;
    }
    
    public int dfs(TreeNode root){
        if(root == null) return 0;
        int val = root.val;  //子树的根节点的值
        if(root.left != null || root.right != null){
            //此时这个根节点不是原子树的叶子节点
            val += dfs(root.left) + dfs(root.right);
        }
        res.add(val);  //添加这个子树的值
        return val;  //返回这个值
    }
}
```

>Given a binary tree, count the number of uni-value subtrees.
>
>A Uni-value subtree means all nodes of the subtree have the same value.
>
>```
>Example : Input:  root = [5,1,5,5,5,null,5]
>
>              5
>             / \
>            1   5
>           / \   \
>          5   5   5
>
>Output: 4
>```
>
>说明：这个题目是说统计这棵树里面，有多少个子树，它们中的任何节点的节点值都是相同的。
>
>在例子中，有三个叶子节点值为5，这三个叶子节点又分别是一颗子树，所以这里就有3棵树了；再加上最右边的那个（包含了2个节点值为5的子树）子树，就一共有4个子树，它们所有的节点的节点值都是5。因此返回了4。

```java
class Solution{
    
    //思路：要想子树中的值都是一样的。那么肯定是先判断叶子节点的值是不是相同的，然后再找叶子节点的父节点的值是不是也一样，从而去形成新的有效的子树。
    int res = 0;
    
    public int countUnivalSubtrees(TreeNode root){
        isSameValue(root);
        return res;
    }
    
    //当且仅当子树中的值都是一样的时候，返回true。当为空的时候，也返回true。
    public boolean isSameValue(TreeNode node){
        if(node == null) return true;
        boolean left  = isSameValue(node.left);
        boolean right = isSameValue(node.right);
        if(left && right){
            if((node.left != null&&node.val != node.left.val)||(node.right != null&&node.val != node.right.val)){
                return false;
            }
            res++;  //上面没有返回false，才可以进到这里来。
            return true;
        }
        return false;
    }
}
```

## 257.二叉树的所有路径

>给定一个二叉树，返回所有从根节点到叶子节点的路径。
>
>说明: 叶子节点是指没有子节点的节点。
>
>示例:
>
>输入:
>
>   1
> /   \
>2     3
> \
>  5
>
>输出: ["1->2->5", "1->3"]
>
>解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
>

```java
class Solution {
    
    List<String> res = new ArrayList<>();
    
    public List<String> binaryTreePaths(TreeNode root) {
        if(root == null) {
            return res;
        }
        dfs(root, "");  //当前没有遍历到任何节点，字符串为空
        return res;
    }
    
    //能进这个函数的都是非null的节点
    public void dfs(TreeNode root, String cur){
        cur += String.valueOf(root.val);
        if(root.left == null && root.right == null){
            res.add(cur);
        }
        else{
            cur += "->";
            if(root.left != null) dfs(root.left, cur);
            if(root.right != null) dfs(root.right, cur);
        }
    }
}
```



## 260.只出现一次的数字III

>给定一个整数数组 nums，其中**恰好有两个元素只出现一次**，**其余所有元素均出现两次**。 
>
>找出只出现一次的那两个元素。
>
>示例 :
>
>输入: [1,2,1,3,2,5]
>输出: [3,5]

```java
class Solution {
    //思路：首先找到所有数字的异或，因为成对的数字异或后得到0，而最后那两个单身狗一定不相同，所以得到的数a不为0
    //然后找到a中为1的最高位。通过这个位将所有数一分为二半。
    //再对两半数，分配进行全部异或，得到的两个数就是哪两个单身狗了。
    public int[] singleNumber(int[] nums) {
        if(nums == null || nums.length < 2) return new int[0];
        if(nums.length == 2) return nums;
        int[] res = new int[2];
        int a = 0;
        for(int num: nums){
            a ^= num;
        }
        int low = 1;
        while((a & low) == 0){
            low = low << 1;  //这里找为1的最低位，相比较找最高位速度更加快。不能去找最低位为0的
        }
        for(int num: nums){
            if((low & num) != 0){
                res[0] ^= num;
            }
            else{
                res[1] ^= num;
            }
        }
        return res;
    }
}
```





## 266.回文排列（字符串）

>给定一个字符串，判断该字符串中是否可以通过重新排列组合，形成一个回文字符串。
>
>**示例 1：**输入: "code"    输出: false
>
>**示例 2：**输入: "aab"     输出: true
>
>**示例 3：**输入: "carerac" 输出: true

```java
class Solution{
    //检查整个字符串，要求最多有一个字符出现的次数是奇数次
	public static boolean canPermutePalindrome(String s){
		if(s == null || s.length() <= 1)
			return true;
		HashMap<Character, Integer> map = new HashMap<>();
		for(int i = 0;i < s.length();i++){
			char c = s.charAt(i);
			if(map.containsKey(c)){
				map.put(c, map.get(c)+1);
			}
			else {
				map.put(c, 1);
			}
		}
		int count = 0;
		for(char c: map.keySet()){
			if(map.get(c) % 2 == 1){
				count++;
			}
		}
		return count <= 1;
	}
}
```

## 268.缺失数字

>给定一个包含 0, 1, 2, ..., n 中*n* 个数的序列，找出 0 .. *n* 中没有出现在序列中的那个数。
>
>**示例 1:**
>
>```
>输入: [3,0,1]
>输出: 2
>```
>
>**示例 2:**
>
>```
>输入: [9,6,4,2,3,5,7,0,1]
>输出: 8
>```

```java
class Solution {
    //思路1：先使用一个set存放下所有的数字，然后再检测0~n哪个数字不在其中
    public int missingNumber(int[] nums) {
        if(nums == null || nums.length == 0) return 1;
        int n = nums.length;
        Set<Integer> set = new HashSet<>();
        for(int num: nums){
            set.add(num);
        }
        for(int i = 0;i <= n;i++){
            if(!set.contains(i)){
                return i;
            }
        }
        return 0;
    }
    
    //思路2：先排个序吧
    
    
}
```



## 280.摆动排序

>给你一个无序的数组nums,将该数字**原地**重排后使得**nums[0]<=nums[1]>=nums[2]<= nums[3]...**。
>
>示例: 输入: nums = [3,5,2,1,6,4]  输出: 一个可能的解答是 [3,5,1,6,2,4]

```java
class Solution {
    //先排序，排好后就是[1, 2, 3, 4, 5, 6]，这样就只需要将2和3换位置，4和5换位置...
	public void wiggleSort1(int[] nums){
		if(nums == null || nums.length <= 1) return;
		Arrays.sort(nums);  
		for(int i = 1;i < nums.length-1;i += 2){
			swap(nums, i, i+1);
		}
	}
	
    //思路：分奇数位置上的数和偶数位置上的数来讨论，位置从0开始
	//偶数位置上的数，不能比两边的数大；
	//奇数位置上的数，不能比两边的数小；
    //出现上述两种不允许的情况时，就进行交换。
	public void wiggleSort2(int[] nums){
		if(nums == null || nums.length <= 1) return;
		for (int i = 0; i < nums.length-1; i++) {
			if(i % 2 == 0 && nums[i] > nums[i+1]){
				swap(nums, i, i+1);
			}
			else if(i % 2 == 1 && nums[i] < nums[i+1]){
				swap(nums, i, i+1);
			}
		}
	}
    
   	public void swap(int[] nums, int i, int i1) {
		int temp = nums[i];
		nums[i] = nums[i1];
		nums[i1] = temp;
	}
}
//注意：这里的解法都只能解决摆动排序I，允许相等的情况，如果是324题摆动排序II，就能行得通了。
//例子：[1,2,2,1,2,1,1,1,1,2,2,2]
```

## 324.摆动排序II

>给定一个无序的数组**nums**，将它重新排列成 **nums[0]<nums[1]>nums[2]<nums[3]...**的顺序。
>
>```
>相对于摆动排序I，去除了相等符号。
>例如： 输入: nums = [1, 5, 1, 1, 6, 4]  输出: 一个可能的答案是 [1, 4, 1, 5, 1, 6]
>
>说明: 你可以假设所有输入都会得到有效的结果。
>进阶:你能用 O(n) 时间复杂度和 / 或原地 O(1) 额外空间来实现吗？
>```

```java
class Solution {
    //思路：先排好序，然后从前一半的末尾拿一个元素，再从后一半的末尾拿一个元素...
	public void wiggleSort(int[] nums){
		if(nums == null || nums.length <= 1) return;
		int[] temp = new int[nums.length];
		System.arraycopy(nums, 0, temp, 0, nums.length);
		Arrays.sort(temp);
		int mid = (nums.length+1)/2-1, r = nums.length-1;
		for(int i = 0;i < nums.length;i++){
			nums[i] = ((i & 1) == 1) ? temp[r--] : temp[mid--];
		}
	}
}
```



## 344.反转字符串

>编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
>
>不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
>
>示例 1：
>
>输入：["h","e","l","l","o"]
>输出：["o","l","l","e","h"]
>示例 2：
>
>输入：["H","a","n","n","a","h"]
>输出：["h","a","n","n","a","H"]

```java
class Solution {
    //思路：将一半的字符交换位置就可以了
    public void reverseString(char[] s) {
        if(s == null || s.length <= 1) return;
        for(int i = 0;i < s.length/2;i++){
            char temp = s[i];
            s[i] = s[s.length-i-1];
            s[s.length-i-1] = temp;
        }
    }
}
```

## 345.反转字符串中的元音字母

>编写一个函数，以字符串作为输入，反转该字符串中的元音字母。
>
>示例 1： 输入："hello"     输出："holle"
>示例 2： 输入："leetcode"  输出："leotcede"

```java
class Solution {
    //思路：前后两个指针，遇元音字母的时候，就开始进行交换。交换完，再各自去寻找下一个元音字母。
    public String reverseVowels(String s) {
        if(s == null || s.length() <= 1) return s;
        char[] chars = new char[s.length()];
        for(int i = 0;i < s.length();i++){
            chars[i] = s.charAt(i);   //转换为字符数组
        }
        int l = 0, r = chars.length-1;
        while(l < r){
            while(l < r && (!isVowel(s.charAt(l)))){
                l++;
            }
            while(l < r && (!isVowel(s.charAt(r)))){
                r--;
            }
            if(l < r){
                char temp = chars[l];
                chars[l] = chars[r];
                chars[r] = temp;
                l++;r--;  //将两个指针一起往中间移动
            }
        }
        StringBuilder res = new StringBuilder();
        for(char c: chars){
            res.append(c);
        }
        return res.toString();
    }
    
    //判断是不是元音字母：a e i o u 和这些字母的大写字母。
    public boolean isVowel(char c){
        return (c == 'a') || (c == 'e') || (c == 'i') || (c == 'o') || (c == 'u')
            || (c == 'A') || (c == 'E') || (c == 'I') || (c == 'O') || (c == 'U');
    }
}
```

## 349.两个数组的交集

>给定两个数组，编写一个函数来计算它们的交集。
>
>示例 1：
>
>输入：nums1 = [1,2,2,1], nums2 = [2,2]
>输出：[2]
>示例 2：
>
>输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
>输出：[9,4]
>
>**说明：**输出结果中的每个元素一定是唯一的。我们可以不考虑输出结果的顺序。

```java
class Solution {
    //思路：使用set去重+记录。
    public int[] intersection(int[] nums1, int[] nums2) {
        List<Integer> list = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for(int num: nums1){
            set.add(num);
        }
        for(int num: nums2){
            if(set.contains(num)){
                list.add(num);
                set.remove(num);
            }
        }
        int[] res = new int[list.size()];
        int index = 0;
        for(int num: list){
            res[index++] = num;
        }
        return res;
    }
}
```

## 350.两个数组的交集II

>给定两个数组，编写一个函数来计算它们的交集。
>
>示例 1：
>
>输入：nums1 = [1,2,2,1], nums2 = [2,2]
>输出：[2,2]
>示例 2:
>
>输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
>输出：[4,9]
>
>说明：输出结果中每个元素出现的次数，应与元素在两个数组中出现次数的最小值一致。
>      我们可以不考虑输出结果的顺序。
>进阶：如果给定的数组已经排好序呢？你将如何优化你的算法？
>	  如果 nums1 的大小比 nums2 小很多，哪种方法更优？
>	  如果 nums2 的元素存储在磁盘上，内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？

```java
class Solution {
    //思路1：先排序，然后使用两个指针从两个数组的开头到尾进行遍历。
    public int[] intersect(int[] nums1, int[] nums2) {
        if(nums1 == null || nums1.length == 0) return new int[0];
        if(nums2 == null || nums2.length == 0) return new int[0];
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0, j = 0;
        List<Integer> list = new ArrayList<>();
        while(i < nums1.length && j < nums2.length){
            if(nums1[i] == nums2[j]){
                list.add(nums1[i]);
                i++;j++;
            }
            else if(nums1[i] < nums2[j]){
                i++;
            }
            else{
                j++;
            }
        }
        int[] res = new int[list.size()];
        int index = 0;
        for(int num: list){
            res[index++] = num;
        }
        return res;
    }
    
    //思路2：用hashmap存放下前一个数组中的数字及其出现的次数
    public int[] intersect(int[] nums1, int[] nums2) {
        if(nums1 == null || nums1.length == 0) return new int[0];
        if(nums2 == null || nums2.length == 0) return new int[0];
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int num: nums1){
            if(!map.containsKey(num)){
                map.put(num, 1);
            }
            else{
                map.put(num, map.get(num) + 1);
            }
        }
        List<Integer> list = new ArrayList<>();        
        for(int num: nums2){
            if(map.containsKey(num) && map.get(num) > 0){
                list.add(num);
                map.put(num, map.get(num)-1);
            }
        }
        int[] res = new int[list.size()];
        int index = 0;
        for(int num: list){
            res[index++] = num;
        }
        return res;
    }
}
```

## 360.有序转换数组

>给你一个已经**排好序**的整数数组**nums**和整数**a、b、c**。对于数组中的每一个数 x，计算函数值 f(x) = ax2 + bx + c，请将函数值产生的数组返回。
>
>要注意，返回的这个数组必须按照**升序排列**，并且我们所期望的解法时间复杂度为 O(n)。
>
>示例 1：
>
>输入: nums = [-4,-2,2,4], a = 1, b = 3, c = 5
>输出: [3,9,15,33]
>示例 2：
>
>输入: nums = [-4,-2,2,4], a = -1, b = 3, c = 5
>输出: [-23,-5,1,7]
>
>思路：
>
>先判断是否为二次函数。
>
>如果是，再判断开口的上下，根据和对称轴点的距离来判断函数值的大小，走双指针的逻辑。

```java
class Solution {
//对于一个函数：f(x) = ax2 + bx + c，但函数中的a和b和c的值确定，求x的值是数组中的每一个的时候，得到的f(x)的值
	//需要保证得到的结果是升序排列的。（注意：时间复杂是O(n)，所以不可以先求出所有的值，在进行排序。）
	public static int[] sortTransformedArray(int[] nums, int a, int b, int c) {
		if(nums == null || nums.length == 0) {
			return new int[0];
		}
		int[] res = new int[nums.length];
		int index = 0;
		//首先，看a是不是为0，如果是0的话，就当成一个一次函数来做
		if(a == 0){
			//一次函数的话，就看b是否为大于0的，如果大于0，就是递增函数；
			if(b >= 0){
				for(int i = 0; i < nums.length; i++) {
					res[index++] = b * nums[i] + c;
				}
				return res;
			}
			else{
				for(int i = nums.length-1;i >= 0;i--){
					res[index++] = b * nums[i] + c;
				}
				return res;
			}
		}
		else{
			//首先找到中心轴的位置，然后找到nums数组中，离中心轴最近的那个数的下标
			double mid = (-b) / (2.0 * a);
			int mid_i = findMin(nums, mid);
			int l = mid_i, r = mid_i+1, left, right;  //双指针
			//最后，根据二次函数的开口方向，来绝对这个mid_i代表的是最大值，还是最小值
			if(a > 0){
				//代表的是最小值
				while(l >= 0 || r < nums.length){
					left = (l >= 0) ? a*nums[l]*nums[l]+b*nums[l]+c : Integer.MAX_VALUE;
					right = (r < nums.length) ? a*nums[r]*nums[r]+b*nums[r]+c : Integer.MAX_VALUE;
					if(left < right){
						res[index++] = left;
						l--;
					}
					else{
						res[index++] = right;
						r++;
					}
				}
			}
			else{
				//代表的是最大值
				index = nums.length-1;
				while(l >= 0 || r < nums.length){
					left = (l >= 0) ? a*nums[l]*nums[l]+b*nums[l]+c : Integer.MIN_VALUE;
					right = (r < nums.length) ? a*nums[r]*nums[r]+b*nums[r]+c : Integer.MIN_VALUE;
					if(left > right){
						res[index--] = left;
						l--;
					}
					else{
						res[index--] = right;
						r++;
					}
				}
			}
		}
		return res;
	}
	
	private static int findMin(int[] nums, double mid) {
		int res = 0;
		double min = Double.MAX_VALUE;
		for(int i = 0;i < nums.length;i++){
			if(Math.abs(nums[i] - mid) < min){
				min = Math.abs(nums[i] - mid);
				res = i;
			}
		}
		return res;
	}
}
```





## 454.四数之和II

>给定四个包含整数的数组列表 A , B , C , D ,计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[l] = 0。
>
>例如: 输入:
>	A = [ 1, 2]
>	B = [-2,-1]
>	C = [-1, 2]
>	D = [ 0, 2]
>
>输出: 2
>
>解释: 两个元组如下:
>
>```
>1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
>2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
>```

```java
class Solution {
    //思路：使用一个map存放下A数组和B数组中的所有对的和，然后，在对C数组和D数组的所有对求和后，找map中有没有相反数存在
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        if(A == null || A.length == 0 || B == null || B.length == 0
          || C == null || C.length == 0 || D == null || D.length == 0) return 0;
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for(int a: A){
            for(int b: B){
                if(!map.containsKey(a+b)){
                    map.put(a+b, 1);
                }
                else{
                    map.put(a+b, map.get(a+b)+1);
                }
            }
        }
        for(int c: C){
            for(int d: D){
                if(map.containsKey(0-c-d)){
                    res += map.get(0-c-d);
                }
            }
        }
        return res;
    }
}
```





## 530.二叉搜索树的最小绝对差

>
>
>

```java
class Solution {
    
    List<Integer> res = new ArrayList<>();
    
    //思路：二叉搜索树的中序遍历是一个有序的数组。相邻之间的两个数的绝对差是最小的。
    //思路1：这里用一个链表存储下中序遍历的结果。
    public int getMinimumDifference(TreeNode root) {
        //树中至少有2个节点，不需要判断root为null
        InOrder(root);
        int min = Integer.MAX_VALUE;
        for(int i = 0;i < res.size()-1;i++){
            min = Math.min(min, Math.abs(res.get(i) - res.get(i+1)));
        }
        return min;
    }
    
    public void InOrder(TreeNode node){
        if(node.left != null){
            InOrder(node.left);        
        }
        res.add(node.val);
        if(node.right != null){
            InOrder(node.right);
        }
    }
}
```





## 541.反转字符串II

>给定一个字符串**s**和一个整数**k**，你需要对从字符串开头算起的每隔**2k**个字符的前**k**个字符进行反转。
>
>如果剩余字符少于 k 个，则将剩余字符全部反转。
>如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
>
>```
>输入: s = "abcdefg", k = 2
>输出: "bacdfeg"
>```

```java
class Solution {
    //还是先将字符串转换成为字符数组，然后再进行特定位置的翻转
    public String reverseStr(String s, int k) {
		if(s == null || s.length() <= 1 || k <= 1) return s;
		char[] chars = new char[s.length()];
		for(int i = 0;i < s.length();i++){
			chars[i] = s.charAt(i);
		}
		int start = 0;
		while(start < s.length()){
			reverse(chars, start, k);
			start += 2*k;  //后k个元素跳过去
		}
		StringBuilder res = new StringBuilder();
		for(int i = 0;i < s.length();i++){
			res.append(chars[i]);
		}
		return res.toString();
	}
	
	//如果i之后剩余的元素小于等于k，就全部翻转；否则只翻转前k个元素，后k个元素不变
	private void reverse(char[] chars, int i, int k) {
		if(i >= chars.length) return;
		int l = i, r = Math.min(i+k-1, chars.length-1);
		while(l < r){
			char temp = chars[l];
			chars[l] = chars[r];
			chars[r] = temp;
			l++;r--;
		}
	}
}
```

## 557.反转字符串中的单词III

>给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
>
>```
>例如：
>输入："Let's take LeetCode contest"
>输出："s'teL ekat edoCteeL tsetnoc"
>提示：在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。
>```

```java
class Solution {
    //思路：因为提示中说了，限定好只有必要的空格存在。所以先进行切割，得到所有的单词字符串。
    //然后，将单词逆序存放，并且添加好空格，最后来个大翻转，并且去除掉多余的空格。
    //记得：需要转换为String之后，才能进行空格的去除。
    public String reverseWords(String s) {
        if(s == null || s.length() <= 1) return s;
        String[] strs = s.split(" ");  //按照空格进行切割
        StringBuilder res = new StringBuilder();
        for(int i = strs.length-1;i >= 0;i--){
            res.append(strs[i]).append(" ");
        }
        return res.reverse().toString().trim();
    }
}
```

## 716.最大栈

>这个题目和155最小栈类似，基于之前的代码改造一下就可以了。

```java
class MaxStack {

    Stack<Integer> stack = new Stack<>();
    int max = Integer.MIN_VALUE;
    
    /** initialize your data structure here. */
    public MaxStack() {
        
    }
    
    //如果当前值大于等于max，需要先将max压栈，然后压入x，并更新max的值
    public void push(int x) {
        if(x >= max){
            stack.push(max);
            max = x;
        }
        stack.push(x);
    }
    
    //如果当前出栈是max，就需要再出栈一个元素，作为新的max
    public void pop() {
        if(stack.pop() == max){
            max = stack.pop();
        }
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMax() {
        return max;
    }
}
```



## 763.划分字母区间

>字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一个字母只会出现在其中的一个片段。返回一个表示每个字符串片段的长度的列表。
>
>示例 1：
>
>输入：S = "ababcbacadefegdehijhklij"
>输出：[9,7,8]
>解释： 划分结果为 "ababcbaca", "defegde", "hijhklij"。 每个字母最多出现在一个片段中。
>像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。
>
>
>提示： S的长度在[1, 500]之间。  S只包含小写字母 'a' 到 'z' 。
>

```java
class Solution {
    //题目理解：将字符串划分为尽可能多的片段，但是要保证任意两个片段中没有重复的字母
    //思路：用map记录下每个字符最后一次出现的位置下标;
    //然后从头开始遍历，根据字符最后一次出现的位置来确定最小片段的长度
    public List<Integer> partitionLabels(String S) {
        List<Integer> res = new ArrayList<>();
		if(S == null || S.length() == 0) {
			return res;
		}
		char c;
		HashMap<Character, Integer> map = new HashMap<>();
		for(int i = 0;i < S.length();i++){
			c = S.charAt(i);
			map.put(c, i);
		}
		int start = 0;
		while(start < S.length()){
			int end = map.get(S.charAt(start));  // 当前字符最后一次出现的位置
			for(int i = start+1;i <= end;i++){
				end = Math.max(end, map.get(S.charAt(i)));  //还要保证其他字符都仅在一个片段中出现。
			}
			res.add(end-start+1);
			start = end+1;
		}
		return res;
    }
}
```



## 844.比较含退格的字符串

>给定 S 和 T 两个字符串，当它们分别被输入到空白的文本编辑器后，判断二者是否相等，并返回结果。 # 代表退格字符。
>
>注意：如果对空文本输入退格字符，文本继续为空。
>
>示例 1：
>
>输入：S = "ab#c", T = "ad#c"
>输出：true
>解释：S 和 T 都会变成 “ac”。
>示例 2：
>
>输入：S = "ab##", T = "c#d#"
>输出：true
>解释：S 和 T 都会变成 “”。
>示例 3：
>
>输入：S = "a##c", T = "#a#c"
>输出：true
>解释：S 和 T 都会变成 “c”。
>示例 4：
>
>输入：S = "a#c", T = "b"
>输出：false
>解释：S 会变成 “c”，但 T 仍然是 “b”。
>
>
>提示：
>
>1 <= S.length <= 200
>1 <= T.length <= 200
>S 和 T 只含有小写字母以及字符 '#'。
>
>
>进阶：你可以用 O(N) 的时间复杂度和 O(1) 的空间复杂度解决该问题吗？
>

```java
class Solution {
    //思路1：使用两个栈来存放字符，遇到#，就弹出一个字符出来。然后比较两个栈里面的元素是不是完全一致
    public boolean backspaceCompare(String S, String T) {
        if((S == null && T == null) || (S.length() == 0 && T.length() == 0)) return true;
        if(S == null || T == null || S.length() == 0 || T.length() == 0) return false;
        Stack<Character> stack1 = new Stack<>();
        Stack<Character> stack2 = new Stack<>(); 
        for(int i = 0;i < S.length();i++){
            char c1 = S.charAt(i);
            if(c1 == '#'){
                if(!stack1.isEmpty()){
                    stack1.pop();  //回退一个
                }
            }
            else{
                stack1.push(c1);
            }
        }
        for(int i = 0;i < T.length();i++){
            char c2 = T.charAt(i);
            if(c2 == '#'){
                if(!stack2.isEmpty()){
                    stack2.pop();  //回退一个
                }
            }
            else{
                stack2.push(c2);
            }
        }
        while(!stack1.isEmpty() && !stack2.isEmpty()){
            if(stack1.peek() != stack2.peek()){
                return false;
            }
            else{
                stack1.pop();
                stack2.pop();
            }
        }
        return stack1.isEmpty() && stack222.isEmpty();
    }
}
```

## 845.数组中的最长山脉

>我们把数组 A 中符合下列属性的任意连续子数组 B 称为 “山脉”：
>
>B.length >= 3 存在 0 < i < B.length - 1 使得 B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... > B[B.length - 1]（注意：B 可以是 A 的任意子数组，包括整个数组 A。）
>
>给出一个整数数组 A，返回最长 “山脉” 的长度。如果不含有 “山脉” 则返回 0。
>
>示例 1：  输入：[2,1,4,7,3,2,5]  输出：5  解释：最长的 “山脉” 是 [1,4,7,3,2]，长度为 5。
>示例 2：  输入：[2,2,2]  输出：0  解释：不含 “山脉”。
>
>
>提示： 0 <= A.length <= 10000   0 <= A[i] <= 10000
>

```java
class Solution {
    //思路：首先找到合适的山顶，然后从山顶往两侧延伸，找到最长的山脉
    public int longestMountain(int[] A) {
        if(A == null || A.length < 3){
            return 0;
        }
        int res = 0;
        for(int i = 1;i < A.length-1;i++){
            if(A[i] > A[i-1] && A[i] > A[i+1]){  //当A[i]是最大的时候，位置i是山顶。
                int left = i-1, right = i+1;
                while(left >= 0 && A[left] < A[left+1]){
                    left --;  //向左侧延伸
                }
                while(right < A.length && A[right] < A[right-1]){
                    right ++;  //向右侧延伸
                }
                res = Math.max(res, right - left - 1);
            }
        }
        return res;
    }
}
```



## 925.长按键入

>你的朋友正在使用键盘输入他的名字**name**。偶尔，在键入字符 c 时，按键可能会被长按，而字符可能被输入 1 次或多次。
>
>你将会检查键盘输入的字符 typed。如果它对应的可能是你的朋友的名字（其中一些字符可能被长按），那么就返回 True。
>
>示例 1：
>
>输入：name = "alex", typed = "aaleex"
>输出：true
>解释：'alex' 中的 'a' 和 'e' 被长按。
>示例 2：
>
>输入：name = "saeed", typed = "ssaaedd"
>输出：false
>解释：'e' 一定需要被键入两次，但在 typed 的输出中不是这样。
>示例 3：
>
>输入：name = "leelee", typed = "lleeelee"
>输出：true
>示例 4：
>
>输入：name = "laiden", typed = "laiden"
>输出：true
>解释：长按名字中的字符并不是必要的。
>
>提示： name.length <= 1000
>	  typed.length <= 1000
>	  name 和 typed 的字符都是小写字母。

```java
class Solution {
    //思路：采用两个下标来做比较，记录下当前比较的字符，然后移动
    public boolean isLongPressedName(String name, String typed) {
        if(name.length() > typed.length()) return false;
        int i = 0, j = 0;
        char pre = ' ';  //记录下typed中的上一个字符
        while(i < name.length() && j < typed.length()){
            if(name.charAt(i) == typed.charAt(j)){
                pre = name.charAt(i);
                i++;
                j++;
            }
            else{
                if(typed.charAt(j) == pre){
                    j++;
                }
                else{
                    return false;
                }
            }
        }
        while(j < typed.length() && typed.charAt(j) == pre){
            j++;
        }
        return i == name.length() && j == typed.length();
    }
}
```



## 977.有序数组的平方

>给定一个按非递减顺序排序的整数数组**A**，返回每个数字的平方组成的新数组，要求也按非递减顺序排序。
>
>示例 1：
>
>输入：[-4,-1,0,3,10]
>输出：[0,1,9,16,100]
>示例 2：
>
>输入：[-7,-3,2,3,11]
>输出：[4,9,9,49,121]
>
>
>提示：
>
>1 <= A.length <= 10000
>-10000 <= A[i] <= 10000
>A 已按非递减顺序排序。

```java
class Solution {
    //思路：先找到非递减数组中绝对值最小的那个数，然后使用双指针向两侧出发
    public int[] sortedSquares(int[] A) {
        if(A == null || A.length == 0){
            return new int[0];
        }
        int[] res = new int[A.length];
        int index = findMinAbs(A), res_i = 0;
        int l = index, r = index+1;
        while(l >= 0 && r < A.length){
            int sum1 = A[l] * A[l], sum2 = A[r] * A[r];
            if(sum1 <= sum2){
                res[res_i++] = sum1;
                l--;
            }
            else{
                res[res_i++] = sum2;
                r++;
            }
        }
        while(l >= 0){
            res[res_i++] = A[l] * A[l--];
        }
        while(r < A.length){
            res[res_i++] = A[r] * A[r++];
        }
        return res;
    }
    
    //找到绝对值最小的那个数的下标
    public int findMinAbs(int[] nums){
        int min = Integer.MAX_VALUE;
        int res = 0;
        for(int i = 0;i < nums.length;i++){
            if(Math.abs(nums[i]) < min){
                res = i;
                min = Math.abs(nums[i]);
            }
        }
        return res;
    }
}
```





## 1002.查找常用字符

>给定仅有小写字母组成的字符串数组 A，返回列表中的每个字符串中都显示的全部字符（包括重复字符）组成的列表。例如，如果一个字符在每个字符串中出现 3 次，但不是 4 次，则需要在最终答案中包含该字符 3 次。
>
>你可以按任意顺序返回答案。
>
>示例 1：
>
>输入：["bella","label","roller"]
>输出：["e","l","l"]
>示例 2：
>
>输入：["cool","lock","cook"]
>输出：["c","o"]

```java
class Solution {
    //思路：因为只需要记得26个英文字母。
    //用一个数组来记录全局下每个字母出现的次数，另一个数组来记录当前这个字符串中各个字母的情况。
    public List<String> commonChars(String[] A) {
        List<String> res = new ArrayList<>();
        if(A == null || A.length == 0) return res;
        int[] count = new int[32];
        for(int i = 0;i < count.length;i++){
            count[i] = Integer.MAX_VALUE;
        }
        int[] c = new int[32];
        for(String str: A){
            for(int j = 0;j < str.length();j++){
                int index = (int)(str.charAt(j) - 'a');
                c[index] ++;
            }
            for(int i = 0;i < 32;i++){
                count[i] = Math.min(count[i], c[i]);
                c[i] = 0;  //清空为0
            }
        }
        for(int i = 0;i < 32;i++){
            while(count[i] != 0){
                res.add(Character.toString('a'+i));
                count[i]--;
            }
        }
        return res;
    }
}
```



## 1119.删去字符串中的元音字母

>给你一个字符串 S，请你删去其中的所有元音字母（ ‘a’，‘e’，‘i’，‘o’，‘u’），并返回这个新字符串。
>
>示例 1： 输入："leetcodeisacommunityforcoders" 
>
>输出："ltcdscmmntyfrcdrs" 
>
>示例 2： 输入："aeiou" 输出：""  
>
>提示： S 仅由小写英文字母组成。 1 <= S.length <= 1000

```java
class Solution{
    //思路：遍历这个字符串，只留下那些不是元音字母的字符。
    public String removeVowels(String s){
        if(s == null || s.length() == 0) return s;
        StringBuilder res = new StringBuilder();
        for(int i = 0;i < s.length();i++){
            if(!isVowel(s.charAt(i))){
                res.append(s.charAt(i));
            }
        }
        return res.toString();
    }
    
    //判断是不是小写的元音字母
    public boolean isVowel(char c){
        return (c == 'a') || (c == 'e') || (c == 'i') || (c == 'o') || (c == 'u');
    }
}
```

## 1209.独一无二的出险次数

>给你一个整数数组 arr，请你帮忙统计数组中每个数的出现次数。
>
>如果每个数的出现次数都是独一无二的，就返回 true；否则返回 false。
>
>示例 1：
>
>输入：arr = [1,2,2,1,1,3]
>输出：true
>解释：在该数组中，1 出现了 3 次，2 出现了 2 次，3 只出现了 1 次。没有两个数的出现次数相同。
>示例 2：
>
>输入：arr = [1,2]
>输出：false
>示例 3：
>
>输入：arr = [-3,0,1,-3,1,1,1,-3,10,0]
>输出：true

```java
class Solution {
    //思路：首先用hashmap存放数字-出现次数，然后用set找是否有数的次数是重复出现的。
    public boolean uniqueOccurrences(int[] arr) {
        if(arr == null || arr.length <= 1) return true;
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int num: arr){
            if(!map.containsKey(num)){
                map.put(num, 1);
            }
            else{
                map.put(num, map.get(num) + 1);
            }
        }
        Set<Integer> set = new HashSet<>();
        for(int key: map.keySet()){
            if(set.contains(map.get(key))){
                return false;
            }
            else{
                set.add(map.get(key));
            }
        }
        return true;
    }
}
```



## 1365.有多少小于当前数字的数字

>给你一个数组 nums，对于其中每个元素 nums[i]，请你统计数组中比它小的所有数字的数目。
>
>换而言之，对于每个 nums[i] 你必须计算出有效的 j 的数量，其中 j 满足 j != i 且 nums[j] < nums[i] 。
>
>以数组形式返回答案。
>
>示例 1：
>
>输入：nums = [8,1,2,2,3]
>输出：[4,0,1,1,3]
>解释： 
>对于 nums[0]=8 存在四个比它小的数字：（1，2，2 和 3）。 
>对于 nums[1]=1 不存在比它小的数字。
>对于 nums[2]=2 存在一个比它小的数字：（1）。 
>对于 nums[3]=2 存在一个比它小的数字：（1）。 
>对于 nums[4]=3 存在三个比它小的数字：（1，2 和 2）。
>示例 2：
>
>输入：nums = [6,5,4,8]
>输出：[2,1,0,3]
>示例 3：
>
>输入：nums = [7,7,7,7]
>输出：[0,0,0,0]

```java
class Solution {
    //思路：采用简单的方式，双层循环来做。
    public int[] smallerNumbersThanCurrent(int[] nums) {
        if(nums == null || nums.length == 0) return new int[0];
        int[] res = new int[nums.length];
        for(int i = 0;i < nums.length;i++){
            for(int j = 0;j < nums.length;j++){
                if(nums[j] < nums[i]){  
                    res[i]++;//这里不需要增加j!=i的判断，因为当相等的时候，他们的值是一样的。
                }
            }
        }
        return res;
    }
}
```





# 二、多线程题目

## 0.实现多线程的几种方式

```java
//方式一：继承Thread类，重写run方法。
class Cat extends Thread{
    
    private String name;
   
    public Cat(String name){
        this.name = name;
   	}
    
    @Override
    public void run(){
        System.out.println("Hello, I am " + name + ".");
    }
    
    public static void main(String[] args) {
        Cat bob = new Cat("bob");
        bob.start();
    }
}

//方式二：实现CatRunnable接口，一样是重写run方法
class CatRunnable implements Runnable{
    
    private String name;
    
    public CatRunnable(String name){
        this.name = name;
    }
    
    @Override
    public void run(){
        System.out.println("Hello, I am " + name + ".");
    }
    
    public static void main(String[] args) {
        CatRunnable runnable = new CatRunnable("bob");
        Thread bob = new Thread(runnable);
        bob.start();
    }
}

//方式三：使用Executor框架来实现多线程。
```

## 1.交替代印

>两个线程交替打印a和b

```java
public class threadTest {
	
	private static volatile boolean flag = true;   //信号控制
	
	public static void main(String[] args) {
		ARunnable runnable1 = new ARunnable();
		BRunnable runnable2 = new BRunnable();
		Thread thread1 = new Thread(runnable1);
		Thread thread2 = new Thread(runnable2);
		thread1.start();
		thread2.start();
	}
	
	static class ARunnable implements Runnable{
		@Override
		public void run() {
			while (true){
				if(flag){
					System.out.print('a');
					flag = false;
				}
			}
		}
	}
	
	static class BRunnable implements Runnable{
		@Override
		public void run() {
			while (true){
				if(!flag){
					System.out.print('b');
					flag = true;
				}
			}
		}
	}
}
```

>三个线程按照顺序打印abcabcabc...

```java
public class threadTest {
	
	private static volatile int flag = 1;   //信号控制
	
	public static void main(String[] args) {
		ARunnable runnable1 = new ARunnable();
		BRunnable runnable2 = new BRunnable();
		CRunnable runnable3 = new CRunnable();
		Thread thread1 = new Thread(runnable1);
		Thread thread2 = new Thread(runnable2);
		Thread thread3 = new Thread(runnable3);
		thread1.start();
		thread2.start();
		thread3.start();
	}
	
	static class ARunnable implements Runnable{
		@Override
		public void run() {
			while (true){
				if(flag == 1){
					System.out.print('a');
					flag = 2;
				}
			}
		}
	}
	
	static class BRunnable implements Runnable{
		@Override
		public void run() {
			while (true){
				if(flag == 2){
					System.out.print('b');
					flag = 3;
				}
			}
		}
	}
	
	static class CRunnable implements Runnable{
		@Override
		public void run() {
			while (true){
				if(flag == 3){
					System.out.print('c');
					flag = 1;
				}
			}
		}
	}
}
```



## 1114.按序打印

>我们提供了一个类：
>
>public class Foo {
>  public void first() { print("first"); }
>  public void second() { print("second"); }
>  public void third() { print("third"); }
>}
>三个不同的线程将会共用一个 Foo 实例。
>
>线程 A 将会调用 first() 方法
>线程 B 将会调用 second() 方法
>线程 C 将会调用 third() 方法
>请设计修改程序，以确保 second() 方法在 first() 方法之后被执行，third() 方法在 second() 方法之后被执行。

```java
class Foo {
    // 思路：使用一个信号量来做标志，如果当前信号量不满足条件，就一直等待；
    // 如果满足条件，就输出，并修改它的值。
    private volatile int flag = 1;
    
    public Foo() {
        
    }

    public void first(Runnable printFirst) throws InterruptedException {
        while(flag != 1){
            Thread.yield();
        }
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        flag = 2;
    }

    public void second(Runnable printSecond) throws InterruptedException {
        while(flag != 2){
            Thread.yield();
        }
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        flag = 3;
    }

    public void third(Runnable printThird) throws InterruptedException {
        while(flag != 3){
            Thread.yield();
        }
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
        flag = 1;
    }
}
```

## 1115.交替打印FootBar

>```
>输入: n = 1
>输出: "foobar"
>解释: 这里有两个线程被异步启动。其中一个调用 foo() 方法, 另一个调用 bar() 方法，"foobar" 将被输出一次。
>```
>
>```
>输入: n = 2
>输出: "foobarfoobar"
>解释: "foobar" 将被输出两次。
>```

```java
class FooBar {
    
    boolean flag = true;
    
    private int n;

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        
        for (int i = 0; i < n; i++) {
            while(!flag){
                Thread.yield();  //如果不满足条件，就先挂起，不要一直轮询，从而节省了资源。
            }
        	// printFoo.run() outputs "foo". Do not change or remove this line.
        	printFoo.run();
            flag = false;
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        
        for (int i = 0; i < n; i++) {
            while(flag){
                Thread.yield();
            }
            // printBar.run() outputs "bar". Do not change or remove this line.
        	printBar.run();
            flag = true;
        }
    }
}
```

## 1116.打印零与奇偶数

>```
>输入：n = 2
>输出："0102"
>说明：三条线程异步执行，其中一个调用 zero()，另一个线程调用 even()，最后一个线程调用odd()。正确的输出为 "0102"。
>
>输入：n = 5
>输出："0102030405"
>```

```java
class ZeroEvenOdd {
    
    private int n;
    
    private volatile Integer bitMap = 0b00;
    
    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for(int i = 1;i <= n;i++){
            while((bitMap ^ 0b00) != 0){
            	Thread.yield();   //如果不是Ob00就挂起
        	}
            printNumber.accept(0);   //打印0
            if(i % 2 == 0){
                //偶数的时候
                bitMap |= 0b10;
            }
            else{
                //奇数的时候
                bitMap |= 0b01;
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for(int i = 2;i <= n;i += 2){
            while((bitMap & 0b10) == 0){
                Thread.yield();  //如果是0b00或者0b01就挂起
            }
            printNumber.accept(i);   //打印i
            bitMap &= 0b00;
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for(int i = 1;i <= n;i += 2){
            while((bitMap & 0b01) == 0){
                Thread.yield();  //如果是0b00或者0b10就挂起
            }
            printNumber.accept(i);   //打印i
            bitMap &= 0b00;  //清零
        }
    }
}
```









