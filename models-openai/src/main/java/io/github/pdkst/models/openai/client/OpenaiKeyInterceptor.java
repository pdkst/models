package io.github.pdkst.models.openai.client;

import io.github.pdkst.models.http.Interceptor;
import io.github.pdkst.models.http.request.HttpRequest;
import io.github.pdkst.models.openai.client.OpenaiKey;
import io.github.pdkst.models.openai.client.OpenaiKeySelector;
import lombok.RequiredArgsConstructor;

/**
 * @author pdkst.zhang
 * @since 2024/01/19
 */
@RequiredArgsConstructor
public class OpenaiKeyInterceptor implements Interceptor {
    private final OpenaiKeySelector openaiKeySelector;

    @Override
    public HttpRequest intercept(HttpRequest request) {
        final OpenaiKey openaiKey = openaiKeySelector.select(request.url());
        request.url(openaiKey.getUrl());
        request.header("Authorization", "Bearer " + openaiKey);
        return request;
    }
}
