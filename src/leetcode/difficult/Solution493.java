package leetcode.difficult;

import util.TimeUtil;

/**
 *给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。
 *
 * 你需要返回给定数组中的重要翻转对的数量。
 *
 * 示例 1:
 *
 * 输入: [1,3,2,3,1]
 * 输出: 2
 * 示例 2:
 *
 * 输入: [2,4,3,5,1]
 * 输出: 3
 * 注意:
 *
 * 给定数组的长度不会超过50000。
 * 输入数组中的所有数字都在32位整数的表示范围内。
 *
 */
public class Solution493 {

    private static int[] mockArray = {123, 123, 12, 123, 43, 24, 21, 9, 45, 23, 234, 55, 123, 123, 12, 123, 43, 24, 21, 9, 45, 23, 234, 55, 123, 123, 12, 123, 43, 24, 21, 9, 45, 23, 234, 55, 123, 123, 12, 123, 43, 24, 21, 9, 45, 23, 234, 55};

    private static int times = 0;

    public static void main(String[] args) {
        System.out.println(new TimeUtil().CalculationTime("Official approach", reversePairs(mockArray)));
        System.out.println(times);
    }

    private static int reversePairs(int[] nums) {
        if(nums.length == 0) return 0;
        int[] numsSorted = new int[nums.length];
        return mergeSort(nums, numsSorted, 0, nums.length - 1);
    }

    // 归并排序思想
    private static int mergeSort(int[] nums, int[] numsSorted, int left, int right) {
        if(left == right) return 0;
        int middle = left + (right - left) / 2;
        int res = mergeSort(nums, numsSorted, left, middle) +
                mergeSort(nums, numsSorted, middle + 1, right) +
                findReversedPairs(nums, left, right);
        int i = left;
        int cursor = left;
        int j = middle + 1;
        while(i <= middle && j <= right) {
            if(nums[i] > nums[j]) {
                numsSorted[cursor++] = nums[j++];
            } else {
                numsSorted[cursor++] = nums[i++];
            }
        }
        while(i <= middle) {numsSorted[cursor++] = nums[i++];}
        while(j <= right) {numsSorted[cursor++] = nums[j++];}

//        for(int ind = left; ind <= right; ind++) {
//            nums[ind] = numsSorted[ind];
//        }
        // 系统自动优化 -> 被复制的数组, 起始下标, 目标数组, 目标数组起始下标, 复制长度
        System.arraycopy(numsSorted, left, nums, left, right - left + 1);
        return res;
    }

    private static int findReversedPairs(int[] nums, int left, int right) {
        int res = 0;
        int middle = left + (right - left) / 2;
        int i = left;
        int j = middle + 1;
        // i控制前半段的坐标
        while(i <= middle) {
            // j控制后半段的坐标，如果此时的i满足翻转对条件
            while(j <= right && (long) nums[i] > 2*(long) nums[j]) {
                // 说明前半段i后面的值也满足对此j值的翻转对条件
                times++; // 80次
                res += middle - i + 1;
                j++;
            }
            i++;
        }
        return res;
    }

    // 排序后数组，前后两半的翻转对数
    private static int findReversedPairs_overTime(int[] numsSorted, int left, int right) {
        int res = 0;
        int middle = left + (right - left) / 2 + 1;
        // i控制后半段的坐标
        for(int i = middle; i <= right; i++) {
            // 当前的坐标i对应的值
            int tempR = numsSorted[i];
            // j控制前半段的坐标
            for(int j = left; j < middle; j++) {
                times++; // 820次
                // 当前的坐标j对应的值
                int tempL = numsSorted[j];
                // 如果j对应值比i对应值的两倍还大（满足翻转对条件）
                if((long) tempL > (long) tempR * 2) {
                    // 说明j后面的值也满足对于此i值的翻转对条件
                    res += middle - j;
                    break;
                }
            }
        }
        return res;
    }
}
