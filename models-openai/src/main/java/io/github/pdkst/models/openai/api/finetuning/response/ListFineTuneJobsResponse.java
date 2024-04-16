package io.github.pdkst.models.openai.api.finetuning.response;

import lombok.Data;

/**
 * A list of paginated fine-tuning job objects.
 *
 * @author pdkst
 * @since 2024/04/07
 */
@Data
public class ListFineTuneJobsResponse {
    /**
     * The object type, which is always list.
     */
    private String object;
    /**
     * The list of fine-tune events.
     */
    private FineTuneJobObject[] data;
    /**
     * Whether there is a next page.
     */
    private Boolean has_more;
}
