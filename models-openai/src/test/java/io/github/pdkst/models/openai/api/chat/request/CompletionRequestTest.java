package io.github.pdkst.models.openai.api.chat.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * @author pdkst.zhang
 * @since 2023/10/29
 */
public class CompletionRequestTest {
    private static final String REQUEST_EXAMPLE = "{\n" +
            "  \"model\": \"gpt-3.5-turbo\",\n" +
            "  \"messages\": [\n" +
            "    {\n" +
            "      \"role\": \"user\",\n" +
            "      \"content\": \"What is the weather like in Boston?\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"functions\": [\n" +
            "    {\n" +
            "      \"name\": \"get_current_weather\",\n" +
            "      \"description\": \"Get the current weather in a given location\",\n" +
            "      \"parameters\": {\n" +
            "        \"type\": \"object\",\n" +
            "        \"properties\": {\n" +
            "          \"location\": {\n" +
            "            \"type\": \"string\",\n" +
            "            \"description\": \"The city and state, e.g. San Francisco, CA\"\n" +
            "          },\n" +
            "          \"unit\": {\n" +
            "            \"type\": \"string\",\n" +
            "            \"enum\": [\"celsius\", \"fahrenheit\"]\n" +
            "          }\n" +
            "        },\n" +
            "        \"required\": [\"location\"]\n" +
            "      }\n" +
            "    }\n" +
            "  ],\n" +
            "  \"function_call\": \"auto\"\n" +
            "}";
    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testSerialize() throws JsonProcessingException {
        final CompletionRequest completionRequest = objectMapper.readValue(REQUEST_EXAMPLE, CompletionRequest.class);
        assertNotNull("request is null", completionRequest);
    }
}