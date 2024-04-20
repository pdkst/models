package io.github.pdkst.models.server.chat;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author pdkst
 * @since 2024/04/20
 */
@Slf4j
@RequiredArgsConstructor
public class JsonLineFileResolver implements Closeable {
    private final BufferedReader reader;

    public JsonLineFileResolver(String file) {
        this(load(file));
    }

    public JsonLineFileResolver(InputStream stream) {
        final BufferedInputStream bis = new BufferedInputStream(stream);
        final InputStreamReader in = new InputStreamReader(bis);
        this.reader = new BufferedReader(in);
    }

    private static InputStream load(String file) {
        final InputStream stream = JsonLineFileResolver.class.getResourceAsStream(file);
        if (stream == null) {
            throw new IllegalArgumentException("file not found: " + file);
        }
        return stream;
    }


    /**
     * read stream and send line to emitter
     */
    public void resolve(SseEmitter emitter) throws IOException {
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                emitter.send(SseEmitter.event().data(line));
                log.info(line);
            }
        } finally {
            emitter.complete();
        }
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }
}
