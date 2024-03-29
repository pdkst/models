package io.github.pdkst.models.openai.client;

import io.github.pdkst.models.http.HttpExchanger;
import io.github.pdkst.models.http.HttpResponse;
import io.github.pdkst.models.http.listener.ServerSideEventListener;
import io.github.pdkst.models.http.request.HttpRequest;
import lombok.RequiredArgsConstructor;

/**
 * @author pdkst.zhang
 * @since 2023/12/30
 */
@RequiredArgsConstructor
public class OpenaiDelegateHttpExchanger implements HttpExchanger {
    private final HttpExchanger delegate;
    private final OpenaiKeySelector openaiKeySelector;

    @Override
    public HttpResponse exchange(HttpRequest request) throws Exception {
        final OpenaiKey openaiKey = openaiKeySelector.select(request.url());
        request.url(openaiKey.getUrl());
        request.header("Authorization", "Bearer " + openaiKey);
        return delegate.exchange(request);
    }

    @Override
    public void serverSideEvent(HttpRequest request, ServerSideEventListener listener) throws Exception {
        final OpenaiKey openaiKey = openaiKeySelector.select(request.url());
        request.url(openaiKey.getUrl());
        request.header("Authorization", "Bearer " + openaiKey);
        this.delegate.serverSideEvent(request, listener);
    }
}
