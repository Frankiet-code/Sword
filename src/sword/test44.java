package sword;

/**
 * @Author: lei
 * @Data: 2020.4.1 16:57
 * @Description: 翻转单词顺序列
 * 牛客最近来了一个新员工Fish，每天早晨总是会拿着一本英文杂志，写些句子在本子上。
 * 同事Cat对Fish写的内容颇感兴趣，有一天他向Fish借来翻看，但却读不懂它的意思。
 * 例如，“student. a am I”。后来才意识到，这家伙原来把句子单词的顺序翻转了，正确的句子应该是“I am a student.”。
 * Cat对一一的翻转这些单词顺序可不在行，你能帮助他么？
 */
public class test44 {
	public static void main(String[] args) {
		String s = "student. a am I";
		System.out.println(ReverseSentence(s)); //I am a student.
	}
	
	//使用split函数进行划分
	public static String ReverseSentence(String str) {
		if(str == null || str.length() == 0) return "";  //应付null 或者 ""
		String [] strings = str.split(" ");
		if(strings.length == 0) return str;  //应付全是空格的输入str
		StringBuilder sb = new StringBuilder();
		for(int i = strings.length-1;i >= 0;i--){
			sb.append(strings[i]);
			if(i != 0) sb.append(" ");
		}
		return sb.toString();
	}
}//class end
