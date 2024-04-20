package io.github.pdkst.models.server.chat;

import io.github.pdkst.models.http.listener.StreamEventListener;
import io.github.pdkst.models.json.JacksonMapper;
import io.github.pdkst.models.openai.api.chat.request.CompletionRequest;
import io.github.pdkst.models.openai.api.chat.response.CompletionResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author pdkst
 * @since 2024/04/20
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class FileOpenaiChatCompletion {
    private final JsonLineFileResolver resolver = new JsonLineFileResolver("/out/chat/stream_chat_completion.jsonl");
    private final JacksonMapper mapper = new JacksonMapper();

    public Object completion(CompletionRequest request) throws IOException {
        final Boolean stream = request.getStream();
        if (BooleanUtils.isFalse(stream)) {
            return completionObject(request);
        } else {
            final SseEmitter emitter = new SseEmitter();
            completionStream(request, new EmitterStreamEventListener(emitter));
            return emitter;
        }
    }

    public CompletionResponse completionObject(CompletionRequest request) throws IOException {
        final InputStream stream = JsonLineFileResolver.class.getResourceAsStream(
                "/out/chat/chat_completion_example.json");
        if (stream == null) {
            throw new RuntimeException("not found");
        }
        try (BufferedInputStream bis = new BufferedInputStream(stream);
             final InputStreamReader reader = new InputStreamReader(bis);
             BufferedReader bufferedReader = new BufferedReader(reader)) {
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            return mapper.parse(sb.toString(), CompletionResponse.class);
        } finally {
            IOUtils.closeQuietly(stream);
        }
    }

    public void completionStream(CompletionRequest request,
                                 StreamEventListener listener) throws IOException {
        resolver.resolve(listener);
    }
}
