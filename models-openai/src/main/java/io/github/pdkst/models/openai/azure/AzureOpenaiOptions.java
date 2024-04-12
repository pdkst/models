package io.github.pdkst.models.openai.azure;

import io.github.pdkst.models.http.HttpExchanger;
import io.github.pdkst.models.http.clients.OkHttp3HttpExchanger;
import io.github.pdkst.models.json.JacksonMapper;
import io.github.pdkst.models.json.JsonMapper;
import io.github.pdkst.models.openai.azure.selector.AzureSingletonEndpointSelector;
import io.github.pdkst.models.openai.client.OpenaiEndpointSelector;
import io.github.pdkst.models.openai.client.selector.RandomOpenaiEndpointSelector;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import okhttp3.OkHttpClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author pdkst.zhang
 * @since 2024/04/13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AzureOpenaiOptions {
    private String schema = "https";
    private String resource;
    private String domain = "openai.azure.com";
    private String environment = "";
    private String deployment;
    private String version = "2023-12-01-preview";
    private String headerName = "api-key";
    private String key;
    private String[] keys;
    private Random random;
    private OpenaiEndpointSelector[] selector;
    private OkHttpClient okHttpClient;
    private JsonMapper jsonMapper;
    private HttpExchanger httpExchanger;

    public void selectors(OpenaiEndpointSelector... keys) {
        selector = keys;
    }

    public void keys(String... keys) {
        this.keys = keys;
    }

    public OpenaiEndpointSelector buildSelector() {
        if (selector != null && selector.length > 0) {
            return buildSelectors();
        }
        final AzureOpenaiUrlBuilder urlBuilder = buildUrlBuilder();
        if (headerName != null) {
            headerName = "api-key";
        }
        if (key != null) {
            final AzureOpenaiCredentials credentials = new AzureOpenaiCredentials(headerName, key);
            return new AzureSingletonEndpointSelector(urlBuilder, credentials);
        }
        if (keys != null && keys.length > 0) {
            return buildKeys(urlBuilder);
        }
        throw new IllegalArgumentException("selector or key or keys must not be null");
    }

    private RandomOpenaiEndpointSelector buildKeys(AzureOpenaiUrlBuilder urlBuilder) {
        final List<OpenaiEndpointSelector> selectors = new ArrayList<>();
        for (String key : keys) {
            final AzureOpenaiCredentials credentials = new AzureOpenaiCredentials(headerName, key);
            final AzureSingletonEndpointSelector endpointSelector = new AzureSingletonEndpointSelector(urlBuilder,
                    credentials);
            selectors.add(endpointSelector);
        }
        if (random == null) {
            random = new Random();
        }
        return new RandomOpenaiEndpointSelector(random, selectors);
    }

    public AzureOpenaiUrlBuilder buildUrlBuilder() {
        if (resource == null) {
            throw new IllegalArgumentException("resource must not be null");
        }
        if (deployment == null) {
            throw new IllegalArgumentException("deployment must not be null");
        }
        if (version == null) {
            throw new IllegalArgumentException("version must not be null");
        }
        if (schema == null) {
            schema = "https";
        }
        if (domain == null) {
            domain = "openai.azure.com";
        }
        if (environment == null) {
            environment = "";
        }
        return new AzureOpenaiUrlBuilder(schema, resource, domain,
                environment, deployment, version);
    }

    private OpenaiEndpointSelector buildSelectors() {
        if (selector.length == 1) {
            return selector[0];
        }
        if (random == null) {
            random = new Random();
        }
        return new RandomOpenaiEndpointSelector(random, selector);
    }

    public HttpExchanger buildHttpExchanger() {
        if (httpExchanger != null) {
            return httpExchanger;
        }
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient();
        }
        if (jsonMapper == null) {
            jsonMapper = new JacksonMapper();
        }
        httpExchanger = new OkHttp3HttpExchanger(okHttpClient, jsonMapper);
        return httpExchanger;
    }
}
