package com.lake.leetcode.top;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lakeqiu
 */
public class Leet036 {
    public boolean isValidSudoku(char[][] board) {
        if (board == null || board.length == 0) {
            return false;
        }

        Map<Integer, List<Character>> rowMap = new HashMap<>();
        Map<Integer, List<Character>> colMap = new HashMap<>();
        Map<Integer, List<Character>> squareMap = new HashMap<>();

        int rowLen = board.length;
        int colLen = board[0].length;
        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                char c = board[row][col];
                // 如果当前位置存的是数字
                if (c != '.') {
                    // 判断行上是否有一样的
                    List<Character> rowList = rowMap.getOrDefault(row, new ArrayList<Character>());
                    if (rowList.contains(c)) {
                        return false;
                    }
                    rowList.add(c);
                    rowMap.put(row, rowList);

                    // 判断列上是否有一样的
                    List<Character> colList = colMap.getOrDefault(col, new ArrayList<Character>());
                    if (colList.contains(c)) {
                        return false;
                    }
                    colList.add(c);
                    colMap.put(col, colList);

                    // 判断 3x3 宫上是否有一样的
                    // 算出当前位置在哪个 3x3 宫内
                    // 我们这样排：从左到右，从上到下
                    // 00  01  02  ...
                    // 10  11  12  ...
                    int square = (row / 3) * 3 + (col / 3);
                    System.out.println(square);
                    List<Character> squareList = squareMap.getOrDefault(square, new ArrayList<Character>());
                    if (squareList.contains(c)) {
                        return false;
                    }
                    squareList.add(c);
                    squareMap.put(square, squareList);
                }
            }
        }
        return true;
    }
}
