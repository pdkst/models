package io.github.pdkst.models.openai.api.finetuning.response;

import lombok.Data;

/**
 * The fine_tuning.job.checkpoint object represents a model checkpoint for a fine-tuning job that is ready to use.
 *
 * @author pdkst.zhang
 * @see
 * <a href="https://platform.openai.com/docs/api-reference/fine-tuning/checkpoint-object">Fine-tuning job checkpoint object</a>
 * @since 2024/04/07
 */
@Data
public class FineTuneJobCheckPointObject {
    /**
     * The checkpoint identifier, which can be referenced in the API endpoints.
     */
    private String id;
    /**
     * The Unix timestamp (in seconds) for when the checkpoint was created.
     */
    private Long created_at;
    /**
     * The name of the fine-tuned checkpoint model that is created.
     */
    private String fine_tuned_model_checkpoint;
    /**
     * The step number that the checkpoint was created at.
     */
    private Integer step_number;
    /**
     * Metrics at the step number during the fine-tuning job.
     */
    private FineTuneMetrics metrics;
    /**
     * The name of the fine-tuning job that this checkpoint was created from.
     */
    private String fine_tuning_job_id;
    /**
     * The object type, which is always "fine_tuning.job.checkpoint".
     */
    private String object;
}
