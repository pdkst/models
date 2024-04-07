package io.github.pdkst.models.openai.api.finetuning.request;

import lombok.Data;

/**
 * The hyperparameters used for the fine-tuning job.
 *
 * @author pdkst.zhang
 * @since 2024/04/07
 */
@Data
public class HyperParameters {
    /**
     * Number of examples in each batch. A larger batch size means that model parameters are updated less frequently,
     * but with lower variance.
     * <p>
     * string or integer
     */
    private Integer batch_size;
    /**
     * Scaling factor for the learning rate. A smaller learning rate may be useful to avoid overfitting.
     * <p>
     * string or number
     */
    private Float learning_rate_multiplier;
    /**
     * The number of epochs to train the model for. An epoch refers to one full cycle through the training dataset.
     * <p>
     * string or integer
     */
    private Integer n_epochs;
}
