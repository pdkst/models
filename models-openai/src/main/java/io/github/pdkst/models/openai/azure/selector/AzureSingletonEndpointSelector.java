package io.github.pdkst.models.openai.azure.selector;

import io.github.pdkst.models.openai.azure.AzureOpenaiCredentials;
import io.github.pdkst.models.openai.azure.AzureOpenaiUrlBuilder;
import io.github.pdkst.models.openai.client.OpenaiEndpoint;
import io.github.pdkst.models.openai.client.OpenaiEndpointSelector;
import lombok.RequiredArgsConstructor;

/**
 * @author pdkst.zhang
 * @since 2024/04/11
 */
@RequiredArgsConstructor
public class AzureSingletonEndpointSelector implements OpenaiEndpointSelector {
    private final AzureOpenaiUrlBuilder builder;
    private final AzureOpenaiCredentials credentials;

    public AzureSingletonEndpointSelector(AzureOpenaiUrlBuilder builder, String subscriptionKey) {
        this(builder, new AzureOpenaiCredentials(subscriptionKey));
    }

    @Override
    public OpenaiEndpoint select(String path) {
        final String url = builder.build(path);
        return new OpenaiEndpoint(url, credentials);
    }
}
