package sword;

/**
 * @Author: lei
 * @Data: 2020.4.6 12:17
 * @Description: 表示数值的字符串
 * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
 * 例如，字符串"+100","5e2","-123","3.1416"和"-1E-16"都表示数值。
 * 但是"12e","1a3.14","1.2.3","+-5"和"12e+4.3"都不是。
 */
public class test53 {
	public static void main(String[] args) {
		String c = "123.45e+6";
		char [] chars = c.toCharArray();
		System.out.println(isNumeric(chars));
	}
	
	//需要有下面的限制
	//1. +/-号后面必定为数字 或者 后面为. （-.123 = -0.123）
	//2. +/-号只出现在第一位或者在e/E的后面
	//3. . 后面必定为数字 或者 .就是最后一个位了 (122. = 122.0)
	//4. eE后面必定为数字或者+/-号
	public static boolean isNumeric(char[] str) {
		boolean point = false, exp = false; //标志已经有小数点、e/E出现
		for(int i = 0;i < str.length;i++){
			if(str[i] == '+' || str[i] =='-'){
				if(i + 1 == str.length || !(str[i+1] >= '0' && str[i+1] <= '9' || str[i+1] == '.'))
					return false;  //如果str只有一个正负号，或者正负号后面既不是数字，也不是. 代表非法
				if(!(i == 0 || str[i-1] == 'e' || str[i-1] == 'E'))
					return false;  //正负号只能出现在开头或者e/E的后面
			}else if(str[i] == '.'){
				if(point || exp || !(i+1 < str.length && str[i+1] >= '0' && str[i+1] <= '9'))
					return false;  //不允许出现两个. 且 .的前面不能有e，小数点后只能是数字
				point = true;
			}else if(str[i] == 'e' || str[i] == 'E'){
				if(exp || i+1 == str.length || !(str[i+1] >= '0' && str[i+1] <= '9' || str[i+1] == '-' || str[i+1] == '+'))
					return false;  //e的前面不能有e，但可以有. 且e/E的后面只能是数字或者正负号
				exp = true;
			}else if(str[i] < '0' || str[i] > '9')
				return false;
		}
		return true;
	}
}//class end
