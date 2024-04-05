package io.github.pdkst.models.openai.api.embeddings.response;

import io.github.pdkst.models.openai.api.common.Usage;
import lombok.Data;

import java.util.List;

/**
 * A list of embedding objects.
 *
 * @author pdkst.zhang
 * @since 2023/11/01
 */
@Data
public class EmbeddingsResponse {
    /**
     * The object type, which is always {@code list}
     */
    private String object;

    /**
     * embedding objects
     */
    private List<EmbeddingObject> data;

    /**
     * The model used for the embedding.
     */
    private String model;

    /**
     * Usage statistics for the embedding request.
     */
    private Usage usage;
}
