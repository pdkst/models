package io.github.pdkst.models.openai.api.chat.request;

import io.github.pdkst.models.annotation.Required;
import io.github.pdkst.models.common.Request;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * {@code POST https://api.openai.com/v1/chat/completions}
 * <p>
 * Creates a model response for the given chat conversation.
 *
 * @author pdkst.zhang
 * @see <a href="https://platform.openai.com/docs/api-reference/chat/create">Create chat completion</a>
 * @since 2023/07/30
 */
@Data
@NoArgsConstructor
public class CompletionRequest extends Request {
    /**
     * ID of the model to use.
     * See the
     * <a href="https://platform.openai.com/docs/models/model-endpoint-compatibility">model endpoint compatibility</a>
     * table for details on which models work with the Chat API.
     */
    @Required
    private String model;

    /**
     * If set, partial message deltas will be sent, like in ChatGPT.
     * Tokens will be sent as data-only
     * <a href="https://developer.mozilla.org/en-US/docs/Web/API/Server-sent_events/Using_server-sent_events#Event_stream_format">server-sent events</a> as they become available,
     * with the stream terminated by a {@code data: [DONE]} message.
     * <p>
     * <a href="https://cookbook.openai.com/examples/how_to_stream_completions">Example Python code</a>.
     * <p>Defaults to false</p>
     */
    private Boolean stream;

    /**
     * A list of messages comprising the conversation so far.
     * <a href="https://cookbook.openai.com/examples/how_to_format_inputs_to_chatgpt_models">Example Python code</a>.
     */
    @Required
    private List<Message> messages;

    /**
     * Controls how the model calls functions.
     * <ul>
     *     <li>"none" means the model will not call a function and instead generates a message.</li>
     *     <li>"auto" means the model can pick between generating a message or calling a function.</li>
     * </ul>
     * Specifying a particular function via {@code {"name": "my_function"}} forces the model to call that function.
     * "none" is the default when no functions are present.
     * "auto" is the default if functions are present.
     * <p>Defaults to "auto"</p>
     *
     * @see FunctionChoice
     */
    private Object function_call;

    /**
     * A list of functions the model may generate JSON inputs for.
     */
    private List<FunctionDefinition> functions;

    /**
     * Controls which (if any) function is called by the model.
     * <ul>
     *     <li>"none" means the model will not call a function and instead generates a message.</li>
     *     <li>"auto" means the model can pick between generating a message or calling a function.</li>
     * </ul>
     * Specifying a particular function via {"type": "function", "function": {"name": "my_function"}} forces the
     * model to call that function.
     * <p>
     * "none" is the default when no functions are present. "auto" is the default if functions are present.
     *
     * @see ToolChoice
     */
    private Object tool_choice;

    /**
     * A list of tools the model may call. Currently, only functions are supported as a tool. Use this to provide a
     * list of functions the model may generate JSON inputs for.
     */
    private List<ToolDefinition> tools;

    /**
     * Modify the likelihood of specified tokens appearing in the completion.
     * <p>
     * Accepts a json object that maps tokens (specified by their token ID in the tokenizer) to an associated bias
     * value from -100 to 100.
     * Mathematically, the bias is added to the logits generated by the model prior to sampling.
     * The exact effect will vary per model, but values between -1 and 1 should decrease or increase likelihood of
     * selection;
     * values like -100 or 100 should result in a ban or exclusive selection of the relevant token.
     */
    private Map<String, Object> logit_bias;

    /**
     * The maximum number of <a href="https://platform.openai.com/tokenizer">tokens</a>
     * to generate in the chat completion.
     * <p>
     * The total length of input tokens and generated tokens is limited by the model's context length.
     * <a href="https://cookbook.openai.com/examples/how_to_count_tokens_with_tiktoken">Example Python code</a>
     * for counting tokens.
     * <p> Defaults to inf </p>
     */
    private Integer max_tokens;

    /**
     * How many chat completion choices to generate for each input message.
     * <p>Defaults to 1</p>
     */
    private Integer n;

    /**
     * Number between -2.0 and 2.0.
     * <p>
     * Positive values penalize new tokens based on their existing frequency in the text so far,
     * decreasing the model's likelihood to repeat the same line verbatim.
     * </p>
     * <p>Defaults to 0</p>
     */
    private Float frequency_penalty;

    /**
     * Number between -2.0 and 2.0.
     * Positive values penalize new tokens based on whether they appear in the text so far,
     * increasing the model's likelihood to talk about new topics.
     * <p>Defaults to 0</p>
     */
    private Float presence_penalty;

    /**
     * An object specifying the format that the model must output. Compatible with gpt-4-1106-preview and gpt-3
     * .5-turbo-1106.
     * <p>
     * Setting to { "type": "json_object" } enables JSON mode, which guarantees the message the model generates is
     * valid JSON.
     * <p>
     * Important: when using JSON mode, you must also instruct the model to produce JSON yourself via a system or
     * user message. Without this, the model may generate an unending stream of whitespace until the generation
     * reaches the token limit, resulting in a long-running and seemingly "stuck" request. Also note that the message
     * content may be partially cut off if finish_reason="length", which indicates the generation exceeded max_tokens
     * or the conversation exceeded the max context length.
     */
    private ResponseFormat response_format;
    /**
     * This feature is in Beta. If specified, our system will make a best effort to sample deterministically, such
     * that repeated requests with the same seed and parameters should return the same result. Determinism is not
     * guaranteed, and you should refer to the system_fingerprint response parameter to monitor changes in the backend.
     */
    private Integer seed;

    /**
     * Up to 4 sequences where the API will stop generating further tokens.
     * <p>string / array / null; Defaults to null</p>
     */
    private List<String> stop;

    /**
     * What sampling temperature to use, between 0 and 2.
     * Higher values like 0.8 will make the output more random,
     * while lower values like 0.2 will make it more focused and deterministic.
     * <p>
     * We generally recommend altering this or {@code top_p} but not both.
     * <p>Defaults to 1</p>
     */
    private Float temperature;

    /**
     * An alternative to sampling with temperature, called nucleus sampling, where the model considers the results of
     * the tokens with top_p probability mass. So 0.1 means only the tokens comprising the top 10% probability mass
     * are considered.
     * <p>
     * We generally recommend altering this or {@code temperature} but not both.
     * <p>Defaults to 1</p>
     */
    private Float top_p;

    /**
     * A unique identifier representing your end-user, which can help OpenAI to monitor and detect abuse.
     * <p>
     * <a href="https://platform.openai.com/docs/guides/safety-best-practices/end-user-ids">Learn more</a>.
     */
    private String user;

}