package leetcode.easy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 给定一种规律 pattern 和一个字符串 str ，判断 str 是否遵循相同的规律。
 *
 * 这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应规律。
 *
 * 示例1:
 *
 * 输入: pattern = "abba", str = "dog cat cat dog"
 * 输出: true
 * 示例 2:
 *
 * 输入:pattern = "abba", str = "dog cat cat fish"
 * 输出: false
 * 示例 3:
 *
 * 输入: pattern = "aaaa", str = "dog cat cat dog"
 * 输出: false
 * 示例 4:
 *
 * 输入: pattern = "abba", str = "dog dog dog dog"
 * 输出: false
 * 说明:
 * 你可以假设 pattern 只包含小写字母， str 包含了由单个空格分隔的小写字母。    
 *
 */

public class Solution290 {

    private static String pattern = "aabca";

    private static String str = "dog dog cat mouse dog";

    public static void main(String[] args) {
        System.out.println(wordPattern(pattern, str));
    }

    // 简单思路,遍历找不同
    private static boolean wordPattern(String pattern, String s) {
        char[] c = pattern.toCharArray();
        String[] words = s.split(" ");
        // 坑No.1 会有长度不同的错误数据
        if(c.length != words.length) {
            return false;
        }
        Map<Character, String> map = new HashMap<>();
        Set<String> wordSet = new HashSet<>();
        for(int i = 0; i < c.length; i++) {
            if(map.containsKey(c[i]) && !map.get(c[i]).equals(words[i])) {
                    return false;
            }
            // Set去重,针对 a->dog b->dog 这样的错误数据
            if(!wordSet.add(words[i])) {
                    return false;
            }
            map.put(c[i], words[i]);
        }
        return true;
    }

}
