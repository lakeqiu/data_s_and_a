package com.lake.recall;

import java.util.LinkedList;
import java.util.List;

/**
 * n皇后优化 place方法
 * @author lakeqiu
 */
public class PlaceQueens2 {
    /**
     * 记录下标行上有没有皇后
     */
    private boolean[] cols;
    /**
     * 记录单斜线由左上角->右下角的斜线上有没有皇后（即撇方向）
     */
    private boolean[] leftTop;
    /**
     * 记录单斜线由右上角->左下角的斜线上有没有皇后（即捺方向）
     */
    private boolean[] rightTop;
    /**
     * 多少中摆法
     */
    private long ways = 0;

    static List<List<String>> listList = new LinkedList<>();

    /**
     * n皇后
     * @param n 多少个皇后
     */
    public void placeQueens(int n) {
        if (n < 1) {
            return;
        }

        cols = new boolean[n];
        leftTop = new boolean[(n << 1) - 1];
        rightTop = new boolean[leftTop.length];

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
            // 判断col列上是否有皇后
            if (cols[col]) {
                continue;
            }
            // 计算row行col列对应的撇斜线索引
            int leftIndex = row - col + cols.length - 1;
            int rightIndex = row + col;
            // 判断row行col列对应斜线上是否有皇后
            if (leftTop[leftIndex] || rightTop[rightIndex]) {
                continue;
            }

            // 到这里说明row行col列可以摆放皇后
            // 将皇后所在的列、斜线标注为有皇后
            cols[col] = true;
            leftTop[leftIndex] = true;
            rightTop[rightIndex] = true;

            // 开始摆放下一行
            place(row + 1);

            // 到这里的话说明row+1行没有位置可以摆放皇后
            // 需要进行回溯
            // 回溯的话由for的col++自动进行
            // 当时需要将row行col列的皇后摆放标注去除
            cols[col] = false;
            leftTop[leftIndex] = false;
            rightTop[rightIndex] = false;
        }
    }

    public static void main(String[] args) {
        new PlaceQueens().placeQueens(16);
        /*System.out.println(listList);*/
    }
}
