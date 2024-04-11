package io.github.pdkst.models.openai.client;

import io.github.pdkst.models.http.Interceptor;
import io.github.pdkst.models.http.request.HttpRequest;
import lombok.RequiredArgsConstructor;

/**
 * @author pdkst.zhang
 * @since 2024/01/19
 */
@RequiredArgsConstructor
public class OpenaiKeyInterceptor implements Interceptor {
    private final OpenaiEndpointSelector openaiKeySelector;

    @Override
    public HttpRequest intercept(HttpRequest request) {
        final OpenaiEndpoint endpoint = openaiKeySelector.select(request.url());
        request.url(endpoint.getUrl());
        final Credentials credentials = endpoint.getCredentials();
        for (String header : credentials) {
            final String euthanize = credentials.get(header);
            request.header(header, euthanize);
        }
        return request;
    }
}
