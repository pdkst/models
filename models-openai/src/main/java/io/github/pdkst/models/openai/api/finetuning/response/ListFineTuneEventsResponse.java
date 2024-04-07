package io.github.pdkst.models.openai.api.finetuning.response;

import lombok.Data;

/**
 * @author pdkst.zhang
 * @since 2024/04/07
 */
@Data
public class ListFineTuneEventsResponse {
    /**
     * The object type, which is always list.
     */
    private String object;
    /**
     * The list of fine-tune events.
     */
    private FineTuneJobEventObject[] data;
    /**
     * Whether there is a next page.
     */
    private Boolean has_more;
}
