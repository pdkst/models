package io.github.pdkst.models.openai.api.chat.response;

import io.github.pdkst.models.openai.api.chat.request.FunctionCall;
import io.github.pdkst.models.openai.api.chat.request.ToolCall;
import lombok.Data;

import java.util.List;

/**
 * A chat completion delta generated by streamed model responses.
 *
 * @author pdkst.zhang
 * @since 2023/12/30
 */
@Data
public class ChunkChoiceDelta {
    /**
     * The role of the author of this message.
     */
    private String role;
    /**
     * The contents of the chunk message.
     */
    private String content;
    /**
     * Deprecated and replaced by tool_calls. The name and arguments of a function that should be called, as generated
     */
    private FunctionCall function_call;
    /**
     * tool calls
     */
    private List<ToolCall> tool_calls;
}