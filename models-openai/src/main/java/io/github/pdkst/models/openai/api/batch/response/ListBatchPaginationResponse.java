package io.github.pdkst.models.openai.api.batch.response;

import lombok.Data;

import java.util.List;

/**
 * @author pdkst
 * @since 2024/04/18
 */
@Data
public class ListBatchPaginationResponse {
    /**
     * The object type, which is always list.
     */
    private String object;
    /**
     * The first ID in the list.
     */
    private String first_id;
    /**
     * The last ID in the list.
     */
    private String last_id;
    /**
     * The list of objects.
     */
    private List<BatchObject> data;
}
