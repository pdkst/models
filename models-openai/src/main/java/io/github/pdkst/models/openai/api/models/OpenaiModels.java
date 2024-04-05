package io.github.pdkst.models.openai.api.models;

import io.github.pdkst.models.http.AbstractHttpExchanger;
import io.github.pdkst.models.http.HttpResponse;
import io.github.pdkst.models.http.request.HttpRequest;
import io.github.pdkst.models.openai.api.models.response.DeleteModelResponse;
import io.github.pdkst.models.openai.api.models.response.ModelObject;
import io.github.pdkst.models.openai.api.models.response.ModelsResponse;
import lombok.RequiredArgsConstructor;

/**
 * List and describe the various models available in the API.
 * You can refer to the <a href="https://platform.openai.com/docs/models">Models</a>
 * documentation to understand what models are available and the differences between them.
 *
 * @author pdkst.zhang
 * @since 2023/11/03
 */
@RequiredArgsConstructor
public class OpenaiModels {
    private final AbstractHttpExchanger exchanger;

    /**
     * Lists the currently available models,
     * and provides basic information about each one such as the owner and availability.
     *
     * @return A list of <a href="https://platform.openai.com/docs/api-reference/models/object">model</a> objects.
     * @throws Exception errors
     */
    public ModelsResponse listModels() throws Exception {
        HttpRequest get = HttpRequest.get("/models");
        final HttpResponse response = exchanger.exchange(get);
        return response.body(ModelsResponse.class);
    }

    /**
     * Retrieves a model instance, providing basic information about the model such as the owner and permissioning.
     *
     * @param model modelName, eg: "gpt-3.5-turbo-instruct"
     * @return model object
     * @throws Exception errors
     */
    public ModelObject retrieveModel(String model) throws Exception {
        HttpRequest get = HttpRequest.get("/models/" + model);
        final HttpResponse response = exchanger.exchange(get);
        return response.body(ModelObject.class);
    }

    /**
     * Delete a fine-tuned model.
     * <p>
     * You must have the Owner role in your organization to delete a model.
     *
     * @param model model name, eg: "ft:gpt-3.5-turbo:acemeco:suffix:abc123"
     * @return delete response
     * @throws Exception errors
     */
    public DeleteModelResponse deleteFineTuneModel(String model) throws Exception {
        HttpRequest delete = HttpRequest.delete("/models/" + model);
        final HttpResponse response = exchanger.exchange(delete);
        return response.body(DeleteModelResponse.class);
    }
}
