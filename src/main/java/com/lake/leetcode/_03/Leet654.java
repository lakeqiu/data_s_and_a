package com.lake.leetcode._03;

/**
 * 给定一个不含重复元素的整数数组。一个以此数组构建的最大二叉树定义如下：
 *
 * 二叉树的根是数组中的最大元素。
 * 左子树是通过数组中最大值左边部分构造出的最大二叉树。
 * 右子树是通过数组中最大值右边部分构造出的最大二叉树。
 * 通过给定的数组构建最大二叉树，并且输出这个树的根节点。
 *
 *  
 *
 * 示例 ：
 *
 * 输入：[3,2,1,6,0,5]
 * 输出：返回下面这棵树的根节点：
 *
 *       6
 *     /   \
 *    3     5
 *     \    /
 *      2  0
 *        \
 *         1
 *  
 *
 * 提示：
 *
 * 给定的数组的大小在 [1, 1000] 之间。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lakeqiu
 */
public class Leet654 {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return findRoot(nums, 0, nums.length);
    }

    private TreeNode findRoot(int[] nums, int begin, int end) {
        // 递归终止条件
        if (end == begin) {
            return null;
        }

        // 存放根节点索引（即最大值索引）
        int maxIndex = begin;

        for (int i = begin + 1; i < end; i++) {
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }

        // 创建根节点
        TreeNode root = new TreeNode(nums[maxIndex]);

        // 对左右子节点一样做
        root.left = findRoot(nums, begin, maxIndex);
        root.right = findRoot(nums, maxIndex + 1, end);

        return root;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public int[] parentIndex(int[] nums) {
        int[] parents = new int[nums.length];
        findParent(nums, 0, nums.length, parents, -1);
        return parents;
    }

    private void findParent(int[] nums, int begin, int end, int[] parents, int parentIndex) {
        // 递归终止条件
        if (end == begin) {
            return;
        }

        // 存放根节点索引（即最大值索引）
        int maxIndex = begin;

        for (int i = begin + 1; i < end; i++) {
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }

        // 添加父节点的索引
        parents[maxIndex] = parentIndex;


        // 对左右子节点一样做
        findParent(nums, begin, maxIndex, parents, maxIndex);
        findParent(nums, maxIndex + 1, end, parents, maxIndex);
    }

    public static void main(String[] args) {
        int[] parentIndex = new Leet654().parentIndex(new int[]{3, 2, 1, 6, 0, 5});
        for (int index : parentIndex) {
            System.out.println(index);
        }
    }
}
