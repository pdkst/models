package io.github.pdkst.models.openai.api.chat.response;

import lombok.Data;

import java.util.List;

/**
 * message content tokens with log probability information.
 *
 * @author pdkst.zhang
 * @since 2023/12/30
 */
@Data
public class LogProbContent {
    /**
     * The token.
     */
    private String token;
    /**
     * The log probability of this token.
     */
    private Double logprob;
    /**
     * A list of integers representing the UTF-8 bytes representation of the token.
     * Useful in instances where
     */
    private List<Integer> bytes;

    /**
     * List of the most likely tokens and their log probability, at this token position.
     * In rare cases, there may be
     */
    private List<TopLogProb> top_logprobs;
}
