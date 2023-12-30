package io.github.pdkst.models.openai.endpoint.chat.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * An object specifying the format that the model must output. Compatible with gpt-4-1106-preview and gpt-3
 * .5-turbo-1106.
 * <p>
 * Setting to { "type": "json_object" } enables JSON mode, which guarantees the message the model generates is valid
 * JSON.
 * <p>
 * Important: when using JSON mode, you must also instruct the model to produce JSON yourself via a system or user
 * message. Without this, the model may generate an unending stream of whitespace until the generation reaches the
 * token limit, resulting in a long-running and seemingly "stuck" request. Also note that the message content may be
 * partially cut off if finish_reason="length", which indicates the generation exceeded max_tokens or the
 * conversation exceeded the max context length.
 *
 * @author pdkst.zhang
 * @since 2023/12/30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseFormat {
    public static final String RESPONSE_FORMAT_JSON_OBJECT = "json_object";
    public static final String RESPONSE_FORMAT_TEXT = "text";
    /**
     * Must be one of "text" or "json_object".
     * <p>
     * Default: text
     */
    private String type;

    public static ResponseFormat json() {
        return new ResponseFormat(RESPONSE_FORMAT_JSON_OBJECT);
    }

    public static ResponseFormat text() {
        return new ResponseFormat(RESPONSE_FORMAT_TEXT);
    }
}
