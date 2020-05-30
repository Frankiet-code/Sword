package sword;

/**
 * @Author: lei
 * @Data: 2020.4.1 11:30
 * @Description: 把数组排成最小的数整数中1出现的次数
 * 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
 * 例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323。
 */
public class test32 {
	public static void main(String[] args) {
		int [] nums = {3, 32, 321};
		System.out.println(PrintMinNumber(nums)); //321323
	}
	
	//方法1：重新定义两个数之间的比较关系，如果 3 和 32比较，因为332 > 323 所以 3 应该在23的前面
	public static String PrintMinNumber(int [] numbers) {
		for(int i = 0;i < numbers.length;i++){
			for(int j = i+1;j < numbers.length;j++){
				if(mergeToInt(numbers[i], numbers[j]) > mergeToInt(numbers[j], numbers[i])){
					int temp = numbers[i];
					numbers[i] = numbers[j];
					numbers[j] = temp;
				}
			}
		}
		StringBuffer sb = new StringBuffer();
		for(int i : numbers)
			sb.append(i);
		return sb.toString();
	}
	
	//先找出j的位数
	public static int mergeToInt(int i, int j){
		int sum = i, temp = j;
		while(temp != 0){
			temp /= 10;
			sum *= 10;
		}
		return sum+j;
	}
	
	//方法2：调用函数
	public static int mergeToInt_2(int i, int j){
		String sum = String.valueOf(i) + String.valueOf(j);
		return Integer.parseInt(sum);
	}
}//class end
