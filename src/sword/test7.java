package sword;

/**
 * @Author: lei
 * @Data: 2020.3.30 22:39
 * @Description: 斐波那契数列
 * 大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项（从0开始，第0项为0）。
 * n<=39
 */
public class test7 {
	public static void main(String[] args) {
		for(int i = 0; i< 10;i++) {
			System.out.print(Fibonacci_2(i) + " "); //0 1 1 2 3 5 8 13 21 34
		}
	}
	
	//递归的写法，时间复杂度：2的n次方
	public static int Fibonacci(int n) {
		if(n == 0) return 0;
		if(n == 1) return 1;
		return Fibonacci(n-1) + Fibonacci(n-2);
	}
	
	//非递归的写法，时间复杂度：N
	public static int Fibonacci_2(int n) {
		if(n == 0) return 0;
		if(n == 1) return 1;
		int res = 0, first = 0, second = 1;
		while(n > 1){
			res = first + second;  //前两个数的和
			first = second;  //再更新后面的两个数
			second = res;
			n--;
		}
		return res;
	}
}//class end
