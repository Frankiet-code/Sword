package sword;

/**
 * @Author: lei
 * @Data: 2020.4.1 11:26
 * @Description: 整数中1出现的次数
 * 求出1~13的整数中1出现的次数,并算出100~1300的整数中1出现的次数？
 * 为此他特别数了一下1~13中包含1的数字有1、10、11、12、13因此共出现6次,但是对于后面问题他就没辙了。
 * ACMer希望你们帮帮他,并把问题更加普遍化,可以很快的求出任意非负整数区间中1出现的次数（从1 到 n 中1出现的次数）。
 */
public class test31 {
	public static void main(String[] args) {
		System.out.println(NumberOf1Between1AndN_Solution(13)); //6
	}
	
	//计算1~n中每个数字中1出现的次数，然后累加
	public static int NumberOf1Between1AndN_Solution(int n) {
		if(n <= 0) return 0;
		int count = 0;
		for(int i = 1;i <= n;i++){
			count += get(i);
		}
		return count;
	}
	
	//单个数字中 1 的个数
	private static int get(int i) {
		int count = 0;
		while(i != 0){
			if(i % 10 == 1)
				count++;
			i = i / 10;
		}
		return count;
	}
}//class end
