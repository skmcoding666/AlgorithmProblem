package leetcode.easy;

import util.TimeUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定两个字符串 s 和 t，判断它们是否是同构的。
 *
 * 如果 s 中的字符可以被替换得到 t ，那么这两个字符串是同构的。
 *
 * 所有出现的字符都必须用另一个字符替换，同时保留字符的顺序。两个字符不能映射到同一个字符上，但字符可以映射自己本身。
 *
 * 示例 1:
 * 输入: s = "egg", t = "add"
 * 输出: true
 *
 * 示例 2:
 * 输入: s = "foo", t = "bar"
 * 输出: false
 *
 * 示例 3:
 * 输入: s = "paper", t = "title"
 * 输出: true
 *
 * 说明:
 * 你可以假设 s 和 t 具有相同的长度。
 *
 */

public class Solution205 {

    private static String mockS = "We could leave the Christmas lights up 'til January And this is our place, we make the rules And there's a dazzling haze, a mysterious way about you, dear Have I known you 20 seconds or 20 years?";

    private static String mockT = "We could let our friends crash in the living room This is our place, we make the call And I'm highly suspicious that everyone who sees you wants you I've loved you three summers now, honey, but I";

    public static void main(String[] args) {
        System.out.println(new TimeUtil().CalculationTime("My approach", isIsomorphic(mockS, mockT)));
        System.out.println(new TimeUtil().CalculationTime("Official approach", isIsomorphicOfficial(mockS, mockT)));
    }
    // 思路:将其中一个字符串离散化( "abcdadbc" -> [[a:0],[b:1],[c:2],[d:3]] -> [0,1,2,3,0,3,1,2] )
    // 然后将另一个字符串依法炮制,过程中不断对比前一个离散化后的数组 -> 有不一样的则说明非同构 -> return false
    private static boolean isIsomorphic(String s, String t) {
        if(s.length() == 0) {
            return true;
        }
        // map维护字符对应离散数字的字典
        char[] charS = s.toCharArray();
        int[] discrete = new int[charS.length];
        Map<Character, Integer> wordMap = new HashMap<>();
        int tempNum = 0;
        for(int i = 0; i < charS.length; i++) {
            if(wordMap.containsKey(charS[i])) {
                discrete[i] = wordMap.get(charS[i]);
            } else {
                wordMap.put(charS[i], tempNum);
                discrete[i] = tempNum++;
            }
        }
        char[] charT = t.toCharArray();
        wordMap = new HashMap<>();
        tempNum = 0;
        // 第二个字符串在离散过程中只需要跟前一个比较即可,不一样则返回 false
        for(int i = 0; i < charT.length; i++) {
            if(wordMap.containsKey(charT[i])) {
                if(discrete[i] != wordMap.get(charT[i])) {
                    return false;
                }
            } else {
                if(discrete[i] != tempNum) {
                    return false;
                }
                wordMap.put(charT[i], tempNum);
                tempNum++;
            }
        }
        return true;
    }

    // 后来看了官方题解才想到为什么要先做一个再做另一个,两个一起做它不香吗...
    private static boolean isIsomorphicOfficial(String s, String t) {
        int len = s.length();
        // 官方题解直接用的CharAt,我这里转了数组
        char[] charS = s.toCharArray();
        char[] charT = t.toCharArray();
        // s -> t 字符串s 对应 字符串t 的字符
        // s: "123a" t: "qwe1"
        // s2t: [[1:q],[2:w],[3:e],[a:1]] t2s: [[q:1],[w:2],[e:3],[1:a]]
        Map<Character, Character> s2t = new HashMap<>();
        Map<Character, Character> t2s = new HashMap<>();
        for(int i = 0; i < len; i++) {
            char tempS = charS[i];
            char tempT = charT[i];
            if((s2t.containsKey(tempS) && s2t.get(tempS) != tempT) || (t2s.containsKey(tempT) && t2s.get(tempT) != tempS)) {
                return false;
            }
            s2t.put(tempS, tempT);
            t2s.put(tempT, tempS);
        }
        return true;
    }
}
