package io.github.pdkst.models.openai.api.audio.request;

import io.github.pdkst.models.annotation.Required;
import io.github.pdkst.models.common.Request;
import lombok.Data;

/**
 * Translates audio into English.
 *
 * @author pdkst
 * @see <a href="https://platform.openai.com/docs/api-reference/audio/createTranslation">Create Translation</a>
 * @since 2024/04/05
 */
@Data
public class CreateTranslationRequest extends Request {
    /**
     * The audio file object (not file name) translate,
     * in one of these formats: flac, mp3, mp4, mpeg, mpga, m4a, ogg, wav, or webm.
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
     * An optional text to guide the model's style or continue a previous audio segment. The prompt should be in
     * English.
     */
    private String prompt;
    /**
     * The format of the transcript output, in one of these options: json, text, srt, verbose_json, or vtt.
     * <p>
     * Defaults to json
     */
    private String response_format;
    /**
     * The sampling temperature, between 0 and 1.
     * <p>
     * Higher values like 0.8 will make the output more random, while lower
     * values like 0.2 will make it more focused and deterministic.
     * <p>
     * If set to 0, the model will use <a href="https://en.wikipedia.org/wiki/Log_probability">log probability</a> to
     * automatically increase the temperature until certain thresholds are hit.
     * <p>
     * Defaults to 0
     */
    private Float temperature;
}
