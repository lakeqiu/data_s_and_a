package com.lake.sequence;

/**
 * 串匹配 - 蛮力算法
 * @author lakeqiu
 */
public class BruteForce {
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

        // 开始蛮力比较
        int ti = 0;
        int pi = 0;

        while ( pi < plen && ti < tlen) {
            // 相等的话各进一步
            if (patterns[pi] == texts[ti]) {
                pi++;
                ti++;
            } else { // 不相等，ti回退上一步+1.pi回退0
                ti -= pi - 1;
                pi = 0;
            }
        }

        // pi == plen说明匹配到了
        return pi == plen ? ti - pi : -1;
    }
}
