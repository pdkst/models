package io.github.pdkst.models.openai.azure;

import io.github.pdkst.models.http.Credentials;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

/**
 * @author pdkst.zhang
 * @since 2024/04/11
 */
public class AzureOpenaiCredentials implements Credentials {
    public static final String HEADER_NAME_API_KEY = "api-key";
    private final Map<String, String> credentials;

    public AzureOpenaiCredentials(String subscriptionKey) {
        this(HEADER_NAME_API_KEY, subscriptionKey);
    }

    public AzureOpenaiCredentials(String header, String subscriptionKey) {
        credentials = Collections.singletonMap(header, subscriptionKey);
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
