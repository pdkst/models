package io.github.pdkst.models.openai.api.batch.request;

import io.github.pdkst.models.openai.api.chat.request.CompletionRequest;
import lombok.Data;

/**
 * The per-line object of the batch input file
 *
 * @author pdkst
 * @see <a href="https://platform.openai.com/docs/api-reference/batch/requestInput">The request input object</a>
 * @since 2024/04/18
 */
@Data
public class RequestInputObject {
    /**
     * A developer-provided per-request id that will be used to match outputs to inputs. Must be unique for each
     * request in a batch.
     */
    private String custom_id;
    /**
     * The HTTP method to be used for the request. Currently only POST is supported.
     */
    private String method;
    /**
     * The OpenAI API relative URL to be used for the request. Currently only /v1/chat/completions is supported.
     */
    private String url;
    /**
     * The body of the request.
     */
    private CompletionRequest body;
}
