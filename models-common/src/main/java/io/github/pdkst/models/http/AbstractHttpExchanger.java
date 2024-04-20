package io.github.pdkst.models.http;

import io.github.pdkst.models.http.listener.StreamEventListener;
import io.github.pdkst.models.http.request.HttpRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pdkst
 * @since 2023/10/29
 */
public abstract class AbstractHttpExchanger implements HttpExchanger {
    private final List<Interceptor> interceptors = new ArrayList<>();

    @Override
    public HttpResponse exchange(HttpRequest request) throws Exception {
        final HttpRequest afterInterpretedRequest = intercept(request);
        return doExchange(afterInterpretedRequest);
    }

    protected abstract HttpResponse doExchange(HttpRequest request) throws Exception;

    public void serverSideEvent(HttpRequest request, StreamEventListener listener) throws Exception {
        final HttpRequest afterInterpretedRequest = intercept(request);
        doServerSideEvent(afterInterpretedRequest, listener);
    }

    protected abstract void doServerSideEvent(HttpRequest afterInterpretedRequest,
                                              StreamEventListener listener) throws Exception;

    private HttpRequest intercept(HttpRequest request) {
        for (Interceptor interceptor : interceptors) {
            request = interceptor.intercept(request);
        }
        return request;
    }

    @Override
    public void addInterceptor(Interceptor interceptor) {
        interceptors.add(interceptor);
    }
}
