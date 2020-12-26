package leetcode.normal;

/**
 * 给定一个 n × n 的二维矩阵表示一个图像。
 *
 * 将图像顺时针旋转 90 度。
 *
 * 说明：
 *
 * 你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。
 *
 * 示例 1:
 * 给定 matrix =
 * [
 *   [1,2,3],
 *   [4,5,6],
 *   [7,8,9]
 * ],
 *
 * 原地旋转输入矩阵，使其变为:
 * [
 *   [7,4,1],
 *   [8,5,2],
 *   [9,6,3]
 * ]
 *
 * 示例 2:
 * 给定 matrix =
 * [
 *   [ 5, 1, 9,11],
 *   [ 2, 4, 8,10],
 *   [13, 3, 6, 7],
 *   [15,14,12,16]
 * ],
 *
 * 原地旋转输入矩阵，使其变为:
 * [
 *   [15,13, 2, 5],
 *   [14, 3, 4, 1],
 *   [12, 6, 8, 9],
 *   [16, 7,10,11]
 * ]
 *
 */

public class Solution48 {

    private static int[][] mockArray = new int[18][18];

    static {
        int tempNum = 1;
        for(int[] temp: mockArray) {
            for(int i = 0; i < temp.length; i++) {
                temp[i] = tempNum++;
            }
        }
    }

    public static void main(String[] args) {
        rotate(mockArray);
        showArray(mockArray);
    }

    // 思路:分层旋转,先旋转最内层的, 然后往外一层一层旋转
    private static void rotate(int[][] matrix) {
        outerRotation(matrix, 0, matrix.length - 1);
    }

    // x为旋转层的左上角
    // y为当前旋转层的边长-1
    // 当旋转层边长为3时,实际上只需要每边转两个,因为顶点是跨越两条边的
    private static void outerRotation(int[][] matrix, int x, int y) {
        // 当旋转层边长>2时,我们还会对其内层先旋转
        if(y > 1) {
            outerRotation(matrix, x + 1, y - 2);
        }
        // 旋转部分
        for(int i = 0; i < y; i++) {
            int temp = matrix[x][x+i]; // 首先存储旋转层上边
            matrix[x][x+i] = matrix[y+x-i][x]; // 上边的元素是左边的元素旋转过来的
            matrix[y+x-i][x] = matrix[y+x][y+x-i]; // 左边的元素是下边的元素旋转过来的
            matrix[y+x][y+x-i] = matrix[x+i][y+x]; // 下边的元素是右边的元素旋转过来的
            matrix[x+i][y+x] = temp; // 右边的元素是下边的元素旋转过来的
        }
        // 调试用,round=0时为最外层
        System.out.println("round " + x);
        showArray(mockArray);
    }

    // 工具方法,调试用,展示数组当前状态
    private static void showArray(int[][] matrix) {
        StringBuilder str = new StringBuilder();
        str.append("The Array: [\r\n");
        for(int[] temp: matrix) {
            str.append(" [");
            for(int tempNum: temp) {
                String s = " " + tempNum + ",";
                str.append(s);
            }
            str.delete(str.lastIndexOf(","), str.lastIndexOf(",") + 1);
            str.append(" ],\r\n");
        }
        str.delete(str.lastIndexOf(","), str.lastIndexOf(",") + 1);
        str.append("]");
        System.out.println(str);
    }
}
