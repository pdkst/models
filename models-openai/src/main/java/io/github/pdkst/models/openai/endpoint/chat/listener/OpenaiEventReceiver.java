package io.github.pdkst.models.openai.endpoint.chat.listener;

import io.github.pdkst.models.openai.endpoint.chat.response.CompletionChunkResponse;

/**
 * @author pdkst.zhang
 * @since 2024/03/12
 */
public interface OpenaiEventReceiver {
    default void onOpen() {
    }

    default void onEvent(CompletionChunkResponse response) {
    }

    default void onError(Throwable e, String data) {

    }

    default void onDone() {

    }

    default void onClose() {

    }

}
