package io.github.pdkst.models.openai.client;

import io.github.pdkst.models.http.HttpExchanger;
import io.github.pdkst.models.openai.api.audio.OpenaiAudio;
import io.github.pdkst.models.openai.api.batch.OpenaiBatch;
import io.github.pdkst.models.openai.api.chat.OpenaiChatCompletion;
import io.github.pdkst.models.openai.api.embeddings.OpenaiEmbeddings;
import io.github.pdkst.models.openai.api.files.OpenaiFiles;
import io.github.pdkst.models.openai.api.finetuning.OpenaiFineTune;
import io.github.pdkst.models.openai.api.image.OpenaiImages;
import io.github.pdkst.models.openai.api.models.OpenaiModels;
import io.github.pdkst.models.openai.api.moderation.OpenaiModeration;
import lombok.Getter;

/**
 * @author pdkst
 * @since 2023/12/30
 */
@Getter
public class OpenaiClient {
    private final OpenaiOptions options;
    private final HttpExchanger httpExchanger;

    public OpenaiClient(OpenaiOptions options) {
        this.options = options;
        this.httpExchanger = buildHttpExchanger(options);
    }

    private HttpExchanger buildHttpExchanger(OpenaiOptions options) {
        final OpenaiEndpointSelector keySelector = options.buildSelector();
        HttpExchanger httpExchanger = options.buildHttpExchanger();
        httpExchanger.addInterceptor(new OpenaiKeyInterceptor(keySelector));
        return httpExchanger;
    }

    public OpenaiAudio audio() {
        return new OpenaiAudio(httpExchanger);
    }

    public OpenaiBatch batch() {
        return new OpenaiBatch(httpExchanger);
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
