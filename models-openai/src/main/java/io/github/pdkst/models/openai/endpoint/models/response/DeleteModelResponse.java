package io.github.pdkst.models.openai.endpoint.models.response;

import io.github.pdkst.models.common.Response;
import lombok.Data;

/**
 * @author pdkst.zhang
 * @since 2023/11/02
 */
@Data
public class DeleteModelResponse extends Response {
    /**
     * The file identifier, which can be referenced in the API endpoints.
     */
    private String id;
    /**
     * The object type, which is always "model".
     */
    private String object;
    /**
     * deleted status
     */
    private Boolean deleted;
}
