package io.github.pdkst.models.openai.api.chat.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * A list of messages comprising the conversation so far.
 * <a href="https://cookbook.openai.com/examples/how_to_format_inputs_to_chatgpt_models">Example Python code</a> .
 *
 * @author pdkst
 * @since 2023-07-30
 */
@Data
@NoArgsConstructor
public class Message {
    /**
     * The role of the messages author.
     * One of {@code system}, {@code user}, {@code assistant}, or {@code function}.
     */
    private String role;
    /**
     * The contents of the message.
     * {@code content} is required for all messages,
     * and may be null for assistant messages with function calls.
     */
    private String content;
    /**
     * The name of the author of this message.
     * {@code name} is required if role is {@code function},
     * and it should be the name of the function whose response is in the {@code content}.
     * <p>
     * May contain a-z, A-Z, 0-9, and underscores,
     * with a maximum length of 64 characters.
     */
    private String name;
    /**
     * The name and arguments of a function that should be called, as generated by the model.
     */
    private FunctionCall function_call;
    /**
     * Tool call that this message is responding to.
     */
    private String tool_call_id;
    /**
     * The tool calls generated by the model, such as function calls.
     */
    private List<ToolCall> tool_calls;

    public static Message system(String content) {
        final Message message = new Message();
        message.setRole("system");
        message.setContent(content);
        return message;
    }

    public static Message user(String content) {
        final Message message = new Message();
        message.setRole("user");
        message.setContent(content);
        return message;
    }

    public static Message assistant(String content) {
        final Message message = new Message();
        message.setRole("assistant");
        message.setContent(content);
        return message;
    }

    public static Message function(String name, String content) {
        final Message message = new Message();
        message.setRole("function");
        message.setName(name);
        message.setContent(content);
        return message;
    }

    public static Message tool(String toolCallId, String content) {
        final Message message = new Message();
        message.setRole("tool");
        message.setTool_call_id(toolCallId);
        message.setContent(content);
        return message;
    }
}