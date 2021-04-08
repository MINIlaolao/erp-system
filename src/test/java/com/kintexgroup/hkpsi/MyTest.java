package com.kintexgroup.hkpsi;


import com.google.common.collect.Sets;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author pengli
 * @since 2020/9/11 2:58 下午
 */
public class MyTest {

    public static void main(String[] args) {
        String brand = "iPhone X";
        String[] capacity = {"64", "256"};
        String[] color = {"Silver", "Space Gray"};
        String[] modelNumber = {"A1865", "A1901", "A1902"};
        System.out.println(sku(brand, capacity, color, modelNumber));
    }

    @SuppressWarnings("unchecked")
    public static String sku(String brand, String[] capacity, String[] color,
                             String[] modelNumber) {
        Set br = new HashSet();
        br.add(brand);
        Set s1 = new HashSet(Arrays.asList(capacity));
        Set s2 = new HashSet(Arrays.asList(color));
        Set s3 = new HashSet(Arrays.asList(modelNumber));
        Set set = Sets.cartesianProduct(br, s1, s2, s3);
        return set.toString();
    }
}
