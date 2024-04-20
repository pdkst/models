package io.github.pdkst.models.openai.api.chat.listener;

import io.github.pdkst.models.http.HttpResponse;
import io.github.pdkst.models.http.listener.StreamEventListener;
import io.github.pdkst.models.json.JacksonMapper;
import io.github.pdkst.models.json.JsonMapper;
import io.github.pdkst.models.openai.api.chat.response.CompletionChunkResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Nullable;

/**
 * @author pdkst
 * @since 2024/03/12
 */
@Slf4j
@RequiredArgsConstructor
public class OpenaiEventListener implements StreamEventListener {
    private final OpenaiEventReceiver receiver;
    private final JsonMapper jsonMapper;

    public OpenaiEventListener(OpenaiEventReceiver receiver) {
        this(receiver, new JacksonMapper());
    }

    @Override
    public void onOpen(HttpResponse httpResponse) {
        receiver.onOpen();
    }

    @Override
    public void onEvent(String id, String type, String data) {
        receiver.onRawEvent(id, type, data);
        dispatch(data);
        receiver.afterEvent(id, type, data);
    }

    private void dispatch(String data) {
        if ("[DONE]".equals(data)) {
            receiver.onDone();
            return;
        }
        try {
            final CompletionChunkResponse response = jsonMapper.parse(data, CompletionChunkResponse.class);
            receiver.onEvent(response);
        } catch (Exception e) {
            receiver.onError(e, data);
        }
    }

    @Override
    public void onClosed() {
        receiver.onClose();
    }

    @Override
    public void onFailure(@Nullable Throwable t, @Nullable HttpResponse httpResponse) {
        log.error("OpenaiEventListener onFailure: ", t);
        receiver.onError(t, httpResponse.string());
    }
}
