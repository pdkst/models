package io.github.pdkst.models.openai.sample.controller.chat;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.Duration;

/**
 * @author pdkst
 * @since 2023/12/31
 */
@Slf4j
@RestController
public class ChatCompletionController {

    @PostMapping(value = "/chat/completion")
    public SseEmitter chatCompletionStream() throws IOException {
        final SseEmitter emitter = new SseEmitter(Duration.ofSeconds(60).toMillis());

        try (final InputStream stream = ChatCompletionController.class.getResourceAsStream(
                "/out/chat/stream_chat_completion.txt");
        ) {
            if (stream == null) {
                return emitter;
            }
            emit(emitter, stream);
        }
        return emitter;
    }

    // read stream and send line to emitter
    private void emit(SseEmitter emitter, InputStream stream) throws IOException {
        try (
                final BufferedInputStream bis = new BufferedInputStream(stream);
                final InputStreamReader in = new InputStreamReader(bis);
                final BufferedReader reader = new BufferedReader(in)) {
            String line;
            while ((line = reader.readLine()) != null) {
                emitter.send(SseEmitter.event().data(line).build());
            }
        }
    }
}
