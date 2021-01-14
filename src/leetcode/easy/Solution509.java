package leetcode.easy;

import util.TimeUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 斐波那契数，通常用 F(n) 表示，形成的序列称为 斐波那契数列 。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
 *
 * F(0) = 0，F(1) = 1
 * F(n) = F(n - 1) + F(n - 2)，其中 n > 1
 * 给你 n ，请计算 F(n) 。
 *
 * 示例 1：
 * 输入：2
 * 输出：1
 * 解释：F(2) = F(1) + F(0) = 1 + 0 = 1
 *
 * 示例 2：
 * 输入：3
 * 输出：2
 * 解释：F(3) = F(2) + F(1) = 1 + 1 = 2
 *
 * 示例 3：
 * 输入：4
 * 输出：3
 * 解释：F(4) = F(3) + F(2) = 2 + 1 = 3
 *  
 * 提示：
 * 0 <= n <= 30
 *
 */

public class Solution509 {

    private static int mockN = 800;

    public static void main(String[] args) {
        System.out.println(new TimeUtil().CalculationTime("Normal function: ", fib(mockN)));
        System.out.println(new TimeUtil().CalculationTime("Recursion function: ", fibRecursion(mockN)));
    }

    // 直接推算 n=800 0ms
    private static int fib(int n) {
        if(n == 0) return 0;
        if(n == 1) return 1;
        int num1 = 0;
        int num2 = 1;
        for(int index = 1; index < n; index++) {
            int temp = num2 + num1;
            num1 = num2;
            num2 = temp;
        }
        return num2;
    }

    // 递归缓存
    private static Map<Integer, Integer> RecursionCache = new HashMap<>();

    // 递归+缓存 n=800 4ms
    private static int fibRecursion(int n) {
        if(n == 0) return 0;
        if(n == 1) return 1;
        if(!RecursionCache.containsKey(n)) {
            RecursionCache.put(n, fibRecursion(n-1) + fibRecursion(n-2));
        }
        return RecursionCache.get(n);
    }
}
