package io.github.pdkst.models.openai.azure;

import io.github.pdkst.models.http.HttpExchanger;
import io.github.pdkst.models.openai.api.audio.OpenaiAudio;
import io.github.pdkst.models.openai.api.chat.OpenaiChatCompletion;
import io.github.pdkst.models.openai.api.embeddings.OpenaiEmbeddings;
import io.github.pdkst.models.openai.api.files.OpenaiFiles;
import io.github.pdkst.models.openai.api.finetuning.OpenaiFineTune;
import io.github.pdkst.models.openai.api.image.OpenaiImages;
import io.github.pdkst.models.openai.api.models.OpenaiModels;
import io.github.pdkst.models.openai.api.moderation.OpenaiModeration;
import io.github.pdkst.models.openai.client.OpenaiEndpointSelector;
import io.github.pdkst.models.openai.client.OpenaiKeyInterceptor;
import lombok.Getter;

/**
 * @author pdkst
 * @since 2024/04/13
 */
@Getter
public class AzureOpenaiClient {
    private final AzureOpenaiOptions options;
    private final HttpExchanger httpExchanger;

    public AzureOpenaiClient(AzureOpenaiOptions options) {
        this.options = options;
        this.httpExchanger = buildHttpExchanger(options);
    }

    private HttpExchanger buildHttpExchanger(AzureOpenaiOptions options) {
        OpenaiEndpointSelector keySelector = options.buildSelector();
        HttpExchanger httpExchanger = options.buildHttpExchanger();
        httpExchanger.addInterceptor(new OpenaiKeyInterceptor(keySelector));
        return httpExchanger;
    }

    public OpenaiAudio audio() {
        return new OpenaiAudio(httpExchanger);
    }

    public OpenaiChatCompletion chat() {
        return new OpenaiChatCompletion(httpExchanger);
    }

    public OpenaiEmbeddings embeddings() {
        return new OpenaiEmbeddings(httpExchanger);
    }

    public OpenaiFiles files() {
        return new OpenaiFiles(httpExchanger);
    }

    public OpenaiFineTune fineTune() {
        return new OpenaiFineTune(httpExchanger);
    }

    public OpenaiImages image() {
        return new OpenaiImages(httpExchanger);
    }

    public OpenaiModels models() {
        return new OpenaiModels(httpExchanger);
    }

    public OpenaiModeration moderation() {
        return new OpenaiModeration(httpExchanger);
    }
}
