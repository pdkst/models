package io.github.pdkst.models.openai.api.chat.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Deprecated in favor of tool_choice.
 * <p>
 * Controls which (if any) function is called by the model. none means the model will not call a function and instead
 * generates a message. auto means the model can pick between generating a message or calling a function. Specifying
 * a particular function via {"name": "my_function"} forces the model to call that function.
 * <p>
 * "none" is the default when no functions are present. "auto" is the default if functions are present.
 *
 * @author pdkst.zhang
 * @since 2023/10/29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FunctionChoice {
    /**
     * The name of the function to call.
     */
    private String name;
}
