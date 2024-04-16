package io.github.pdkst.models.openai.azure;

import io.github.pdkst.models.openai.api.chat.request.CompletionRequest;
import io.github.pdkst.models.openai.api.chat.request.Message;
import io.github.pdkst.models.openai.api.chat.response.CompletionResponse;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * @author pdkst
 * @since 2024/04/14
 */
public class AzureOpenaiClientTest {
    @Test
    public void testAzureClient() throws Exception {
        AzureOpenaiOptions options = new AzureOpenaiOptions();
        options.headerName("Ocp-Apim-Subscription-Key");
        options.key("<your azure key>");
        options.domain("<your domain>");
        options.resource("<your resource>");
        options.deployment("<your deployment>");
        final AzureOpenaiClient client = new AzureOpenaiClient(options);
        final CompletionRequest request = new CompletionRequest();
        request.setModel("gpt-3.5-turbo");
        request.setStream(false);
        request.messages(Message.user("hello"));
        final CompletionResponse response = client.chat().completion(request);
        assertNotNull(response);
    }

}