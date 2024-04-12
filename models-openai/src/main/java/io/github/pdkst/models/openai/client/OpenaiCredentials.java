package io.github.pdkst.models.openai.client;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

/**
 * @author pdkst.zhang
 * @since 2024/04/11
 */
@RequiredArgsConstructor
public class OpenaiCredentials implements Credentials {
    private final Map<String, String> credentials;

    public OpenaiCredentials(String key) {
        this.credentials = Collections.singletonMap("Authorization", "Bearer " + key);
    }

    @NotNull
    @Override
    public Iterator<String> iterator() {
        return credentials.keySet().iterator();
    }

    @Override
    public String get(String key) {
        return credentials.get(key);
    }
}
