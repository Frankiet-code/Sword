package sword;

import java.util.ArrayList;

/**
 * @Author: lei
 * @Data: 2020.4.1 21:21
 * @Description: 字符串排列
 * 输入一个字符串,按字典序打印出该字符串中字符的所有排列。
 * 例如输入字符串abc,则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
 * 输入一个字符串,长度不超过9(可能有字符重复),字符只包括大小写字母。
 */
public class test27 {
	public static void main(String[] args) {
		String str = "abc";
		System.out.println(Permutation(str));
	}
	
	public static ArrayList<String> Permutation(String str) {
		ArrayList<String> res = new ArrayList<>();
		if(str == null || str.length() == 0) return res;
		if(str.length() == 1) {
			res.add(str);
			return res;
		}
		String str_sub = str.substring(0, str.length()-1);
		char c = str.charAt(str.length() - 1);
		return helper(Permutation(str_sub), c);
	}
	
	public static ArrayList<String> helper(ArrayList<String> list, char c){
		ArrayList<String> res = new ArrayList<>();
		for(String string : list){
			for(int i = 0;i <= string.length();i++){
				StringBuilder sb = new StringBuilder(string);
				sb.insert(i, c);  //不同的位置上插入字符 c
				if(!res.contains(sb.toString()))
					res.add(sb.toString());
			}
		}
		//按照字典顺序进行排序
		for(int i = 0;i < res.size();i++){
			for(int j = i+1;j < res.size();j++){
				if(res.get(i).compareTo(res.get(j)) > 0){
					String temp = res.get(i);
					res.set(i, res.get(j));
					res.set(j, temp);
				}
			}
		}
		return res;
	}
}//class end
