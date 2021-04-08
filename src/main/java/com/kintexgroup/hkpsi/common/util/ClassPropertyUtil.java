package com.kintexgroup.hkpsi.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ClassPropertyUtil {
    public static List<HashMap<String, Object>> getFieldsInfo(Object o) {
        Field[] fields = o.getClass().getDeclaredFields();
        var list = new ArrayList<HashMap<String, Object>>();
        for (Field field : fields) {
            var infoMap = new HashMap<String, Object>();
            infoMap.put("type", field.getType().toString());
            infoMap.put("name", field.getName());
            infoMap.put("value", getFieldValueByName(field.getName(), o));
            list.add(infoMap);
        }
        return list;
    }


    public static Object getFieldValueByName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter);
            return method.invoke(o);
        } catch (Exception e) {
            return null;
        }
    }
}
