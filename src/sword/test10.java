package sword;

/**
 * @Author: lei
 * @Data: 2020.3.30 23:00
 * @Description: 矩形覆盖
 * 我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
 * 比如n=3时，2*3的矩形块有3种覆盖方法：
 */
public class test10 {
	public static void main(String[] args) {
		for(int i = 1;i < 10;i++)
			System.out.print(RectCover(i) + " "); //
	}
	
	//找到递归式：f(n) = f(n-1) + f(n-2)，就是说放第n个块的时候，前面的n-1个块中的最后一个可能使竖着的，也可能使横着的。
	//如果是竖着的，那么这n-1块可以完整覆盖2*(n-1)的大矩阵，就是f(n-1)次方法；
	//如果是横着的，那么前n-2块可以完整覆盖2*(n-2)的大矩阵，因为第n-1块是横着的，无法单独参与到覆盖中去，所以只能是f(n-2)种方法。
	public static int RectCover(int target) {
		if(target == 0) return 0;
		if(target == 1) return 1;
		if(target == 2) return 2;
		if(target == 3) return 3;
		int res = 0, first = 1, second = 2;
		while(target > 2){
			res = first + second;
			first = second;
			second = res;
			target--;
		}
		return res;
	}
}//class end
