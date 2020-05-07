package com.lake.leetcode.top;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author lakeqiu
 */
public class Leet048 {
    public void rotate(int[][] matrix){
        if(matrix.length == 0 || matrix.length != matrix[0].length) {
            return;
        }
        int nums = matrix.length;
        for (int i = 0; i < nums; ++i){
            for (int j = 0; j < nums - i; ++j){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[nums - 1 - j][nums - 1 - i];
                matrix[nums - 1 - j][nums - 1 - i] = temp;
            }
        }
        for (int i = 0; i < (nums >> 1); ++i){
            for (int j = 0; j < nums; ++j){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[nums - 1 - i][j];
                matrix[nums - 1 - i][j] = temp;
            }
        }
    }

    public static void main(String[] args) {
        new Leet048().rotate(new int[][]{{1,2,3},{4,5,6},{7,8,9}});
    }
}
