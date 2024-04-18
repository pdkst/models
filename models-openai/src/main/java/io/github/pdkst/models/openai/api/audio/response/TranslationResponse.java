package io.github.pdkst.models.openai.api.audio.response;

import lombok.Data;

/**
 * @author pdkst
 * @since 2024/04/05
 */
@Data
public class TranslationResponse {
    /**
     * The translated text.
     */
    private String text;
}
