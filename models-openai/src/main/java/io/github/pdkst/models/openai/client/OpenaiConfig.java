package io.github.pdkst.models.openai.client;

import io.github.pdkst.models.http.HttpExchanger;
import io.github.pdkst.models.http.clients.OkHttp3HttpExchanger;
import io.github.pdkst.models.json.JacksonMapper;
import io.github.pdkst.models.json.JsonMapper;
import io.github.pdkst.models.openai.client.selector.RandomOpenaiKeySelector;
import lombok.Data;
import lombok.experimental.Delegate;

/**
 * @author pdkst.zhang
 * @since 2023/11/02
 */
@Data
public class OpenaiConfig {
    private JsonMapper jsonMapper = new JacksonMapper();
    private HttpExchanger httpExchanger = new OkHttp3HttpExchanger();
    @Delegate
    private OpenaiUrlBuilder builder = new OpenaiUrlBuilder();
    private OpenaiKeySelector keySelector;

    public void key(String key) {
        keySelector = OpenaiKeySelector.singleton(key);
    }

    public void keys(String... key) {
        keySelector = new RandomOpenaiKeySelector(builder, key);
    }

    public void initDefaults() {
        if (keySelector == null) {
            throw new NullPointerException("keySelector");
        }
        if (jsonMapper == null) {
            jsonMapper = new JacksonMapper();
        }
        if (httpExchanger == null) {
            httpExchanger = new OkHttp3HttpExchanger();
        }
    }
}
