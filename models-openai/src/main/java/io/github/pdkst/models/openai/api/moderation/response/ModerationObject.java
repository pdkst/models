package io.github.pdkst.models.openai.api.moderation.response;

import lombok.Data;

import java.util.Map;

/**
 * moderation object
 *
 * @author pdkst.zhang
 * @since 2023/11/03
 */
@Data
public class ModerationObject {

    /**
     * Whether the content violates <a href="https://platform.openai.com/policies/usage-policies">OpenAI's usage policies</a>.
     */
    private Boolean flagged;
    /**
     * A list of the categories, and whether they are flagged or not.
     */
    private Map<String, Boolean> categories;
    /**
     * A list of the categories along with their scores as predicted by model.
     */
    private Map<String, Double> categoryScores;
}
