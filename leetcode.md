
- 请使用crtl+f，自己搜索题号或者题目名称，就不加目录了。
​:happy:​


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

## 142.环形链表II

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









