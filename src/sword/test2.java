package sword;

/**
 * @Author: lei
 * @Data: 2020.3.30 21:21
 * @Description: 替换空格
 * 请实现一个函数，将一个字符串中的每个空格替换成“%20”。
 * 例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
 */
public class test2 {
	public static void main(String[] args) {
		StringBuffer s1 = new StringBuffer("We Are Happy");
		StringBuffer s2 = new StringBuffer("  ");
		System.out.println(replaceSpace(s1)); //We%20Are%20Happy
		System.out.println(replaceSpace(s2)); //%20%20
	}
	
	//直接遍历一遍字符串
	public static String replaceSpace(StringBuffer str) {
		if(str == null || str.length() == 0) return "";
		StringBuffer sb = new StringBuffer();
		for(int i = 0;i < str.length();i++){
			if(str.charAt(i) == ' ')
				sb.append("%20");
			else
				sb.append(str.charAt(i));
		}
		return sb.toString();
	}
}//class end
