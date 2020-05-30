package sword;

import java.util.ArrayList;

/**
 * @Author: lei
 * @Data: 2020.4.5 12:54
 * @Description: 数据流中的中位数
 * 如何得到一个数据流中的中位数？
 * 如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。
 * 如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
 * 我们使用Insert()方法读取数据流，使用GetMedian()方法获取当前读取数据的中位数。
 */
public class test63 {
	public static void main(String[] args) {
		Insert(0);
		Insert(4);
		Insert(3);
		System.out.println(GetMedian());  //3.0
		Insert(1);
		System.out.println(GetMedian());  //2.0
		System.out.println(list);    //[0, 1, 3, 4]
	}
	
	static ArrayList<Integer> list = new ArrayList<>();
	
	//从小到大排好序
	public static void Insert(Integer num) {
		int i = 0;
		while(i < list.size() && num > list.get(i))
			i++;
		list.add(i, num);
	}
	
	public static Double GetMedian() {
		if(list.size() % 2 == 0)
			return (list.get(list.size()/2-1) + list.get(list.size()/2)) / 2.00;
		else
			return list.get(list.size()/2) * 1.00;
	}
}//class end
