package io.github.pdkst.models.tool;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author pdkst.zhang
 * @since 2024/03/20
 */
@Data
public class ToolDefinition {
    private String name;
    private String description;
    private List<ToolParameter> parameters;

    public ToolDefinition(String name, String description) {
        this.name = name;
        this.description = description;
        this.parameters = new ArrayList<>();
    }

    public void addParameter(ToolParameter parameter) {
        parameters.add(parameter);
    }

    public Object[] parseParameterArray(Map<String, Object> parameterMap) {
        if (parameters == null || parameters.isEmpty()) {
            return new Object[0];
        }

        final Object[] parameterArray = new Object[parameters.size()];
        for (ToolParameter parameter : parameters) {
            final Integer index = parameter.getIndex();
            final String parameterName = parameter.getName();
            parameterArray[index] = parameterMap.get(parameterName);
        }
        return parameterArray;
    }
}
