package io.github.pdkst.models.openai.client;

import lombok.RequiredArgsConstructor;

/**
 * @author pdkst.zhang
 * @since 2023/12/30
 */
@RequiredArgsConstructor
public class SingletonOpenaiKeySelector implements OpenaiKeySelector {
    private final String key;
    private final OpenaiUrlBuilder builder;

    public SingletonOpenaiKeySelector(String key) {
        this.key = key;
        this.builder = new OpenaiUrlBuilder();
    }

    @Override
    public OpenaiKey select(String api) {
        return new OpenaiKey(key, builder.build(api));
    }
}
