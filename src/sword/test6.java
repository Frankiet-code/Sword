package sword;

/**
 * @Author: lei
 * @Data: 2020.3.30 22:16
 * @Description: 选择数组中的的最小数字
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 * 输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。
 * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
 * NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
 */
public class test6 {
	public static void main(String[] args) {
		int[] nums = {3,4,5,1,1,2};
		System.out.println(minNumberInRotateArray_2(nums));  // 1
	}
	
	//方式1：从头开始遍历，时间复杂度 N
	public static int minNumberInRotateArray(int [] array) {
		if(array == null || array.length == 0) return 0;
		for(int i = 0;i < array.length-1;i++)
			if(array[i] > array[i+1])
				return array[i+1];
		return array[0];
	}
	
	//方式2：使用二分查找，时间复杂度 LogN
	public static int minNumberInRotateArray_2(int [] array) {
		if(array == null || array.length == 0) return 0;
		int left = 0, right = array.length-1;
		while(left < right){
			if(array[left] < array[right])
				return array[left];          //left和 right之间已经是非递减排序 1 1 2 3 3 4 5 5
			
			int mid = left + (right-left)/2;
			if(array[mid] < array[right])
				right = mid;                 //mid的右边是非递减排序：4 5 1* 2 3  （1*为中间点）
			else if(array[left] < array[mid])
				left = mid;                   //mid的左边是非递减排序：3 4 5* 1 2  （5*为中间点）
			else
				left++;                       //否则，例如：3 4 5* 5 5这些情况，就只缩小范围。
		}
		return array[left];
	}
}//class end
