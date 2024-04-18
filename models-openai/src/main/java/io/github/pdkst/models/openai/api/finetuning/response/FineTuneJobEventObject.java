package io.github.pdkst.models.openai.api.finetuning.response;

import lombok.Data;

/**
 * @author pdkst
 * @since 2024/04/07
 */
@Data
public class FineTuneJobEventObject {

    /**
     * The object id;
     */
    private String id;
    /**
     * The Unix timestamp (in seconds) for when the event was created.
     */
    private Long created_at;
    /**
     * The level of the event,
     */
    private String level;
    /**
     * message
     */
    private String message;
    /**
     * The object type, which is always fine_tuning.job.event.
     */
    private String object;
}
