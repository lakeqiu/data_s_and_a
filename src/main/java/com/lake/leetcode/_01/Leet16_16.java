package com.lake.leetcode._01;

/**
 * 给定一个整数数组，编写一个函数，找出索引m和n，只要将索引区间[m,n]的元素排好序，整个数组就是有序的。注意：n-m尽量最小，
 * 也就是说，找出符合条件的最短序列。函数返回值为[m,n]，若不存在这样的m和n（例如整个数组是有序的），请返回[-1,-1]。
 *
 * 示例：
 *
 * 输入： [1,2,4,7,10,11,7,12,6,7,16,18,19]
 * 输出： [3,9]
 * 提示：
 *
 * 0 <= len(array) <= 1000000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sub-sort-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lakeqiu
 */
public class Leet16_16 {
    public int[] subSort(int[] array) {
        if (array.length == 0) {
            return new int[] {-1, -1};
        }

        // 从左到右扫描逆序对（正序：越来越大）
        // 存放从左到右最大的元素,默认0位置
        int max = array[0];
        // 记录最右边的哪个逆序对
        int r = -1;
        for (int i = 1; i < array.length; i++) {
            int value = array[i];
            if (value >= max) {
                max = value;
            } else {
                r = i;
            }
        }

        // 从右到左扫描逆序对（正序：越来越小）
        // 存放从左到右最小的元素,默认最后的位置
        int min = array[array.length - 1];
        // 记录最左边的逆序对
        int l = -1;
        for (int i = array.length - 2; i >= 0; i--) {
            int value = array[i];
            if (value <= min) {
                min = value;
            } else {
                l = i;
            }
        }

        return new int[]{l, r};
    }

    public static void main(String[] args) {
        int[] ints = new Leet16_16().subSort(new int[]{1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19});
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }
}
