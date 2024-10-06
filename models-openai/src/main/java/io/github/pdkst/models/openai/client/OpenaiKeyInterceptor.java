package io.github.pdkst.models.openai.client;

import io.github.pdkst.models.http.Authorization;
import io.github.pdkst.models.http.Interceptor;
import io.github.pdkst.models.http.request.HttpRequest;
import lombok.RequiredArgsConstructor;

/**
 * @author pdkst
 * @since 2024/01/19
 */
@RequiredArgsConstructor
public class OpenaiKeyInterceptor implements Interceptor {
    private final OpenaiEndpointSelector openaiKeySelector;

    @Override
    public HttpRequest intercept(HttpRequest request) {
        final OpenaiEndpoint endpoint = openaiKeySelector.select(request.url());
        request.url(endpoint.getUrl());
        final Authorization authorization = endpoint.getAuthorization();
        authorization.authorize(request);
        return request;
    }
}
