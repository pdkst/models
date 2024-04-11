package io.github.pdkst.models.openai.azure;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author pdkst.zhang
 * @since 2024/01/20
 */
public class AzureOpenaiUrlBuilderTest {

    @Test
    public void build() {
        AzureOpenaiUrlBuilder builder = new AzureOpenaiUrlBuilder();
        builder.setResource("YOUR_RESOURCE_NAME");
        builder.setEnvironment("/test");
        builder.setDeployment("gpt-35-turbo");
        final String expect = "https://YOUR_RESOURCE_NAME.openai.azure.com" +
                "/test/openai/deployments/gpt-35-turbo/completions" +
                "?api-version=2023-12-01-preview";
        assertEquals(expect, builder.build("/completions"));
    }
}