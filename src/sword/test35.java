package sword;

import java.util.Arrays;

/**
 * @Author: lei
 * @Data: 2020.4.1 13:30
 * @Description: 数组中的逆序对
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
 * 输入一个数组,求出这个数组中的逆序对的总数P。
 * 并将P对1000000007取模的结果输出。 即输出P%1000000007
 * 输入：1,2,3,4,5,6,7,0 输出: 7
 */
public class test35 {
	public static void main(String[] args) {
		int [] nums = {1,2,3,4,5,6,7,0};
		System.out.println(InversePairs_2(nums));  //7
		sort(0, nums.length - 1, nums); //归并排序
		System.out.println(Arrays.toString(nums));
	}
	
	//第1种方式，判断每一个数和其后面的每个数的关系，时间复杂度 N*N
	public static int InversePairs_1(int [] array) {
		long count_1 = 0;
		for(int i = 0;i < array.length;i++){
			for(int j = i+1;j< array.length;j++){
				if(array[i] > array[j])
					count_1 ++;
			}
		}
		return (int)(count_1 % mod);
	}
	
	static long mod = 1000000007;
	
	static long count = 0;
	
	//第2种方式：采用归并排序，在归并排序的过程中，找到那些逆序对。时间复杂度 N*logN
	public static int InversePairs_2(int [] array) {
		if(array == null || array.length <= 1) return 0;
		sort(0, array.length-1, array);
		return (int)(count % mod);
	}
	
	//就是一个归并排序的完整实现，
	private static void sort(int start, int end, int[] nums) {
		if(start >= end) return;
		int mid = start + (end - start) / 2;
		sort(start, mid, nums);
		sort(mid+1, end, nums);
		merge(start, mid, end, nums);
	}
	
	//处理两段排了序的数组 left~mid, mid+1~end的合并操作，并在其中计算出需要的count值
	private static void merge(int left, int mid, int end, int[] nums) {
		int [] temp = new int[end-left+1];  //针对的那些数据的长度
		int m = 0;  //与nums对应起来
		int l = left, r = mid + 1;  //两段数组的开始位置
		while(l <= mid && r <= end){
			if(nums[l] <= nums[r])
				temp[m++] = nums[l++];
			else {
				count += mid-l+1;     //代表nums[l]这个数和它后面的数都是大于nums[r]的
				temp[m++] = nums[r++];
			}
		}
		while(l <= mid)
			temp[m++] = nums[l++];
		while(r <= end)
			temp[m++] = nums[r++];
		m = 0;
		for(int i = left;i <= end;i++)
			nums[i] = temp[m++];  //搬迁到nums中
	}
}//class end
