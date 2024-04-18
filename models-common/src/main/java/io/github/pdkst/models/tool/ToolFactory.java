package io.github.pdkst.models.tool;

import lombok.Getter;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author pdkst
 * @since 2024/03/21
 */
public class ToolFactory {

    @Getter
    private static final ToolFactory instance = new ToolFactory();

    private static final Map<Class<?>, ToolDescriptor> cached = new ConcurrentHashMap<>();

    public List<ToolCall> parseToolCalls(Object target) {
        final Class<?> aClass = target.getClass();
        final ToolDescriptor descriptor = obtainDescriptor(aClass);
        final List<ToolCall> tools = new ArrayList<>();
        for (Map.Entry<Method, ToolDefinition> entry : descriptor) {
            final Method method = entry.getKey();
            final ToolDefinition definition = entry.getValue();
            tools.add(new ToolCall(definition, method, target));
        }
        return tools;
    }

    private ToolDescriptor obtainDescriptor(Class<?> aClass) {
        ToolDescriptor descriptor = cached.get(aClass);
        if (descriptor != null) {
            return descriptor;
        }
        descriptor = ToolDescriptor.parse(aClass);
        cached.put(aClass, descriptor);
        return descriptor;
    }

    public ToolCall get(Object target, Method method) {
        final ToolDescriptor descriptor = obtainDescriptor(target.getClass());
        ToolDefinition definition = descriptor.getToolDefinition(method);
        return new ToolCall(definition, method, target);
    }
}
