package sword;

/**
 * @Author: lei
 * @Data: 2020.4.1 14:57
 * @Description: 数字在排序数组中出现的次数
 * 统计一个数字在排序数组中出现的次数。
 */
public class test37 {
	public static void main(String[] args) {
		int [] nums = {1,2,3,3,4,4,4,5,6};
		System.out.println(GetNumberOfK_2(nums, 4)); //3
	}
	
	//从头开始检索，时间复杂度为N
	public static int GetNumberOfK_1(int [] array , int k) {
		if(array == null || array.length == 0)
			return 0;
		int sum = 0;
		for(int i = 0;i < array.length;i++){
			if(array[i] == k)
				sum++;
			if(sum != 0 && array[i] != k)
				break;
		}
		return sum;
	}
	
	//因为排序好了的，所以还可以二分查找,时间复杂度为logN * O(sum)
	public static int GetNumberOfK_2(int [] array , int k) {
		if(array == null || array.length == 0)
			return 0;
		int sum = 0, l = 0, r = array.length-1;
		while(l <= r){
			int mid = l + (r - l) / 2;
			if(array[mid] > k)
				r--;
			else if(array[mid] < k)
				l++;
			else{
				int left = mid-1, right = mid;
				while(left >= 0){
					if(array[left--] == k)
						sum++;
					else
						break;
				}
				while(right <= array.length-1){
					if(array[right++] == k)
						sum++;
					else
						break;
				}
				return sum;
			}
		}
		return 0;
	}
}//class end
