package io.github.pdkst.models.openai.api.models.response;

import lombok.Data;

/**
 * Describes an OpenAI model offering that can be used with the API.
 *
 * @author pdkst.zhang
 * @since 2023/11/03
 */
@Data
public class ModelObject {
    /**
     * The model identifier, which can be referenced in the API endpoints.
     */
    private String id;
    /**
     * The object type, which is always "model".
     */
    private String object;
    /**
     * The Unix timestamp (in seconds) when the model was created.
     */
    private Integer created;
    /**
     * The organization that owns the model.
     */
    private String ownedBy;
}
