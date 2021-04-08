package com.kintexgroup.hkpsi.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kintexgroup.hkpsi.information.model.LogisticsResult;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * @author junhangs
 * @since 2020/9/9 23:25
 */
@Slf4j
public final class JsonUtil {

    private JsonUtil() {
    }

    private static final String WARN_MSG = "write to json string error:";

    //jackson

    public static <T> T parse(String json) {
        try {
            ObjectMapper om = new ObjectMapper();
            return om.readValue(json, new TypeReference<>() {
            });
        } catch (IOException e) {
            log.warn(WARN_MSG + json, e);
            return null;
        }
    }

    public static String stringify(Object object) {
        try {
            ObjectMapper om = new ObjectMapper();
            return om.writeValueAsString(object);
        } catch (IOException e) {
            log.warn(WARN_MSG + object, e);
            return "";
        }
    }

    /**
     * Set集合转为Json
     *
     * @param set
     * @return
     */
    public static String parseSet(Set<?> set) {
        StringBuilder json = new StringBuilder();
        json.append("[");
        if (set != null && set.size() > 0) {
            for (Object obj : set) {
                json.append(stringify(obj));
                json.append(",");
            }
            json.setCharAt(json.length() - 1, ']');
        } else {
            json.append("]");
        }
        return json.toString();
    }

    /*
    fastjson
     */

    public static String toString(Object object) {
        JSON oJson = new JSONObject();
        return oJson.toJSONString();
    }

    public static String toJsonString(Map<String, String> map) {
        return JSON.toJSONString(map);
    }

    public static Object toObject(String json) {
        return JSON.parse(json);
    }

    public static LogisticsResult toLogisticsResult(String json) {
        JSONObject jsonObject = JSON.parseObject(json);
        return JSON.parseObject(jsonObject.toJSONString(), new com.alibaba.fastjson.TypeReference<>() {
        });
    }

}
