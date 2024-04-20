package io.github.pdkst.models.json;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

/**
 * @author pdkst
 * @since 2023/10/29
 */
public class JacksonMapper implements JsonMapper {
    private final ObjectMapper objectMapper;

    public JacksonMapper() {
        this(new ObjectMapper());
    }

    public JacksonMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        configure();
    }

    public void configure() {
        // 设置忽略未知字段
        objectMapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setSerializationInclusion(NON_NULL);
    }

    @Override
    public String value(Object object) throws Exception {
        return objectMapper.writeValueAsString(object);
    }

    @Override
    public <T> T parse(String json, Class<T> tClass) throws IOException {
        return objectMapper.readValue(json, tClass);
    }
}
