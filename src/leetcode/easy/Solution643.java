package leetcode.easy;

import util.TimeUtil;

/**
 * 给定 n 个整数，找出平均数最大且长度为 k 的连续子数组，并输出该最大平均数。
 *
 * 示例：
 * 输入：[1,12,-5,-6,50,3], k = 4
 * 输出：12.75
 * 解释：最大平均数 (12-5-6+50)/4 = 51/4 = 12.75
 *  
 * 提示：
 * 1 <= k <= n <= 30,000。
 * 所给数据范围 [-10,000，10,000]。
 */

public class Solution643 {

    private static int[] mockNums = {1,12,-5,-6,50,3};

    private static int mockK = 4;

    public static void main(String[] args) {
        System.out.println(new TimeUtil().CalculationTime("滑动窗口: ", findMaxAverage(mockNums, mockK)));
        System.out.println(new TimeUtil().CalculationTime("PreNum: ", findMaxAveragePreNum(mockNums, mockK)));
    }

    // 思路: 滑动窗口, 首先计算出第一个区间和, 然后通过减掉尾部, 加上首部以移动窗口, 比较区间值
    private static double findMaxAverage(int[] nums, int k) {
        int tempSum = 0;
        for(int i = 0; i < k; i++) {
            tempSum += nums[i];
        }
        int maxSum = tempSum;
        for(int i = k; i < nums.length; i++) {
            tempSum -= nums[i-k];
            tempSum += nums[i];
            maxSum = Math.max(tempSum, maxSum);
        }
        return 1.0 * maxSum / k;
    }

    // 思路: PreNum, 把数组中每一个元素变为其左边元素和, 则 sum(i,j) = num[j]-num[i-1]
    private static double findMaxAveragePreNum(int[] nums, int k) {
        for(int i = 1; i < nums.length; i++) {
            nums[i] += nums[i-1];
        }
        int max = nums[k-1];
        for(int i = k; i < nums.length; i++) {
            max = Math.max(max, nums[i]-nums[i-k]);
        }
        return 1.0 * max / k;
    }
}
