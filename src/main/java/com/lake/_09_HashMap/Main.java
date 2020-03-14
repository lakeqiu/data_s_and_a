package com.lake._09_HashMap;

import com.lake._09_HashMap.map.HashMap;
import com.lake._09_HashMap.map.Map;

/**
 * @author lakeqiu
 */
public class Main {
    public static void test1() {
        HashMap<String, String> map = new HashMap<>();
        map.put("a", "b");
        map.put("c", "skh");
        map.put("f", "sa");
        map.put("kk", "bsa");
        map.put("lo", "qds");
        map.traversal(new Map.Visitor<String, String>() {
            @Override
            public boolean visit(String key, String value) {
                System.out.println(key + "-->" + value);
                return false;
            }
        });
        System.out.println(map.size());
        System.out.println(map.get("kk"));
        map.remove("a");
        map.remove("a");
        map.remove("b");

        map.traversal(new Map.Visitor<String, String>() {
            @Override
            public boolean visit(String key, String value) {
                System.out.println(key + "-->" + value);
                return false;
            }
        });
        System.out.println(map.size());
    }

    public static void main(String[] args) {
        test1();
    }
}
