package sword;

/**
 * @Author: lei
 * @Data: 2020.4.1 20:44
 * @Description: 正则表达式匹配
 * 请实现一个函数用来匹配包括'.'和'*'的正则表达式。
 * 模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（包含0次）。
 * 在本题中，匹配是指字符串的所有字符匹配整个模式。
 * 例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但是与"aa.a"和"ab*a"均不匹配
 */
public class test52 {
	public static void main(String[] args) {
		char [] str = {'a', 'a'};
		char [] pat = {'.'};
		System.out.println(match(str, pat));
	}
	
	//方式1:直接在字符数组中做。还可以先将数组转换成为字符串来做，会更快一点。代码在test52_str中
	public static boolean match(char[] str, char[] pattern) {
		if((str == null && pattern == null)) return true;
		if(str == null || str.length == 0) return helper1(pattern, 0);
		if(pattern == null || pattern.length == 0) return false;
		return helper2(str, 0, pattern, 0);
	}
	
	//看pat[index]及其后面是不是都是a*b*c*这样的
	private static boolean helper1(char[] pat, int index) {
		while(index+1 < pat.length && pat[index+1] == '*')
			index += 2;
		return index == pat.length;
	}
	
	//判断str[index1]与pat[index2]后面是否匹配
	private static boolean helper2(char[] str, int index1, char[] pat, int index2) {
		boolean res1 = index1 == str.length; //str结束了
		boolean res2 = index2 == pat.length; //pat结束了
		boolean res3 = helper1(pat, index2); //pat末尾是b*类似的
		if(res1 && (res2 || res3))
			return true;
		if(res1 || res2){
			if(res2)
				return false;
			else
				return res3;
		}
		//当都没有到达末尾的时候，情况1：pat后面是a*这样的结构
		//如果第一个字符可以匹配，那么可能匹配0次或n次
		//不可以匹配，那么就只能匹配0次了
		if(index2+1 < pat.length && pat[index2+1] == '*'){
			if(helper3(str, index1, pat, index2))
				return helper2(str, index1, pat, index2+2) || helper2(str, index1+1, pat, index2);
			else
				return helper2(str, index1, pat, index2+2);
		}
		//情况2：没有a*这样的结构，只能一个个匹配了
		if(helper3(str, index1, pat, index2))
			return helper2(str, index1+1, pat, index2+1);
		else
			return false;
	}
	
	//匹配 str[index1] 与 pat[index2]
	private static boolean helper3(char[] str, int index1, char[] pat, int index2) {
		return str[index1] == pat[index2] || pat[index2] == '.';
	}
}//class end
