package io.github.pdkst.models.openai.client;

/**
 * select openai keys
 *
 * @author pdkst.zhang
 * @since 2023/12/30
 */
public interface OpenaiKeySelector {
    /**
     * select openai key
     *
     * @return openai key
     */
    OpenaiKey select(String path);

    static OpenaiKeySelector singleton(String key, OpenaiUrlBuilder builder) {
        return new SingletonOpenaiKeySelector(key, builder);
    }

    static OpenaiKeySelector singleton(String key) {
        return new SingletonOpenaiKeySelector(key, new OpenaiUrlBuilder());
    }
}
