package io.github.pdkst.models.openai.api.audio.request;

import io.github.pdkst.models.annotation.Required;
import io.github.pdkst.models.common.Request;
import lombok.Data;

/**
 * @author pdkst
 * @since 2024/04/05
 */
@Data
public class CreateSpeechRequest extends Request {
    /**
     * One of the available <a href="https://platform.openai.com/docs/models/tts">TTS models</a>: "tts-1" or "tts-1-hd"
     */
    @Required
    private String model;
    /**
     * The text to generate audio for. The maximum length is 4096 characters.
     */
    @Required
    private String input;
    /**
     * The voice to use when generating the audio. Supported voices are alloy, echo, fable, onyx, nova, and shimmer.
     * Previews of the voices are available in the
     * <a href='https://platform.openai.com/docs/guides/text-to-speech/voice-options'>Text to speech guide</a>.
     */
    @Required
    private String voice;
    /**
     * The format to audio in. Supported formats are "mp3", "opus", "aac", "flac", "wav", and "pcm".
     */
    private String response_format;
    /**
     * The speed of the generated audio. Select a value from "0.25" to "4.0".
     * <p>
     * "1.0" is the default.
     */
    private Float speed;
}
