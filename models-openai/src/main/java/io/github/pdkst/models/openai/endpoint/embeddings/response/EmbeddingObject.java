package io.github.pdkst.models.openai.endpoint.embeddings.response;

import lombok.Data;

import java.util.List;

/**
 * Get a vector representation of a given input that can be easily consumed by machine learning models and algorithms.
 * <p>
 * Related guide: <a href="https://platform.openai.com/docs/guides/embeddings">Embeddings</a>
 *
 * @author pdkst.zhang
 * @see <a href="https://platform.openai.com/docs/api-reference/embeddings/object">The embedding object</a>
 * @since 2023/11/01
 */
@Data
public class EmbeddingObject {
    /**
     * The index of the embedding in the list of embeddings.
     */
    private Integer index;
    /**
     * The embedding vector, which is a list of floats.
     * The length of vector depends on the model as listed in the
     * <a href="https://platform.openai.com/docs/guides/embeddings">embedding guide</a>.
     */
    private List<Float> embedding;
    /**
     * The object type, which is always "embedding".
     */
    private String object;
}
