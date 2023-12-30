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
    String select();

    static OpenaiKeySelector singleton(String key) {
        return new SingletonOpenaiKeySelector(key);
    }
}
