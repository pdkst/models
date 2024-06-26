package io.github.pdkst.models.openai.api.chat.response;

import lombok.Data;

import java.util.List;

/**
 * Log probability information for the choice.
 *
 * @author pdkst
 * @since 2023/12/30
 */
@Data
public class LogProb {
    /**
     * A list of message content tokens with log probability information.
     */
    private List<LogProbContent> content;
}
