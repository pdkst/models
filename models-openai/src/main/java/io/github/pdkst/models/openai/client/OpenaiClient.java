package io.github.pdkst.models.openai.client;

import io.github.pdkst.models.http.HttpExchanger;
import io.github.pdkst.models.openai.endpoint.chat.OpenaiChatCompletion;
import lombok.Data;

/**
 * @author pdkst.zhang
 * @since 2023/12/30
 */
@Data
public class OpenaiClient {
    private OpenaiConfig config;
    private HttpExchanger httpExchanger;

    public OpenaiClient(OpenaiConfig config) {
        this.config = config;
        this.config.initDefaults();
        final OpenaiKeySelector keySelector = config.getKeySelector();
        this.httpExchanger = this.config.getHttpExchanger();
        this.httpExchanger.addInterceptor(new OpenaiKeyInterceptor(keySelector));
    }

    public OpenaiChatCompletion chatCompletion() {
        return new OpenaiChatCompletion(httpExchanger);
    }
}
