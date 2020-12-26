package leetcode.easy;

import java.util.HashSet;

/**
 *给定一个整数数组，判断是否存在重复元素。
 *
 * 如果任意一值在数组中出现至少两次，函数返回 true 。如果数组中每个元素都不相同，则返回 false 。
 *
 * 示例 1:
 * 输入: [1,2,3,1]
 * 输出: true
 *
 * 示例 2:
 * 输入: [1,2,3,4]
 * 输出: false
 *
 * 示例 3:
 * 输入: [1,1,1,3,3,4,3,2,4,2]
 * 输出: true
 *
 */

public class Solution217 {

    private static int[] mockArray = {55, 56, 57, 58, 59, 60, 55};

    public static void main(String[] args) {
        System.out.println(containsDuplicate(mockArray));
    }

    private static boolean containsDuplicate(int[] nums) {
        if(nums.length == 0) {
            return false;
        }
        // 利用Set去重机制判断是否存在重复元素
        HashSet<Integer> set = new HashSet<>();
        for(int n : nums) {
            // 当存在同样值的时候 add方法会返回false,以此优化流程
            if(!set.add(n)) {
                return true;
            }
        }
        return false;
    }
}
