package io.github.pdkst.models.tool;

import io.github.pdkst.models.tool.annotation.Param;
import lombok.Data;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.lang.reflect.Parameter;
import java.math.BigDecimal;
import java.math.BigInteger;
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

    public Object[] parseParameterArray(Parameter[] parameters, Map<String, Object> parameterMap) {
        if (parameters == null || parameters.length == 0) {
            return new Object[0];
        }

        final Object[] parameterArray = new Object[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            final String name = getParamName(parameter);
            final Object rawValue = parameterMap.get(name);
            parameterArray[i] = convertByType(rawValue, parameter.getType());
        }
        return parameterArray;
    }

    private String getParamName(Parameter parameter) {
        final Param annotation = parameter.getAnnotation(Param.class);
        if (annotation == null) {
            return parameter.getName();
        }
        final String declareName = annotation.name();
        if (declareName == null || declareName.isEmpty()) {
            return parameter.getName();
        }
        return declareName;
    }

    Object convertByType(Object rawValue, Class<?> type) {
        if (rawValue == null) {
            return null;
        }
        if (type.isInstance(rawValue)) {
            return rawValue;
        }
        if (type.equals(String.class)) {
            return rawValue.toString();
        }
        final String stringValue = rawValue.toString();
        if (type.equals(Boolean.class) || type.equals(Boolean.TYPE)) {
            return BooleanUtils.toBoolean(stringValue);
        }
        if (!NumberUtils.isCreatable(stringValue)) {
            return null;
        }
        if (type.equals(Integer.class) || type.equals(Integer.TYPE)) {
            return NumberUtils.toInt(stringValue);
        } else if (type.equals(Long.class) || type.equals(Long.TYPE)) {
            return NumberUtils.toLong(stringValue);
        } else if (type.equals(Double.class) || type.equals(Double.TYPE)) {
            return NumberUtils.toDouble(stringValue);
        } else if (type.equals(Float.class) || type.equals(Float.TYPE)) {
            return NumberUtils.toFloat(stringValue);
        } else if (type.equals(Short.class) || type.equals(Short.TYPE)) {
            return NumberUtils.toShort(stringValue);
        } else if (type.equals(Byte.class) || type.equals(Byte.TYPE)) {
            return NumberUtils.toByte(stringValue);
        } else if (type.equals(BigDecimal.class)) {
            return NumberUtils.toScaledBigDecimal(stringValue);
        } else if (type.equals(BigInteger.class)) {
            return NumberUtils.createBigInteger(stringValue);
        }
        return null;
    }
}
