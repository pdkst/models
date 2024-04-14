package io.github.pdkst.models.openai.client;

import io.github.pdkst.models.http.HttpExchanger;
import io.github.pdkst.models.http.clients.OkHttp3HttpExchanger;
import io.github.pdkst.models.json.JacksonMapper;
import io.github.pdkst.models.json.JsonMapper;
import io.github.pdkst.models.openai.client.selector.RandomOpenaiEndpointSelector;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import okhttp3.OkHttpClient;

/**
 * @author pdkst.zhang
 * @since 2023/11/02
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(fluent = true, chain = true)
public class OpenaiOptions {
    private String[] keys;
    private OpenaiEndpointSelector[] selectors;
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
        if (keys != null && keys.length > 0) {
            return new RandomOpenaiEndpointSelector(keys);
        }
        throw new IllegalArgumentException("keys or selectors must not be null");
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
