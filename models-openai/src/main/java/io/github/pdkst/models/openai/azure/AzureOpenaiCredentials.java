package io.github.pdkst.models.openai.azure;

import io.github.pdkst.models.openai.client.Credentials;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author pdkst.zhang
 * @since 2024/04/11
 */
@NoArgsConstructor
@RequiredArgsConstructor
public class AzureOpenaiCredentials implements Credentials {
    private final Map<String, String> credentials = new HashMap<>();

    public AzureOpenaiCredentials(String subscriptionKey) {
        credentials.put("api-key", subscriptionKey);
    }

    public AzureOpenaiCredentials(String header, String subscriptionKey) {
        credentials.put(header, subscriptionKey);
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
