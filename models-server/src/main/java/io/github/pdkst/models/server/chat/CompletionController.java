package io.github.pdkst.models.server.chat;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

/**
 * @author pdkst
 * @since 2023/12/31
 */
@Slf4j
@RestController
@RequestMapping("/v1/chat")
public class CompletionController {

    @PostMapping(value = "/completions")
    public SseEmitter chatCompletion() throws IOException {
        final SseEmitter emitter = new SseEmitter();

        try (final JsonLineFileResolver resolver
                     = new JsonLineFileResolver("/out/chat/stream_chat_completion.jsonl")) {
            resolver.resolve(emitter);
        }
        return emitter;
    }

}
