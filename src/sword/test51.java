package sword;

import java.util.Arrays;

/**
 * @Author: lei
 * @Data: 2020.4.1 20:10
 * @Description: 构建乘积数组
 * 给定一个数组A[0,1,...,n-1],请构建一个数组B[0,1,...,n-1],其中B中的元素B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。
 * 不能使用除法。（注意：规定B[0] = A[1] * A[2] * ... * A[n-1]，B[n-1] = A[0] * A[1] * ... * A[n-2];）
 */
public class test51 {
	public static void main(String[] args) {
		int [] A = {1, 2, 3, 4};
		int [] B = multiply(A);
		System.out.println(Arrays.toString(B));
	}
	
	//意思就是说 B[i] = A[0]* ... * A[i-1] *  A[i+1]* ... *A[n-1].中间缺了A[i]
	public static int[] multiply(int[] A) {
		if(A == null || A.length == 0) return A;
		int [] res = new int[A.length];
		for(int i = 0;i < A.length;i++){
			res[i] = 1;
			for(int j = 0;j < A.length;j++){
				if(j == i)continue;
				res[i] *= A[j];
			}
		}
		return res;
	}
}//class end
