package com.lake.recall;

import java.util.LinkedList;
import java.util.List;

/**
 * n皇后问题
 * @author lakeqiu
 */
public class PlaceQueens {
    /**
     * 下标是行号，元素是列号
     */
    private int[] cols;
    /**
     * 多少中摆法
     */
    private long ways = 0;

    static List<List<String>> listList = new LinkedList<>();

    private void save() {
        List<String> list = new LinkedList<>();
        for (int row = 0; row < cols.length; row++) {
            StringBuilder builder = new StringBuilder();
            for (int col = 0; col < cols.length; col++) {

                if (cols[row] == col) {
                    builder.append("Q");
                }else {
                    builder.append(".");
                }
            }
            list.add(builder.toString());
        }
        listList.add(list);
    }

    /**
     * n皇后
     * @param n 多少个皇后
     */
    public void placeQueens(int n) {
        if (n < 1) {
            return;
        }

        // 创建n行n列的棋盘
        cols = new int[n];
        // 开始摆放皇后
        place(0);
        System.out.println(n + "皇后一共有" + ways + "种摆法");
    }

    /**
     * 从第row行摆放皇后
     * @param row 行
     * @return
     */
    private void place(int row) {
        // 如果row == cols.length，说明最后一个已经摆放完成
        if (row == cols.length) {
            ways++;
            /*save();*/
            // 一种摆法已经结束，返回
            return;
        }

        // 开始遍历row行
        for (int col = 0; col < cols.length; col++) {

            // 判断这个位置是否可以摆放（即剪枝）
            // 进入if说明可以
            if (isValid(row, col)) {
                // 将皇后摆放到这个位置
                cols[row] = col;
                // 摆放下一行
                place(row+1);

                // 到这里的话说明row+1行没有位置摆放皇后，说明row行的皇后位置需要进行调整
                // 需要进行回溯
                // 回溯的话由for循环的i++自动进行
                // 虽然前面已经进行了摆放，当时cols数组只记载了皇后的摆放位置
                // 所以上后面找到了新的摆放皇后的位置时会进行覆盖
            }
        }
    }

    /**
     * 判断row行col列是否可以摆放皇后
     * @param row 行
     * @param col 列
     * @return
     */
    private boolean isValid(int row, int col) {
        // 遍历前面已经摆放过的行
        for (int i = 0; i < row; i++) {
            // 看一下前面摆放的皇后是否在同一列
            if (cols[i] == col) {
                return false;
            }
            // 斜线上是否存在皇后，（数学斜率知识）
            if (row - i == Math.abs(col - cols[i])) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        new PlaceQueens().placeQueens(16);
        /*System.out.println(listList);*/
    }
}
