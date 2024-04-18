package io.github.pdkst.models.openai.api.batch.response;

import lombok.Data;

/**
 * The request counts for different statuses within the batch.
 *
 * @author pdkst
 * @see <a href="https://platform.openai.com/docs/api-reference/batch/object">Batch Request Object</a>
 * @since 2024/04/16
 */
@Data
public class RequestCounts {
    /**
     * Total number of requests in the batch.
     */
    private Integer total;
    /**
     * Number of requests that have been completed successfully.
     */
    private Integer completed;
    /**
     * Number of requests that have failed.
     */
    private Integer failed;
}