package com.kintexgroup.hkpsi.common.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.TimeZone;

/**
 * @author gradylo 简单封装Jackson
 */
@Slf4j
public class JsonMapper {

    private final ObjectMapper mapper;

    private static final String WARN_MSG = "write to json string error:";

    /**
     * 构造函数
     */
    public JsonMapper(JsonInclude.Include include) {
        mapper = new ObjectMapper();
        // 设置输出时包含属性风格
        if (include != null) {
            mapper.setSerializationInclusion(include);
        }
        // 设置输入时忽略在JSON字符串中存在但JAVA对象实际没有的属性
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

        // 设置时区
        mapper.setTimeZone(TimeZone.getDefault());
    }

    /**
     * 创建只输出非Null且非Empty(如List.isEmpty)的属性到Json字符串的Mapper，建议在外部接口使用
     */
    public static JsonMapper nonEmptyMapper() {
        return new JsonMapper(JsonInclude.Include.NON_NULL);
    }

    /**
     * Object 可以是POJO，也可以是Collection或数组，如果对象为Null，返回"null"，如果集合为空集合，返回"[]"
     */
    public String toJson(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (IOException e) {
            log.warn(WARN_MSG + object, e);
            return " ";
        }
    }

    /**
     * 将json字符串的array转换成list
     *
     * @param json 内容
     */
    public List<String> toList(String json) {
        try {
            return mapper.readValue(json, new TypeReference<>() {
            });
        } catch (IOException e) {
            log.warn(WARN_MSG + json, e);
            return new LinkedList<>();
        }
    }

    /**
     * 将json字符串的array转换成array
     *
     * @param json 内容
     */
    public int[] toArray(String json) {
        try {
            return mapper.readValue(json, new TypeReference<>() {
            });
        } catch (IOException e) {
            log.warn(WARN_MSG + json, e);
            return new int[0];
        }
    }

    /**
     * 将json形式的字符串数据转换成JsonNode
     *
     * @param json
     * @return
     */
    public JsonNode toJsonNode(String json) {
        try {
            return mapper.readTree(json);
        } catch (IOException e) {
            log.warn(WARN_MSG + json, e);
            return null;
        }
    }


}
