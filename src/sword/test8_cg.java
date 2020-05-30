package sword;

/**
 * @Author: lei
 * @Data: 2020.3.30 23:42
 * @Description: AliTest1: 就是跳台阶的一个变式：
 * 给一个整数字符，例如1234,我们可以将1~26译码成为A~Z
 * 那么看能有多少种译码方式
 * 比如11 可以翻译成 AA、K 两种
 * 1141 可以翻译成 AADA ANA KDA 三种
 */
public class test8_cg {
	public static void main(String[] args) {
		String s = "1111";  //AAAA KAA AKA AAK KK
		System.out.println(how_many(s)); //5
		String s2 = "1141";  // AADA KDA ANA
		System.out.println(how_many(s2)); //3
		System.out.println(2 << 2);
	}
	
	//思路和之前的跳台阶一样，如果末尾的两个数拼起来大于26，那么末尾的数只能加上去，也就是种类和没加它是一样的，就是f(n)
	//如果小于等于26，那么说明可以拼起来作为一个新的数，因此除了单纯地加最后一个数上去之后，还可以末尾两个数成为新的数，也就得到f(n-2)个方式
	public static int how_many(String s){
		if(s == null || s.length() == 0) return 0;
		if(s.length() == 1) return 1;
		int [] dp = new int[s.length()];
		dp[0] = 1;
		int sum = s.charAt(0) - '0';
		sum = sum * 10 + (s.charAt(1) - '0');
		if(sum > 26)
			dp[1] = 1;
		else
			dp[1] = 2;
		for(int i = 2;i < s.length();i++){
			sum = s.charAt(i-1) - '0';
			sum = sum * 10 + (s.charAt(i) - '0');
			if(sum > 26)
				dp[i] = dp[i-1];
			else
				dp[i] = dp[i-1] + dp[i-2];
		}
		return dp[s.length()-1];
	}
}//class end
