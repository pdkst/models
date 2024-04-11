package io.github.pdkst.models.openai.client;

/**
 * @author pdkst.zhang
 * @since 2024/04/11
 */
public interface Credentials extends Iterable<String> {
    /**
     * 获取header value
     *
     * @param header header key
     * @return value
     */
    String get(String header);
}
