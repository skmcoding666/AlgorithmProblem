package leetcode.normal;

import util.ArrayUtil;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 *
 * 示例:
 *
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出:
 * [
 *   ["ate","eat","tea"],
 *   ["nat","tan"],
 *   ["bat"]
 * ]
 *
 */

public class Solution49 {

    private static String[] mockArray = {"eat", "tea", "tan", "ate", "nat", "bat"};

    public static void main(String[] args) {
        ArrayUtil.showListList(groupAnagrams2(mockArray));
    }

    // 朴实无华思想,str排序做key,存入map,再导一遍,略复杂
    private static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        // map<key: 排序后的str, value: 下标List>
        Map<String, List<Integer>> map = new HashMap<>();
        for(int i = 0; i < strs.length; i++) {
            char[] chars = strs[i].toCharArray();
            Arrays.sort(chars);
            String tempKey = new String(chars);
            if(map.containsKey(tempKey)) {
                map.get(tempKey).add(i);
            } else {
                List<Integer> indexs = new ArrayList<>();
                indexs.add(i);
                map.put(tempKey, indexs);
            }
        }
        // 遍历map将对应key存入的下标List取出
        Set<String> keySet = map.keySet();
        Iterator i = keySet.iterator();
        while(i.hasNext()) {
            List<Integer> indexs = map.get(i.next());
            List<String> resultList = new ArrayList<>();
            for(int index : indexs) {
                resultList.add(strs[index]);
            }
            result.add(resultList);
        }
        return result;
    }

    private static List<List<String>> groupAnagrams2(String[] strs) {
        return new ArrayList<>(Arrays.stream(strs)
                .collect(Collectors.groupingBy(str -> {
                    // 返回 str 排序后的结果
                    // 按排序后的结果来grouping by，算子类似于 sql 里的 group by
                    char[] array = str.toCharArray();
                    Arrays.sort(array);
                    return new String(array);
                })).values());
    }

}
