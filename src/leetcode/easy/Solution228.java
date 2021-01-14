package leetcode.easy;

import util.ArrayUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个无重复元素的有序整数数组 nums 。
 *
 * 返回 恰好覆盖数组中所有数字 的 最小有序 区间范围列表。也就是说，nums 的每个元素都恰好被某个区间范围所覆盖，并且不存在属于某个范围但不属于 nums 的数字 x 。
 *
 * 列表中的每个区间范围 [a,b] 应该按如下格式输出：
 *
 * "a->b" ，如果 a != b
 * "a" ，如果 a == b
 *  
 * 示例 1：
 * 输入：nums = [0,1,2,4,5,7]
 * 输出：["0->2","4->5","7"]
 * 解释：区间范围是：
 * [0,2] --> "0->2"
 * [4,5] --> "4->5"
 * [7,7] --> "7"
 *
 * 示例 2：
 * 输入：nums = [0,2,3,4,6,8,9]
 * 输出：["0","2->4","6","8->9"]
 * 解释：区间范围是：
 * [0,0] --> "0"
 * [2,4] --> "2->4"
 * [6,6] --> "6"
 * [8,9] --> "8->9"
 *
 * 示例 3：
 * 输入：nums = []
 * 输出：[]
 *
 * 示例 4：
 * 输入：nums = [-1]
 * 输出：["-1"]
 *
 * 示例 5：
 * 输入：nums = [0]
 * 输出：["0"]
 *  
 * 提示：
 * 0 <= nums.length <= 20
 * -231 <= nums[i] <= 231 - 1
 * nums 中的所有值都 互不相同
 * nums 按升序排列
 *
 */

public class Solution228 {

    private static int[] mockNums = {0, 2, 3, 4, 6, 8, 9};

    public static void main(String[] args) {
        ArrayUtil.showList(summaryRanges(mockNums));
    }

    // 思路简单, 一次遍历
    private static List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();
        if(nums.length == 0) {
            return result;
        }
        // 存储区间其实下标
        int tempIndex = 0;
        for(int index = 0; index < nums.length; index++) {
            // 需要注意当遍历到最后一位时, 需要将结果存储, 而当后一位连续时, 进入下一次循环
            if(index != (nums.length-1) && (nums[index]+1) == nums[index+1]) {
                continue;
            }
            // 判断当前区间是否只有一位数字
            if(tempIndex == index) {
                result.add(nums[index]+"");
            } else {
                result.add(nums[tempIndex] + "->" + nums[index]);
            }
            // 因为存储的为下标值, 无需考虑溢出问题
            tempIndex = index + 1;
        }
        return result;
    }

}
