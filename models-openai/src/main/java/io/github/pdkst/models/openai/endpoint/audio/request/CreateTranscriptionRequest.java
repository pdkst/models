package io.github.pdkst.models.openai.endpoint.audio.request;

import io.github.pdkst.models.annotation.Required;
import io.github.pdkst.models.common.Request;
import lombok.Data;

/**
 * Transcribes audio into the input language.
 *
 * @author pdkst.zhang
 * @since 2024/04/05
 */
@Data
public class CreateTranscriptionRequest extends Request {
    /**
     * The audio file object (not file name) to transcribe, in one of these formats: flac, mp3, mp4, mpeg, mpga, m4a,
     * ogg, wav, or webm.
     */
    @Required
    private String file;
    /**
     * ID of the model to use. Only whisper-1 (which is powered by our open source Whisper V2 model) is currently
     * available.
     */
    @Required
    private String model;
    /**
     * The language of the input audio.
     * Supplying the input language in <a href="https://en.wikipedia.org/wiki/ISO_639-1">ISO-639-1</a>
     * format will improve accuracy and
     */
    private String language;
    /**
     * An optional text to guide the model's style or continue a previous audio segment.
     * The <a href="https://platform.openai.com/docs/guides/speech-to-text/prompting">prompt</a>
     * should match the audio language.
     */
    private String prompt;
    /**
     * The format of the transcript output, in one of these options: "json", "text", "srt", "verbose_json", or "vtt".
     * <p>
     * Defaults to "json"
     */
    private String response_format;
    /**
     * The sampling temperature, between 0 and 1. Higher values like 0.8 will make the output more random, while lower
     * values like 0.2 will make it more focused and deterministic.
     * <p>
     * If set to 0, the model will use <a href="https://en.wikipedia.org/wiki/Log_probability">log probability</a> to
     * automatically increase the temperature until certain thresholds are hit.
     * <p>
     * Defaults to 0
     */
    private Float temperature;
    /**
     * Defaults to segment
     * The timestamp granularities to populate for this transcription.
     * response_format must be set verbose_json to use timestamp granularities.
     * Either or both of these options are supported: word, or segment.
     * <p>
     * Note: There is no additional latency for segment timestamps, but generating word timestamps incurs additional
     * latency.
     */
    private String[] timestamp_granularities;

}
