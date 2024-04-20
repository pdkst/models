package io.github.pdkst.models.openai.api.chat;

import io.github.pdkst.models.http.clients.OkHttp3HttpExchanger;
import io.github.pdkst.models.openai.api.chat.request.CompletionRequest;
import io.github.pdkst.models.openai.api.chat.request.Message;
import io.github.pdkst.models.openai.api.chat.response.CompletionResponse;
import io.github.pdkst.models.openai.client.OpenaiKeyInterceptor;
import io.github.pdkst.models.openai.client.OpenaiUrlBuilder;
import io.github.pdkst.models.openai.client.selector.SingletonOpenaiEndpointSelector;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertNotNull;

/**
 * @author pdkst
 * @since 2024/01/18
 */
public class OpenaiChatCompletionTest {
    private OpenaiChatCompletion completion;

    @Before
    public void init() {
        final OpenaiUrlBuilder urlBuilder = new OpenaiUrlBuilder();
        urlBuilder.setDomain("localhost:8080");
        final SingletonOpenaiEndpointSelector selector = new SingletonOpenaiEndpointSelector("", urlBuilder);
        final OkHttp3HttpExchanger delegate = new OkHttp3HttpExchanger();
        delegate.addInterceptor(new OpenaiKeyInterceptor(selector));
        completion = new OpenaiChatCompletion(delegate);
    }

    @Test
    public void completion() throws Exception {
        final CompletionRequest request = new CompletionRequest();
        request.setModel("gpt-3.5-turbo");
        request.setStream(false);
        request.setMessages(Collections.singletonList(Message.user("hello")));
        final CompletionResponse response = completion.completion(request);
        assertNotNull(response);
    }
}