package sword;

/**
 * @Author: lei
 * @Data: 2020.3.31 11:14
 * @Description: 数值的整数次方
 * 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
 * 保证base和exponent不同时为0.
 */
public class test12 {
	public static void main(String[] args) {
		System.out.print(Power(2, 3) + " ");
	}
	
	//需要考虑exponent是正负两种情况
	public static double Power(double base, int exponent) {
		if(exponent == 0) return 1;
		if(base == 0) return 0;
		boolean flag = (exponent > 0);  //获取正负性
		exponent = Math.abs(exponent);
		double res = base;
		while(exponent != 1){
			res *= base;
			exponent--;
		}
		return flag ? res : 1 / res;
	}
}//class end
