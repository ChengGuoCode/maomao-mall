package com.gdng.support.common.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gdng.support.common.dto.res.GlobalResponseEnum;
import com.gdng.support.common.exception.GdngException;

import java.io.IOException;
import java.util.Map;

public class JacksonUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public static Map<String, Object> anyToMap(Object obj) {
        try {
            return objectMapper.readValue(objectMapper.writeValueAsString(obj), Map.class);
        } catch (IOException e) {
            throw new GdngException(GlobalResponseEnum.JSON_CONVERT, "method: anyToMap(" + obj + ")");
        }
    }

    public static Map<String, Object> jsonToMap(String json) {
        try {
            return objectMapper.readValue(json, Map.class);
        } catch (IOException e) {
            throw new GdngException(GlobalResponseEnum.JSON_CONVERT, "method: jsonToMap(" + json + ")");
        }
    }

    public static String anyToJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new GdngException(GlobalResponseEnum.JSON_CONVERT, "method: anyToJson(" + obj + ")");
        }
    }

    public static <T> T jsonToBean(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            throw new GdngException(GlobalResponseEnum.JSON_CONVERT, "method: jsonToBean(" + json + ", " + clazz.getName() + ")");
        }
    }

}
