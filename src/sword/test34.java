package sword;

import java.util.HashMap;

/**
 * @Author: lei
 * @Data: 2020.4.1 13:07
 * @Description: 第一个只出现一次的字符
 * 在一个字符串(0<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,并返回它的位置,
 * 如果没有则返回 -1（需要区分大小写）.
 */
public class test34 {
	public static void main(String[] args) {
		String str = "hellohellokmmno";
		System.out.println(FirstNotRepeatingChar(str)); //10
	}
	
	//思路：遍历两次字符串。第一次记录每个字符出现的次数，第二次找最先出现1次的那个字符；
	public static int FirstNotRepeatingChar(String str) {
		if(str == null || str.length() == 0) return -1;
		HashMap<Character, Integer> map = new HashMap<>();
		for(int i = 0;i < str.length();i++){
			char ch = str.charAt(i);
			if(map.containsKey(ch))
				map.put(ch, map.get(ch) + 1);
			else
				map.put(ch, 1);
		}
		for(int i = 0;i < str.length();i++){
			if(map.get(str.charAt(i)) == 1)
				return i;
		}
		return -1;
	}
}//class end
