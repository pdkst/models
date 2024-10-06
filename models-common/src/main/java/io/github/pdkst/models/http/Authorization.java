package io.github.pdkst.models.http;

import io.github.pdkst.models.http.request.HttpRequest;

/**
 * @author pdkst
 * @since 2024/04/11
 */
public interface Authorization {
    /**
     * 处理登录请求
     *
     * @param request 登录请求
     */
    void authorize(HttpRequest request);
}
