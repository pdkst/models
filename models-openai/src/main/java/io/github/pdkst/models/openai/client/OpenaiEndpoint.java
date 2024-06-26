package io.github.pdkst.models.openai.client;

import io.github.pdkst.models.http.Credentials;
import lombok.Data;

/**
 * @author pdkst
 * @since 2024/01/18
 */
@Data
public class OpenaiEndpoint {
    /**
     * api路径
     */
    private final String url;
    /**
     * api key
     */
    private final Credentials credentials;
}
