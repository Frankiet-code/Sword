package sword;

/**
 * @Author: lei
 * @Data: 2020.4.5 15:00
 * @Description: 机器人的运动范围
 * 地上有一个m行和n列的方格。一个机器人从坐标0,0的格子开始移动，每一次只能向左，右，上，下四个方向移动一格，
 * 但是不能进入 行坐标 和 列坐标 的数位之和 大于k 的格子。
 * 例如，当k为18时，机器人能够进入方格（35,37），因为3+5+3+7 = 18。
 * 但是，它不能进入方格（35,38），因为3+5+3+8 = 19。请问该机器人能够达到多少个格子？
 */
public class test66 {
	public static void main(String[] args) {
		System.out.println(movingCount(10,1,100));
	}
	
	static int sum;
	
	/**
	 *
	 * @param threshold 阈值k
	 * @param rows 行数
	 * @param cols 列数
	 * @return 能够到达的格子个数
	 */
	public static int movingCount(int threshold, int rows, int cols){
		sum = 0;  //在牛客网里，需要在函数中设置sum的初值
		boolean [][] visit = new boolean[rows][cols];
		helper(0, 0, rows, cols, threshold, visit); //从 (0, 0)出发,然后找四周能够到达的点
		return sum;
	}
	
	//求两个数的每个位上的数字的和
	public static int cul(int x, int y){
		int res = 0;
		while(x != 0){
			res += x % 10;
			x /= 10;
		}
		while(y != 0){
			res += y % 10;
			y /= 10;
		}
		return res;
	}
	
	private static void helper(int x, int y, int rows, int cols, int threshold, boolean[][] visit) {
		//必须是先判断了cur，然后再才能看这个地方的visit能不能设置为true
		if(x < 0 || x >= rows || y < 0 || y >= cols || visit[x][y] || cul(x, y) > threshold) return;
		sum++; visit[x][y] = true;
		//向四周扩散
		helper(x+1, y, rows,cols,threshold,visit);
		helper(x-1, y, rows,cols,threshold,visit);
		helper(x, y-1, rows,cols,threshold,visit);
		helper(x, y+1, rows,cols,threshold,visit);
	}
}//class end
