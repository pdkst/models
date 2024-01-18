package io.github.pdkst.models.openai.client;

import lombok.Data;

/**
 * @author pdkst.zhang
 * @since 2024/01/18
 */
@Data
public class OpenaiKey {
    /**
     * api路径
     */
    private final String url;
    /**
     * api key
     */
    private final String key;
}
