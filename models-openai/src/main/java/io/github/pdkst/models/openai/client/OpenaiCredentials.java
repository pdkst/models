package io.github.pdkst.models.openai.client;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Iterator;

/**
 * @author pdkst.zhang
 * @since 2024/04/11
 */
@RequiredArgsConstructor
public class OpenaiCredentials implements Credentials {
    private final String key;

    @NotNull
    @Override
    public Iterator<String> iterator() {
        return Collections.singleton("Authorization").iterator();
    }

    @Override
    public String get(String key) {
        return "Bearer " + this.key;
    }
}
