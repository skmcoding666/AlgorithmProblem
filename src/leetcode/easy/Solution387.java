package leetcode.easy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 *
 * 示例：
 * s = "leetcode"
 * 返回 0
 *
 * s = "loveleetcode"
 * 返回 2
 *
 */

public class Solution387 {

    private static String mockStr = "";

    public static void main(String[] args) {
        System.out.println(firstUniqChar(mockStr));
    }

    private static int firstUniqChar(String s) {
        Map<Character, Integer> map = new HashMap<>();
        List<Character> list = new ArrayList<>();
        char[] c = s.toCharArray();
        for(int i = 0; i < c.length; i++) {
            if(map.containsKey(c[i])) {
                list.remove((Character) c[i]);
                continue;
            }
            map.put(c[i], i);
            list.add(c[i]);
        }
        if(list.get(0) == null) {
            return -1;
        }
        return map.get(list.get(0));
    }
}
