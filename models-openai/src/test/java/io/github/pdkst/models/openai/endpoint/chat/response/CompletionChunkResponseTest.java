package io.github.pdkst.models.openai.endpoint.chat.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author pdkst.zhang
 * @since 2023/12/30
 */
public class CompletionChunkResponseTest {
    private ObjectMapper objectMapper = new ObjectMapper();

    private static final List<String> content = Arrays.asList(
            "{\"id\":\"chatcmpl-123\",\"object\":\"chat.completion.chunk\",\"created\":1694268190,\"model\":\"gpt-3" +
                    ".5-turbo-0613\", \"system_fingerprint\": \"fp_44709d6fcb\", \"choices\":[{\"index\":0," +
                    "\"delta\":{\"role\":\"assistant\",\"content\":\"\"},\"logprobs\":null,\"finish_reason\":null}]}",
            "{\"id\":\"chatcmpl-123\",\"object\":\"chat.completion.chunk\",\"created\":1694268190,\"model\":\"gpt-3" +
                    ".5-turbo-0613\", \"system_fingerprint\": \"fp_44709d6fcb\", \"choices\":[{\"index\":0," +
                    "\"delta\":{\"content\":\"Hello\"},\"logprobs\":null,\"finish_reason\":null}]}",
            "{\"id\":\"chatcmpl-123\",\"object\":\"chat.completion.chunk\",\"created\":1694268190,\"model\":\"gpt-3" +
                    ".5-turbo-0613\", \"system_fingerprint\": \"fp_44709d6fcb\", \"choices\":[{\"index\":0," +
                    "\"delta\":{\"content\":\"!\"},\"logprobs\":null,\"finish_reason\":null}]}",
            "{\"id\":\"chatcmpl-123\",\"object\":\"chat.completion.chunk\",\"created\":1694268190,\"model\":\"gpt-3" +
                    ".5-turbo-0613\", \"system_fingerprint\": \"fp_44709d6fcb\", \"choices\":[{\"index\":0," +
                    "\"delta\":{\"content\":\" today\"},\"logprobs\":null,\"finish_reason\":null}]}",
            "{\"id\":\"chatcmpl-123\",\"object\":\"chat.completion.chunk\",\"created\":1694268190,\"model\":\"gpt-3" +
                    ".5-turbo-0613\", \"system_fingerprint\": \"fp_44709d6fcb\", \"choices\":[{\"index\":0," +
                    "\"delta\":{\"content\":\"?\"},\"logprobs\":null,\"finish_reason\":null}]}",
            "{\"id\":\"chatcmpl-123\",\"object\":\"chat.completion.chunk\",\"created\":1694268190,\"model\":\"gpt-3" +
                    ".5-turbo-0613\", \"system_fingerprint\": \"fp_44709d6fcb\", \"choices\":[{\"index\":0," +
                    "\"delta\":{},\"logprobs\":null,\"finish_reason\":\"stop\"}]}\n");


    @Test
    public void testDeserialize() throws JsonProcessingException {
        for (String message : content) {
            final CompletionChunkResponse object = objectMapper.readValue(message,
                    CompletionChunkResponse.class);
            System.out.println(object);
        }
    }

}