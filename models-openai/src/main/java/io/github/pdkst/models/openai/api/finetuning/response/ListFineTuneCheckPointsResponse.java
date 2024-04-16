package io.github.pdkst.models.openai.api.finetuning.response;

import lombok.Data;

/**
 * @author pdkst
 * @since 2024/04/07
 */
@Data
public class ListFineTuneCheckPointsResponse {
    /**
     * The object type, which is always list.
     */
    private String object;
    /**
     * The list of fine-tune checkpoints.
     */
    private FineTuneJobCheckPointObject[] data;
    /**
     * The first ID in the list.
     */
    private String first_id;
    /**
     * The last ID in the list.
     */
    private String last_id;
    /**
     * Whether there is a next page.
     */
    private Boolean has_more;
}
