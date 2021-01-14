package leetcode.difficult;

/**
 * 给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
 *
 * 示例 1：
 * 输入：matrix = [['1','0','1','0','0'],['1','0','1','1','1'],['1','1','1','1','1'],['1','0','0','1','0']]
 * ['1','0','1','0','0']
 * ['1','0','1','1','1']
 * ['1','1','1','1','1']
 * ['1','0','0','1','0']
 * 输出：6
 * 解释：最大矩形如上图所示。
 *
 * 示例 2：
 * 输入：matrix = []
 * 输出：0
 *
 * 示例 3：
 * 输入：matrix = [['0']]
 * 输出：0
 *
 * 示例 4：
 * 输入：matrix = [['1']]
 * 输出：1
 *
 * 示例 5：
 * 输入：matrix = [['0','0']]
 * 输出：0
 *
 * 提示：
 * rows == matrix.length
 * cols == matrix[0].length
 * 0 <= row, cols <= 200
 * matrix[i][j] 为 '0' 或 '1'
 *
 */

public class Solution85 {

    private static char[][] mockMatrix = {
            {'1','1','1','1','0'},
            {'1','1','1','1','1'},
            {'1','1','1','1','1'},
            {'1','0','0','1','0'}};

    public static void main(String[] args) {
        System.out.println(maximalRectangle(mockMatrix));
    }
    // 看了官方题解才写出来的代码...
    // 思路: 首先遍历数组,将每一位前面有几个'1'存在另一个int数组中
    // 接下来遍历存好的int数组,把每一个点作为右下角计算其最大面积
    private static int maximalRectangle(char[][] matrix) {
        // 获取数组的高度
        int y = matrix.length;
        if(y == 0) {
            return 0;
        }
        // 获取数组的宽度
        int x = matrix[0].length;
        int[][] counted = new int[y][x];
        // 遍历数组
        for(int i = 0; i < y; i++) {
            int count = 0;
            for(int j = 0; j < x; j++) {
                if(matrix[i][j] == '1') {
                    counted[i][j] = ++count;
                } else {
                    count = 0;
                }
            }
        }
        // ['1','0','1','0','0']      [1,0,1,0,0]
        // ['1','0','1','1','1']  ->  [1,0,1,2,3]
        // ['1','1','1','1','1']      [1,2,3,4,5]
        // ['1','0','0','1','0']      [1,0,0,1,0]
        int result = 0;
        // 因为遍历的点要做右下角,所以从数组的右下角开始遍历,后来发现没什么必要...反正都要走一遍
        for(int tempY = y - 1; tempY >= 0; tempY--) {
            for(int tempX = x - 1; tempX >= 0; tempX--) {
                if(counted[tempY][tempX] != 0) {
                    // floor -> 层数/高度
                    int floor = 0;
                    // tempWidth -> 底边长度
                    int tempWidth = counted[tempY][tempX];
                    while(tempY >= floor) {
                        // 必须算出每一个高度的面积,直到上面断档了,不能以当前点为右下角为止
                        tempWidth = Math.min(tempWidth, counted[tempY - floor][tempX]);
                        if(tempWidth == 0) {
                            break;
                        }
        // [1,2,0,1,0]      [1,0,1,2,0]        [1,0,1,2,0]        [1,0,1,2,0]        [1,2,0,1,0]        [1,2,0,|1|,0]
        // [1,0,1,2,0]      [1,0,1,2,0]        [1,0,1,2,0]        [1,0,1,2,0]        [1,0,1,|2|,0]      [1,0,1,|2|,0]
        // [1,0,0,1,0]  ->  [1,0,0,1,0]    ->  [1,0,0,1,0]    ->  [1,0,0,|1|,0]  ->  [1,0,0,|1|,0]  ->  [1,0,0,|1|,0]
        // [1,0,1,2,0]      [1,0,1,2,0]        [1,0,|1,2|,0]      [1,0,1,|2|,0]      [1,0,1,|2|,0]      [1,0,1,|2|,0]
        // [1,2,3,4,0]      [|1,2,3,4|,0]      [1,2,|3,4|,0]      [1,2,3,|4|,0]      [1,2,3,|4|,0]      [1,2,3,|4|,0]
        // 例:初始数组       高度1,面积4         高度2,面积4         高度3,面积3         高度4,面积4         高度5,面积5
                        result = Math.max(result, tempWidth * (floor + 1));
                        floor++;
                    }
                }
            }

        }
        return result;
    }

}
