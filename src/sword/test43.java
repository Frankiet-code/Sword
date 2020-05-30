package sword;

/**
 * @Author: lei
 * @Data: 2020.4.1 16:48
 * @Description: 左旋转字符串
 * 汇编语言中有一种移位指令叫做循环左移（ROL），现在有个简单的任务，就是用字符串模拟这个指令的运算结果。
 * 对于一个给定的字符序列S，请你把其循环左移K位后的序列输出。
 * 例如，字符序列S=”abcXYZdef”,要求输出循环左移3位后的结果，即“XYZdefabc”。
 * 是不是很简单？OK，搞定它！
 */
public class test43 {
	public static void main(String[] args) {
		String str = "abcXYZdef";
		System.out.println(LeftRotateString_2(str, 3)); //XYZdefabc
	}
	
	//方式1：挨个字符来处理
	public static String LeftRotateString(String str, int n) {
		if(str == null || str.length() <= 1 || n % str.length() == 0) return str;
		n = n % str.length();
		StringBuilder sb = new StringBuilder();
		for(int i = n;i < str.length();i++)
			sb.append(str.charAt(i));
		for(int i = 0;i < n;i++)
			sb.append(str.charAt(i));
		return sb.toString();
	}
	
	//方式2：使用函数 String substring(int beginIndex, int endIndex)   //[begin, end)
	public static String LeftRotateString_2(String str, int n) {
		if(str == null || str.length() <= 1 || n % str.length() == 0) return str;
		n = n % str.length();
		String sb = str.substring(n);  //从n开始的字符都放进去
		sb += str.substring(0, n);     //连接上 [0,n) 字符
		return sb;
	}
}//class end
