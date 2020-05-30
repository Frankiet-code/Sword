package sword;

/**
 * @Author: lei
 * @Data: 2020.3.30 22:48
 * @Description: 青蛙跳台阶
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。
 * 求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
 */
public class test8 {
	public static void main(String[] args) {
		for (int i = 1; i < 10; i++) {
			System.out.print(JumpFloor(i) + " ");
			System.out.print(JumpFloor_2(i) + " ");
		}
	}
	
	//发现递归式：f(n) = f(n-1) + f(n-2)，就是说跳n阶台阶，最后一跳可能是只跳了1阶，也可能使跳了2阶
	//时间复杂度 2的n次方
	public static int JumpFloor(int target) {
		if (target == 0) return 0;
		if (target == 1) return 1;
		if (target == 2) return 2;
		return JumpFloor(target - 1) + JumpFloor(target - 2);
	}
	
	//非递归的写法：时间复杂度 N
	public static int JumpFloor_2(int target) {
		if (target == 0) return 0;
		if (target == 1) return 1;
		if (target == 2) return 2;
		int res = 0, first = 1, second = 2;
		while (target > 2) {
			res = first + second;
			first = second;
			second = res;
			target--;
		}
		return res;
	}//class end
}