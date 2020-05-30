package sword;

/**
 * @Author: lei
 * @Data: 2020.3.30 22:54
 * @Description: 变态跳台阶
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
 */
public class test9 {
	public static void main(String[] args) {
		for (int i = 1; i < 10; i++) {
			System.out.print(JumpFloorII(i) + " ");
		}
	}
	
	//发现递归式：f(n) = f(n-1) + f(n-2) + f(n-3) + ... + f(1) + f(0)，
	//就是说：跳n阶台阶，最后一跳可能是只跳了1阶，也可能使跳了2阶，也可能直接就跳了n阶
	//变换后：f(n-1) = f(n-2) + f(n-3) + ... + f(1) + f(0)，
	//上下相减：f(n) - f(n-1) = f(n-1)  ->   f(n) = f(n-1) * 2
	public static int JumpFloorII(int target) {
		if(target == 0) return 0;
		if(target == 1) return 1;
		int res = 1;
		while(target > 1){
			res *= 2;  //翻倍
			target--;
		}
		return res;
	}
}//class end
