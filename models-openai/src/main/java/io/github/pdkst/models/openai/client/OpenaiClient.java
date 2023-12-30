package io.github.pdkst.models.openai.client;

import io.github.pdkst.models.openai.endpoint.chat.OpenaiChatCompletion;
import lombok.Data;

/**
 * @author pdkst.zhang
 * @since 2023/12/30
 */
@Data
public class OpenaiClient {
    private OpenaiConfig config;

    public OpenaiClient(OpenaiConfig config) {
        this.config = config;
    }

    public OpenaiChatCompletion chatCompletion() {
        return new OpenaiChatCompletion(config.getHttpExchanger());
    }
}
