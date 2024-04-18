package io.github.pdkst.models.tool;

import lombok.Data;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Map;

/**
 * @author pdkst
 * @since 2024/03/21
 */
@Data
public class ToolCall {
    private final ToolDefinition definition;
    private final Method method;
    private final Object target;

    public Object apply(Map<String, Object> parameterMap)
            throws InvocationTargetException, IllegalAccessException {
        final Parameter[] parameters = method.getParameters();
        Object[] parameterArrays = definition.parseParameterArray(parameters, parameterMap);
        return method.invoke(target, parameterArrays);
    }
}
