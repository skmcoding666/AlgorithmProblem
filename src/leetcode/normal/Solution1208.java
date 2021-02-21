package leetcode.normal;

/**
 * 给你两个长度相同的字符串，s 和 t。
 *
 * 将 s 中的第 i 个字符变到 t 中的第 i 个字符需要 |s[i] - t[i]| 的开销（开销可能为 0），也就是两个字符的 ASCII 码值的差的绝对值。
 *
 * 用于变更字符串的最大预算是 maxCost。在转化字符串时，总开销应当小于等于该预算，这也意味着字符串的转化可能是不完全的。
 *
 * 如果你可以将 s 的子字符串转化为它在 t 中对应的子字符串，则返回可以转化的最大长度。
 *
 * 如果 s 中没有子字符串可以转化成 t 中对应的子字符串，则返回 0。
 *
 * 示例 1：
 * 输入：s = "abcd", t = "bcdf", cost = 3
 * 输出：3
 * 解释：s 中的 "abc" 可以变为 "bcd"。开销为 3，所以最大长度为 3。
 *
 * 示例 2：
 * 输入：s = "abcd", t = "cdef", cost = 3
 * 输出：1
 * 解释：s 中的任一字符要想变成 t 中对应的字符，其开销都是 2。因此，最大长度为 1。
 *
 * 示例 3：
 * 输入：s = "abcd", t = "acde", cost = 0
 * 输出：1
 * 解释：你无法作出任何改动，所以最大长度为 1。
 *
 * 提示：
 * 1 <= s.length, t.length <= 10^5
 * 0 <= maxCost <= 10^6
 * s 和 t 都只含小写英文字母。
 */

public class Solution1208 {

    private static String mockS = "abcd";

    private static String mockT = "cdef";

    private static int mockMaxCost = 3;

    public static void main(String[] args) {
        System.out.println(equalSubstring(mockS, mockT, mockMaxCost));
    }

    // 思路: 滑动窗口
    private static int equalSubstring(String s, String t, int maxCost) {
        int len = s.length();
        int[] cost = new int[len];
        // 预处理, 算出每一个元素转化的开销
        for(int i = 0; i < len; i++) {
            cost[i] = Math.abs(s.charAt(i) - t.charAt(i));
        }
        int result = 0;
        int tempCost = 0;
        int left = 0;
        int right = 0;
        // 双指针循环, 遍历算出每一块不超过开销的窗口大小
        for(int i = 0; i < len; i++) {
            tempCost += cost[i];
            right++;
            if(tempCost > maxCost) {
                // 若超过, 则符合条件的窗口即为 [left, right)
                result = Math.max(result, right - left - 1);
                // 要继续计算下一个符合的窗口, 所以从左侧开始一个一个删除元素, 直至再次小于开销
                while(tempCost > maxCost && left < right) {
                    tempCost -= cost[left++];
                }
            }
        }
        // 需要注意遍历到最后仍然小于开销的场合
        return Math.max(result, right - left);
    }
}