package com.kintexgroup.hkpsi.common.util;

import com.google.common.collect.Sets;
import com.kintexgroup.hkpsi.information.model.RecordAboutQueryTheBidPrice;
import com.kintexgroup.hkpsi.information.model.ConditionsAboutQueryTheBidPriceDTO;

import java.util.*;

/**
 * 通用工具类
 * <p>
 * 处理 uncheck 警告 castType 检查
 *
 * @author lmao
 * @since 2020/10/27 15:07
 */
public class CommonUtil {

    private CommonUtil() {
        throw new UnsupportedOperationException();
    }


    /**
     * cast: 'java.lang.Object' to 'java.util.List<T>' 避免 Unchecked Warning
     * <p>
     * https://blog.godiscoder.cn/hong/article/5af505f329023064843b6ffd
     *
     * @param obj   要转换的 obj
     * @param clazz clazz
     * @param <T>   类型
     * @return java.util.List<T>
     */
    public static <T> List<T> castList(Object obj, Class<T> clazz) {
        List<T> result = new ArrayList<>();
        if (obj instanceof List<?>) {
            for (Object o : (List<?>) obj) {
                result.add(clazz.cast(o));
            }
            return result;
        }
        return new ArrayList<>();
    }

    public static <T> String castString(Object obj, Class<T> clazz) {
        if (obj instanceof String) {
            return (String) clazz.cast(obj);
        }
        return "";
    }

    public static int castInt(Object obj) {
        if (obj instanceof Integer) {
            return (int) obj;
        } else if (obj instanceof String) {
            return Integer.parseInt(castString(obj, String.class));
        }
        return -1;
    }

    /**
     * 求 map 交集,用 guava.Sets
     *
     * @param bigMap   大 map
     * @param smallMap 小 map
     * @return key 交集
     */
    public static Set<String> duplicateSet(Map<String, Object> bigMap,
                                           Map<String, Object> smallMap) {
        Set<String> bigKeySet = bigMap.keySet();
        Set<String> smallKeySet = smallMap.keySet();
        //取交集
        return Sets.intersection(bigKeySet, smallKeySet);
    }

    @SuppressWarnings("unchecked")
    public static <T> T setValueIfNull(T t, String value) {
        boolean expressionStr = t == null || t instanceof String;
        if (expressionStr) {
            t = (T) value;
            return t;
        }
        return t;
    }

    /**
     * 优化 list.removeAll 方法 https://blog.csdn.net/kangxingang/article/details/9033491
     *
     * @param source      源合集
     * @param destination 目标合集
     * @param <T>         通配符
     * @return 差集
     */
    public static <T> List<T> removeAll(List<T> source, List<T> destination) {
        List<T> result = new LinkedList<>();
        Set<T> destinationSet = new HashSet<>(destination);
        for (T t : source) {
            if (!destinationSet.contains(t)) {
                result.add(t);
            }
        }
        return result;
    }


    public static Map<ConditionsAboutQueryTheBidPriceDTO, List<Integer>> findDuplicatesByMap(List<RecordAboutQueryTheBidPrice> list) {
        Map<ConditionsAboutQueryTheBidPriceDTO, List<Integer>> map = new HashMap<>(list.size() / 2);
//        for (int i = 0; i < list.size(); i++) {
//            var tag = list.get(i).getTag();
//            if (map.containsKey(tag)) {
//                List<Integer> indexs = map.get(tag);
//                indexs.add(i);
//                map.put(tag, indexs);
//            } else {
//                List<Integer> indexs = new ArrayList<>();
//                indexs.add(i);
//                map.put(tag, indexs);
//            }
//        }

        return map;
    }

    public static boolean isValidString(String arg) {
        return arg != null && !arg.isEmpty();
    }
}


