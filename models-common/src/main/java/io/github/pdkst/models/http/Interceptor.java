package io.github.pdkst.models.http;

import io.github.pdkst.models.common.Request;
import io.github.pdkst.models.http.request.HttpRequest;

/**
 * @author pdkst
 * @since 2024/01/19
 */
public interface Interceptor {
    /**
     * 请求拦截
     *
     * @param request 请求
     * @return 拦截后的请求
     */
    HttpRequest intercept(HttpRequest request);
}
