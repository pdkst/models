package io.github.pdkst.models.bytedance.doubao.client;

import io.github.pdkst.models.http.Interceptor;
import io.github.pdkst.models.http.request.HttpRequest;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

/**
 * @author pdkst
 * @since 2024/10/06
 */
@RequiredArgsConstructor
public class DoubaoAccessInterceptor implements Interceptor {
    private final String model;
    private final DoubaoAccessTokenGenerator tokenGenerator;
    private final DoubaoEndpointSelector selector;

    @SneakyThrows
    @Override
    public HttpRequest intercept(HttpRequest request) {
        final String accessToken = tokenGenerator.generate("endpoint", model);
        final DoubaoEndpoint endpoint = selector.select(model, request.url());
        request.url(endpoint.getUrl());
        request.header("Authorization", "Bearer " + accessToken);
        return request;
    }
}
