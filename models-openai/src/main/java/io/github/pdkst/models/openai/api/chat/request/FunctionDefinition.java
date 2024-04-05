package io.github.pdkst.models.openai.api.chat.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 方法生命
 *
 * @author pdkst
 * @since 2023-07-30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FunctionDefinition {
    /**
     * The name of the function to be called.
     * Must be a-z, A-Z, 0-9, or contain underscores and dashes,
     * with a maximum length of 64.
     */
    private String name;
    /**
     * A description of what the function does, used by the model to choose when and how to call the function.
     */
    private String description;
    /**
     * The parameters the functions accepts, described as a JSON Schema object. See the
     * <a href="https://platform.openai.com/docs/guides/text-generation/function-calling">guide</a>
     * for examples, and the <a href="https://json-schema.org/understanding-json-schema/">JSON Schema reference</a>
     * for documentation about the format.
     * <p>
     * Omitting "parameters" defines a function with an empty parameter list.
     */
    private FunctionParameters parameters;
}