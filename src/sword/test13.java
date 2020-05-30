package sword;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Author: lei
 * @Data: 2020.3.31 12:13
 * @Description: 将数组中的奇数放在偶数前面
 * 要求：奇数之间的相对位置不变，偶数也是
 */
public class test13 {
	public static void main(String[] args) {
		int [] nums1 = {1,2,3,4,5,6,7,8};
		int [] nums2 = {6,8,1,3,5};
		reOrderArray(nums1);
		System.out.println(Arrays.toString(nums1)); //[1, 3, 5, 7, 2, 4, 6, 8]
	}
	
	//思路：从后面的数组中依次找到所有的奇数，然后找到一个，就把它前面的偶数都后移
	// 用时，把找到的奇数放到前面的可以放奇数的位置上，而这个位置每增加一个奇数，就后移一位。
	// 时间复杂度 N*N, 空间：1
	public static void reOrderArray(int [] array) {
		if(array == null || array.length <= 1) return ;
		int m = 0;  //记录第一个可以放奇数的位置
		for(int i = 0;i < array.length;i++){
			if(array[i] % 2 == 1){
				int temp = array[i];
				int j = i;
				while(j > m){
					array[j] = array[j-1];
					j--;
				}
				array[m] = temp;  //奇数归位
				m++;
			}
		}
	}
	
	//思路，采用冒泡排序的方式，每次将一个偶数交换到末尾去
	// 时间复杂度 N*N, 空间：1
	public static void reOrderArray_1(int [] array) {
		if (array == null || array.length <= 1) return;
		for(int i = 0;i < array.length - 1;i++){
			for(int j = 0;j < array.length-1;j++){
				if(array[j] % 2 == 0 && array[j+1] % 2 == 1){
					int temp = array[j+1];
					array[j+1] = array[j];
					array[j] = temp;
				}
			}
		}
	}
	
	// 采用两个链表来存放
	// 时间复杂度 N, 空间：N
	public static void reOrderArray_2(int [] array) {
		if (array == null || array.length <= 1) return;
		ArrayList<Integer> even = new ArrayList<>();  //存偶数
		ArrayList<Integer> odd = new ArrayList<>();   //存奇数
		for(int i = 0;i < array.length;i++){
			if(array[i] % 2 == 0)
				even.add(array[i]);
			else
				odd.add(array[i]);
		}
		int index = 0;
		for(int i : odd)
			array[index++] = i;
		for(int i : even)
			array[index++] = i;
	}
}//class end
