package io.github.pdkst.models.openai.client;

import lombok.Data;

/**
 * @author pdkst.zhang
 * @since 2024/01/18
 */
@Data
public class OpenaiUrlBuilder {
    private String schema = "https";
    private String domain = "api.openai.com";
    private String version = "v1";

    public String build(String api) {
        return String.format("%s://%s/%s%s", schema, domain, version, api);
    }
}
