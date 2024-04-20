package io.github.pdkst.models.json;

import java.io.IOException;

/**
 * @author pdkst
 * @since 2023/10/29
 */
public interface JsonMapper {
    String value(Object object) throws Exception;

    <T> T parse(String json, Class<T> tClass) throws IOException;
}
