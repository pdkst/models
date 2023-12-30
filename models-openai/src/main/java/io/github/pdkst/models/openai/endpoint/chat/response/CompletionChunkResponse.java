package io.github.pdkst.models.openai.endpoint.chat.response;

import io.github.pdkst.models.common.Response;
import io.github.pdkst.models.openai.endpoint.common.Usage;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Represents a completion response from the API.
 * <p>
 * Note: both the streamed and non-streamed response objects share the same shape (unlike the chat endpoint).
 *
 * @author pdkst
 * @since 2023-07-30
 */
@Data
@NoArgsConstructor
public class CompletionChunkResponse extends Response {
    /**
     * A unique identifier for the chat completion.
     */
    private String id;
    /**
     * A list of chat completion choices. Can be more than one if {@code n} is greater than 1.
     */
    private List<ChunkChoice> choices;
    /**
     * The object type, which is always {@code chat.completion}.
     */
    private String object;
    /**
     * The Unix timestamp (in seconds) of when the chat completion was created.
     */
    private Long created;
    /**
     * The model used for the chat completion.
     */
    private String model;
    /**
     * Usage statistics for the completion request.
     */
    private Usage usage;
    /**
     * This fingerprint represents the backend configuration that the model runs with. Can be used in conjunction
     * with the seed request parameter to understand when backend changes have been made that might impact determinism.
     */
    private String system_fingerprint;
}
