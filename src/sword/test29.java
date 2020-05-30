package sword;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Author: lei
 * @Data: 2020.3.31 21:23
 * @Description: 最小的K个数
 * 输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4
 */
public class test29 {
	public static void main(String[] args) {
		int [] nums = {4,5,1,6,2,7,3,8};
		System.out.println(GetLeastNumbers_Solution(nums, 4)); //[1, 2, 3, 4]
	}
	
	public static ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
		ArrayList<Integer> res = new ArrayList<>();
		if(k < 1 || input.length < k) return res;
		Arrays.sort(input);  //先排序，再取前面k个数字
		int index = 0;
		while(k != 0){
			res.add(input[index]);
			k--; index++;
		}
		return res;
	}
}//class end
