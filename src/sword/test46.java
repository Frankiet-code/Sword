package sword;

import java.util.ArrayList;

/**
 * @Author: lei
 * @Data: 2020.4.1 17:14
 * @Description: 孩子们的游戏(圆圈中最后剩下的数)
 * 每年六一儿童节,牛客都会准备一些小礼物去看望孤儿院的小朋友,今年亦是如此。
 * HF作为牛客的资深元老,自然也准备了一些小游戏。其中,有个游戏是这样的:
 * 首先,让小朋友们围成一个大圈。然后,他随机指定一个数m,让编号为0的小朋友开始报数。
 * 每次喊到m-1的那个小朋友要出列唱首歌,然后可以在礼品箱中任意的挑选礼物,并且不再回到圈中,
 * 从他的下一个小朋友开始,继续0...m-1报数....
 * 这样下去....直到剩下最后一个小朋友,可以不用表演,并且拿到牛客名贵的“名侦探柯南”典藏版(名额有限哦!!^_^)。
 * 请你试着想下,哪个小朋友会得到这份礼品呢？(注：小朋友的编号是从0到n-1)
 *
 * 如果没有小朋友，请返回-1
 */
public class test46 {
	public static void main(String[] args) {
		System.out.println(LastRemaining_Solution(5, 3));  //3
	}
	
	//思路：使用链表存放n个孩子，然后cur指向后面需要出圈的孩子，初始值为-1,代表喊0的那个孩子的前面的那个孩子。
	//然后cur不断加1，直到来到了m，然后cur出圈。中间如果cur为list.size()了，就应该变为0，代表从头开始的那个孩子。
	public static int LastRemaining_Solution(int n, int m) {
		if(n < 1) return -1;
		if(n == 1) return 0;
		ArrayList<Integer> list = new ArrayList<>();
		for(int i = 0;i < n;i++){
			list.add(i);
		}
		int cur = -1;  //指向出圈的那个孩子
		while(list.size() > 1){
			for(int i = 0;i < m;i++){
				cur++;
				if(cur == list.size())
					cur = 0;  //从头开始
			}
			list.remove(cur);
			cur--;           //再从后面一个开始，相当于后面那个点喊的是-1
		}
		return list.get(0);
	}
}//class end
