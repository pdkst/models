package io.github.pdkst.models.openai.client;

import io.github.pdkst.models.http.HttpExchanger;
import io.github.pdkst.models.http.clients.OkHttp3HttpExchanger;
import io.github.pdkst.models.json.JacksonMapper;
import io.github.pdkst.models.json.JsonMapper;
import io.github.pdkst.models.openai.client.selector.RandomOpenaiEndpointSelector;
import lombok.Data;
import okhttp3.OkHttpClient;

/**
 * @author pdkst.zhang
 * @since 2023/11/02
 */
@Data
public class OpenaiOptions {
    private String[] keys;
    private OpenaiEndpointSelector[] selectors;
    private OkHttpClient okHttpClient;
    private JsonMapper jsonMapper;
    private HttpExchanger httpExchanger;

    public void keys(String... keys) {
        this.keys = keys;
    }

    public void selectors(OpenaiEndpointSelector... keys) {
        this.selectors = keys;
    }

    public OpenaiEndpointSelector buildSelector() {
        if (selectors != null && selectors.length > 0) {
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
