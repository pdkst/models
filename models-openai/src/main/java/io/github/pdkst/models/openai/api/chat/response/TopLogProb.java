package io.github.pdkst.models.openai.api.chat.response;

import lombok.Data;

import java.util.List;

/**
 * List of the most likely tokens and their log probability, at this token position.
 * In rare cases, there may be fewer than the number of requested top_logprobs returned.
 *
 * @author pdkst
 * @since 2023/12/30
 */
@Data
public class TopLogProb {
    /**
     * The token.
     */
    private String token;
    /**
     * The log probability of this token.
     */
    private Double logprob;
    /**
     * A list of integers representing the UTF-8 bytes representation of the token. Useful in instances where
     * characters are represented by multiple tokens and their byte representations must be combined to generate the
     * correct text representation. Can be null if there is no bytes representation for the token.
     */
    private List<Integer> bytes;
}
