package io.github.pdkst.models.openai.api.finetuning.response;

import lombok.Data;

/**
 * @author pdkst
 * @see
 * <a href="https://platform.openai.com/docs/api-reference/fine-tuning/checkpoint-object#fine-tuning/checkpoint-object-metrics">Metrics</a>
 * @since 2024/04/07
 */
@Data
public class FineTuneMetrics {
    private Integer step;
    private Float train_loss;
    private Float train_mean_token_accuracy;
    private Float valid_loss;
    private Float valid_mean_token_accuracy;
    private Float full_valid_loss;
    private Float full_valid_mean_token_accuracy;
}
