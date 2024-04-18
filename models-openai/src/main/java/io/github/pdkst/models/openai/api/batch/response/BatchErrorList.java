package io.github.pdkst.models.openai.api.batch.response;

import lombok.Data;

import java.util.List;

/**
 * @author pdkst
 * @see <a href="https://platform.openai.com/docs/api-reference/batch/object">Batch Request Object</a>
 * @since 2024/04/17
 */
@Data
public class BatchErrorList {
    /**
     * The object type, which is always list.
     */
    private String object;
    /**
     * The list of errors.
     */
    private List<BatchExecuteError> data;
}
