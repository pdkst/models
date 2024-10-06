package io.github.pdkst.models.openai.azure;

import io.github.pdkst.models.http.Authorization;
import io.github.pdkst.models.http.request.HttpRequest;
import lombok.RequiredArgsConstructor;

/**
 * @author pdkst
 * @since 2024/04/11
 */
@RequiredArgsConstructor
public class AzureOpenaiAuthorization implements Authorization {
    private static final String HEADER_NAME_API_KEY = "api-key";
    private final String subscriptionHeader;
    private final String subscriptionKey;

    public AzureOpenaiAuthorization(String subscriptionKey) {
        this(HEADER_NAME_API_KEY, subscriptionKey);
    }

    @Override
    public void authorize(HttpRequest request) {
        request.header(subscriptionHeader, subscriptionKey);
    }
}
