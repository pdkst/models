package io.github.pdkst.models.openai.endpoint.moderation.response;

import lombok.Data;

import java.util.List;

/**
 * Represents policy compliance report by OpenAI's content moderation model against a given input.
 *
 * @author pdkst.zhang
 * @since 2023/11/03
 */
@Data
public class ModerationResponse {
    /**
     * The unique identifier for the moderation request.
     */
    private String id;
    /**
     * The model used to generate the moderation results.
     */
    private String model;
    /**
     * A list of moderation objects.
     */
    private List<ModerationObject> results;
}
