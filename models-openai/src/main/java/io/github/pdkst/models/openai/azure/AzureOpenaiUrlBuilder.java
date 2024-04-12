package io.github.pdkst.models.openai.azure;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author pdkst.zhang
 * @since 2024/01/19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AzureOpenaiUrlBuilder {
    private String schema = "https";
    private String resource = "";
    private String domain = "openai.azure.com";
    private String environment = "";
    private String deployment = "";
    private String version = "2023-12-01-preview";

    public AzureOpenaiUrlBuilder(String resource, String deployment) {
        this.resource = resource;
        this.deployment = deployment;
    }

    public AzureOpenaiUrlBuilder(AzureOpenaiUrlBuilder other) {
        this.schema = other.schema;
        this.resource = other.resource;
        this.domain = other.domain;
        this.environment = other.environment;
        this.deployment = other.deployment;
        this.version = other.version;
    }

    public String build(String api) {
        return String.format("%s://%s.%s%s/openai/deployments/%s%s?api-version=%s",
                schema, resource, domain, environment, deployment, api, version);
    }

}
