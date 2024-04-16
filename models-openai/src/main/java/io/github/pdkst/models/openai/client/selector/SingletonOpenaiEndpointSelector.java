package io.github.pdkst.models.openai.client.selector;

import io.github.pdkst.models.http.Credentials;
import io.github.pdkst.models.openai.client.OpenaiCredentials;
import io.github.pdkst.models.openai.client.OpenaiEndpoint;
import io.github.pdkst.models.openai.client.OpenaiEndpointSelector;
import io.github.pdkst.models.openai.client.OpenaiUrlBuilder;
import lombok.RequiredArgsConstructor;

/**
 * @author pdkst.zhang
 * @since 2023/12/30
 */
@RequiredArgsConstructor
public class SingletonOpenaiEndpointSelector implements OpenaiEndpointSelector {
    private final Credentials credentials;
    private final OpenaiUrlBuilder builder;

    public SingletonOpenaiEndpointSelector(String key) {
        this(key, new OpenaiUrlBuilder());
    }

    public SingletonOpenaiEndpointSelector(String key, OpenaiUrlBuilder urlBuilder) {
        this(new OpenaiCredentials(key), urlBuilder);
    }

    @Override
    public OpenaiEndpoint select(String api) {
        final String url = builder.build(api);
        return new OpenaiEndpoint(url, credentials);
    }
}
