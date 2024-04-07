package io.github.pdkst.models.openai.api.finetuning.response;

import io.github.pdkst.models.openai.api.finetuning.request.HyperParameters;
import io.github.pdkst.models.openai.api.finetuning.request.Integration;
import lombok.Data;

/**
 * The fine_tuning.job object represents a fine-tuning job that has been created through the API.
 *
 * @author pdkst.zhang
 * @since 2024/04/07
 */
@Data
public class FineTuneJobObject {
    /**
     * The object identifier, which can be referenced in the API endpoints.
     */
    private String id;
    /**
     * The Unix timestamp (in seconds) for when the fine-tuning job was created.
     */
    private Long created_at;
    /**
     * The Unix timestamp (in seconds) for when the fine-tuning job was finished. The value will be null if the
     * fine-tuning job is still running.
     */
    private Long finished_at;
    /**
     * For fine-tuning jobs that have failed, this will contain more information on the cause of the failure.
     */
    private FineTuneError error;
    /**
     * The base model that is being fine-tuned.
     */
    private String fine_tuned_model;
    /**
     * The hyperparameters used for the fine-tuning job. See the fine-tuning guide for more details.
     */
    private HyperParameters hyperparameters;
    /**
     * The name of the fine-tuned model that is being created. The value will be null if the fine-tuning job is still
     * running.
     */
    private String model;
    /**
     * The object type, which is always "fine_tuning.job".
     */
    private String object;
    /**
     * The total number of billable tokens processed by this fine-tuning job. The value will be null if the
     * fine-tuning job is still running.
     */
    private String organization_id;

    /**
     * The compiled results file ID(s) for the fine-tuning job.
     * You can retrieve the results with the
     * <a href="https://platform.openai.com/docs/api-reference/files/retrieve-contents">Files API</a>.
     */
    private String[] result_files;
    /**
     * The current status of the fine-tuning job,
     * which can be either validating_files, queued, running, succeeded,
     * failed, or cancelled.
     */
    private String status;
    /**
     * The total number of billable tokens processed by this fine-tuning job.
     * The value will be null if the fine-tuning job is still running.
     */
    private Integer trained_tokens;
    /**
     * The file ID used for training. You can retrieve the training data with the
     * <a href="https://platform.openai.com/docs/api-reference/files/retrieve-contents">Files API</a>.
     */
    private String training_file;
    /**
     * The file ID used for validation. You can retrieve the validation results with the
     * <a href="https://platform.openai.com/docs/api-reference/files/retrieve-contents">Files API</a>.
     */
    private String validation_file;
    /**
     * A list of integrations to enable for this fine-tuning job.
     */
    private Integration[] integrations;
    /**
     * The seed used for the fine-tuning job.
     */
    private Integer seed;
}
