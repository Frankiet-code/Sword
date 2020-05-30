package sword;

/**
 * @Author: lei
 * @Data: 2020.4.1 12:56
 * @Description: 丑数
 * 把只包含质因子2、3和5的数称作丑数（Ugly Number）。
 * 例如6、8都是丑数，但14不是，因为它包含质因子7。
 * 习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。
 */
public class test33 {
	public static void main(String[] args) {
		for(int i = 0;i < 10;i++)
			System.out.print(GetUglyNumber_Solution(i) + " "); //0 1 2 3 4 5 6 8 9 10
	}
	
	//首先，我们判断一个数是不是丑数，就是看它能不能被2 3 5整除
	public static boolean IsUgly(int n){
		while(n != 1){
			if(n % 2 == 0)
				n /= 2;
			else if(n % 3 == 0)
				n /= 3;
			else if(n % 5 == 0)
				n /= 5;
			else
				return false;
		}
		return true;
	}
	
	//而如果是求第index个丑数，我们如果一个个的判断，会很麻烦，所以选择直接求出这index个丑数，然后返回最后一个
	public static int GetUglyNumber_Solution(int index) {
		if(index == 0) return 0;
		int [] dp = new int[index];
		dp[0] = 1;
		int p2 = 0, p3 = 0, p5 = 0;   //比如p2代表dp[p2]的那个数下次可以乘上2得到新的丑数。当使用过之后，p2就后移一位
		for(int i = 1;i < index;i++){
			dp[i] = Math.min(dp[p2]*2, Math.min(dp[p3]*3, dp[p5]*5));
			if(dp[i] == dp[p2]*2) p2++;  //出现同样的数字，也视为使用过了
			if(dp[i] == dp[p3]*3) p3++;
			if(dp[i] == dp[p5]*5) p5++;
		}
		return dp[index-1];
	}
}//class end
