package io.github.pdkst.models.openai.api.files.response;

import io.github.pdkst.models.common.Response;
import lombok.Data;

/**
 * @author pdkst
 * @since 2023/11/02
 */
@Data
public class DeleteFileResponse extends Response {
    /**
     * The file identifier, which can be referenced in the API endpoints.
     */
    private String id;
    /**
     * The object type, which is always "file".
     */
    private String object;
    /**
     * deleted status
     */
    private Boolean deleted;
}
