package io.github.pdkst.models.openai.api.audio.response;

import lombok.Data;

/**
 * Represents a transcription response returned by model, based on the provided input.
 *
 * @author pdkst.zhang
 * @see <a href="https://platform.openai.com/docs/api-reference/audio/json-object">The transcription object</a>
 * @since 2024/04/05
 */
@Data
public class TranscriptionResponse {
    /**
     * The transcription text.
     */
    private String text;
}
