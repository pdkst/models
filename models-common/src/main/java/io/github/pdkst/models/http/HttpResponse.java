package io.github.pdkst.models.http;

import java.io.InputStream;

/**
 * @author pdkst.zhang
 * @since 2023/10/29
 */
public interface HttpResponse {
    <T> T body(Class<T> resultType) throws Exception;

    InputStream byteStream();
}
