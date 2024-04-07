package io.github.pdkst.models.tool;

import io.github.pdkst.models.tool.annotation.Param;
import io.github.pdkst.models.tool.annotation.Tool;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author pdkst.zhang
 * @since 2024/03/22
 */
public class ToolDescriptorTest {
    static class SingleFunction {
        @Tool(name = "test_name", description = "test this function")
        public Object test(@Param(name = "arg_name",
                description = "parameters 1",
                type = "string",
                enums = "john;mary",
                required = true) String name,
                           @Param(name = "arg_age",
                                   description = "parameters 2",
                                   type = "number",
                                   required = false) Integer value) {
            return null;
        }
    }

    @Test
    public void parse() {
        final ToolDescriptor descriptor = ToolDescriptor.parse(SingleFunction.class);
        for (Map.Entry<Method, ToolDefinition> entry : descriptor) {
            final Method method = entry.getKey();
            final ToolDefinition definition = entry.getValue();
            Assert.assertNotNull(method);
            Assert.assertNotNull(definition);
        }
    }

    @Test
    public void getToolDefinition() {
        final Method method = SingleFunction.class.getMethods()[0];
        final ToolDescriptor descriptor = ToolDescriptor.parse(SingleFunction.class);
        final ToolDefinition definition = descriptor.getToolDefinition(method);
        assertNotNull(definition);
        assertEquals("test_name", definition.getName());
        assertEquals("test this function", definition.getDescription());
        final List<ToolParameter> parameters = definition.getParameters();
        assertNotNull(parameters);
        assertEquals(2, parameters.size());
        final ToolParameter parameter1 = parameters.get(0);
        assertEquals("arg_name", parameter1.getName());
        assertEquals("parameters 1", parameter1.getDescription());
        assertEquals("string", parameter1.getType());
        assertEquals(2, parameter1.getEnums().size());
        final ToolParameter parameter2 = parameters.get(1);
        assertEquals("arg_age", parameter2.getName());
        assertEquals("parameters 2", parameter2.getDescription());
        assertEquals("number", parameter2.getType());
        assertNotNull(parameter2.getEnums());
    }
}