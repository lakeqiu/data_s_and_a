package com.lake.leetcode.top;

import java.util.*;

/**
 * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
 *
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 *
 * 示例:
 *
 * 输入: 5
 * 输出:
 * [
 *      [1],
 *     [1,1],
 *    [1,2,1],
 *   [1,3,3,1],
 *  [1,4,6,4,1]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/pascals-triangle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lakeqiu
 */
public class Leet118 {
    // 动态规划
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> dg = new LinkedList<>();

        if (numRows == 0) {
            return dg;
        }

        // 初始条件，第0行只有一个1
        dg.add(new ArrayList<>(1));
        dg.get(0).add(1);

        // 从第1行开始算
        for (int numRow = 1; numRow < numRows; numRow++) {
            // 存放本行结果
            List<Integer> row = new ArrayList<>(numRow + 1);
            // 获取上一行结果
            List<Integer> preRow = dg.get(numRow - 1);

            // 不管那一行，第一个数一定是1
            row.add(1);

            // 每一行有numRow + 1个数，我们只需要填充到最后第2个数
            for (int j = 1; j < numRow; j++) {
                row.add(preRow.get(j - 1) + preRow.get(j));
            }

            // 不管那一行，最后一个数1
            row.add(1);
            // 加入结果集
            dg.add(row);
        }

        return dg;
    }

    /*// 递归
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> dg = new LinkedList<>();

        if (numRows == 0) {
            return dg;
        }

        // 递归基
        if (numRows == 1) {
            dg.add(new ArrayList<>(1));
            dg.get(0).add(1);

            return dg;
        }


        // 存放本次（第numRows - 1行结果，从0开始）
        List<Integer> row = new ArrayList<>(numRows);

        // 递归获取上一行的结果
        dg = generate(numRows - 1);

        // 本行第一个数一定为 1
        row.add(1);
        // 如第4行有5个数（此时numRows是5），但因为时候尾都为1
        // 所以只需要填充下标为 1,2,3 的位置，所以是 < numRows - 1
        for (int i = 1; i < numRows - 1; i++) {
            // 1        第0行
            // 1 1      第1行
            // 1 2 1    第2行
            // 如上面第2行需要填充的值（下标为1）等于上一行下标为0，1点值
            row.add(dg.get(numRows - 2).get(i - 1) + dg.get(numRows - 2).get(i));
        }

        // 最后一个数也一定是1
        row.add(1);
        // 加入结果集中
        dg.add(row);

        return dg;
    }*/
}
