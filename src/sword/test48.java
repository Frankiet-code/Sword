package sword;

/**
 * @Author: lei
 * @Data: 2020.4.1 18:32
 * @Description: 不用加减乘除做加法
 * 写一个函数，求两个整数之和，要求在函数体内不得使用+、-、*、/四则运算符号。
 */
public class test48 {
	public static void main(String[] args) {
		System.out.println(Add(3, 7));  //10
	}
	
	public static int Add(int num1,int num2) {
		int sum = num1 ^ num2;    //异或求和，不考虑进位
		int carry = num1 & num2;  //并，求出进位
		while(carry != 0){
			carry = carry << 1;  //进位左移1位
			int temp = sum;
			sum = temp ^ carry;   //再求异或
			carry = temp & carry; //再求进位
		}
		return sum;
	}
}//class end
