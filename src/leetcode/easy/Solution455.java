package leetcode.easy;

import java.util.Arrays;

/**
 * 假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。
 *
 * 对每个孩子 i，都有一个胃口值 g[i]，这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干 j，都有一个尺寸 s[j] 。如果 s[j] >= g[i]，我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。
 *
 * 示例 1:
 * 输入: g = [1,2,3], s = [1,1]
 * 输出: 1
 * 解释:
 * 你有三个孩子和两块小饼干，3个孩子的胃口值分别是：1,2,3。
 * 虽然你有两块小饼干，由于他们的尺寸都是1，你只能让胃口值是1的孩子满足。
 * 所以你应该输出1。
 *
 * 示例 2:
 * 输入: g = [1,2], s = [1,2,3]
 * 输出: 2
 * 解释:
 * 你有两个孩子和三块小饼干，2个孩子的胃口值分别是1,2。
 * 你拥有的饼干数量和尺寸都足以让所有孩子满足。
 * 所以你应该输出2.
 *
 */
public class Solution455 {

    private static int[] mockG = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    private static int[] mockS = {10, 11, 12, 3, 4, 5};

    public static void main(String[] args) {
        System.out.println(findContentChildren(mockG, mockS));
    }

    // 思路: 排序+贪心
    private static int findContentChildren(int[] g, int[] s) {
        // 首先将两个数组都排好序
        Arrays.sort(g);
        Arrays.sort(s);
        int result = 0;
        int indexG = 0;
        int indexS = 0;
        // 循环遍历数组s,对每个 g 找到其最小 s
        while(indexG < g.length && indexS < s.length) {
            // 当前 g <= s -> 满足条件,开始为下一个 g 找 s
            if(g[indexG] <= s[indexS]) {
                indexG++;
                result++;
            }
            indexS++;
        }
        return result;
    }
}
