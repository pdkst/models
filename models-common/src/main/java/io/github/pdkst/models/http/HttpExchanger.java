package io.github.pdkst.models.http;

import io.github.pdkst.models.http.listener.ServerSideEventListener;
import io.github.pdkst.models.http.request.HttpRequest;

/**
 * @author pdkst.zhang
 * @since 2023/11/18
 */
public interface HttpExchanger {

    HttpResponse exchange(HttpRequest request) throws Exception;

    void serverSideEvent(HttpRequest request, ServerSideEventListener listener) throws Exception;
}
