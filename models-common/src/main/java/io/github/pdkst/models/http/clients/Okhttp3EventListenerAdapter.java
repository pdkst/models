package io.github.pdkst.models.http.clients;

import io.github.pdkst.models.json.JsonMapper;
import io.github.pdkst.models.http.listener.ServerSideEventListener;
import lombok.RequiredArgsConstructor;
import okhttp3.Response;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author pdkst
 * @since 2023/10/29
 */
@RequiredArgsConstructor
public class Okhttp3EventListenerAdapter extends EventSourceListener {
    private final ServerSideEventListener target;
    private final JsonMapper jsonMapper;

    @Override
    public void onOpen(@NotNull EventSource eventSource, @NotNull Response response) {
        final Okhttp3HttpResponse httpResponse = new Okhttp3HttpResponse(response, jsonMapper);
        target.onOpen(httpResponse);
    }

    @Override
    public void onClosed(@NotNull EventSource eventSource) {
        target.onClosed();
    }

    @Override
    public void onEvent(@NotNull EventSource eventSource,
                        @Nullable String id,
                        @Nullable String type,
                        @NotNull String data) {
        target.onEvent(id, type, data);
    }

    @Override
    public void onFailure(@NotNull EventSource eventSource, @Nullable Throwable t, @Nullable Response response) {
        final Okhttp3HttpResponse httpResponse = new Okhttp3HttpResponse(response, jsonMapper);
        target.onFailure(t, httpResponse);
    }
}
