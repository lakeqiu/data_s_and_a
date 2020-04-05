package com.lake.sequence;

/**
 * KMP
 * @author lakeqiu
 */
public class KMP {
    static int indexOf(String text, String pattern) {
        // 将一些情况排除
        if (null == text || null == pattern) {
            return -1;
        }

        char[] texts = text.toCharArray();
        int tlen = texts.length;
        if (tlen == 0) {
            return -1;
        }

        char[] patterns = pattern.toCharArray();
        int plen = patterns.length;
        if (plen == 0) {
            return -1;
        }

        if (tlen < plen) {
            return -1;
        }
        // 获取失配表
        int[] next = next(pattern);

        int ti = 0;
        int pi = 0;

        while ( pi < plen && ti < tlen) {
            // 相等的话各进一步
            if (pi < 0 || patterns[pi] == texts[ti]) {
                pi++;
                ti++;
            } else { // 不相等，根据失配表进行下一步
                pi = next[pi];
            }
        }

        // pi == plen说明匹配到了
        return pi == plen ? ti - pi : -1;
    }

    static int[] next(String pattern) {
        int len = pattern.length();
        int[] next = new int[len];
        int i = 0;
        int n = next[i] = -1;
        int iMax = len - 1;
        while (i < iMax) {
            if (n < 0 || pattern.charAt(i) == pattern.charAt(n)) {
                next[++i] = ++n;
            } else {
                n = next[n];
            }
        }
        return next;
    }
}
