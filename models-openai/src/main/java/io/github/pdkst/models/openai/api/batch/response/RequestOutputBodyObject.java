package io.github.pdkst.models.openai.api.batch.response;

import io.github.pdkst.models.openai.api.chat.response.CompletionResponse;
import lombok.Data;

/**
 * @author pdkst
 * @since 2024/04/18
 */
@Data
public class RequestOutputBodyObject {
    /**
     * The HTTP status code of the response
     */
    private Integer status_code;
    /**
     * An unique identifier for the OpenAI API request. Please include this request ID when contacting support.
     */
    private String request_id;
    /**
     * The JSON body of the response
     */
    private CompletionResponse body;
}
