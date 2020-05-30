package sword;

import java.util.ArrayList;

/**
 * @Author: lei
 * @Data: 2020.4.1 15:57
 * @Description: 和为S的连续正数序列
 * 小明很喜欢数学,有一天他在做数学作业时,要求计算出9~16的和,他马上就写出了正确答案是100。
 * 但是他并不满足于此,他在想究竟有多少种连续的正数序列的和为100(至少包括两个数)。
 * 没多久,他就得到另一组连续正数和为100的序列:18,19,20,21,22。
 * 现在把问题交给你,你能不能也很快的找出所有和为S的连续正数序列? Good Luck!
 * 输出描述：输出所有和为S的连续正数序列。序列内按照从小至大的顺序，序列间按照开始数字从小到大的顺序
 */
public class test41 {
	public static void main(String[] args) {
		ArrayList<ArrayList<Integer>> res = FindContinuousSequence_2(100);
		System.out.println(res); //[[9, 10, 11, 12, 13, 14, 15, 16], [18, 19, 20, 21, 22]]
	}
	
	//第一种方式：根据等差数列的求和公式，来找合法的a0
	public static ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<>();
		for(int n = 2;n < sum;n++){
			if((2 * sum - n * n + n) % (2 * n) == 0){
				int a0 = (2 * sum - n * n + n) / (2 * n);
				if(a0 <= 0) continue;   //排除负数
				int temp = n;
				ArrayList<Integer> list = new ArrayList<>();
				while(temp != 0){
					list.add(a0++);
					temp--;
				}
				res.add(0,list);  //因为我们是按照n的从小到大做的，而n越大，代表其中的数字越多，开始数字越小，就应该放前面
			}
		}
		return res;
	}
	
	//第二种方式：滑动窗口的做法
	public static ArrayList<ArrayList<Integer>> FindContinuousSequence_2(int sum) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<>();
		int left = 1, right = 2;  //因为至少是两个数字
		while(left < right){
			int sum0 = (left + right) * (right - left + 1) / 2;  //等差数列求和
			if(sum0 < sum)
				right++;
			else if(sum0 > sum)
				left++;
			else{
				ArrayList<Integer> list = new ArrayList<>();
				for(int i = left;i <= right;i++)
					list.add(i);
				left++; right++;
				res.add(list);
			}
		}
		return res;
	}
}//class end
