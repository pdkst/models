package io.github.pdkst.models.openai.azure.selector;

import io.github.pdkst.models.openai.azure.AzureOpenaiCredentials;
import io.github.pdkst.models.openai.azure.AzureOpenaiUrlBuilder;
import io.github.pdkst.models.openai.client.OpenaiEndpoint;
import io.github.pdkst.models.openai.client.OpenaiEndpointSelector;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;

/**
 * @author pdkst.zhang
 * @since 2024/04/11
 */
@RequiredArgsConstructor
public class AzureSingletonEndpointSelector implements OpenaiEndpointSelector {
    @Delegate
    private final AzureOpenaiUrlBuilder builder;
    private final AzureOpenaiCredentials credentials;

    public AzureSingletonEndpointSelector(String subscriptionKey) {
        this(new AzureOpenaiUrlBuilder(), new AzureOpenaiCredentials(subscriptionKey));
    }

    public AzureSingletonEndpointSelector(String header, String subscriptionKey) {
        this(new AzureOpenaiUrlBuilder(), new AzureOpenaiCredentials(header, subscriptionKey));
    }

    public AzureSingletonEndpointSelector(AzureOpenaiUrlBuilder builder, String subscriptionKey) {
        this(builder, new AzureOpenaiCredentials(subscriptionKey));
    }

    @Override
    public OpenaiEndpoint select(String path) {
        final String url = builder.build(path);
        return new OpenaiEndpoint(url, credentials);
    }
}
