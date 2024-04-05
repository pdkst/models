package io.github.pdkst.models.openai.api.audio.response;

import lombok.Data;

/**
 * Represents a verbose json transcription response returned by model, based on the provided input.
 *
 * @author pdkst.zhang
 * @see <a href="https://platform.openai.com/docs/api-reference/audio/verbose-json-object">The transcription object</a>
 * @since 2024/04/05
 */
@Data
public class TranscriptionVerboseResponse {
    /**
     * The language of the input audio.
     */
    private String language;
    /**
     * The transcribed text.
     */
    private String text;
    /**
     * The duration of the input audio.
     */
    private String duration;
    /**
     * Extracted words and their corresponding timestamps.
     */
    private TranscriptionWord[] words;
    /**
     * Segments of the transcribed text and their corresponding details.
     */
    private TranscriptionSegment[] segments;

}
