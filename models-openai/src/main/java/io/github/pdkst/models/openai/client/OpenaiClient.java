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
    private OpenaiDelegateHttpExchanger delegateHttpExchanger;

    public OpenaiClient(OpenaiConfig config) {
        this.config = config;
        this.config.initDefaults();
        final HttpExchanger httpExchanger = config.getHttpExchanger();
        final OpenaiKeySelector keySelector = config.getKeySelector();
        this.delegateHttpExchanger = new OpenaiDelegateHttpExchanger(
                httpExchanger, keySelector);
    }

    public OpenaiChatCompletion chatCompletion() {
        return new OpenaiChatCompletion(delegateHttpExchanger);
    }
}
