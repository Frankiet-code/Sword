package sword;

/**
 * @Author: lei
 * @Data: 2020.3.13 11:36
 * @Description: 匹配两个字符串是否相等
 */
public class test52_str {
	public static void main(String[] args) {
		char [] str = {'a',           'a',           'a'};
		char [] pat = {'a' ,'b', '*', 'a', 'c', '*', 'a'};
		System.out.println(match(str,pat));
	}
	
	//将字符串数组转换为字符串
	public static boolean match(char[] str, char[] pattern) {
		StringBuilder sbs = new StringBuilder();
		StringBuilder sbp = new StringBuilder();
		for(char ch : str)
			sbs.append(ch);
		for(char ch : pattern)
			sbp.append(ch);
		String s = sbs.toString();
		String p = sbp.toString();
		if(s.length() == 0 && p.length() == 0)
			return true;
		if(s.length() == 0){
			return change(p,0);
		}
		if(p.length() == 0)
			return false;
		return isMatch(s, p);
	}
	
	public static boolean isMatch(String s, String p) {
		return helper(s, p, 0, 0);
	}
	
	/**
	 * 返回两个字符串是否能够完整地匹配
	 * @param s 字符串1
	 * @param p 字符串2
	 * @param index1 字符串1中正在接受检测的字符的当前下标
	 * @param index2 字符串2中正在接受检测的字符的当前下标
	 * @return
	 */
	private static boolean helper(String s, String p, int index1, int index2) {

		//都到达了末尾或者说index2后面就只有一对 a* 这样的了
		boolean res1 = (   index1 == s.length()    );      //s为空了
		boolean res2 = (   index2 == p.length()    );               //p为空了
		boolean res3 = (   index2+1 == p.length() - 1  &&  p.charAt(index2+1) == '*');  //p到了末尾的两个字符，且为c*类似的
		if(res1 && (res2 || res3))
			return true;
		if(res1 || res2){
			if(res1)
				return change(p, index2);
			else
				return false;
		}
		//情况1，index后面的字符为 * 的时候
		if((index2+1) < p.length() &&  p.charAt(index2+1) == '*') {
			if (judge(s, p, index1, index2))
				return helper(s, p, index1, index2+2) || helper(s, p, index1+1, index2);  //匹配0次或者多次时
			else
				return helper(s, p, index1, index2+2);//只能匹配0次，index1不变，index2将直接后移两位
		}
		//情况2，如果当前字符是可以匹配的，那么就看下一个字符
		if(judge(s, p, index1, index2))
			return helper(s, p, index1 + 1, index2 + 1);
		return false;
	}
	
	//检查p字符串中index2及其后面的字符是不是a*c*这样子的
	private static boolean change(String p, int index2) {
		while(index2 < p.length()){
			if(index2+1 < p.length() && p.charAt(index2+1) == '*')
				index2 += 2;
			else
				return false;
		}
		return true;
	}
	
	//判断连个字符是否是相同的，可以是 a==a ,也可以是 a==.
	private static boolean judge(String s, String p, int i, int j) {
		return (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');
	}
}//class end
