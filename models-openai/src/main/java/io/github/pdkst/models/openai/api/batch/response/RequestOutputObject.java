package io.github.pdkst.models.openai.api.batch.response;

import lombok.Data;

/**
 * The per-line object of the batch output and error files
 *
 * @author pdkst
 * @see <a href="https://platform.openai.com/docs/api-reference/batch/requestOutput">The request output object</a>
 * @since 2024/04/18
 */
@Data
public class RequestOutputObject {

    private String id;
    /**
     * A developer-provided per-request id that will be used to match outputs to inputs. Must be unique for each
     * request in a batch.
     */
    private String custom_id;

    /**
     * The body of the request.
     */
    private RequestOutputBodyObject response;

    /**
     * error
     */
    private BatchExecuteError error;
}
