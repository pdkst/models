package io.github.pdkst.models.openai.client;

import io.github.pdkst.models.http.HttpExchanger;
import io.github.pdkst.models.http.clients.OkHttp3HttpExchanger;
import io.github.pdkst.models.json.JacksonMapper;
import io.github.pdkst.models.json.JsonMapper;
import io.github.pdkst.models.openai.client.selector.RandomOpenaiEndpointSelector;
import io.github.pdkst.models.openai.client.selector.SingletonOpenaiEndpointSelector;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import okhttp3.OkHttpClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author pdkst.zhang
 * @since 2023/11/02
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(fluent = true, chain = true)
public class OpenaiOptions {
    private String schema = "https";
    private String domain = "api.openai.com";
    private String version = "v1";
    private String[] keys;
    private OpenaiEndpointSelector[] selectors;
    private Random random;
    private OkHttpClient okHttpClient;
    private JsonMapper jsonMapper;
    private HttpExchanger httpExchanger;

    public OpenaiOptions key(String... keys) {
        this.keys = keys;
        return this;
    }

    public OpenaiOptions selector(OpenaiEndpointSelector... selectors) {
        this.selectors = selectors;
        return this;
    }

    public OpenaiEndpointSelector buildSelector() {
        if (selectors != null && selectors.length == 1) {
            return selectors[0];
        }
        if (selectors != null && selectors.length > 1) {
            return new RandomOpenaiEndpointSelector(selectors);
        }
        final OpenaiUrlBuilder urlBuilder = buildOpenaiUrlBuilder();
        if (keys == null || keys.length == 0) {
            throw new IllegalArgumentException("keys or selectors must not be null");
        }
        if (keys.length == 1) {
            return new SingletonOpenaiEndpointSelector(keys[0], urlBuilder);
        }
        return buildRandomSelectors(urlBuilder);
    }

    public OpenaiUrlBuilder buildOpenaiUrlBuilder() {
        if (schema == null) {
            schema = "https";
        }
        if (domain == null) {
            domain = "api.openai.com";
        }
        if (version == null) {
            version = "v1";
        }
        return new OpenaiUrlBuilder(schema, domain, version);
    }

    public RandomOpenaiEndpointSelector buildRandomSelectors(OpenaiUrlBuilder urlBuilder) {
        if (random == null) {
            random = new Random();
        }
        // 把key转换成selectors list
        final List<OpenaiEndpointSelector> selectors = new ArrayList<>();
        for (String key : keys) {
            selectors.add(new SingletonOpenaiEndpointSelector(key, urlBuilder));
        }
        return new RandomOpenaiEndpointSelector(random, selectors);
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
        return new OkHttp3HttpExchanger(okHttpClient, jsonMapper);
    }
}
