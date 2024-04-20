package io.github.pdkst.models.openai.api.chat.request;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;

/**
 * @author pdkst
 * @since 2024/04/18
 */
@Data
@Accessors(chain = true, fluent = true)
public class FunctionDefinitionBuilder {
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
     * 参数类型
     */
    private String type = "object";
    /**
     * 参数列表
     */
    private Map<String, FunctionParameter> properties = new LinkedHashMap<>();
    /**
     * 必填参数
     */
    private List<String> required = new ArrayList<>();


    public FunctionDefinition build() {
        final FunctionParameters parameters = buildParameters();
        return new FunctionDefinition(this.name, this.description, parameters);
    }

    public FunctionDefinitionBuilder required(String... required) {
        this.required.addAll(new ArrayList<>(Arrays.asList(required)));
        return this;
    }

    public FunctionDefinitionBuilder add(String name, FunctionParameter parameter) {
        properties.put(name, parameter);
        return this;
    }

    public FunctionDefinitionBuilder addRequired(String name, FunctionParameter parameter) {
        properties.put(name, parameter);
        required.add(name);
        return this;
    }

    public FunctionDefinitionBuilder add(String name, String type, String description) {
        final FunctionParameter parameter = new FunctionParameter();
        parameter.setType(type);
        parameter.setDescription(description);
        properties.put(name, parameter);
        return this;
    }

    public FunctionDefinitionBuilder addRequired(String name, String type, String description) {
        add(name, type, description);
        required.add(name);
        return this;
    }

    public FunctionParameters buildParameters() {
        final FunctionParameters functionParameters = new FunctionParameters();
        functionParameters.setType(type);
        functionParameters.setProperties(this.properties);
        if (isNotEmpty(required)) {
            functionParameters.setRequired(required);
        }
        return functionParameters;
    }
}
