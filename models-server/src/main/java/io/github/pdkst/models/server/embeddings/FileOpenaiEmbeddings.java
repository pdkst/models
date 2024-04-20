package io.github.pdkst.models.server.embeddings;

import io.github.pdkst.models.json.JacksonMapper;
import io.github.pdkst.models.openai.api.embeddings.request.EmbeddingsRequest;
import io.github.pdkst.models.openai.api.embeddings.response.EmbeddingsResponse;
import io.github.pdkst.models.server.common.JsonFileResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author pdkst
 * @since 2024/04/20
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class FileOpenaiEmbeddings {
    private final JacksonMapper mapper = new JacksonMapper();

    public EmbeddingsResponse embeddings(EmbeddingsRequest request) throws IOException {
        final JsonFileResolver resolver = new JsonFileResolver("/out/embeddings/example.json", mapper);
        return resolver.resolve(EmbeddingsResponse.class);
    }
}
