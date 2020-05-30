package sword;

/**
 * @Author: lei
 * @Data: 2020.4.5 13:51
 * @Description: 机器人的运动范围
 * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。
 * 路径可以从矩阵中的任意一个格子开始，每一步可以在矩阵中向左，向右，向上，向下移动一个格子。
 * 如果一条路径经过了矩阵中的某一个格子，则该路径不能再进入该格子。
 * 例如:
 * a b c e
 * s f c s
 * a d e e
 * 矩阵中包含一条字符串"bcced"的路径，但是矩阵中不包含"abcb"路径，
 * 因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入该格子。
 */
public class test65 {
	public static void main(String[] args) {
		char [] matrix = {'a', 'b', 'c', 'e', 's', 'f', 'c', 's', 'a', 'd', 'e', 'e'};
		char [] str = {'a', 'b', 'c','b'};
		System.out.println(hasPath(matrix, 3,4, str));
	}
	
	public static boolean hasPath(char[] matrix, int rows, int cols, char[] str){
		char [][] mat = new char[rows][cols];
		boolean [][] visit = new boolean[rows][cols];
		//转换为二维数组
		for(int i = 0;i < rows;i++){
			for(int j = 0;j < cols;j++)
				mat[i][j] = matrix[i * cols + j];
		}
		//从其中的每一个节点出发，看是否能找到这样的路径
		for(int i = 0;i < rows;i++){
			for(int j = 0;j < cols;j++)
				if(helper(mat, i, j, str, visit, 0))
					return true;
		}
		return false;
	}
	
	//index代表正在寻找str中的第index元素
	private static boolean helper(char[][] mat, int x, int y, char[] str, boolean[][] visit, int index) {
		if(index == str.length) return true;  //到达末尾的时候，返回true
		if(x < 0 || x >= mat.length || y < 0 || y >= mat[0].length || visit[x][y] || mat[x][y] != str[index])
			return false;  //(x, y)坐标越界、已经访问过了、值不匹配。都返回false
		visit[x][y] = true;
		boolean res = (helper(mat, x-1, y, str, visit, index+1) ||
				       helper(mat, x+1, y, str, visit, index+1) ||
				       helper(mat, x, y+1, str, visit, index+1) ||
				       helper(mat, x, y-1, str, visit, index+1));
		if(!res) visit[x][y] = false;  //从某个点出发没有得到true，就置为null，以免影响其他开始路径下的遍历
		return res;
	}
}//class end
