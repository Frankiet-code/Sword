package sword;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @Author: lei
 * @Data: 2020.4.5 13:12
 * @Description: 滑动窗口的最大值
 * 给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值。
 * 例如，如果输入数组{2,3,4,2,6,2,5,1}及滑动窗口的大小3，那么一共存在6个滑动窗口，他们的最大值分别为{4,4,6,6,6,5}；
 * 针对数组{2,3,4,2,6,2,5,1}的滑动窗口有以下6个：
 * {[2,3,4],2,6,2,5,1}， {2,[3,4,2],6,2,5,1}， {2,3,[4,2,6],2,5,1}，
 * {2,3,4,[2,6,2],5,1}， {2,3,4,2,[6,2,5],1}， {2,3,4,2,6,[2,5,1]}。
 */
public class test64 {
	public static void main(String[] args) {
		int [] num = {2,3,4,2,6,2,5,1};
		System.out.println(maxInWindows(num, 3));
	}
	
	//思路：使用一个列表存放当前进来的最大的几个元素的下标
	public static ArrayList<Integer> maxInWindows(int [] num, int size){
		ArrayList<Integer> res = new ArrayList<>();
		if(num == null || num.length == 0 || size == 0) return res;
		LinkedList<Integer> list = new LinkedList<>();
		for(int i = 0;i < num.length;i++){
			while(!list.isEmpty() && num[i] > num[list.peekLast()])
				list.removeLast();     //移除掉较小的元素
			list.add(i);
			if(i + 1 >= size)          //当i到size-1的时候，代表扫过了size个元素，开始出现第一个需要添加的值
				res.add(num[list.peekFirst()]);
			if(i - list.peekFirst() == size - 1)  //第一个序号不在窗口内，过期了需要删除
				list.removeFirst();
		}
		return res;
	}
	
	//暴力解
	public static ArrayList<Integer> maxInWindows_2(int [] num, int size){
		ArrayList<Integer> res = new ArrayList<>();
		//size超出了数组长度时
		if(size > num.length || size < 1)
			return res;
		for(int i = 0;i < num.length - size + 1;i++){
			int max = Integer.MIN_VALUE;
			for(int j = i;j < i + size;j++)
				max = Math.max(max, num[j]);
			res.add(max);
		}
		return res;
	}
}//class end
