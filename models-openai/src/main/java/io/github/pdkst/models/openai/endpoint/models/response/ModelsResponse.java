package io.github.pdkst.models.openai.endpoint.models.response;

import io.github.pdkst.models.common.Response;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Models list response
 *
 * @author pdkst.zhang
 * @since 2023/11/03
 */
@NoArgsConstructor
@Data
public class ModelsResponse extends Response {

    /**
     * The object type, which is always "list".
     */
    private String object;
    /**
     * models
     */
    private List<ModelObject> data;
}
