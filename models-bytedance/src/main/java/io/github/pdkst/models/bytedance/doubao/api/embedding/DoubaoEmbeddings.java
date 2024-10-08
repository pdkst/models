package io.github.pdkst.models.bytedance.doubao.api.embedding;

import io.github.pdkst.models.bytedance.doubao.api.embedding.request.DoubaoEmbeddingsRequest;
import io.github.pdkst.models.http.HttpExchanger;
import io.github.pdkst.models.http.HttpResponse;
import io.github.pdkst.models.http.request.HttpRequest;
import io.github.pdkst.models.openai.api.embeddings.response.EmbeddingsResponse;
import lombok.RequiredArgsConstructor;

/**
 * @author pdkst
 * @since 2024/10/08
 */
@RequiredArgsConstructor
public class DoubaoEmbeddings {
    private final HttpExchanger exchanger;

    /**
     * Creates an embedding vector representing the input text.
     *
     * @param request request params
     * @return embedding vectors
     * @throws Exception errors
     */
    public EmbeddingsResponse embeddings(DoubaoEmbeddingsRequest request) throws Exception {
        final HttpRequest post = HttpRequest.post("/embeddings", request);
        final HttpResponse response = exchanger.exchange(post);
        return response.body(EmbeddingsResponse.class);
    }
}
