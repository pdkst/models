package io.github.pdkst.models.openai.endpoint.chat;

import io.github.pdkst.models.http.clients.OkHttp3HttpExchanger;
import io.github.pdkst.models.openai.client.selector.SingletonOpenaiKeySelector;
import io.github.pdkst.models.openai.endpoint.chat.request.CompletionRequest;
import io.github.pdkst.models.openai.endpoint.chat.request.Message;
import io.github.pdkst.models.openai.endpoint.chat.response.CompletionResponse;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.*;

/**
 * @author pdkst.zhang
 * @since 2024/01/18
 */
public class OpenaiChatCompletionTest {
    private OpenaiChatCompletion completion;

    @Before
    public void init() {
        final SingletonOpenaiKeySelector selector = new SingletonOpenaiKeySelector("");
        selector.setDomain("localhost:8080");
        final OkHttp3HttpExchanger delegate = new OkHttp3HttpExchanger();
        final OpenaiDelegateHttpExchanger exchanger = new OpenaiDelegateHttpExchanger(delegate, selector);
        completion = new OpenaiChatCompletion(exchanger);
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