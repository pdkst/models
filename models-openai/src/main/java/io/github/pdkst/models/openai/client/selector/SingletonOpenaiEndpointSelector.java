package io.github.pdkst.models.openai.client.selector;

import io.github.pdkst.models.http.Authorization;
import io.github.pdkst.models.http.auth.BearerAuthorization;
import io.github.pdkst.models.openai.client.OpenaiEndpoint;
import io.github.pdkst.models.openai.client.OpenaiEndpointSelector;
import io.github.pdkst.models.openai.client.OpenaiUrlBuilder;
import lombok.RequiredArgsConstructor;

/**
 * @author pdkst
 * @since 2023/12/30
 */
@RequiredArgsConstructor
public class SingletonOpenaiEndpointSelector implements OpenaiEndpointSelector {
    private final Authorization authorization;
    private final OpenaiUrlBuilder builder;

    public SingletonOpenaiEndpointSelector(String key) {
        this(key, new OpenaiUrlBuilder());
    }

    public SingletonOpenaiEndpointSelector(String key, OpenaiUrlBuilder urlBuilder) {
        this(BearerAuthorization.of(key), urlBuilder);
    }

    @Override
    public OpenaiEndpoint select(String api) {
        final String url = builder.build(api);
        return new OpenaiEndpoint(url, authorization);
    }
}
