package io.github.pdkst.models.openai.client;

import lombok.RequiredArgsConstructor;

/**
 * @author pdkst.zhang
 * @since 2023/12/30
 */
@RequiredArgsConstructor
public class SingletonOpenaiKeySelector implements OpenaiKeySelector {
    private final String key;

    @Override
    public String select() {
        return key;
    }
}
