package io.github.pdkst.models.openai.api.chat.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A list of tools the model may call. Currently, only functions are supported as a tool. Use this to provide a list
 * of functions the model may generate JSON inputs for.
 *
 * @author pdkst.zhang
 * @since 2023/12/30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToolDefinition {
    /**
     * The type of the tool. Currently, only "function" is supported.
     */
    private String type;
    /**
     * 方法定义
     */
    private FunctionDefinition function;

    public ToolDefinition(FunctionDefinition function) {
        this.type = "function";
        this.function = function;
    }
}
