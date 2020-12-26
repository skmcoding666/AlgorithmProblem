package leetcode.normal;

import java.util.*;

/**
 * 给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；非负整数 fee 代表了交易股票的手续费用。
 *
 * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
 *
 * 返回获得利润的最大值。
 *
 * 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
 *
 * 示例 1:
 *
 * 输入: prices = [1, 3, 2, 8, 4, 9], fee = 2
 * 输出: 8
 * 解释: 能够达到的最大利润:
 * 在此处买入 prices[0] = 1
 * 在此处卖出 prices[3] = 8
 * 在此处买入 prices[4] = 4
 * 在此处卖出 prices[5] = 9
 * 总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
 * 注意:
 *
 * 0 < prices.length <= 50000.
 * 0 < prices[i] < 50000.
 * 0 <= fee < 50000.
 *
 */

public class Solution714 {

    private static int[] mockPrices = {1, 3, 2, 8, 4, 9};

    private static int fee = 2;

    public static void main(String[] args) {
        System.out.println(OfficialSolution(mockPrices, fee));
    }

    private static int maxProfit(int[] prices, int fee) {
        dfs(prices, fee, 0, 0, false);
        return Collections.max(resultSet);
    }

    private static List<Integer> resultSet = new ArrayList<>();

    // 杀鸡用牛刀, 而且leetcode官网有bug, 这段代码提交的运行结果和测试的运行结果不匹配...
    // 不匹配的原因是: Line47 -> resultSet 这个静态变量只初始化了一次,leetCode官网提交后会有很多个 测试case
    // 代码多次执行 resultSet 不会被重置,导致结果有问题
    private static void dfs(int[] prices, int fee, int tempDay, int tempMoney, boolean gotStock) {
        if(tempDay == prices.length - 1) {
            if(gotStock) {
                tempMoney = tempMoney + prices[tempDay];
                tempMoney = tempMoney - fee;
            }
            resultSet.add(tempMoney);
            return;
        }
        // 持有股票的状态下
        if(gotStock) {
            // 按兵不动
            dfs(prices, fee, tempDay+1, tempMoney, true);
            // 全部抛出
            int soldPrice = prices[tempDay] - fee;
            dfs(prices, fee, tempDay+1, tempMoney + soldPrice, false);
        } else {
            // 未持有股票的状态下
            // 按兵不动
            dfs(prices, fee, tempDay+1, tempMoney, false);
            // 梭哈购进
            int buyPrice = prices[tempDay];
            dfs(prices, fee, tempDay+1, tempMoney - buyPrice, true);
        }
    }

    // 官方题解: 贪心算法
    private static int OfficialSolution(int[] prices, int fee) {
        int n = prices.length;
        // 将手续费算在购买股票的时候
        // buy 初始化为第一天购买的成本
        int buy = prices[0] + fee;
        // 当前收益
        int profit = 0;
        for (int i = 1; i < n; ++i) {
            // 从第二天开始,首先看股价是否降低
            if (prices[i] + fee < buy) {
                // 降低的话就选择当前天购买
                buy = prices[i] + fee;
            } else if (prices[i] > buy) {
                // 进到这里说明现在卖出去是有利润的
                // 把当前利润保存,成本变为当前股价
                profit += prices[i] - buy;
                buy = prices[i];
                // 下一天则有三种情况
                // [1.股价跌到再花手续费购入还有利润]->确定当前售出
                // [2.股价又涨了]->计划变为下一天售出
                // [3.股价跌了少许,但是再花手续费购入是亏的]->再等等, 若直到结束都是这种情况,直接返回保存好的利润即可,即确定当前售出
            }
        }
        return profit;
    }
    // 例: [2, 1, 3, 8, 10, 4, 4] 手续费 2
    // 初始化打算第1天购入,计算成本 buy=2+2=4
    // 第2天股价降低了,比第1天购入更加合适,所以计划更改为第2天购入,计算成本 buy=1+2=3
    // 第3天虽然股价在涨,可是由于手续费的原因,卖出去不赚钱
    // 第4天的股价比自己的成本高,卖出去是赚钱的,但我不确定第5天卖出去会不会更赚钱
    // 所以第4天我保留一下原价买回的选择,因为毕竟是在推算,不是真实操作,所以这里不是说卖出去赚钱就卖出去了
    // 然后把第1天购入,第4天卖出的利润保存一下 profit=0+(8-3)=5,而成本改为第4天的价钱,不加手续费 buy=8
    // 第5天由于股价还在涨,所以我们就改变计划了,改为第5天卖出
    // 但实际上第5天再卖出的利润就只需要在前面保存好的利润 profit 上面加上第5天相比于第4天的涨价 profit=5+(10-8)=7
    // 然后跟之前一样,保存利润,保留第6天再卖的选择
    // 到了第6天发现,股价降了很多,加上手续费也没有第5天的价钱高,所以决定第5天真正卖出,第6天再计划买回来,计算成本 buy=4+2=6
    // 第7天的股价不比自己的成本高,所以不赚钱,再等等看
    // 但是已经没有下一天了,所以第6天购入的计划舍弃,最大化收益方案为 第2天购入,第5天卖出 profit=7
}
