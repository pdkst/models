package io.github.pdkst.models.openai.client;

import io.github.pdkst.models.http.HttpExchanger;
import io.github.pdkst.models.http.clients.OkHttp3HttpExchanger;
import io.github.pdkst.models.json.JacksonMapper;
import io.github.pdkst.models.json.JsonMapper;
import lombok.Builder;
import lombok.Data;

/**
 * @author pdkst.zhang
 * @since 2023/11/02
 */
@Data
@Builder
public class OpenaiConfig {
    @Builder.Default
    private String base = "api.openai.com";
    private JsonMapper jsonMapper;
    private HttpExchanger httpExchanger;
    private OpenaiKeySelector keySelector;

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
