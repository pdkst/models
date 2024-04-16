package io.github.pdkst.models.openai.api.embeddings;

import io.github.pdkst.models.http.HttpExchanger;
import io.github.pdkst.models.http.HttpResponse;
import io.github.pdkst.models.http.request.HttpRequest;
import io.github.pdkst.models.openai.api.embeddings.request.EmbeddingsRequest;
import io.github.pdkst.models.openai.api.embeddings.response.EmbeddingsResponse;
import lombok.RequiredArgsConstructor;

/**
 * Get a vector representation of a given input that can be easily consumed by machine learning models and algorithms.
 *
 * @author pdkst
 * @see <a href="https://platform.openai.com/docs/api-reference/embeddings">Embeddings</a>
 * @since 2023/11/01
 */
@RequiredArgsConstructor
public class OpenaiEmbeddings {
    private final HttpExchanger exchanger;

    /**
     * Creates an embedding vector representing the input text.
     *
     * @param request request params
     * @return embedding vectors
     * @throws Exception errors
     */
    public EmbeddingsResponse embeddings(EmbeddingsRequest request) throws Exception {
        final HttpRequest post = HttpRequest.post("/embeddings", request);
        final HttpResponse response = exchanger.exchange(post);
        return response.body(EmbeddingsResponse.class);
    }
}
