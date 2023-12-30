package io.github.pdkst.models.json;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author pdkst.zhang
 * @since 2023/10/29
 */
public class JacksonMapper implements JsonMapper {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String value(Object object) throws Exception {
        return objectMapper.writeValueAsString(object);
    }

    @Override
    public <T> T parse(String json, Class<T> tClass) throws Exception {
        return objectMapper.readValue(json, tClass);
    }
}
