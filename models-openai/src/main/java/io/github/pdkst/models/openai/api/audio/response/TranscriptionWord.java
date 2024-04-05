package io.github.pdkst.models.openai.api.audio.response;

import lombok.Data;

@Data
public class TranscriptionWord {
    /**
     * The text content of the word.
     */
    private String word;
    /**
     * Start time of the word in seconds.
     */
    private Float start;
    /**
     * End time of the word in seconds.
     */
    private Float end;
}