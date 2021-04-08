package com.kintexgroup.hkpsi;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LMAO
 * @since 2020/12/2 16:19
 */
public class CountDuplicatedList {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("b");
        list.add("c");
        list.add("a");
        list.add("a");
        list.add("a");
        System.out.println("\nExample 3 - Count all with Map");
        Map<String, Integer> map = new HashMap<>();

        for (String temp : list) {
            Integer count = map.get(temp);
            map.put(temp, (count == null) ? 1 : count + 1);
        }
        printMap(map);
    }

    public static void printMap(Map<String, Integer> map) {

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println("Key : " + entry.getKey() + " Value : "
                + entry.getValue());
        }

    }

}


