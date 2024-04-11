package io.github.pdkst.models.openai.client;

import io.github.pdkst.models.http.HttpExchanger;
import io.github.pdkst.models.http.clients.OkHttp3HttpExchanger;
import io.github.pdkst.models.json.JacksonMapper;
import io.github.pdkst.models.json.JsonMapper;
import io.github.pdkst.models.openai.client.selector.RandomOpenaiKeySelector;
import lombok.Data;

/**
 * @author pdkst.zhang
 * @since 2023/11/02
 */
@Data
public class OpenaiOptions {
    private JsonMapper jsonMapper = new JacksonMapper();
    private HttpExchanger httpExchanger = new OkHttp3HttpExchanger();
    private OpenaiEndpointSelector selector;

    public void key(String key) {
        selector = OpenaiEndpointSelector.singleton(key);
    }

    public void keys(String... key) {
        selector = new RandomOpenaiKeySelector(key);
    }

    public void selectors(OpenaiEndpointSelector... keys) {
        selector = new RandomOpenaiKeySelector(keys);
    }

    public OpenaiOptions withDefaults() {
        initDefaults(this);
        return this;
    }

    public static void initDefaults(OpenaiOptions config) {
        if (config.selector == null) {
            throw new NullPointerException("keySelector");
        }
        if (config.jsonMapper == null) {
            config.jsonMapper = new JacksonMapper();
        }
        if (config.httpExchanger == null) {
            config.httpExchanger = new OkHttp3HttpExchanger();
        }
    }
}
