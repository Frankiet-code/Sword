package sword;

import java.util.HashMap;

/**
 * @Author: lei
 * @Data: 2020.3.31 21:18
 * @Description: 数组中出现次数超过一半的数字
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
 * 例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。
 * 由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0。
 */
public class test28 {
	public static void main(String[] args) {
		int [] nums = {1};
		System.out.println(MoreThanHalfNum_Solution(nums));
	}
	
	//使用HashMap来存放数字和它出现的次数
	public static int MoreThanHalfNum_Solution(int [] array) {
		HashMap<Integer, Integer> map = new HashMap<>();
		for(int i = 0;i < array.length;i++){
			if(map.containsKey(array[i]))
				map.put(array[i], map.get(array[i])+1);
			else
				map.put(array[i], 1);
			if(map.get(array[i]) > array.length/2)
				return array[i];  //判断语句写在这里，而不是在上面的if中，因为有可能数组中只有一个数字
		}
		return 0;
	}
}//class end
