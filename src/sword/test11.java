package sword;

/**
 * @Author: lei
 * @Data: 2020.3.31 11:08
 * @Description:11. 二进制中1的个数
 * 输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
 */
public class test11 {
	public static void main(String[] args) {
		for(int i = 0;i < 20;i++)
			System.out.println("i = " + i + ": " + NumberOf1(i));
	}
	
	//思路：数字n与n-1求并之后，1的个数就会减去1,例如：111 & 110 = 110，1的个数由3变为了2.
	public static int NumberOf1(int n) {
		int count = 0;
		while(n != 0){
			n = n & (n-1);  //求并
			count++;  //次数加1
		}
		return count;
	}
}//class end
