package io.github.pdkst.models.bytedance.doubao.client;

import com.volcengine.ApiClient;
import com.volcengine.sign.Credentials;
import io.github.pdkst.models.bytedance.doubao.api.chat.DoubaoChatCompletion;
import io.github.pdkst.models.bytedance.doubao.api.embedding.DoubaoEmbeddings;
import io.github.pdkst.models.http.HttpExchanger;
import lombok.Getter;

/**
 * @author pdkst
 * @since 2024/06/19
 */
@Getter
public class DoubaoClient {
    private final DoubaoOptions options;
    private final HttpExchanger httpExchanger;

    public DoubaoClient(DoubaoOptions options) {
        this(options, options.getModel());
    }

    public DoubaoClient(DoubaoOptions options, String model) {
        this.options = options;
        this.httpExchanger = buildHttpExchanger(options, model);
    }

    private HttpExchanger buildHttpExchanger(DoubaoOptions options, String model) {
        final DoubaoEndpointSelector keySelector = options.buildSelector();
        HttpExchanger httpExchanger = options.buildHttpExchanger();
        final ApiClient apiClient = new ApiClient();
        apiClient.setCredentials(Credentials.getCredentials(options.getAccessKey(), options.getAccessSecret()));
        final DoubaoAccessTokenGenerator tokenGenerator = new DoubaoAccessTokenGenerator(apiClient);
        final DoubaoAccessInterceptor interceptor = new DoubaoAccessInterceptor(model,
                tokenGenerator,
                keySelector);
        httpExchanger.addInterceptor(interceptor);
        return httpExchanger;
    }

    public DoubaoChatCompletion chat() {
        return new DoubaoChatCompletion(httpExchanger);
    }

    public DoubaoEmbeddings embeddings() {
        return new DoubaoEmbeddings(httpExchanger);
    }
}
