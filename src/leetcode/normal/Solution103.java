package leetcode.normal;

import util.ArrayUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 *
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回锯齿形层序遍历如下：
 *
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 *
 */

public class Solution103 {

    // 输入对象定义
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    private static TreeNode root;

    // 初始化测试数据
    static {
        root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
    }

    public static void main(String[] args) {
        ArrayUtil.showListList(zigzagLevelOrder(root));

    }

    // 思路简单,分层取值
    private static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        // 测试数据坑1: root=null; 如不检测下面会空指针异常
        if(root != null) {
            List<TreeNode> tempFloor = new ArrayList<>();
            tempFloor.add(root);
            // 为达到锯齿形效果,设置变量控制数组是否翻转
            boolean leftBegin = true;
            // tempFloor存储当前层的所有节点,初始为根节点
            while(tempFloor.size() > 0) {
                int tempCount = tempFloor.size();
                // tempList存储当前层的数据
                List<Integer> tempList = new ArrayList<>();
                // 循环前存储当前层节点个数,以便一次循环完成 1.当前层取值; 2.下一层节点存入; 3.当前层节点删除
                for(int i =0; i < tempCount; i++) {
                    tempList.add(tempFloor.get(0).val);
                    // 先左后右,保证顺序
                    if(tempFloor.get(0).left != null) {
                        tempFloor.add(tempFloor.get(0).left);
                    }
                    if(tempFloor.get(0).right != null) {
                        tempFloor.add(tempFloor.get(0).right);
                    }
                    // 每次都删除第一个元素,模拟队列
                    tempFloor.remove(0);
                }
                // 数组翻转控制
                if(!leftBegin) {
                    Collections.reverse(tempList);
                }
                result.add(tempList);
                leftBegin = !leftBegin;
            }
        }
        return result;
    }

}


