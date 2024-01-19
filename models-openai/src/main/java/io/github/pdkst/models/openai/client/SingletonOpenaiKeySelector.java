package io.github.pdkst.models.openai.client;

import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;

/**
 * @author pdkst.zhang
 * @since 2023/12/30
 */
@RequiredArgsConstructor
public class SingletonOpenaiKeySelector implements OpenaiKeySelector {
    private final String key;
    @Delegate
    private final OpenaiUrlBuilder builder;

    public SingletonOpenaiKeySelector(String key) {
        this.key = key;
        this.builder = new OpenaiUrlBuilder();
    }

    @Override
    public OpenaiKey select(String api) {
        final String url = builder.build(api);
        return new OpenaiKey(url, key);
    }
}
