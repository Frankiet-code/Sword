package sword;

/**
 * @Author: lei
 * @Data: 2020.4.1 18:29
 * @Description: 1+2+3+...+n
 * 求1+2+3+...+n，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。w
 */
public class test47 {
	public static void main(String[] args) {
		System.out.println(Sum_Solution(3)); //6
	}
	
	public static int Sum_Solution(int n) {
		int sum = n;
		boolean check = (n > 0) && ((sum += Sum_Solution(n-1)) > 0);
		return sum;
	}
}//class end
