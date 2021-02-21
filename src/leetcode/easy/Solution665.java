package leetcode.easy;

/**
 * 给你一个长度为 n 的整数数组，请你判断在 最多 改变 1 个元素的情况下，该数组能否变成一个非递减数列。
 *
 * 我们是这样定义一个非递减数列的： 对于数组中任意的 i (0 <= i <= n-2)，总满足 nums[i] <= nums[i + 1]。
 *
 * 示例 1:
 * 输入: nums = [4,2,3]
 * 输出: true
 * 解释: 你可以通过把第一个4变成1来使得它成为一个非递减数列。
 *
 * 示例 2:
 * 输入: nums = [4,2,1]
 * 输出: false
 * 解释: 你不能在只改变一个元素的情况下将其变为非递减数列。
 *  
 * 提示：
 * 1 <= n <= 10 ^ 4
 * - 10 ^ 5 <= nums[i] <= 10 ^ 5
 */

public class Solution665 {

    private static int[] mockNums = {1,4,1,2};

    public static void main(String[] args) {
        System.out.println(checkPossibility(mockNums));
    }

    // 思路: 一次遍历
    private static boolean checkPossibility(int[] nums) {
        int len = nums.length;
        // flag表示这一次机会还没用过
        boolean flag = true;
        // max存储数组中目前最大值, 当出现非递减情况时, 需要判断修改前一个值还是后一个值
        // 这时就需要比对后一个出现的值是否比当前最大值还要大, 如果是, 则必定要修改后一个值, 如果不是, 则修改前一个值
        // 例: [1,4,1,2] -> 比对到 [1,{4,1},2] 时, 当前 i=2, max=nums[0] (1), nums[2] (1) >= nums[0] (1)
        // 所以 nums[1] = nums[2] -> [1,{4,1},2] -> [1,{1,1},2]
        // 完成改变一个数字 使其变为非递减数列
        int max = nums[0];
        for(int i = 1; i < len; i++) {
            if(nums[i] < nums[i-1]) {
                if(!flag) {
                    return false;
                }
                if(nums[i] >= max) {
                    nums[i-1] = nums[i];
                } else {
                    nums[i] = nums[i-1];
                }
                flag = false;
            }
            max = nums[i-1];
        }
        return true;
    }
}
