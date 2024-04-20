package io.github.pdkst.models.http.listener;

import io.github.pdkst.models.http.HttpResponse;
import org.jetbrains.annotations.Nullable;

/**
 * @author pdkst
 * @since 2023/10/29
 */
public interface StreamEventListener {
    void onOpen(HttpResponse httpResponse);

    void onEvent(String id, String type, String data);

    void onClosed();

    void onFailure(@Nullable Throwable t, @Nullable HttpResponse httpResponse);
}
