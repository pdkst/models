package io.github.pdkst.models.openai.api.chat;

import io.github.pdkst.models.http.HttpExchanger;
import io.github.pdkst.models.http.HttpResponse;
import io.github.pdkst.models.http.listener.ServerSideEventListener;
import io.github.pdkst.models.http.request.HttpRequest;
import io.github.pdkst.models.openai.api.chat.request.CompletionRequest;
import io.github.pdkst.models.openai.api.chat.response.CompletionResponse;
import lombok.RequiredArgsConstructor;

/**
 * Given a prompt, the model will return one or more predicted completions,
 * and can also return the probabilities of alternative tokens at each position.
 * We recommend most users use our Chat completions API. Learn more
 *
 * @author pdkst.zhang
 * @see <a href="https://platform.openai.com/docs/api-reference/completions">Completions</a>
 * @see <a href="https://platform.openai.com/docs/guides/gpt/completions-api">Completions API Guide</a>
 * @since 2023/10/29
 */
@RequiredArgsConstructor
public class OpenaiChatCompletion {
    private final HttpExchanger exchanger;

    /**
     * Creates a completion for the provided prompt and parameters.
     *
     * @param request request parameters
     * @return completion
     * @throws Exception errors
     */
    public CompletionResponse completion(CompletionRequest request) throws Exception {
        HttpRequest post = HttpRequest.post("/chat/completions", request);
        final HttpResponse response = exchanger.exchange(post);
        return response.body(CompletionResponse.class);
    }

    /**
     * Creates a completion for the provided prompt and parameters.
     *
     * @param request  request parameters
     * @param listener listen the steam
     * @throws Exception errors
     */
    public void stream(CompletionRequest request, ServerSideEventListener listener) throws Exception {
        final HttpRequest post = HttpRequest.post("/chat/completions", request);
        exchanger.serverSideEvent(post, listener);
    }
}
