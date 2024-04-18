package io.github.pdkst.models.openai.api.batch.response;

import lombok.Data;

/**
 * @author pdkst
 * @see <a href="https://platform.openai.com/docs/api-reference/batch/object">Batch Request Object</a>
 * @since 2024/04/17
 */
@Data
public class BatchExecuteError {
    /**
     * An error code identifying the error type.
     */
    private String code;
    /**
     * A human-readable message providing more details about the error.
     */
    private String message;
    /**
     * The name of the parameter that caused the error, if applicable.
     */
    private String param;
    /**
     * The line number of the input file where the error occurred, if applicable.
     */
    private Integer line;
}
