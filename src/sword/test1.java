package sword;

/**
 * @Author: lei
 * @Data: 2020.3.30 21:11
 * @Description: 二维数组中的查找
 * 在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，
 * 一列都按照从上到下递增的顺序排序。
 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 */
public class test1 {
	public static void main(String[] args) {
		int [][] nums = {
				{1, 2, 3, 4, 6},
				{3, 4, 6, 6, 7},
				{4, 6, 7, 9, 10}
		};
		System.out.println(Find(5, nums));  //false
		System.out.println(Find(9, nums));  //true
	}
	
	//思路：从右上开始查找，如果碰到的数比自己大，就去左边的一列找；
	// 如果碰到的数比自己小的，就去下面一行找。如果找到了左下角还没找到，就说明不在。
	// 当然，从左下开始找也是类似的思路。
	public static boolean Find(int target, int [][] array) {
		if(array == null || array.length == 0 || array[0].length == 0) return false;
		int row = 0, col = array[0].length - 1;
		while(row < array.length && col >= 0){
			if(array[row][col] == target) return true;
			else if(array[row][col] > target) col--;
			else row ++;
		}
		return false;
	}
}//class end
