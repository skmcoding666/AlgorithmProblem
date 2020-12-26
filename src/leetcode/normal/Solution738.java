package leetcode.normal;

import java.util.Arrays;

/**
 * 给定一个非负整数 N，找出小于或等于 N 的最大的整数，同时这个整数需要满足其各个位数上的数字是单调递增。
 *
 * （当且仅当每个相邻位数上的数字 x 和 y 满足 x <= y 时，我们称这个整数是单调递增的。）
 *
 * 示例 1:
 *
 * 输入: N = 10
 * 输出: 9
 * 示例 2:
 *
 * 输入: N = 1234
 * 输出: 1234
 * 示例 3:
 *
 * 输入: N = 332
 * 输出: 299
 * 说明: N 是在 [0, 10^9] 范围内的一个整数。
 *
 */

public class Solution738 {

    private static int mockNum = 509;

    public static void main(String[] args) {
        System.out.println(monotoneIncreasingDigits(mockNum));
    }

    private static int monotoneIncreasingDigits(int N) {
        // 初始化数组,按位大小存储不同位的数字,因为N<=10^9,所以10位够用
        int[] bits = new int[10];
        Arrays.fill(bits, -1);
        for(int i = 0; ; i++) {
            bits[i] = N % 10;
            N = N / 10;
            if(N == 0) {
                break;
            }
        }
        // 从左至右遍历数组,若低位小于高位,则不符合规则 -> 高位-1,低位全变成9
        for(int i = 0; i < bits.length - 1 && bits[i+1] != -1; i++) {
            if(bits[i] >= bits[i+1]) {
                continue;
            }
            bits[i+1] -= 1;
            for(int j = 0; j <= i; j++) {
                bits[j] = 9;
            }
        }
        // 组合成结果值
        int result = 0;
        int tempMultiplier = 1;
        for(int i = 0; i < bits.length && bits[i] > 0; i++) {
            result += tempMultiplier * bits[i];
            tempMultiplier *= 10;
        }
        return result;
    }
}
