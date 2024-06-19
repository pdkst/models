package io.github.pdkst.models.openai.api.chat.request;

import lombok.Data;

/**
 * @author pdkst
 * @since 2024/06/19
 */
@Data
public class StreamOptions {
    /**
     * If set, an additional chunk will be streamed before the "data: [DONE]" message.
     * <p>
     * The "usage" field on this chunk shows the token usage statistics for the entire request,
     * and the "choices" field will always be an empty array.
     * All other chunks will also include a "usage" field, but with a null value.
     */
    public Boolean include_usage;
}
