package leetcode.easy;

import java.util.Arrays;

/**
 * 给你一个整型数组 nums ，在数组中找出由三个数组成的最大乘积，并输出这个乘积。
 *
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：6
 *
 * 示例 2：
 * 输入：nums = [1,2,3,4]
 * 输出：24
 *
 * 示例 3：
 * 输入：nums = [-1,-2,-3]
 * 输出：-6
 *
 * 提示：
 * 3 <= nums.length <= 104
 * -1000 <= nums[i] <= 1000
 */

public class Solution628 {

    private static int[] mockNums = {-1,-2,-3,-4,-5};

    public static void main(String[] args) {
        System.out.println(maximumProduct(mockNums));
    }

    private static int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        //两种情况: 均为同符号数字, 正负皆有 -> 两个最小的负数*最大的正数 / 最大的三个正数
        return Math.max(nums[0] * nums[1] * nums[n - 1], nums[n - 3] * nums[n - 2] * nums[n - 1]);
    }
}
