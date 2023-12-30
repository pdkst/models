package io.github.pdkst.models.http;

import io.github.pdkst.models.http.listener.ServerSideEventListener;
import io.github.pdkst.models.http.request.HttpRequest;
import lombok.RequiredArgsConstructor;

/**
 * @author pdkst.zhang
 * @since 2023/11/18
 */
@RequiredArgsConstructor
public class DelegateHttpExchanger implements HttpExchanger {
    private final HttpExchanger delegate;

    @Override
    public HttpResponse exchange(HttpRequest request) throws Exception {
        return delegate.exchange(request);
    }

    @Override
    public void serverSideEvent(HttpRequest request, ServerSideEventListener listener) throws Exception {
        delegate.serverSideEvent(request, listener);
    }
}
