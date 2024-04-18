package io.github.pdkst.models.openai.api.chat.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * @author pdkst
 * @since 2023/10/29
 */
public class CompletionResponseTest {

    private static final String RESPONSE_EXAMPLE = "{\n" +
            "  \"id\": \"chatcmpl-123\",\n" +
            "  \"object\": \"chat.completion\",\n" +
            "  \"created\": 1677652288,\n" +
            "  \"model\": \"gpt-3.5-turbo-0613\",\n" +
            "  \"choices\": [{\n" +
            "    \"index\": 0,\n" +
            "    \"message\": {\n" +
            "      \"role\": \"assistant\",\n" +
            "      \"content\": \"\\n\\nHello there, how may I assist you today?\"\n" +
            "    },\n" +
            "    \"finish_reason\": \"stop\"\n" +
            "  }],\n" +
            "  \"usage\": {\n" +
            "    \"prompt_tokens\": 9,\n" +
            "    \"completion_tokens\": 12,\n" +
            "    \"total_tokens\": 21\n" +
            "  }\n" +
            "}\n";

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testSerialize() throws JsonProcessingException {
        final CompletionResponse response = objectMapper.readValue(RESPONSE_EXAMPLE, CompletionResponse.class);
        assertNotNull("response is null", response);
    }
}