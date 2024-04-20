package io.github.pdkst.models.http;

import io.github.pdkst.models.http.listener.StreamEventListener;
import io.github.pdkst.models.http.request.HttpRequest;
import lombok.RequiredArgsConstructor;

/**
 * @author pdkst
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
    public void serverSideEvent(HttpRequest request, StreamEventListener listener) throws Exception {
        delegate.serverSideEvent(request, listener);
    }

    @Override
    public void addInterceptor(Interceptor interceptor) {
        delegate.addInterceptor(interceptor);
    }
}
