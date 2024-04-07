package io.github.pdkst.models.openai.api.chat.listener;

import io.github.pdkst.models.openai.api.chat.response.CompletionChunkResponse;
import org.jetbrains.annotations.Nullable;

/**
 * @author pdkst.zhang
 * @since 2024/03/12
 */
public interface OpenaiEventReceiver {
    /**
     * on connection open
     */
    default void onOpen() {
    }

    /**
     * on receive a raw event
     *
     * @param id   eventId
     * @param type event type
     * @param data event payload
     */
    default void onRawEvent(String id, String type, String data) {
        // just ignore
    }

    /**
     * after event handle
     *
     * @param id   event id
     * @param type event type
     * @param data event payload
     */
    default void afterEvent(String id, String type, String data) {
        // just ignore
    }

    /**
     * on event, mapped CompletionChunkResponse, if raise a exception dispatch OnError
     *
     * @param response response
     */
    default void onEvent(CompletionChunkResponse response) {
    }

    /**
     * on Exception raise
     *
     * @param e    exception
     * @param data event payload,may null
     */
    default void onError(Throwable e, @Nullable String data) {

    }

    /**
     * on dispatch [DONE]
     */
    default void onDone() {

    }

    /**
     * on connection close
     */
    default void onClose() {

    }
}
