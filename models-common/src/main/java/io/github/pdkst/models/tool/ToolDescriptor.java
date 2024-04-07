package io.github.pdkst.models.tool;

import io.github.pdkst.models.tool.annotation.Param;
import io.github.pdkst.models.tool.annotation.Tool;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author pdkst.zhang
 * @since 2024/03/21
 */
@RequiredArgsConstructor
public class ToolDescriptor implements Iterable<Map.Entry<Method, ToolDefinition>> {

    private final Map<Method, ToolDefinition> cachedMap = new ConcurrentHashMap<>();

    public static ToolDescriptor parse(Class<?> clazz) {
        final ToolDescriptor descriptor = new ToolDescriptor();
        final Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Tool.class)) {
                descriptor.getToolDefinition(method);
            }
        }
        return descriptor;
    }

    public ToolDefinition getToolDefinition(Method method) {
        ToolDefinition toolDefinition = cachedMap.get(method);
        if (toolDefinition == null) {
            toolDefinition = parseDefinition(method);
            cachedMap.put(method, toolDefinition);
        }
        return toolDefinition;
    }

    private ToolDefinition parseDefinition(Method method) {
        final ToolDefinition definition = parseMethod(method);
        final Parameter[] parameters = method.getParameters();
        List<ToolParameter> parameterList = parseParameters(parameters);
        definition.setParameters(parameterList);
        return definition;
    }

    private ToolDefinition parseMethod(Method method) {
        final Tool tool = method.getAnnotation(Tool.class);
        if (tool == null) {
            return new ToolDefinition(method.getName(), "");
        }
        String name = tool.name();
        if (name == null || name.isEmpty()) {
            name = method.getName();
        }
        return new ToolDefinition(name, tool.description());
    }

    private List<ToolParameter> parseParameters(Parameter[] parameters) {
        final List<ToolParameter> toolParameters = new ArrayList<>();
        if (parameters == null || parameters.length == 0) {
            return toolParameters;
        }
        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            final ToolParameter toolParameter = parseParameter(i, parameter);
            toolParameters.add(toolParameter);
        }
        return toolParameters;
    }

    private ToolParameter parseParameter(int index, Parameter parameter) {
        final Param toolParameter = parameter.getAnnotation(Param.class);
        if (toolParameter == null) {
            throw new NullPointerException("tool param[" + index + "] annotation not exists;");
        }
        String name = toolParameter.name();
        if (name == null || name.isEmpty()) {
            name = parameter.getName();
        }
        final String enums = toolParameter.enums();
        final List<String> enumList = this.parseEnums(enums);
        final ToolParameter functionParameter = new ToolParameter();
        functionParameter.setIndex(index);
        functionParameter.setName(name);
        functionParameter.setDescription(toolParameter.description());
        functionParameter.setType(toolParameter.type());
        functionParameter.setEnums(enumList);
        functionParameter.setRequired(toolParameter.required());
        return functionParameter;
    }

    @NotNull
    private List<String> parseEnums(String enums) {
        if (enums == null || enums.isEmpty()) {
            return new ArrayList<>();
        }
        final String[] split = StringUtils.split(enums, ';');
        return new ArrayList<>(Arrays.asList(split));
    }

    @NotNull
    @Override
    public Iterator<Map.Entry<Method, ToolDefinition>> iterator() {
        return cachedMap.entrySet().iterator();
    }
}
