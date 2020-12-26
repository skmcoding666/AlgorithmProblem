package leetcode.normal;

/**
 * 给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
 * 注意：该题与 1081 https://leetcode-cn.com/problems/smallest-subsequence-of-distinct-characters 相同
 *
 * 返回字符串 text 中按字典序排列最小的子序列，该子序列包含 text 中所有不同字符一次。
 *
 * 示例 1：
 * 输入："cdadabcc"
 * 输出："adbc"
 *
 * 示例 2：
 * 输入："abcd"
 * 输出："abcd"
 *
 * 示例 3：
 * 输入："ecbacba"
 * 输出："eacb"
 *
 * 示例 4：
 * 输入："leetcode"
 * 输出："letcod"
 *
 * 提示：
 * 1 <= text.length <= 1000
 * text 由小写英文字母组成
 *
 */

public class Solution316 {

    private static String str = "edebbed";

    public static void main(String[] args) {
        System.out.println(removeDuplicateLetters(str));
    }

    private static String removeDuplicateLetters(String s) {
        // 作栈用,先进先出原则,结果存放
        StringBuilder str = new StringBuilder();
        // 26个字母是否在栈中的标志位
        boolean[] vis = new boolean[26];
        // 26个字符在字符串中的剩余个数
        int[] count = new int[26];
        // 统计字符个数,存入 count 数组
        char[] letterArray = s.toCharArray();
        for(char letter: letterArray) {
            count[letter - 'a']++;
        }
        // 遍历整个字符串
        for(char letter: letterArray) {
            // 如果不在栈内,则应入栈
            if(!vis[letter - 'a']) {
                vis[letter - 'a'] = true;
                // 入栈前为保持最小字典序,循环判断栈顶元素(栈顶元素>当前元素 && 栈顶元素后面还有) -> 删除栈顶元素,直至不满足括号内条件
                while(str.length() > 0 && str.charAt(str.length() - 1) > letter && count[str.charAt(str.length() - 1) - 'a'] > 0) {
                    // 删除栈内元素的同时,需要维护此 boolean 数组以作判断
                    vis[str.charAt(str.length() - 1) - 'a'] = false;
                    str.deleteCharAt(str.length() - 1);
                }
                // 当前元素入栈;
                str.append(letter);
                // 当前元素剩余数量--
                count[letter - 'a']--;
            } else {
                // 如果在栈内,也不需要判断什么,当前元素剩余数量--
                count[letter - 'a']--;
            }
        }
        // 整个字符串遍历结束后即可得到结果
        return str.toString();
    }
}
