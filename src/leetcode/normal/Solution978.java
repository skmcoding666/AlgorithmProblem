package leetcode.normal;

import util.TimeUtil;

import java.util.Arrays;

/**
 * 当 A 的子数组 A[i], A[i+1], ..., A[j] 满足下列条件时，我们称其为湍流子数组：
 *
 * 若 i <= k < j，当 k 为奇数时， A[k] > A[k+1]，且当 k 为偶数时，A[k] < A[k+1]；
 * 或 若 i <= k < j，当 k 为偶数时，A[k] > A[k+1] ，且当 k 为奇数时， A[k] < A[k+1]。
 * 也就是说，如果比较符号在子数组中的每个相邻元素对之间翻转，则该子数组是湍流子数组。
 *
 * 返回 A 的最大湍流子数组的长度。
 *
 * 示例 1：
 * 输入：[9,4,2,10,7,8,8,1,9]
 * 输出：5
 * 解释：(A[1] > A[2] < A[3] > A[4] < A[5])
 *
 * 示例 2：
 * 输入：[4,8,12,16]
 * 输出：2
 *
 * 示例 3：
 * 输入：[100]
 * 输出：1
 *  
 * 提示：
 * 1 <= A.length <= 40000
 * 0 <= A[i] <= 10^9
 */

public class Solution978 {

    private static int[] mockArr = {4,8,12,16,4,8,12,16,12,16,4,8,12,16,12,16,4,8,12,16,12,16,4,8,12,16,12,16,4,8,12,16,12,16,4,8,12,16,12,16,4,8,12,16,12,16,4,8,12,16,12,16,4,8,12,16,12,16,4,8,12,16};

    public static void main(String[] args) {
        System.out.println(new TimeUtil().CalculationTime("my function", maxTurbulenceSize(mockArr)));
        System.out.println(new TimeUtil().CalculationTime("dp function", maxTurbulenceSizeDP(mockArr)));
    }

    // 思路: 一次遍历
    private static int maxTurbulenceSize(int[] arr) {
        int len = arr.length;
        if(len == 1) {
            return 1;
        }
        int result = 1;
        int left = 0;
        int right = 1;
        // 为左指针找到一个不重复的开始
        for(; right < len; left++, right++) {
            if(arr[left] != arr[right]) {
                result = 2;
                break;
            }
        }
        if(right == len) {
            return result;
        }
        // flag存储判断标志位, 每次循环遍历取反
        boolean flag = arr[left] < arr[right];
        for(; right < len; right++) {
            flag = !flag;
            if(arr[right-1] == arr[right] || (flag != arr[right-1] < arr[right])) {
                result = Math.max(result, right - left);
                left = right-1;
                flag = arr[left] < arr[right];
            }
        }
        return Math.max(result, right - left);
    }

    private static int maxTurbulenceSizeDP(int[] arr) {
        int len = arr.length;
        int[] up = new int[len];
        int[] down = new int[len];
        Arrays.fill(up, 1);
        Arrays.fill(down, 1);
        int res = 1;
        for(int i = 1; i < len; i++) {
            if(arr[i-1] < arr[i]) {
                up[i] = down[i-1] + 1;
            } else if(arr[i-1] > arr[i]) {
                down[i] = up[i-1] + 1;
            }
            res = Math.max(res, Math.max(up[i], down[i]));
        }
        return res;
    }

}
