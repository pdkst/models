package io.github.pdkst.models.http.auth;

import io.github.pdkst.models.http.Authorization;
import io.github.pdkst.models.http.request.HttpRequest;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author pdkst
 * @since 2024/10/06
 */
public class SimpleAuthorization implements Authorization {
    private final Map<String, String> headers;

    public SimpleAuthorization(Map<String, String> headers) {
        this.headers = Collections.unmodifiableMap(new HashMap<>(headers));
    }

    @Override
    public void authorize(HttpRequest request) {
        if (headers == null || headers.isEmpty()) {
            return;
        }
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            request.header(key, value);
        }
    }

    public static SimpleAuthorization of(Map<String, String> headers) {
        return new SimpleAuthorization(headers);
    }

    public static SimpleAuthorization of(String key, String value) {
        return new SimpleAuthorization(Collections.singletonMap(key, value));
    }
}
