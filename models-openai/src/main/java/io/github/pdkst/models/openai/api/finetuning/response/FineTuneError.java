package io.github.pdkst.models.openai.api.finetuning.response;

import lombok.Data;

/**
 * @author pdkst
 * @since 2024/04/07
 */
@Data
public class FineTuneError {
    /**
     * A machine-readable error code.
     */
    private String code;
    /**
     * A human-readable error message.
     */
    private String message;
    /**
     * The parameter that was invalid, usually training_file or validation_file. This field will be null if the
     * failure was not parameter-specific.
     */
    private String param;
}
