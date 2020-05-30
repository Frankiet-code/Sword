package sword;

import java.util.ArrayList;

/**
 * @Author: lei
 * @Data: 2020.4.1 16:39
 * @Description: 和为S的两个数字
 * 输入一个递增排序的数组和一个数字S，在数组中查找两个数，使得他们的和正好是S，如果有多对数字的和等于S，输出两个数的乘积最小的。
 */
public class test42 {
	public static void main(String[] args) {
		int [] array = {1,2,4,7,11,15};
		ArrayList<Integer> list = FindNumbersWithSum(array, 15);
		System.out.println("list = " + list); // list = [4, 11]
	}
	
	//思路：从两边往中间夹
	public static ArrayList<Integer> FindNumbersWithSum(int [] array, int sum) {
		ArrayList<Integer> res = new ArrayList<>();
		if(array == null || array.length <= 1) return res;
		int left = 0, right = array.length-1;
		while(left < right){
			if(array[left] + array[right] > sum)
				right--;
			else if(array[left] + array[right] < sum)
				left ++;
			else {
				if(res.isEmpty()){
					res.add(array[left]); res.add(array[right]);
				}
				else{
					//如果后面来的乘积要小，需要更新
					if(res.get(0) * res.get(1) > array[left] * array[right]){
						res.set(0, array[left]); res.set(0, array[right]);
					}
				}
				left ++;
				right--;
			}
		}
		return res;
	}
}//class end
