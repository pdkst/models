package io.github.pdkst.models.openai.api.batch.response;

import lombok.Data;

import java.util.Map;

/**
 * The batch object
 *
 * @author pdkst
 * @see <a href="https://platform.openai.com/docs/api-reference/batch/object">Batch Request Object</a>
 * @since 2024/04/16
 */
@Data
public class BatchObject {
    /**
     * the id
     */
    private String id;
    /**
     * The object type, which is always batch.
     */
    private String object;
    /**
     * The OpenAI API endpoint used by the batch.
     */
    private String endpoint;
    /**
     * error
     */
    private BatchErrorList error;
    /**
     * The ID of the input file for the batch.
     */
    private String input_file_id;
    /**
     * The time frame within which the batch should be processed.
     */
    private String completion_window;
    /**
     * The current status of the batch.
     */
    private String status;
    /**
     * The ID of the file containing the outputs of successfully executed requests.
     */
    private String output_file_id;
    /**
     * The ID of the file containing the outputs of requests with errors.
     */
    private String error_file_id;
    /**
     * The Unix timestamp (in seconds) for when the batch was created.
     */
    private Long created_at;
    /**
     * The Unix timestamp (in seconds) for when the batch started processing.
     */
    private Long in_progress_at;
    /**
     * The Unix timestamp (in seconds) for when the batch will expire.
     */
    private Long expires_at;
    /**
     * The Unix timestamp (in seconds) for when the batch started finalizing.
     */
    private Long finalizing_at;
    /**
     * The Unix timestamp (in seconds) for when the batch was completed.
     */
    private Long completed_at;
    /**
     * The Unix timestamp (in seconds) for when the batch failed.
     */
    private Long failed_at;
    /**
     * The Unix timestamp (in seconds) for when the batch expired.
     */
    private Long expired_at;
    /**
     * The Unix timestamp (in seconds) for when the batch started cancelling.
     */
    private Long cancelling_at;
    /**
     * The Unix timestamp (in seconds) for when the batch was cancelled.
     */
    private Long cancelled_at;
    /**
     * The request counts for different statuses within the batch.
     */
    private RequestCounts request_counts;
    /**
     * Set of 16 key-value pairs that can be attached to an object. This can be useful for storing additional
     * information about the object in a structured format. Keys can be a maximum of 64 characters long and values can
     * be a maxium of 512 characters long.
     */
    private Map<String, String> metadata;
}
