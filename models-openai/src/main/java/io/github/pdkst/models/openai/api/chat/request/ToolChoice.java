package io.github.pdkst.models.openai.api.chat.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Controls which (if any) function is called by the model. none means the model will not call a function and instead
 * generates a message. auto means the model can pick between generating a message or calling a function. Specifying
 * a particular function via {"type": "function", "function": {"name": "my_function"}} forces the model to call that
 * function.
 * <p>
 * "none" is the default when no functions are present. "auto" is the default if functions are present.
 *
 * @author pdkst.zhang
 * @since 2023/12/30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToolChoice {
    /**
     * The type of the tool. Currently, only "function" is supported.
     */
    private String type;
    /**
     * The function to call.
     */
    private FunctionChoice function;

    public ToolChoice(FunctionChoice function) {
        this.type = "function";
        this.function = function;
    }

    public ToolChoice(String name) {
        this(new FunctionChoice(name));
    }
}
