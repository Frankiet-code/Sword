package sword;

/**
 * @Author: lei
 * @Data: 2020.4.1 18:37
 * @Description: 把字符串转换成整数
 * 将一个字符串转换成一个整数，要求不能使用字符串转换整数的库函数。 数值为0或者字符串不是一个合法的数值则返回0
 */
public class test49 {
	public static void main(String[] args) {
		String s = "-2147483648";
		System.out.println(StrToInt(s)); //-2147483648
	}
	
	public static int StrToInt(String str) {
		if(str == null || str.length() == 0) return 0;
		long res = 0;
		int flag = 1, index = 0;
		if(str.charAt(index) == '+'){
			index++;
		}
		else if(str.charAt(index) == '-'){
			flag = -1; index++;
		}
		for(int i = index;i < str.length();i++){
			if(str.charAt(i) >= '0' && str.charAt(i) <= '9'){
				res = res * 10;
				res += str.charAt(i) - '0';
			}
			else
				return 0;  //出现非数字的字符时，返回0
		}
		res = res * flag; //接下来判断它是不是一个整型数
		if(res >= Integer.MIN_VALUE && res <= Integer.MAX_VALUE)
			return (int)res;
		else
			return 0;
	}
}//class end
