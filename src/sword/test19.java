package sword;

import java.util.ArrayList;

/**
 * @Author: lei
 * @Data: 2020.3.31 14:31
 * @Description: 顺时针打印矩阵
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，
 * 例如，如果输入如下4 X 4矩阵：
 * 1   2    3    4
 * 5   6     7   8
 * 9   10  11 12
 * 13 14  15 16 则依次打印出数字 1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10
 */
public class test19 {
	public static void main(String[] args) {
		int [][] nums = {
				{1,  2,  3,  4},
				{5,  6,  7,  8},
				{9,  10, 11, 12},
				{13, 14, 15, 16}};
		ArrayList<Integer> list = printMatrix(nums);
		System.out.println(list);    //[1, 2, 3, 4, 8, 12, 16, 15, 14, 13, 9, 5, 6, 7, 11, 10]
	}
	
	public static ArrayList<Integer> printMatrix(int [][] matrix) {
		ArrayList<Integer> res = new ArrayList<>();
		int rows = matrix.length, cols = matrix[0].length;  //总的行数和列数
		print(matrix, 0, 0, rows-1, cols-1, res);
		return res;
	}
	
	public static void print(int [][] a, int row_start, int col_start, int row_end, int col_end, ArrayList<Integer> res){
		if(row_start > row_end || col_start > col_end) return;
		int i = row_start, j = col_start;
		//单独考虑只有一行或者一列的场景
		if(row_start == row_end){
			while(j <= col_end) {
				res.add(a[row_start][j]);j++;
			}
		}
		else if(col_start == col_end){
			while(i <= row_end){
				res.add(a[i][col_start]);i++;
			}
		}
		else {
			while(j < col_end){
				res.add(a[i][j]);j++;
			}
			while(i < row_end){
				res.add(a[i][j]);i++;
			}
			while(j > col_start){
				res.add(a[i][j]);j--;
			}
			while(i > row_start){
				res.add(a[i][j]);i--;
			}
			print(a, row_start+1,col_start+1, row_end-1, col_end-1, res);
		}
	}
}//class end
