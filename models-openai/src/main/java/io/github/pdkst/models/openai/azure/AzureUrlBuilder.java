package io.github.pdkst.models.openai.azure;

import lombok.Data;

/**
 * @author pdkst.zhang
 * @since 2024/01/19
 */
@Data
public class AzureUrlBuilder {
    private String schema = "https";
    private String resource = "";
    private String domain = "openai.azure.com";
    private String environment = "";
    private String deployment = "";
    private String version = "2023-12-01-preview";

    public String build(String api) {
        return String.format("%s://%s.%s%s/openai/deployments/%s%s?api-version=%s",
                schema, resource, domain, environment, deployment, api, version);
    }

}
