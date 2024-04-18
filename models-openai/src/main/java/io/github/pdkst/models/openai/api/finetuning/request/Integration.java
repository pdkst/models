package io.github.pdkst.models.openai.api.finetuning.request;

import io.github.pdkst.models.annotation.Required;
import lombok.Data;

/**
 * @author pdkst
 * @since 2024/04/07
 */
@Data
public class Integration {
    /**
     * The type of integration to enable. Currently, only "wandb" (Weights and Biases) is supported.
     */
    @Required
    private String type;
    /**
     * The settings for your integration with Weights and Biases. This payload specifies the project that metrics
     * will be sent to. Optionally, you can set an explicit display name for your run, add tags to your run, and set
     * a default entity (team, username, etc) to be associated with your run.
     */
    @Required
    private WandbSettings wandb;
}
