package sword;

/**
 * @Author: lei
 * @Data: 2020.4.5 15:22
 * @Description: 剪绳子
 * 给你一根长度为n的绳子，请把绳子剪成整数长的 m 段（m、n都是整数，n>1并且m>1），
 * 每段绳子的长度记为k[0],k[1],...,k[m]。请问k[0]xk[1]x...xk[m]可能的最大乘积是多少？
 * 例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 */
public class test67 {
	public static void main(String[] args) {
		System.out.println(cutRope(8));
	}
	
	// n > 1 & m > 1
	// f(n) = f(i) * f(n-i);
	// 思路：找局部最优解
	public static int cutRope(int target) {
		if(target < 2) return 0;
		if(target == 2) return 1;
		if(target == 3) return 2;
		if(target == 4) return 4;
		int [] dp = new int[target+1];
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 3;
		dp[4] = 4;
		for(int i = 4;i <= target;i++){
			int max = Integer.MIN_VALUE;
			for(int j = 1;j <= i/2;j++){
				max = Math.max(max, dp[j] * dp[i-j]);  //f(n) = f(i) * f(n-i);
			}
			dp[i] = max;
		}
		return dp[target];
	}
}//class end
