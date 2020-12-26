package leetcode.normal;

import util.ArrayUtil;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的数字可以无限制重复被选取。
 *
 * 说明：
 *
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。 
 * 示例 1：
 *
 * 输入：candidates = [2,3,6,7], target = 7,
 * 所求解集为：
 * [
 *   [7],
 *   [2,2,3]
 * ]
 * 示例 2：
 *
 * 输入：candidates = [2,3,5], target = 8,
 * 所求解集为：
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 *  
 *
 * 提示：
 *
 * 1 <= candidates.length <= 30
 * 1 <= candidates[i] <= 200
 * candidate 中的每个元素都是独一无二的。
 * 1 <= target <= 500
 *
 */

public class Solution39 {

    private static int[] mockArray = {4, 5, 6, 7, 8};

    private static List<Integer> tempList = new ArrayList<>();

    private static List<List<Integer>> result = new ArrayList<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int target = in.nextInt();
        combinationSum(target, 0);
        ArrayUtil.showListList(result);

    }

    // 典型全排列
    private static void combinationSum(int rest, int position) {
        // 剩余为0,即达成条件，存入结果集
        if(rest == 0) {
            result.add(new ArrayList<>(tempList));
            return;
        }
        // 剩余小于0,或者没有更多的元素,结束递归
        if(rest < 0 || position >= mockArray.length) {
            return;
        }
        // 此处为不选择当前元素的分支
        combinationSum(rest, position + 1);

        int index = 1;

        // 此处对当前位置元素进行遍历,在不超过目标值的情况下,依次存入1~n个当前元素的分支
        for(; rest >= (index * mockArray[position]); index++) {
            tempList.add(mockArray[position]);
            combinationSum(rest - (index * mockArray[position]), position + 1);
        }
        // 因为tempList为公用静态变量,此处为回溯到之前的状态
        for(; index > 1; index--) {
            tempList.remove(tempList.size()-1);
        }

    }


}
