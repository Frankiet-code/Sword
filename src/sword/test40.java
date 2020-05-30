package sword;

import java.util.HashMap;

/**
 * @Author: lei
 * @Data: 2020.4.1 15:21
 * @Description: 数组中只出现一次的数字
 * 一个整型数组里除了两个数字之外，其他的数字都出现了两次。请写程序找出这两个只出现一次的数字
 */
public class test40 {
	public static void main(String[] args) {
		int [] array = {2,4,3,6,3,2,5,5};
		int [] nums1 = new int[1], nums2 = new int[1];
		FindNumsAppearOnce_2(array, nums1, nums2);
		System.out.println(nums1[0] + " " + nums2[0]); //4 6
	}
	
	//方式1：采用map存放数字和它们出现的次数 时间空间复杂度都是 N
	public static void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
		if(array == null || array.length <= 1) return;
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int value : array) {
			if (map.containsKey(value))
				map.remove(value);
			else
				map.put(value, 1);
		}
		boolean flag = true;
		for(int key : map.keySet()) {
			if(flag) {
				num1[0] = key;
				flag = false;
			}
			else
				num2[0] = key;
		}
	}
	
	//方式2：将这个数与后面的数求异或，就等于0了 时间复杂度N， 空间复杂度 1
	public static void FindNumsAppearOnce_2(int [] array,int num1[] , int num2[]) {
		int xor = 0;
		for(int i = 0;i < array.length;i++){
			xor = xor ^ array[i];    //xor = num1[0] ^ num2[0] 这样的话，xor中为1的那个位代表一个数的这一位为0，另一个数的这个位为1
		}
		int index = 1;
		while((index & xor) == 0)
			index = index << 1;  //因为xor可能有多个位为1所以需要求一下位置，就取第一个为1的那个位置
		for(int i = 0;i < array.length;i++){
			if((index & array[i]) == 0)
				num1[0] = num1[0] ^ array[i];//根据index & array[i]的结果，将原来的数组分成两部分：index中1对应的那个位为1和为0
			else
				num2[0] = num2[0] ^ array[i];
		}
	}
}//class end
