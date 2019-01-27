package com.lin.cms.admin.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

/**
 * Created by zhouyajun on 2019/1/23
 */

@Component
public class JsonUtil {
    // 定义jackson对象
    private static final ObjectMapper mapper = new ObjectMapper();

    public static String objectToJson(Object data) {
        try {
            return mapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
