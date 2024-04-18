package io.github.pdkst.models.openai.api.models;

import io.github.pdkst.models.http.clients.OkHttp3HttpExchanger;
import io.github.pdkst.models.openai.api.models.response.ModelObject;
import io.github.pdkst.models.openai.api.models.response.ModelsResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

/**
 * @author pdkst
 * @since 2023/11/18
 */
@Slf4j
public class OpenaiModelsTest {
    private OpenaiModels openaiModels;

    @Before
    public void setUp() {
        openaiModels = new OpenaiModels(new OkHttp3HttpExchanger());
    }

    @Test
    public void listModels() throws Exception {
        final ModelsResponse modelsResponse = openaiModels.listModels();
        log.info("{}", modelsResponse);
    }

    @Test
    public void retrieveModel() throws Exception {
        final ModelObject modelObject = openaiModels.retrieveModel("ada");
        log.info("{}", modelObject);
    }

    @Test
    public void deleteFineTuneModel() {
    }
}