package io.github.pdkst.models.openai.api.embeddings.request;

import io.github.pdkst.models.annotation.Required;
import io.github.pdkst.models.common.Request;
import lombok.Data;

import java.util.List;

/**
 * Creates an embedding vector representing the input text.
 *
 * @author pdkst
 * @since 2023/11/01
 */
@Data
public class EmbeddingsRequest extends Request {
    /**
     * Input text to embed, encoded as a string or array of tokens.
     * To embed multiple inputs in a single request,
     * pass an array of strings or array of token arrays.
     * The input must not exceed the max input tokens for the model
     * (8192 tokens for {@code text-embedding-ada-002}) and cannot be an empty string.
     * <a href="https://cookbook.openai.com/examples/how_to_count_tokens_with_tiktoken">Example Python code</a>
     * for counting tokens.
     * <p>string or array</p>
     */
    @Required
    private List<String> input;

    /**
     * ID of the model to use. You can use the
     * <a href="https://platform.openai.com/docs/api-reference/models/list">List models</a>
     * API to see all of your available models,
     * or see our <a href="https://platform.openai.com/docs/models/overview">Model overview</a> for descriptions of them.
     */
    @Required
    private String model;

    /**
     * The format to return the embeddings in. Can be either {@code float} or {@code base64}.
     * <p>Defaults to "float"</p>
     */
    private String encoding_format;

    /**
     * A unique identifier representing your end-user, which can help OpenAI to monitor and detect abuse.
     * <p>
     * <a href="https://platform.openai.com/docs/guides/safety-best-practices/end-user-ids">Learn more</a>.
     */
    private String user;
}
