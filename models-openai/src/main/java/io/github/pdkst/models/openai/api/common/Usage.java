package io.github.pdkst.models.openai.api.common;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * token消耗量
 *
 * @author pdkst
 * @since 2023-07-30
 */
@Data
@NoArgsConstructor
public class Usage {
    /**
     * Number of tokens in the generated completion.
     */
    private Integer completion_tokens;
    /**
     * Number of tokens in the prompt.
     */
    private Integer prompt_tokens;
    /**
     * Total number of tokens used in the request (prompt + completion).
     */
    private Integer total_tokens;
}