package io.github.pdkst.models.openai.api.audio.response;

import lombok.Data;

/**
 * @author pdkst
 * @since 2024/04/05
 */
@Data
public class TranscriptionSegment {
    /**
     * Unique identifier of the segment.
     */
    private Long id;
    /**
     * Seek offset of the segment.
     */
    private Long seek;
    /**
     * Start time of the segment in seconds.
     */
    private Float start;
    /**
     * End time of the segment in seconds.
     */
    private Float end;
    /**
     * Text content of the segment.
     */
    private String text;
    /**
     * Array of token IDs for the text content.
     */
    private Integer[] tokens;
    /**
     * Temperature parameter used for generating the segment.
     */
    private Float temperature;
    /**
     * Average logprob of the segment. If the value is lower than -1, consider the logprobs failed.
     */
    private Float avg_logprob;
    /**
     * Compression ratio of the segment. If the value is greater than 2.4, consider the compression failed.
     */
    private Float compression_ratio;
    /**
     * Probability of no speech in the segment. If the value is higher than 1.0 and the avg_logprob is below -1,
     * consider this segment silent.
     */
    private Float no_speech_prob;
}
