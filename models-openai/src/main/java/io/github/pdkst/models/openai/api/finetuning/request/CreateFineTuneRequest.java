package io.github.pdkst.models.openai.api.finetuning.request;

import io.github.pdkst.models.annotation.Required;
import io.github.pdkst.models.common.Request;
import lombok.Data;

/**
 * @author pdkst
 * @since 2024/04/07
 */
@Data
public class CreateFineTuneRequest extends Request {
    /**
     * The name of the model to fine-tune. You can select one of the
     * <a href="https://platform.openai.com/docs/guides/fine-tuning/what-models-can-be-fine-tuned">supported models</a>.
     */
    @Required
    private String model;
    /**
     * The ID of an uploaded file that contains training data.
     * <p>
     * See <a href="https://platform.openai.com/docs/api-reference/files/upload">upload file</a>
     * for how to upload a file.
     * <p>
     * Your dataset must be formatted as a JSONL file. Additionally, you must upload your file with the purpose
     * "fine-tune".
     * <p>
     * See the <a href="https://platform.openai.com/docs/guides/fine-tuning">fine-tuning guide</a> for more details.
     */
    @Required
    private String training_file;
    /**
     * The hyperparameters used for the fine-tuning job.
     */
    private HyperParameters hyperparameters;
    /**
     * A string of up to 18 characters that will be added to your fine-tuned model name.
     * <p>
     * For example, a suffix of "custom-model-name" would produce a model name like
     * <code>ft:gpt-3.5-turbo:openai:custom-model-name:7p4lURel</code>.
     */
    private String suffix;
    /**
     * The ID of an uploaded file that contains validation data.
     * <p>
     * If you provide this file, the data is used to generate validation metrics periodically during fine-tuning.
     * These metrics can be viewed in the fine-tuning results file. The same data should not be present in both train
     * and validation files.
     * <p>
     * Your dataset must be formatted as a JSONL file. You must upload your file with the purpose "fine-tune".
     * <p>
     * See the <a href="https://platform.openai.com/docs/guides/fine-tuning">fine-tuning guide</a> for more details.
     */
    private String validation_file;
    /**
     * A list of integrations to enable for your fine-tuning job.
     */
    private Integration[] integrations;
    /**
     * The seed controls the reproducibility of the job. Passing in the same seed and job parameters should produce
     * the same results, but may differ in rare cases. If a seed is not specified, one will be generated for you.
     */
    private Integer seed;
}
