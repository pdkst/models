package io.github.pdkst.models.openai.api.chat.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The tool calls generated by the model, such as function calls.
 *
 * @author pdkst
 * @since 2023/12/30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToolCall {
    /**
     * The index of the tool call.
     */
    private Integer index;
    /**
     * The ID of the tool call.
     */
    private String id;
    /**
     * The type of the tool. Currently, only function is supported.
     */
    private String type;
    /**
     * The function that the model called.
     */
    private FunctionCall function;
}
