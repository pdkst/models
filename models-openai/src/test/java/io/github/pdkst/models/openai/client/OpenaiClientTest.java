package io.github.pdkst.models.openai.client;

import io.github.pdkst.models.openai.api.chat.request.CompletionRequest;
import io.github.pdkst.models.openai.api.chat.request.Message;
import io.github.pdkst.models.openai.api.chat.response.CompletionResponse;
import io.github.pdkst.models.openai.client.selector.SingletonOpenaiEndpointSelector;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * @author pdkst
 * @since 2024/04/12
 */
public class OpenaiClientTest {

    @Test
    public void testClient() throws Exception {
        final OpenaiOptions options = new OpenaiOptions();
        options.key("sk-**********");
        options.schema("http");
        options.domain("localhost:8080");
        OpenaiClient client = new OpenaiClient(options);
        final CompletionRequest request = new CompletionRequest();
        request.setModel("gpt-3.5-turbo");
        request.setStream(false);
        request.messages(Message.user("hello"));
        final CompletionResponse response = client.chat().completion(request);
        assertNotNull(response);
    }
}