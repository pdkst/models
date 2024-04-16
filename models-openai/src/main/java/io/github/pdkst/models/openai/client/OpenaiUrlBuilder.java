package io.github.pdkst.models.openai.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author pdkst
 * @since 2024/01/18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpenaiUrlBuilder {
    private String schema = "https";
    private String domain = "api.openai.com";
    private String version = "v1";

    public String build(String api) {
        return String.format("%s://%s/%s%s", schema, domain, version, api);
    }
}
