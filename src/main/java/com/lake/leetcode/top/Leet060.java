package com.lake.leetcode.top;

/**
 * @author lakeqiu
 */
public class Leet060 {
    static String res = "";
    static int m ;

    public static void main(String[] args) {
        System.out.println(new Leet060().getPermutation(3, 1));
    }

    public String getPermutation(int n, int k) {
        boolean[] used = new boolean[n];
        m = k;
        dfs(n, new StringBuilder(), used);
        return res;
    }

    private void dfs(int n, StringBuilder sb, boolean[] used) {
        if (sb.length() == n) {
            m--;
            if (m == 0) {
                res = sb.toString();
            }

            return;
        }

        for (int i = 0; i < n; i++) {
            if (used[i]) {
                continue;
            }

            sb.append(i + 1);
            used[i] = true;

            dfs(n, sb, used);

            sb.deleteCharAt(sb.length() - 1);
            used[i] = false;

            if(m == 0) {
                break;
            }
        }
    }
}
