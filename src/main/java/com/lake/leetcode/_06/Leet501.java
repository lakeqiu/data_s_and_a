package com.lake.leetcode._06;

import java.util.*;

/**
 * 给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。
 *
 * 假定 BST 有如下定义：
 *
 * 结点左子树中所含结点的值小于等于当前结点的值
 * 结点右子树中所含结点的值大于等于当前结点的值
 * 左子树和右子树都是二叉搜索树
 * 例如：
 * 给定 BST [1,null,2,2],
 *
 *    1
 *     \
 *      2
 *     /
 *    2
 * 返回[2].
 *
 * 提示：如果众数超过1个，不需考虑输出顺序
 *
 * 进阶：你可以不使用额外的空间吗？（假设由递归产生的隐式调用栈的开销不被计算在内）
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-mode-in-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lakeqiu
 */
public class Leet501 {
    public int[] findMode(TreeNode root) {
        if(root == null) {
            return new int[0];
        }

        HashMap<Integer, Integer> map = new HashMap<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            map.put(poll.val, map.getOrDefault(poll.val, 0) + 1);
            if (poll.left != null) {
                queue.offer(poll.left);
            }

            if (poll.right != null) {
                queue.offer(poll.right);
            }
        }

        List<Integer> list = new ArrayList<>();
        int max = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > max) {
                list.clear();
                list.add(entry.getKey());
                max = entry.getValue();
            } else if (entry.getValue() == max) {
                list.add(entry.getKey());
            }
        }

        int[] ints = new int[list.size()];

        for (int i = 0; i < ints.length; i++) {
            ints[i] = list.get(i);
        }
        System.out.println(Arrays.toString(ints));
        return ints;
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        TreeNode node3 = new TreeNode(2);
        node.right = node3;
        System.out.println(new Leet501().findMode(node));
    }

    static class TreeNode {
        Integer val;
        TreeNode left;
        TreeNode right;
        TreeNode(Integer x) { val = x; }
    }
}
